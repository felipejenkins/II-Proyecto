/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundoproyecto;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @authores Johanny Solano y Felipe Jenkins
 */
public class Inicio {
    private String var;// variable que se utiliza en el código
    private int juegosGanados=0;// estas 3 variables enteras es para el control de estadística
    private int juegosPerdidos=0;
    private int totalJuegos=0;   
    private int tablero[][];// tablero del juego
    private String tabUsuario[][];// tablero que se le va a mostrar al usuario
    public Inicio(){// esto solo representa un constructor
        
        
    }
    public void imprimeTableroSistema(){
        for(int i=0;i<tablero.length;i++){// recorre elementos
            System.out.print('|');
            for(int e=0;e<tablero[i].length;e++){//recorre elementos de cada elemento
                System.out.print(tablero[i][e]+"|");         
            }
            System.out.println();
        }
    }// la misma funcion lo dice..Imprime el tablero del sistema
    public void obtenerTamTab(){
        Scanner nuevo = new Scanner(System.in); // se utiliza en todo el código para obtener información del usuario
        System.out.println("Ingrese el tamaño del tablero....");
        String num =nuevo.next();
        try{// control de excepciones
            int tama=Integer.parseInt(num);// conversión de String a int
            if(tama==1||tama==0||tama==2){// no se puede hacer un tablero de 1 casilla..porque la cantidad de minas sería el doble-> 2*1=2 no habría donde poner la otra
                JOptionPane.showMessageDialog(null,
                "No se puede crear un tablero de tamaño 0, ni 1, ni 2",
                "Error",
                JOptionPane.WARNING_MESSAGE); 
                obtenerTamTab();   
            }
            else{
                this.tablero=new int[tama][tama];// se solicita espacio a memoria/se crea el arreglo tablero con la cantidad de elementos que va a obtener               
                this.ubicaMinas(tama);   
            }    
        }
        catch(Exception e){// captura un posible error a la hora de hacer la conversión de ingresar un número al sistema..por aquello de que e usuario ingrese algo indebido
            JOptionPane.showMessageDialog(null,
            "Caractér no válido",
            "Error",
            JOptionPane.WARNING_MESSAGE); 
            obtenerTamTab(); // organiza el tablero...lo contruye           
        }               
    }//Obtiene el tamaño del tablero deseado y lo valida y crea el tablero del sistema
    public void ubicaMinas(int L){// 11 representa la mina 
        LinkedList<Minas>minass=new LinkedList<Minas>();//Lista donde están solo los objetos con las coordenadas de las posiciones del tablero del sistema donde hay minas
        Random rnd=new Random();// se instancia la utilización del random
        int ciclo=2*L;// f+ormula para sacar las minas
        int i=0;//  contador del while de abajo
        while(i<ciclo){//esto es por la cantidad de minas que se necesiten generar..dependiendo del tamaño del tablero
            int auxX =rnd.nextInt(L);
            int auxY=rnd.nextInt(L);
            boolean sigue=false;// para verificar que no se repitan coordenadas en el siguiente ciclo
            for(int z=0;z<minas.size();z++){// valida que el random no genere una mina que ya está en la lista
                if(((minass.get(z).obtenerX())==auxX)&&((minass.get(z).obtenerY())==auxY) ){
                    sigue=true;
                    break; 
                }   
            }
            if(sigue==false){// si es falso indica que en la lita no existe una mina con los datos generador por el random
                Minas min=new Minas();// instancia nuevos objetos
                min.asignarX(auxX);
                min.asignarY(auxY);
                minass.addLast(min);
                i++;                
            } 
        }   
        asignarCoordenadas(minass); // asigna las minas que están en la lista "minass" sobre el tablero del sistema       
    }
    public void asignarCoordenadas(LinkedList<Minas> minaS){// recibe la lista de minas que va a utilizar
        for(int i=0;i<minaS.size();i++){
            int ex=minaS.get(i).obtenerX();
            int ey=minaS.get(i).obtenerY();
            tablero[ex][ey]=11; // el 11 solo indica que hay una mina en esa posición. No se puso un * para determinar la mina porque es un arreglo de enteros el tablero del sistema
        } 
        System.out.println("Tablero máquina(Su uso es únicamente para verificación del correcto funcionamiento del programa)");
        for(int i=0;i<tablero.length;i++){// este ciclo imprime en la consola el tablero del sistema para que el usuario pueda verificar el sistema
            System.out.print("|");
            for(int e=0;e<tablero[i].length;e++){
                if(tablero[i][e]==11){
                    System.out.print("* ");   // donde encuentra un 11 pone el *..es solo para la visualización del usuario
                }
                else{
                    System.out.print(tablero[i][e]+" ");    
                    }                                             
                }
                System.out.println("|");
        }                
        int minas=minaS.size();
        this.tabUsuario=new String[this.tablero.length][this.tablero.length];
        for(int i=0;i<this.tabUsuario.length;i++){//este for es solo para recorrer el tablero usuario y asignarle a cada posición un "-" para que no quede en vacíos
            for(int e=0;e<this.tabUsuario[i].length;e++){
                tabUsuario[i][e]="-";                        
            }
        }
        System.out.println("Tablero Usuario");
        this.imprimirUsuario();//imprime el tablero del usuario
        System.out.println("******Instrucciones del juego******\nm.Para marcar(Bandera)\nd.Desmarcar\nz.Destapar\nf.Finalizar juego(Si finaliza el juego no se tomará en cuenta en las estadísticas)");
        MuestraResultados();// JOptionPane con la información de estadísticas
        Playing(minas);// llamada recursiva de la misma función
    }
    public void imprimirUsuario(){
            for(int i=0;i<this.tabUsuario.length;i++){
                System.out.print('|');//Esto es solo para darle estructura en a consola
                for(int e=0;e<this.tabUsuario[i].length;e++){
                    System.out.print(tabUsuario[i][e]+"|");// igual que lo anteriormente escrito                       
                }
                System.out.println(); //imprime un rengló vació..como para separar                 
            }  
            return;//retorna a donde fue llamado
        
    }//recorre el arreglo de arreglos tabUsuario y luego retorna a donde fue llamado
    public void updateUsuario(){//actualiza el tablero usuario con respecto al del sistema
        for(int i=0;i<tablero.length;i++){
            for(int e=0;e<tablero.length;e++){
                if(tablero[i][e]==12){// el 12 significa que ha sido marcado...entonces si en el de sistema una coordenada está en 12 en el del usuario se va a poner una D->Indica destapado
                    if(this.tabUsuario[i][e]=="-"||this.tabUsuario[i][e]=="M"){
                        this.tabUsuario[i][e]="D";   //Asigna la D     
                    }                                      
                }
            }
        }
    }
    public void Playing(int minas){//Función recursiva que se llama en todo momentos mientaras se esté jungando
        try{
            Scanner nuevo = new Scanner(System.in); 
            System.out.println("Ingrese la instrucción....");
            String inst=nuevo.next();  
            if(inst.equals("f")){//es por si el usuario desea salir del programa
                for(int i=0;i<20;i++){// este ciclo es solo para que haga espacio entre un juego antiguo y otro
                    System.out.println();
                }
                menuS();//regresa al menú principal               
            }
            else if(!inst.equals("z")&& !inst.equals("m")&&!inst.equals("d")){// Valida las instrucciones del juego 
                JOptionPane.showMessageDialog(null,"Error en la instrucción ingresada");
                Playing(minas);// autollama a la función para que pregunte de nuevo
            }
            else{// si todo sale bien se le solicitan al usuario las coordenadas para desmarcar o lo que haya indicado en las instrucciones
                System.out.println("Ingrese la coordenada X....");
                String X =nuevo.next();
                int x= Integer.parseInt(X);
                System.out.println("Ingrese la coordenada Y....");
                String Y=nuevo.next();
                int y=Integer.parseInt(Y);
                if(inst.equals("m")){
                    this.tabUsuario[x][y]="M";
                    imprimirUsuario();
                    Playing(minas);
                }
                else if(inst.equals("d")){
                    this.tabUsuario[x][y]="-";
                    imprimirUsuario();
                    Playing(minas);
                }
                else if(inst.equals("z")){// destapar 
                    //en el tablero del sistema (tablero) se va a poner en la coordenada del tablero el numero 12
                   //0 -> vacio
                   //11->mina
                   //12->destapado
                    destapando(x,y);//funcion para destapar lo que el usuario desee
                    Playing(minas);
                }               
            }           
        }
        catch(Exception e){//control de excepciones
            System.out.println("Datos ingresados incorrectos o error en coordenadas");
            Playing(minas);           
        }  
    }
    public void destapando(int x,int y ){
        if(this.tablero[x][y]==11){// el 11 indica minas
            this.tabUsuario[x][y]="*";//El usuario digitó una coordenada que corresponde a una mina...perdió el juego
            this.imprimirUsuario();//muestra el tablero para que el usuario vea porqué perdidó
            System.out.println("****************UBICACION DE LAS MINAS****************");
            for(int i=0;i<tablero.length;i++){// este ciclo imprime en la consola el tablero del sistema para que el usuario vea donde está las minas al final del juego
                System.out.print("|");
                for(int e=0;e<tablero[i].length;e++){
                    if(tablero[i][e]==11){
                        System.out.print("*");   // donde encuentra un 11 pone el *..es solo para la visualización del usuario
                    }
                    else{
                        System.out.print("-");    
                    }   
                    System.out.print("|");
                }
                System.out.println();
            } 
            JOptionPane.showMessageDialog(null,"Has perdido..Marcaste una mina..Fin del juego");
            this.juegosPerdidos++;//aumento en 1 a los juegos perdidos para que se muestren los resultados
            this.totalJuegos++;//aumenta en 1 a los juegos totales
            this.MuestraResultados();//muestra el JOptionPane con las estadísticas
            try{
                String name=JOptionPane.showInputDialog("1.Para jugar de nuevo \n 2.Para cancelar");
                if(name.equals("1")){
                    for(int i=0;i<31;i++){// este for es solo para que cuando se quiera jugar un juego nuevo limpie la pantalla que se trabaje solo con los datos nuevos
                        System.out.println();
                    }
                    this.obtenerTamTab();//inicia todo para jugar de nuevo                   
                }
                else{
                    menuS();    // vuelve al menú principal           
                }                
            }
            catch(Exception e){
                menuS();               
            }
        }
        else if(this.tablero[x][y]==12){// si el usuario indica una coordenada que ya tiene un 12 es porque ya ha sido utilizada
            System.out.println("La casilla correspondiente a la coordenada indicada ya ha sido destapada....");
        }
        else{
            int cantMinas=buscaMinas(x,y);// obtiene la cantidad de minas que rodean una coordenada del tablero
            if(cantMinas>0){//si el dato es mayor a 0 es porque hay minas a su alrededor                
                String cadena= Integer.toString(cantMinas);// hace la respectiva conversión
                this.tabUsuario[x][y]=cadena;//sustituye lo que hay en la coordenada por el numero de las minas que haya obtenido               
                imprimirUsuario();//Imprime el tablero del usuario               
            }
            else{
                recorridoBackTracking(x,y);//se llama recursivamente
                updateUsuario();// Actualiza el tablero del usuario
                imprimirUsuario();// ya esto es obvio             
            }
            this.tablero[x][y]=12;  // siempre debe marcar lo que seleccione el usuario
            validacionGanar();// valida si se ganó el juego
        }        
    }
    public void validacionGanar(){
        if(VerificaJuegoGanado()==true){// si devuelve true indica que se ha ganado el juego
             JOptionPane.showMessageDialog(null,"Usted ha ganado la partida"); 
             
             this.juegosGanados++;// es para que cuando se gane el juego se aumente la variable global juegosGanados
             this.totalJuegos++;//Siempre se aumenta..se gane o se pierde ..es el total de veces que se gana un juego
             MuestraResultados();
             try{
                String name=JOptionPane.showInputDialog("1.Para jugar de nuevo \n 2.Para cancelar");
                if(name.equals("1")){
                    for(int i=0;i<31;i++){// este for es solo para que cuando se quiera jugar un juego nuevo limpie la pantalla que se trabaje solo con los datos nuevos
                        System.out.println();
                    }
                    this.obtenerTamTab();  //inicializa todo el proceso del juego                 
                }
                else{
                    menuS();               
                }               
            }
            catch(Exception e){
                menuS();               
            }         
        }
    }
    public boolean VerificaJuegoGanado(){// recorre el tablero del usuario verificando si se han descubierto todas las minas
         for(int i=0;i<tablero.length;i++){
             for(int e=0;e<tablero[i].length;e++){
                 if(tablero[i][e]==0){//si en el tablero se encuentra aunque sea un 0 es porque existe una coordenada del tablero del sistema que el usuario no ha marcado...puesto que para ganar se necesitan dejar activas solo las minas
                     return false;//falso= no ganó
                 }
             }
         }
         return true;// si termina el ciclo indica que no hay 0's en el tablero del sistema..lo que indica que ganó
    }
    public void recorridoBackTracking(int x,int y){// arbol de recorrido
        if(tablero[x][y]==12){//si se recorre una coordenada que ya se marcó..retorna
                return;
        }
        LinkedList<Minas> recorrido=new LinkedList<Minas>();//lista de hijos de una coordenada...es lo que está a su alrededor      
        //---------------------------------------------------Representa las 4 esquinas
        if(x==0&&y==0){ // esquina superior izquierda
            Minas recoA=new Minas();// se instancian nuevos objetos y se guardan en la lista..correspondientes a los numeros que rodean una coordenada
            recoA.asignarX(x+1);
            recoA.asignarY(y);
            recorrido.addLast(recoA);
            
            Minas recoB=new Minas();
            recoB.asignarX(x);
            recoB.asignarY(y+1);
            recorrido.addLast(recoB);
            
            Minas recoC=new Minas();
            recoC.asignarX(x+1);
            recoC.asignarY(y+1);
            recorrido.addLast(recoC);            
        }
        else if(x==0&&y==(tablero.length-1)){//esquina superior derecha de un tablero cualquiera
            
            Minas recoA=new Minas();
            recoA.asignarX(x);
            recoA.asignarY(y-1);
            recorrido.addLast(recoA);
            
            Minas recoB=new Minas();
            recoB.asignarX(x+1);
            recoB.asignarY(y);
            recorrido.addLast(recoB);            
            
            Minas recoC=new Minas();
            recoC.asignarX(x+1);
            recoC.asignarY(y-1);
            recorrido.addLast(recoC);            
  
        }
        else if(x==(tablero.length-1)&&y==0){//Esquina inferior izquierda
            
            Minas recoA=new Minas();
            recoA.asignarX((tablero.length-1)-1);
            recoA.asignarY(0);
            recorrido.addLast(recoA);   
            
            Minas recoB=new Minas();
            recoB.asignarX((tablero.length-1));
            recoB.asignarY(y+1);
            recorrido.addLast(recoB);  
            
            Minas recoC=new Minas();
            recoC.asignarX((tablero.length-1)-1);
            recoC.asignarY(y+1);
            recorrido.addLast(recoC);             
   
        }
        else if(x==(tablero.length-1)&&y==(tablero.length-1)){//esquina inferior derecha
            
            
            Minas recoA=new Minas();
            recoA.asignarX((tablero.length-1));
            recoA.asignarY((tablero.length-1)-1);
            recorrido.addLast(recoA);   
            
            Minas recoB=new Minas();
            recoB.asignarX((tablero.length-1)-1);
            recoB.asignarY((tablero.length-1));
            recorrido.addLast(recoB);  
            
            Minas recoC=new Minas();
            recoC.asignarX((tablero.length-1)-1);
            recoC.asignarY((tablero.length-1)-1);
            recorrido.addLast(recoC);  

        } 
        
        //-------------------------------------------------------------------------Fin de representación de las 4 esquinas
        else if(x==0&&y>0&&y<(tablero.length-1)){//posiciones del tablero entre esquinas superiores
            
            Minas recoA=new Minas();
            recoA.asignarX(x);
            recoA.asignarY(y-1);
            recorrido.addLast(recoA);   
            
            Minas recoB=new Minas();
            recoB.asignarX(x);
            recoB.asignarY(y+1);
            recorrido.addLast(recoB);  
            
            Minas recoC=new Minas();
            recoC.asignarX(x+1);
            recoC.asignarY(y);
            recorrido.addLast(recoC);            
            
            Minas recoD=new Minas();
            recoD.asignarX(x+1);
            recoD.asignarY(y-1);
            recorrido.addLast(recoD);    
            
            Minas recoE=new Minas();
            recoE.asignarX(x+1);
            recoE.asignarY(y+1);
            recorrido.addLast(recoE);                
            
            
        }
        else if(x==(tablero.length)-1&&y>0&&y<(tablero.length-1)){//posiciones entre las esquinas inferiores
            
            Minas recoA=new Minas();
            recoA.asignarX(x-1);
            recoA.asignarY(y);
            recorrido.addLast(recoA);   
            
            Minas recoB=new Minas();
            recoB.asignarX(x);
            recoB.asignarY(y+1);
            recorrido.addLast(recoB);  
            
            Minas recoC=new Minas();
            recoC.asignarX(x);
            recoC.asignarY(y-1);
            recorrido.addLast(recoC);            
            
            Minas recoD=new Minas();
            recoD.asignarX(x-1);
            recoD.asignarY(y-1);
            recorrido.addLast(recoD);    
            
            Minas recoE=new Minas();
            recoE.asignarX(x-1);
            recoE.asignarY(y+1);
            recorrido.addLast(recoE);            
            
  
        }
        else if((x>0&&x<(tablero.length-1))&&y==0){//posiciones laterales izquierda
            
            
            Minas recoA=new Minas();
            recoA.asignarX(x-1);
            recoA.asignarY(y);
            recorrido.addLast(recoA);   
            
            Minas recoB=new Minas();
            recoB.asignarX(x);
            recoB.asignarY(y+1);
            recorrido.addLast(recoB);  
            
            Minas recoC=new Minas();
            recoC.asignarX(x+1);
            recoC.asignarY(y);
            recorrido.addLast(recoC);            
            
            Minas recoD=new Minas();
            recoD.asignarX(x-1);
            recoD.asignarY(y+1);
            recorrido.addLast(recoD);    
            
            Minas recoE=new Minas();
            recoE.asignarX(x+1);
            recoE.asignarY(y+1);
            recorrido.addLast(recoE);             

        }
        else if(x>0&&x<(tablero.length)-1&&y==(tablero.length)-1){//posiciones del tablero lateral derecha
            
            Minas recoA=new Minas();
            recoA.asignarX(x-1);
            recoA.asignarY(y);
            recorrido.addLast(recoA);   
            
            Minas recoB=new Minas();
            recoB.asignarX(x);
            recoB.asignarY(y-1);
            recorrido.addLast(recoB);  
            
            Minas recoC=new Minas();
            recoC.asignarX(x+1);
            recoC.asignarY(y);
            recorrido.addLast(recoC);            
            
            Minas recoD=new Minas();
            recoD.asignarX(x-1);
            recoD.asignarY(y-1);
            recorrido.addLast(recoD);    
            
            Minas recoE=new Minas();
            recoE.asignarX(x+1);
            recoE.asignarY(y-1);
            recorrido.addLast(recoE);                 
        }
        else{// posiciones centrales , si no es ninguna de las anteriores fijo son posiciones centrales           
            Minas recoA=new Minas();
            recoA.asignarX(x-1);
            recoA.asignarY(y);
            recorrido.addLast(recoA);   
            
            Minas recoB=new Minas();
            recoB.asignarX(x);
            recoB.asignarY(y+1);
            recorrido.addLast(recoB);  
            
            Minas recoC=new Minas();
            recoC.asignarX(x);
            recoC.asignarY(y-1);
            recorrido.addLast(recoC);            
            
            Minas recoD=new Minas();
            recoD.asignarX(x+1);
            recoD.asignarY(y);
            recorrido.addLast(recoD);    
            
            Minas recoE=new Minas();
            recoE.asignarX(x-1);
            recoE.asignarY(y-1);
            recorrido.addLast(recoE);             
            
            
            Minas recoF=new Minas();
            recoF.asignarX(x-1);
            recoF.asignarY(y+1);
            recorrido.addLast(recoF);                  
            
            Minas recoG=new Minas();
            recoG.asignarX(x+1);
            recoG.asignarY(y-1);
            recorrido.addLast(recoE);      
            
            Minas recoH=new Minas();
            recoH.asignarX(x+1);
            recoH.asignarY(y+1);
            recorrido.addLast(recoH);                  
          
        }
        this.tablero[x][y]=12;// siempre se debe marcar lo que pisa el usuario
        this.validacionGanar();//valida si se gana o no        
        for(int i=0;i<recorrido.size();i++){// recorre la lista de objetos o posiciones que rodean a una coordenada
            int ex=recorrido.get(i).obtenerX();// obtiene la x
            int ey=recorrido.get(i).obtenerY();//obtiene la y
            int result=this.buscaMinas(ex,ey);//obtiene la cantidad de minas que rodean a la coordenada     
            if(result>0){//si hay minas alrededor
                this.tabUsuario[ex][ey]=String.valueOf(result);// se pone ese número en la coordenada del tablero del usuario
                this.tablero[ex][ey]=12;// Se marca ese "hijo" dentro del tablero del sistema
            }
            else{
                recorridoBackTracking(ex,ey);//si no tiene minas alrededor...llama drecursivamente para obtener las minas de los vecinos   
            }                          
            result=0;// inicializa el resultado a 0 nuevamente
        }   
    }
    public void MuestraResultados(){// Esta función es para mostrar en un JOptionPane los resultados de los juegos ganados y perdidos
        JOptionPane.showMessageDialog(null,"Juegos ganados: "+ this.juegosGanados+"\nJuegosPerdidos: "+this.juegosPerdidos+"\nTotal de veces jugado: "+this.totalJuegos);
    } // solo imprime las variable globales de las estadísticas al usuario
    public int buscaMinas(int x,int y){// esta función recibe una coordenada y analiza las minas que tenga al rededor..y devuelve el total de estas
        int cM=0;// cuenta minas
        //---------------------------------------------------Representa las 4 esquinas
        if(x==0&&y==0){// todos los if y los else tienden a ser igual a la función anteriormente analizada recorridoBacktracking
            if(this.tablero[x+1][y]==11){// para esta área ya cambia
                cM++;
            }// lo único que hace es buscar los 11's-> las minas en el tablero del sistema
            if(this.tablero[x][y+1]==11){
                cM++;// y bajo estos aumentos de contador...se llega al total de minas que tiene una respectiva coordenada
            }
            if(this.tablero[x+1][y+1]==11){
                cM++;               
            }
        }
        else if(x==0&&y==(tablero.length-1)){
            if(this.tablero[x][y-1]==11){
                cM++;
            }
            if(this.tablero[x+1][y]==11){
                cM++;
            }           
            if(this.tablero[x+1][y-1]==11){
                cM++;
            }           
        }
        else if(x==(tablero.length-1)&&y==0){
            if(this.tablero[(tablero.length-1)-1][0]==11){
                cM++;
            }           
            if(this.tablero[(tablero.length-1)][y+1]==11){
                cM++;
            }            
            if(this.tablero[(tablero.length-1)-1][y+1]==11){
                cM++;
            }            
        }
        else if(x==(tablero.length-1)&&y==(tablero.length-1)){
            if(this.tablero[(tablero.length-1)][(tablero.length-1)-1]==11){
                cM++;
            }
            if(this.tablero[(tablero.length-1)-1][(tablero.length-1)]==11){
                cM++;
            }           
            if(this.tablero[(tablero.length-1)-1][(tablero.length-1)-1]==11){
                cM++;
            }           
        } 
        
        //-------------------------------------------------------------------------Fin de representación de las 4 esquinas
        else if(x==0&&y>0&&y<(tablero.length-1)){
            if(this.tablero[x][y-1]==11){
                cM++;
            }
            if(this.tablero[x][y+1]==11){
                cM++;
            }           
            if(this.tablero[x+1][y]==11){
                cM++;
            }
            if(this.tablero[x+1][y-1]==11){
                cM++;
            }   
            if(this.tablero[x+1][y+1]==11){
                cM++;
            }               
        }
        else if(x==(tablero.length)-1&&y>0&&y<(tablero.length-1)){
            if(this.tablero[x-1][y]==11){
                cM++;
            }
            if(this.tablero[x][y+1]==11){
                cM++;
            }           
            if(this.tablero[x][y-1]==11){
                cM++;
            }
            if(this.tablero[x-1][y-1]==11){
                cM++;
            }   
            if(this.tablero[x-1][y+1]==11){
                cM++;
            }             
            
        }
        else if((x>0&&x<(tablero.length-1))&&y==0){
            if(this.tablero[x-1][y]==11){
                cM++;
            }
            if(this.tablero[x][y+1]==11){
                cM++;
            }           
            if(this.tablero[x+1][y]==11){
                cM++;
            }    
            if(this.tablero[x-1][y+1]==11){
                cM++;
            }            
            if(this.tablero[x+1][y+1]==11){
                cM++;
            }            
        }
        else if(x>0&&x<(tablero.length)-1&&y==(tablero.length)-1){
            if(this.tablero[x-1][y]==11){
                cM++;
            }
            if(this.tablero[x][y-1]==11){
                cM++;
            }           
            if(this.tablero[x+1][y]==11){
                cM++;
            }    
            if(this.tablero[x-1][y-1]==11){
                cM++;
            }            
            if(this.tablero[x+1][y-1]==11){
                cM++;
            }              
            
            
        }
        else{// posiciones centrales 
            if(this.tablero[x-1][y]==11){
                cM++;
            }
            if(this.tablero[x][y+1]==11){
                cM++;
            }           
            if(this.tablero[x][y-1]==11){
                cM++;
            } 
 
            if(this.tablero[x+1][y]==11){
                cM++;
            }             
            if(this.tablero[x-1][y-1]==11){
                cM++;
            } 
            if(this.tablero[x-1][y+1]==11){
                cM++;
            }
            if(this.tablero[x+1][y-1]==11){
                cM++;
            }  
            if(this.tablero[x+1][y+1]==11){
                cM++;
            }    
        }
        return cM;//y lo retorna       
    } 
    public void menuS(){// menú principal del juego
        try{              
            String name=JOptionPane.showInputDialog("*****Bienvenido*****\n 1.Para jugar \n 2.Para salir");
        if(name.equals("1")){// si es 1 es porque el usuario desea jugar
            this.obtenerTamTab();                
        }
        else if(name.equals("2")){
            JOptionPane.showMessageDialog(null, "BYE BYE");          
        }
        else{
            JOptionPane.showMessageDialog(null,
            "Por favor utilice lo disponible en el menu",
            "Error",
            JOptionPane.WARNING_MESSAGE); 
            this.menuS();
        }
        }
        catch(Exception e){                      
        }    
    }   
}
//Fin del proyecto....Elaborado por Felipe Jenkins y Johanny Solano