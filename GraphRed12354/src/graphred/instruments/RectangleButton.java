/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphred.instruments;

import graphred.shape.RectangleShape;
import javax.swing.JButton;
import save.BaseShape;

/**
 *
 * @author andr
 */
public class RectangleButton extends JButton{
    BaseShape bs= new RectangleShape();
    public RectangleButton(String name){
    super(name);    
    }
    public BaseShape getShape(){
        return new RectangleShape();
    }
}

