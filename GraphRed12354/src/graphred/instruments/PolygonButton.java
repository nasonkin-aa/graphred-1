/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphred.instruments;

import graphred.shape.PolygonShape;

import javax.swing.JButton;
import save.BaseShape;

/**
 *
 * @author andr
 */
public class PolygonButton extends JButton {
    BaseShape bs= new PolygonShape();
    public PolygonButton(String name){
    super(name);    
    }
    public BaseShape getShape(){
        return new PolygonShape();
    }
}
    
    
