package principal;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class Analizador {

    public static void main(String[] args) {
	// write your code here
        Analizador a=new Analizador();
        a.leerTSimbolos();
    }

    public void leerTSimbolos(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder leer

            URL ruta=getClass().getResource("/assets/tablaSimbolos.txt");

            archivo = new File (ruta.getPath());
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
                System.out.println(linea);
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, sin importar si entra bien o va a una excepcion
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

    public void leerTOperadores(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder leer

            URL ruta=getClass().getResource("/assets/tablaOperadores.txt");

            archivo = new File (ruta.getPath());
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
                System.out.println(linea);
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, sin importar si entra bien o va a una excepcion
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

    public void leerCodigo(){

        //Declaracion de variables a usar
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder leer
            //Creacion de ruta del fichero
            URL ruta=getClass().getResource("/assets/codigoAnalizable.txt");

            //Creacion de File, FileReader, BufferedReader
            archivo = new File (ruta.getPath());
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
                System.out.println(linea);

        } catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, sin importar si entra bien o va a una excepcion
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

    public static void analizador(){

    }

    public static void tablaCodigo(){

    }


}
