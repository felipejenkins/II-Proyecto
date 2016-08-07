/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.b;
//importes
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 **
 ** @author joha & Felipe
 ** @date 2016-08-06 Sabado
 **/
public class Inicio {
    private String var;
    
    private int tablero[][];// tablero del juego
    private String tabUsuario[][];// tablero que se le va a mostrar al usuario
    
    
    public Inicio(){
        
        
    }
    public void obtenerTamTab(){
        
        
        
        Scanner nuevo = new Scanner(System.in); 
        System.out.println("Ingrese el tamaño del tablero....");
        String num =nuevo.next();
       
        try{
            int tama=Integer.parseInt(num);
       
            if(tama>10000){
                System.out.println("Error....el máximo es 10");
                obtenerTamTab();

            }
            else if(tama==1){
                JOptionPane.showMessageDialog(null,
                "No se puede crear un tablero de tamaño 1",
                "Error",
                JOptionPane.WARNING_MESSAGE); 
                obtenerTamTab();
            
                
            }
                 else if(tama==0){
                JOptionPane.showMessageDialog(null,
                "No se puede crear un tablero de tamaño 0",
                "Error",
                JOptionPane.WARNING_MESSAGE); 
                obtenerTamTab();
            
                
            }
            else{
                System.out.println("Listo");
                this.tablero=new int[tama][tama];// se crea el arreglo tablero con la cantidad de elementos que va a obtener
                  //  JOptionPane.showMessageDialog(null,tablero[2][2]);
                
                for(int i=0;i<tablero.length;i++){
                    System.out.print('|');
                    for(int e=0;e<tablero[i].length;e++){
                        System.out.print(tablero[i][e]+"|");
                        
                    }
                    System.out.println();
                }
                this.ubicaMinas(tama);
                // creando nuevo arreglo...
                
             
                
                 
            }
           
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,
            "Caractér no válido",
            "Error",
            JOptionPane.WARNING_MESSAGE); 
            obtenerTamTab();
            
        }
        
        
    }
    public void ubicaMinas(int L){// 11 representa la mina 
        LinkedList<Minas>minass=new LinkedList<Minas>();
        Random rnd=new Random();
        int ciclo=2*L;// f+ormula para sacar las minas
        int i=0;
        while(i<ciclo){
            int auxX =rnd.nextInt(L);
            int auxY=rnd.nextInt(L);
           
            
            boolean sigue=false;// para verificar que no se repitan coordenadas en el siguiente ciclo
            
            
         
            for(int z=0;z<minass.size();z++){
                if(((minass.get(z).obtenerX())==auxX)&&((minass.get(z).obtenerY())==auxY) ){
                    sigue=true;
                    break;
                    
                }
                
            }
            if(sigue==false){
                Minas min=new Minas();
                min.asignarX(auxX);
                min.asignarY(auxY);
                minass.addLast(min);

                i++;
                
            } 
        }   
        asignarCoordenadas(minass);
        
    }
    public void asignarCoordenadas(LinkedList<Minas> minaS){
        for(int i=0;i<minaS.size();i++){
            int ex=minaS.get(i).obtenerX();
            int ey=minaS.get(i).obtenerY();
            tablero[ex][ey]=11;



    
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

        
  
    
  
    

