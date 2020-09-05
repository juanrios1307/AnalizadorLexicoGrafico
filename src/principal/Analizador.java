package principal;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Analizador {
    //Declaracion de las listas a usar
    ArrayList<String> palabrasClave=new ArrayList<>();
    ArrayList<String> tiposPalabrasClave=new ArrayList<>();
    ArrayList<String> operadores=new ArrayList<>();
    ArrayList<String> tiposOperadores=new ArrayList<>();
    ArrayList<String> operadoresEspeciales =new ArrayList<>();
    ArrayList<String> tiposOpEspeciales=new ArrayList<>();

    public static void main(String[] args) {
        Analizador a=new Analizador();

        //Se lee e inicializan las listas
        a.leerTSimbolos();
        a.leerTOperadores();
        a.leerTOperadoresEspeciales();

        //Se lee el codigo a analizar y se analiza
        a.leerAnalizarCodigo();
    }//cierre main

    //Se abre metodo para leer lista de simbolos
    public void leerTSimbolos(){
        //Declaracion de variables
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
        }//cierre finally
    }//cierre metodo

    //Se abre metodo para almacenar palabras clave de fichero en lista
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

    }//cierre metodo

    //Se abre metodo para leer lista de operadores
    public void leerTOperadores(){
        //Declaracion de variables
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
        }//cierre finally
    }//cierre metodo

    //Se abre metodo para almacenar operadores de fichero en lista
    public void operadores(BufferedReader br) throws IOException {
        //Declaracion de variables
        String linea;

        //Se lee linea a linea el fichero hasta que encuentra un espacio y guarda la palabra encontrada
        while((linea=br.readLine())!=null) {

            operadores.add(linea.substring(0,1));
            tiposOperadores.add(linea.substring(4));
        }//cierre while
    }//Cierre metodo

    //Se abre metodo para leer lista de operadores especiales
    public void leerTOperadoresEspeciales(){
        //Declaracion de variables
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
        }//Cierre finally
    }//Cierre metodo

    //Se abre metodo para almacenar operadores especiales de fichero en lista
    public void operadoresEspeciales(BufferedReader br) throws IOException {
        //Declaracion de variables
        String linea;

        //Se lee linea a linea el fichero hasta que encuentra un espacio y guarda la palabra encontrada
        while((linea=br.readLine())!=null) {

            operadoresEspeciales.add(linea.substring(0,2));
            tiposOpEspeciales.add(linea.substring(5));
        }
    }//Cierre metodo

    //Se crea metodo para leer codigo
    public void leerAnalizarCodigo(){
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
        }//cierre finally
    }//Cierre metodo

    //Se crea metodo para analizar codigo y leerlo caracter por caracter
    public ArrayList<Simbolo> analizador(BufferedReader br) throws IOException {
        //Declaro variables
        String linea;
        String tipo = "";
        ArrayList<Simbolo> simbolos = new ArrayList<>();

        //Declaro variables para ubicacion de simbolos
        int fila = 1;

        //Se lee linea por linea del fichero
        while ((linea = br.readLine()) != null) {

            //Se lee caracter por caracter de cada linea
            for (int i = 0; i < linea.length(); i++) {

                //Almacenamos numero de columna
                int columna = i + 1;

                //Creamos stringBuilder para almacenar las palabras y simbolos
                StringBuilder simbolo = new StringBuilder();

                //Corremos un ciclo mientras no encuentre operadores para guardar palabras
                while (i < linea.length() && !operadores.contains(linea.substring(i, i + 1)) &&
                        !operadoresEspeciales.contains(linea.substring(i, i + 2))) {

                    //Agregamos caracteres al stringBuilder
                    simbolo.append(linea.charAt(i));
                    i++;
                }//cierre while

                //Parseamos stringBuilder to String
                String simbol = simbolo.toString();

                //Verificamos que la palabra sea una palabra reservada o un identificador
                if (palabrasClave.contains(simbol)) {
                    tipo = tiposPalabrasClave.get(busquedaPalClave(simbol));
                } else {
                    tipo = "identificador";
                }

                //Creamos el simbolo y lo almacenamos en la lista de simbolos y se verifica que no sea vacio
                if(simbol.length()>0) {
                    Simbolo temporal = new Simbolo(simbol, new Ubicacion(fila, columna), tipo);
                    simbolos.add(temporal);
                }

                //Creamos string para almacenar las operadores
                String op = "";
                columna = i + 1;

                //Busco si hay un operador en el punto y lo almaceno
                if (operadores.contains(linea.substring(i, i + 1))) {
                    op = linea.substring(i, i + 1);
                    tipo = tiposOperadores.get(busquedaOperadores(op));
                } else if (operadoresEspeciales.contains(linea.substring(i, i + 2))) {
                    op = linea.substring(i, i + 2);
                    tipo = tiposOpEspeciales.get(busquedaOpEspeciales(op));
                }//Cierre else if

                //Verificamos que string de operadores no sea vacio
                if(op.length()>0){
                    //Creamos el simbolo y lo almacenamos en la lista de simbolos
                    Simbolo opEsp = new Simbolo(op, new Ubicacion(fila, columna), tipo);
                    simbolos.add(opEsp);
                }//Cierre if

            }//Cierre for

            //Aumento 1 fila
            fila++;

        }//Cierre While

        //retorno simbolos
        System.out.println(simbolos);
        return simbolos;
    }//Cierre metodo
    
    //Búsqueda de un valor en palabras clave, operadores
    public  int busquedaPalClave(String palabra){
        int i=0;
        //Mientras la palabra no este en la lista sigue buscando
        while ( i<palabrasClave.size() && palabrasClave.get(i).compareTo(palabra)!=0) i++ ;
        return(i==palabrasClave.size()) ? -1: i;
    }//Cierre metodo

    //Búsqueda de un valor en operadores
    public  int busquedaOperadores(String palabra){
        int i=0;
        //Mientras la palabra no este en la lista sigue buscando
        while ( i<operadores.size() && operadores.get(i).compareTo(palabra)!=0) i++ ;
        return(i==operadores.size()) ? -1: i;
    }//Cierre metodo

    //Búsqueda de un valor en operadoresEspeciales
    public  int busquedaOpEspeciales(String palabra){
        int i=0;
        //Mientras la palabra no este en la lista sigue buscando
        while ( i<operadoresEspeciales.size() && operadoresEspeciales.get(i).compareTo(palabra)!=0) i++ ;
        return(i==operadoresEspeciales.size()) ? -1: i;
    }//Cierre metodo

    //Se crea fichero con lista de simbolos
    public void tablaCodigo(){

    }//Cierre metodo


}//Cierre clase
