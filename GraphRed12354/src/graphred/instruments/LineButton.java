/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphred.instruments;

import graphred.shape.*;
import javax.swing.JButton;
import save.BaseShape;



public class LineButton extends JButton{
    BaseShape bs= new PolyLineShape();
    public LineButton(String name){
    super(name);    
    }
    public BaseShape getShape(){
        return new PolyLineShape();
    }
}
