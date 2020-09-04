package principal;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Analizador {

    ArrayList<String> palabrasClave=new ArrayList<>();
    ArrayList<String> tiposPalabrasClave=new ArrayList<>();
    ArrayList<String> operadores=new ArrayList<>();
    ArrayList<String> tiposOperadores=new ArrayList<>();
    ArrayList<String> operadoresEspeciales =new ArrayList<>();
    ArrayList<String> tiposOpEspeciales=new ArrayList<>();

    public static void main(String[] args) {
	// write your code here
        Analizador a=new Analizador();
        a.leerTSimbolos();
        a.leerTOperadores();
        a.leerTOperadoresEspeciales();

        a.leerCodigo();
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

            // Lectura del fichero y alamcenamiento de palabras clave en DE
            palabrasClave(br);

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

    public void palabrasClave(BufferedReader br) throws IOException {
        //Declaracion de variables
        String linea;

        //Se lee linea a linea el fichero hasta que encuentra un espacio y guarda la palabra encontrada
        while((linea=br.readLine())!=null) {
            int i=0;
            while(i<linea.length() && linea.charAt(i) != ' '){
                i++;
            }
            palabrasClave.add(linea.substring(0,i));
            tiposPalabrasClave.add(linea.substring((i+3)));
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


            // Lectura del fichero y alamcenamiento de palabras clave en DE
            operadores(br);

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

    public void operadores(BufferedReader br) throws IOException {
        //Declaracion de variables
        String linea;

        //Se lee linea a linea el fichero hasta que encuentra un espacio y guarda la palabra encontrada
        while((linea=br.readLine())!=null) {

            operadores.add(linea.substring(0,1));
            tiposOperadores.add(linea.substring(4));
        }
    }

    public void leerTOperadoresEspeciales(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder leer

            URL ruta=getClass().getResource("/assets/tablaOperadoresEspeciales.txt");

            archivo = new File (ruta.getPath());
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);


            // Lectura del fichero y alamcenamiento de palabras clave en DE
            operadoresEspeciales(br);

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

    public void operadoresEspeciales(BufferedReader br) throws IOException {
        //Declaracion de variables
        String linea;

        //Se lee linea a linea el fichero hasta que encuentra un espacio y guarda la palabra encontrada
        while((linea=br.readLine())!=null) {

            operadoresEspeciales.add(linea.substring(0,2));
            tiposOpEspeciales.add(linea.substring(5));
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
            ArrayList<Simbolo> simbolos=analizador(br);

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

    public ArrayList<Simbolo> analizador(BufferedReader br) throws IOException {
        //Declaro variables
        String linea;
        ArrayList<Simbolo> simbolos=new ArrayList<>();

        //Declaro variables para ubicacion de simbolos
        int columna=0;

        //Declaro bufferedString para crear simbolos


        while((linea=br.readLine()) != null){
            columna++;
            for(int i=0;i<linea.length();i++){
                int fila=0;
                StringBuilder simbolo=new StringBuilder();
                while(i<linea.length() && !operadores.contains(linea.substring(i,i+1)) &&
                        !operadoresEspeciales.contains(linea.substring(i,i+2))){
                    simbolo.append(linea.charAt(i));
                    i++;
                }

                String simbol=simbolo.toString();
                String tipo="";

                if(palabrasClave.contains(simbol)){
                    tipo=tiposPalabrasClave.get(busquedaPalClave(simbol));
                }else{
                    tipo="identificador";
                }

                Simbolo temporal=new Simbolo(simbol,new Ubicacion(i,columna),tipo);
                simbolos.add(temporal);

                if(operadores.contains(linea.substring(i,i+1))){

                }


            }
        }
        //retorno simbolos
        System.out.println(simbolos);
        return simbolos;
    }
    
    //Búsqueda de un valor
    public  int busquedaPalClave(String palabra){
        int i=0;
        while ( i<palabrasClave.size() && palabrasClave.get(i).compareTo(palabra)!=0) i++ ;
        return(i==palabrasClave.size()) ? -1: i;
    }

    public  int busquedaOperadores(String palabra){
        int i=0;
        while ( i<operadores.size() && operadores.get(i).compareTo(palabra)!=0) i++ ;
        return(i==operadores.size()) ? -1: i;
    }




    public void tablaCodigo(){

    }


}
