/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundoproyecto;

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Random;
/**
 *
 * @author Johanny & Felipe
 */
public class Inicio {
    private String var;
    private int tablero [][];
    public Inicio(){
        
    }
    
    public void obtenertamtab(){
        Scanner nuevo = new Scanner(System.in);
        System.out.println("Ingrese el tamaño del tablero");
        String num =nuevo.next();
        try{
            int tama=Integer.parseInt(num);
            if(tama>10000){
                System.out.println("Error....el máximo es 10000");
                obtenertamtab();
            }
            else if(tama==1){
                JOptionPane.showMessageDialog(null,
                "No se puede crear un tablero de tamaño 1",
                "Error",
                JOptionPane.WARNING_MESSAGE); 
                obtenertamtab();
            }
        }
        
    }
    
    
    
    public void menu(){
    try{
        //Menu
        String name=JOptionPane.showInputDialog("=====Bienvenido=====\n 1.Para jugar \n 2.Para salir");
        if(name.equals("1")){
        }
        else if(name.equals("2")){
            JOptionPane.showMessageDialog(null, "BYE BYE");
            
        }
        else{
            JOptionPane.showMessageDialog(null,
            "Por favor utilice lo disponible en el menu",
            "Error",
            JOptionPane.WARNING_MESSAGE); 
            this.menu();
        }
        }
        catch(Exception e){ //  para solucionar errores que se generen el el bloque  try                  
        }
  
       
        
        
    }
    
}
