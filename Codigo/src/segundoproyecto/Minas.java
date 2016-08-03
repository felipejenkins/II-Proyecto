/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundoproyecto;

/**
 *
 * @author Johanny & Felipe
 */
public class Minas {
    private int x;
    private int y;
    
    public Minas(){
        
    }
    public void asignarX(int xnew){
        this.x=xnew;
    }
    public void asignarY(int ynew){
        this.y=ynew;
    }
    public int obtenerX(){
        return this.x;
    }
    public int obtenerY(){
        return this.y;
    }
}
