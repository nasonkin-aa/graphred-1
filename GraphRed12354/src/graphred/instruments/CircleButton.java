package graphred.instruments;

import save.BaseShape;
import graphred.shape.CircleShape;
import javax.swing.JButton;


public class CircleButton extends JButton {
    BaseShape bs = new CircleShape();
    public CircleButton(String name){
    super(name);    
    }
    public BaseShape getShape(){
        return new CircleShape();
        
    }
    
}
