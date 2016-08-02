/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.b;
//importes
import java.io.IOException;
import javax.swing.JOptionPane;
/**
 **
 ** @author joha & Felipe
 ** @date 2016-07-24 Domingo
 **/
public class Buscaminas {
    
    public void menu(){
    try{
                
        String name=JOptionPane.showInputDialog("*****Bienvenido*****\n 1.Para jugar \n 2.Para salir");
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
        catch(Exception e){                      
        }
  
       
        
        
    }
}

        
  
    
  
    

