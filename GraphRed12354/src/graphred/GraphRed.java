
package graphred;


import save.*;
import graphred.instruments.CircleButton;
import graphred.instruments.LineButton;
import graphred.instruments.PolygonButton;
import graphred.instruments.RectangleButton;
import graphred.shape.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GraphRed extends javax.swing.JFrame{
    
    JTextField R, G, B;
    JLabel jl,l,l1,l2;
    JPanel p;
    JFrame fr;
    MyCanvas jp;
    Queue q= new Queue();
    
    public GraphRed(){
       super("noviy graph red");
        this.setSize(1920,1080);
        fr= this;
        this.setVisible(true);
        this.setLayout(null);
        jl=new JLabel("Hello");
        jl.setVisible(true);
        jl.setBounds(30,30,100,20);
        jp = new MyCanvas(1920,1080,q);
        jp.setVisible(true);
        jp.setBounds(30,90,1920,1080);
        
        p = new JPanel();
        p.setVisible(true);
        p.setBounds(1250, 10, 70, 70);
        
        R = new JTextField(3);
        R.setBounds(1100,15,30,20);
        G = new JTextField(3);
        G.setBounds(1100,45,30,20);
        B = new JTextField(3);
        B.setBounds(1180,15,30,20);
        
        l = new JLabel("Red= ");
        l.setVisible(true);
        l.setBounds(1070,15,50,20);
        l1 = new JLabel("Green= ");
        l1.setVisible(true);
        l1.setBounds(1058,45,50,20);
        l2 = new JLabel("Blue= ");
        l2.setVisible(true);
        l2.setBounds(1147,15,50,20);
        
        JButton ld = new JButton("Load");
        ld.setBounds(1450, 30, 90, 25);
        ld.setVisible(true);
        ld.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
		SaveShapeQueue shapes = new SaveShapeQueue();
		FileInputStream fileInputStream = null;
                        try {
                            fileInputStream = new FileInputStream("save.ser");
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(GraphRed.class.getName()).log(Level.SEVERE, null, ex);
                        }
	    try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
			shapes = (SaveShapeQueue) objectInputStream.readObject();
			q.refresh();
			for (SaveShape saveShape:shapes.getShapes()) {
				q.setCvetColor(saveShape.getColor());
				if(saveShape.getType().equals("Ellipse")) {
					q.addShape(new CircleShape());
				}else if (saveShape.getType().equals("Rectangle")) {
					q.addShape(new RectangleShape());
				}else if (saveShape.getType().equals("Polygon")) {
					q.addShape(new PolygonShape());
				}else if (saveShape.getType().equals("Polyline")) {
					q.addShape(new PolyLineShape());
				}else throw new RuntimeException("problem");
				if(saveShape.getPoints().size()!=0) {
					q.getLastShape().setCoordinates(saveShape.getPoints());
				}
                                
			}
                        
			;
		}       catch (IOException ex) {
                            Logger.getLogger(GraphRed.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(GraphRed.class.getName()).log(Level.SEVERE, null, ex);
                        } 
            jp.paintToBuffer();
            repaint();
                    }
                   
                });
        
        
        JButton s = new JButton("Save");
        s.setBounds(1350, 45, 90, 25);
        s.setVisible(true);
        s.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        SaveShapeQueue shapes = new SaveShapeQueue();
		for(BaseShape shape:q.getShapes()) {
			shapes.addShape(new SaveShape(shape));
		}
		FileOutputStream outputStream = null;
                        try {
                            outputStream = new FileOutputStream("save.ser");
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(GraphRed.class.getName()).log(Level.SEVERE, null, ex);
                        }
	    ObjectOutputStream objectOutputStream = null;
                        try {
                            objectOutputStream = new ObjectOutputStream(outputStream);
                        } catch (IOException ex) {
                            Logger.getLogger(GraphRed.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            objectOutputStream.writeObject(shapes);
                        } catch (IOException ex) {
                            Logger.getLogger(GraphRed.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            objectOutputStream.close();
                        } catch (IOException ex) {
                            Logger.getLogger(GraphRed.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        
        JButton sv = new JButton("SavePNG");
        sv.setBounds(1350, 15, 90, 25);
        sv.setVisible(true);
        sv.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        File outputfile = new File("image.png");
                        try {
                            ImageIO.write(jp.buf, "png", outputfile);
                        } catch (IOException ex) {
                            Logger.getLogger(GraphRed.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        
        
        
        JButton b = new JButton("ok");
        b.setBounds(1147, 45, 50, 30);
        b.setVisible(true);
        b.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        int r,g,b;
                        r = Integer.parseInt(R.getText());
                        g = Integer.parseInt(G.getText());
                        b = Integer.parseInt(B.getText());
                        Color c = new Color(r,g,b);
                        p.setBackground(c);
                        q.getLastShape().setColor(c);
                        q.setCvetColor(c);
                    }
                }
        );
        
        JButton kvad = new RectangleButton("Прямогольник");
        kvad.setBounds(430,30,200,20);
        kvad.setVisible(true);
        kvad.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if (kvad instanceof RectangleButton)q.addShape(((RectangleButton)kvad).getShape());
                        
                    }
                }
            
        );
        
        JButton cir = new CircleButton("Элипс");
        cir.setBounds(630,30,200,20);
        cir.setVisible(true);
        cir.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(cir instanceof CircleButton)q.addShape(((CircleButton)cir).getShape());
                    }
                }
            
        );
        
        JButton pol = new PolygonButton("Полигон");
        pol.setBounds(830,30,200,20);
        pol.setVisible(true);
        pol.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(pol instanceof PolygonButton)q.addShape(((PolygonButton)pol).getShape());
                        
                    }
                }
            
        );
        
        
        
        
        JButton jb = new LineButton("line");
        jb.setVisible(true);
        jb.setBounds(230, 30,200,20);
        jb.addActionListener ( 
        new ActionListener()
        {
             @Override
             public void actionPerformed(ActionEvent e){
                 if (jb instanceof LineButton) q.addShape(((LineButton)jb).getShape());
                 jl.setText("new Line"+q.getSize()+"generated");
                 fr.repaint();
             }
        });
        this.add(ld);
        this.add(s);
        this.add(sv);
        this.add(b);
        this.add(p);
        this.add(R);
        this.add(G);
        this.add(B);
        this.add(l);
        this.add(l1);
        this.add(l2);
        this.add(pol);
        this.add(cir);
        this.add(kvad);
        this.add(jp);
        this.add(jl);
        this.add(jb);
        this.add(jp);
        this.repaint();
        
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
    }
    public static void main(String[] args){
        GraphRed app =new GraphRed();
        app.setAlwaysOnTop(true);
        app.setEnabled(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
        
}
     
    
    
    

    
    

