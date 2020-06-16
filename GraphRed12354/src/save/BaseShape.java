package save;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.List;


public abstract class BaseShape{
        
        public abstract void drawShape(Graphics g);
        public abstract void setColor(Color color);
        public abstract Shape getShape();
	abstract public Color getColor();
	abstract public void setShape(Shape shape);
	abstract public void addFirstCoordinates(Point2D point);
	abstract public void addSecondCoordinates(Point2D point);
	abstract public void addCurrCoordinates(Point2D point);
	abstract public List<Point2D> getPoints();
	abstract public String getType();
	abstract public void setCoordinates(List<Point2D> points);
}



/*  public abstract void addCoordinate(int x, int y);
    public abstract void putCanvasCoordinate(int x,int y);
    public abstract void paintShape(Graphics g);
    public abstract void setColor(Color color);
    public abstract Shape getShape();*/

