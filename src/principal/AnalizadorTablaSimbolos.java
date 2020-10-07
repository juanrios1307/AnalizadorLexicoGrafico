/*Realizado por:
* Juan Sebastian Henao
* Juan Esteban Rios
*
* Proyecto en Github : https://github.com/juanrios1307/AnalizadorLexicoGrafico
* Version java : 11.0.8-openjdk
* pruebas de funcionamiento ubuntu 20.04 lts */


package principal;

import java.io.*;
import java.util.ArrayList;


public class AnalizadorTablaSimbolos {
    //Declaracion de las listas a usar
    ArrayList<String> palabrasClave=new ArrayList<>();
    ArrayList<String> tiposPalabrasClave=new ArrayList<>();
    ArrayList<String> operadores=new ArrayList<>();
    ArrayList<String> tiposOperadores=new ArrayList<>();
    ArrayList<String> operadoresEspeciales =new ArrayList<>();
    ArrayList<String> tiposOpEspeciales=new ArrayList<>();

    public static void main(String[] args) {
        AnalizadorTablaSimbolos a=new AnalizadorTablaSimbolos();

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
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder leer
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream is = null;
            is = loader.getResourceAsStream("assets/tablaSimbolos.txt");
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            // Lectura del fichero y alamcenamiento de palabras clave en DE
            palabrasClave(br);

            //Se cierran archivos
            br.close();
            is.close();


        }
        catch(Exception e){
            e.printStackTrace();
        }
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
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder leer
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream is = null;
            is = loader.getResourceAsStream("assets/tablaOperadores.txt");
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));


            // Lectura del fichero y alamcenamiento de palabras clave en DE
            operadores(br);

            //Se cierran archivos
            br.close();
            is.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
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
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder leer
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream is = null;
            is = loader.getResourceAsStream("assets/tablaOperadoresEspeciales.txt");
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));


            // Lectura del fichero y alamcenamiento de palabras clave en DE
            operadoresEspeciales(br);

            //Se cierran archivos
            br.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
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
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder leer
            //Creacion de File, FileReader, BufferedReader
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream is = null;
            is = loader.getResourceAsStream("assets/codigoAnalizable.txt");
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            // Lectura del fichero
            ArrayList<Simbolo> simbolos=analizador(br);

            //Se cierran archivos
            br.close();

        } catch(Exception e){
            e.printStackTrace();
        }
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

            int columna=1;

            //Se lee caracter por caracter de cada linea
            for (int i = 0; i < linea.length(); i++) {

                //Almacenamos numero de columna
                columna = i + 1;

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
                    simbolos.add(addSimbolo(simbol, new Ubicacion(fila, columna), tipo));
                }

                //Creamos string para almacenar las operadores
                String op = "";
                columna = i + 1;

                //Busco si hay un operador en el punto y lo almaceno
                if (operadores.contains(linea.substring(i, i + 1))) {
                    op = linea.substring(i, i + 1);

                    //Creamos stringBuilder para almacenar las palabras y simbolos
                    StringBuilder cadena = new StringBuilder();

                    //Se corre un ciclo para verificar cadenas entre comillas dobles y simples
                    if(op.equals("\"")){

                        //Se da el tipo
                        tipo = tiposOperadores.get(busquedaOperadores(op));

                        //Se crea simbolo con " para abrir
                        simbolos.add(addSimbolo(op, new Ubicacion(fila, columna), tipo));

                        i++;
                        columna = i + 1;

                        while (i< linea.length() && !(linea.substring(i, i + 1)).equals("\"")) {
                            //Agregamos caracteres al stringBuilder
                            cadena.append(linea.charAt(i));
                            i++;
                        }//cierre while

                        //Se agrega la cadena

                        simbolos.add(addSimbolo(cadena.toString(), new Ubicacion(fila, columna), "cadena"));

                        columna=i+1;


                    }else if(op.equals("\'")) {

                        //Se da el tipo
                        tipo = tiposOperadores.get(busquedaOperadores(op));

                        simbolos.add(addSimbolo(op, new Ubicacion(fila, columna), tipo));

                        i++;
                        columna = i + 1;

                        while (i< linea.length() && !(linea.substring(i, i + 1)).equals("\'")) {
                            //Agregamos caracteres al stringBuilder
                            cadena.append(linea.charAt(i));
                            i++;
                        }//cierre while

                        //se agrega la cadena

                        simbolos.add(addSimbolo(cadena.toString(), new Ubicacion(fila, columna), "cadena"));

                        columna=i+1;

                    }

                    if(op.length()>0)
                        tipo = tiposOperadores.get(busquedaOperadores(op));

                } else if (operadoresEspeciales.contains(linea.substring(i, i + 2))) {
                    op = linea.substring(i, i + 2);
                    tipo = tiposOpEspeciales.get(busquedaOpEspeciales(op));
                }//Cierre else if

                //Verificamos que string de operadores no sea vacio
                if(op.length()>0){
                    //Creamos el simbolo y lo almacenamos en la lista de simbolos

                    simbolos.add(addSimbolo(op, new Ubicacion(fila, columna), tipo));
                }//Cierre if

            }//Cierre for


            //Se almacena un salto de linea por que se garantiza que existe
            simbolos.add(addSimbolo("\\n", new Ubicacion(fila, linea.length()+1), "Separador"));

            //Aumento 1 fila
            fila++;


        }//Cierre While

        //retorno y creacion del fichero de la tabla de simbolos
        tablaCodigo(simbolos);

        return simbolos;
    }//Cierre metodo

    //Metodo para crear simbolos
    public Simbolo addSimbolo(String simbolo, Ubicacion ub, String tipos){
        Simbolo s=new Simbolo(simbolo,ub,tipos);
        return s;
    }

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
    public void tablaCodigo(ArrayList<Simbolo> a){
        //Declaracion de variables para escribir en fichero
        File f;
        FileWriter w;
        BufferedWriter bw;
        BufferedReader br;
        PrintWriter wr;

        try {
            //Inicializacion de archivos para escritura de tabla
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream is = null;

            is=loader.getResourceAsStream("assets/tablaAnalisisSimbolos.txt");

           // is = loader.getResourceAsStream("assets/tablaSimbolos.txt");
            OutputStream os=null;



            w = new FileWriter("tablaAnalisisSimbolos.txt");
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);

            //Se escribe la lista de simbolos en archivo
            wr.write(a.toString());
            System.out.println("Tabla de simbolos creada correctamente...");

            //Se cierra archivos
            wr.close();
            bw.close();
            w.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }//Cierre metodo


}//Cierre clase
