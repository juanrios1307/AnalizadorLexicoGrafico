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

public class AnalizadorTablaTokens {

        //Declaracion de las listas a usar
        ArrayList<String> lexemas=new ArrayList<>();
        ArrayList<String> tokens=new ArrayList<>();
        ArrayList<Integer> idTokens=new ArrayList<Integer>();


        public static void main(String[] args) {
            AnalizadorTablaTokens a=new AnalizadorTablaTokens();

            //Se lee e inicializan las listas
            a.leerTLexemas();

            //Se lee el codigo a analizar y se analiza
            a.leerAnalizarCodigo();

        }//cierre main

        //Se abre metodo para leer lista de tokens,lexemas y idTokens
        public void leerTLexemas(){
            //Declaracion de variables
            BufferedReader br = null;

            try {
                // Apertura del fichero y creacion de BufferedReader para poder leer
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                InputStream is = null;
                is = loader.getResourceAsStream("assets/tablaTokens.txt");
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                // Lectura del fichero y alamcenamiento de palabras clave en DE
                lexemas(br);

                //Se cierran archivos
                br.close();
                is.close();


            }
            catch(Exception e){
                e.printStackTrace();
            }
        }//cierre metodo

        //Se abre metodo para almacenar palabras clave de fichero en lista
        public void lexemas(BufferedReader br) throws IOException {
            //Declaracion de variables
            String linea;

            //Se lee linea a linea el fichero hasta que encuentra un espacio y guarda la palabra encontrada
            while((linea=br.readLine())!=null) {
                int i=0;
                while(i<linea.length() && linea.charAt(i) != ' '){
                    i++;
                }
                lexemas.add(linea.substring(0,i));

                int j=i+3;
                while(j<linea.length() && linea.charAt(j) != ' '){
                    j++;
                }

                tokens.add(linea.substring((i+3),j));
                idTokens.add(Integer.parseInt(linea.substring(j+3)));
                //idTokens.add(linea.substring(j+3));
            }
        }//cierre metodo


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
                ArrayList<Token> simbolos=analizador(br);

                //Se cierran archivos
                br.close();

            } catch(Exception e){
                e.printStackTrace();
            }
        }//Cierre metodo

        //Se crea metodo para analizar codigo y leerlo caracter por caracter
        public ArrayList<Token> analizador(BufferedReader br) throws IOException {
            //Declaro variables
            String linea;
            String token = "";
            int idToken = 0;
            ArrayList<Token> tokensAux = new ArrayList<>();


            //Se lee linea por linea del fichero
            while ((linea = br.readLine()) != null) {

                //Se lee caracter por caracter de cada linea
                for (int i = 0; i < linea.length(); i++) {


                    //Creamos stringBuilder para almacenar las palabras y lexemas
                    StringBuilder tokenAux = new StringBuilder();

                    //Corremos un ciclo mientras no encuentre operadores para guardar palabras
                    while (i < linea.length() && !linea.substring(i, i + 1).equals(" ")) {

                        //Agregamos caracteres al stringBuilder
                        tokenAux.append(linea.charAt(i));
                        i++;
                    }//cierre while

                    //Parseamos stringBuilder to String
                    String lexema = tokenAux.toString();

                    //Verificamos que la palabra sea un lexema conocido,
                    // si es desconocido se asume como identificador
                    if (lexemas.contains(lexema)) {
                        token = tokens.get(busquedaLexema(lexema));
                        idToken = idTokens.get(busquedaLexema(lexema));
                    } else {
                        token = "Identificador";
                        idToken= 2 ;
                    }

                    //Creamos el token y lo almacenamos en la lista de simbolos y se verifica que no sea vacio
                    if(lexema.length()>0) {
                        tokensAux.add(createToken(lexema, token, idToken));
                    }


                }//Cierre for

            }//Cierre While

            //retorno y creacion del fichero de la tabla de simbolos
            tablaCodigo(tokensAux);

            return tokensAux;
        }//Cierre metodo

        //Metodo para crear tokens
        public Token createToken(String lexema, String token, int idToken){
            Token t=new Token(lexema,token,idToken);
            return t;
        }

        //Búsqueda de un lexema
        public  int busquedaLexema(String palabra){
            int i=0;
            //Mientras la palabra no este en la lista sigue buscando
            while ( i<lexemas.size() && lexemas.get(i).compareTo(palabra)!=0) i++ ;
            return(i==lexemas.size()) ? -1: i;
        }//Cierre metodo


        //Se crea fichero con lista de tokens
        public void tablaCodigo(ArrayList<Token> a){
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

                is=loader.getResourceAsStream("assets/tablaAnalisisTokens.txt");

                // is = loader.getResourceAsStream("assets/tablaSimbolos.txt");
                OutputStream os=null;



                w = new FileWriter("tablaAnalisisTokens.txt");
                bw = new BufferedWriter(w);
                wr = new PrintWriter(bw);

                //Se escribe la lista de simbolos en archivo
                wr.write(a.toString());

                System.out.println("Tabla de tokens creada correctamente...");

                //Se cierra archivos
                wr.close();
                bw.close();
                w.close();

            }catch(Exception e){
                e.printStackTrace();
            }
        }//Cierre metodo


    }//Cierre clase

