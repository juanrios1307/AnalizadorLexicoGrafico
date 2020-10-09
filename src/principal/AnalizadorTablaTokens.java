 /*Realizado por:
  * Juan Sebastian Henao
  * Juan Esteban Rios
  *
  * Proyecto en Github : https://github.com/juanrios1307/AnalizadorLexicoGrafico
  * Version java : 11.0.8-openjdk
  * pruebas de funcionamiento ubuntu 20.04 lts */

 package principal;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class AnalizadorTablaTokens {

        //Declaracion de las listas a usar
        ArrayList<String> lexemas=new ArrayList<>();
        ArrayList<String> tokens=new ArrayList<>();
        ArrayList<String> separadores=new ArrayList<>();
        ArrayList<Integer> idTokens=new ArrayList<Integer>();

        //Es el archivo del codigo a analizar
        File codigoAnalizar;


    AnalizadorTablaTokens(File codigoAnalizar){
        //Inicializa el archivo
        this.codigoAnalizar=codigoAnalizar;

        //Ejecuta dos metodos al construir el objeto
        this.leerTLexemas();
        this.leerTSeparadores();


    }

        //Se abre metodo para leer lista de tokens,lexemas y idTokens
        public void leerTLexemas(){
            //Declaracion de variables
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;

            try {
                // Apertura del fichero y creacion de BufferedReader para poder leer
                URL ruta=getClass().getResource("/assets/tablaTokens.txt");
                archivo = new File (ruta.getPath());
                fr = new FileReader (archivo);
                br = new BufferedReader(fr);

                // Lectura del fichero y alamcenamiento de palabras clave en DE
                lexemas(br);

                //Se cierran archivos
                br.close();
                fr.close();

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
                if(i!=0)
                    lexemas.add(linea.substring(0,i));

                int j=i+3;

                if(i==0 && linea.charAt(i) == ' '){
                    lexemas.add(linea.substring(0,1));
                    j++;
                }

                //Lee la linea desde que encuentra nuevamente una palabra y guarda el token
                //y su respectivo idToken
                while(j<linea.length() && linea.charAt(j) != ' '){
                    j++;
                }

                //Agrega el token y el id, encontrado en la linea
                tokens.add(linea.substring((i+3),j));
                idTokens.add(Integer.parseInt(linea.substring(j+3)));

            }
        }//cierre metodo

    //Se abre metodo para leer lista de operadores
    public void leerTSeparadores(){
        //Declaracion de variables
        BufferedReader br = null;
        File archivo = null;
        FileReader fr = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder leer
            URL ruta=getClass().getResource("/assets/tablaOperadores.txt");
            archivo = new File (ruta.getPath());
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero y alamcenamiento de palabras clave en DE
            separadores(br);

            //Se cierran archivos
            br.close();
            fr.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//cierre metodo

    //Se abre metodo para almacenar operadores de fichero en lista
    public void separadores(BufferedReader br) throws IOException {
        //Declaracion de variables
        String linea;

        //Se lee linea a linea el fichero hasta que encuentra un espacio y guarda el separador encontrado
        while((linea=br.readLine())!=null) {

            separadores.add(linea.substring(0,1));

        }//cierre while


    }//Cierre metodo

        //Se crea metodo para leer codigo
        public ArrayList<Token> leerAnalizarCodigo(){
            //Declaracion de variables a usar
            BufferedReader br = null;
            FileReader fr;

            try {
                // Apertura del fichero y creacion de BufferedReader para poder leer
                //Creacion de File, FileReader, BufferedReader

                fr=new FileReader(codigoAnalizar);
                br = new BufferedReader(fr);

                // Lectura del fichero
                ArrayList<Token> tokens=analizador(br);

                //Se cierran archivos
                br.close();

                //retorna la lista de tokens
                return tokens;

            } catch(Exception e){
                e.printStackTrace();
            }
            return null;
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
                    while (i < linea.length() && !separadores.contains(linea.substring(i, i + 1)) ) {

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

                    //Si la linea contiene separadores
                    if(separadores.contains(linea.substring(i, i+1))){
                        //asigna el lexema, token y idToken
                        lexema=linea.substring(i, i+1 );
                        token = tokens.get(busquedaLexema(lexema));
                        idToken = idTokens.get(busquedaLexema(lexema));

                        //Si el lexema encontrado es " : comilla_doble
                        if(lexema.equals("\"")){
                            //Agrega el token
                            tokensAux.add(createToken(lexema, token, idToken));

                            //Crea un nuevo stringBuilder cadena
                            StringBuilder cadena=new StringBuilder();

                            //Incrementa i
                            i++;

                            //Mientras la linea no contenga " : comilla_doble
                            while(i<linea.length() && !linea.substring(i,i+1).equals("\"")){
                                //Agrega caracter por caracter al StringBuilder cadena
                                cadena.append(linea.charAt(i));

                                //Incrementa i
                                i++;
                            }

                            //Asigna a lexema la cadena encontrada
                            lexema = cadena.toString();

                            //Si encontro una cadena la almacena
                            if(lexema.length()>0) {
                                token = "Cadena_Constante";
                                idToken= 72 ;
                                tokensAux.add(createToken(lexema, token, idToken));
                            }

                            //Almacena la comilla_doble ", junto a su token y id
                            lexema=linea.substring(i, i+1 );
                            token = tokens.get(busquedaLexema(lexema));
                            idToken = idTokens.get(busquedaLexema(lexema));
                        }

                        //Si el lexema encontrado es ' : comilla_simple
                        if(lexema.equals("\'")){
                            //Agrega el token
                            tokensAux.add(createToken(lexema, token, idToken));

                            //Crea un nuevo stringBuilder cadena
                            StringBuilder cadena=new StringBuilder();

                            //Incrementa i
                            i++;

                            //Mientras la linea no contenga " : comilla_doble
                            while(i<linea.length() && !linea.substring(i,i+1).equals("\'")){
                                //Agrega caracter por caracter al StringBuilder cadena
                                cadena.append(linea.charAt(i));

                                //Incrementa i
                                i++;
                            }

                            //Asigna a lexema la cadena encontrada
                            lexema = cadena.toString();

                            //Si encontro una cadena la almacena
                            if(lexema.length()>0) {
                                token = "Cadena_Constante";
                                idToken= 72 ;
                                tokensAux.add(createToken(lexema, token, idToken));
                            }

                            //Almacena la comilla_simple ', junto a su token y id
                            lexema=linea.substring(i, i+1 );
                            token = tokens.get(busquedaLexema(lexema));
                            idToken = idTokens.get(busquedaLexema(lexema));
                        }

                        //Almacena el separador encontrado como token
                        tokensAux.add(createToken(lexema, token, idToken));
                    }//Cierre if

                }//Cierre for

                //Almacena un salto de linea
                String lexema="\\n";
                token = tokens.get(busquedaLexema(lexema));
                idToken = idTokens.get(busquedaLexema(lexema));

                tokensAux.add(createToken(lexema, token, idToken));

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
            FileWriter w;
            BufferedWriter bw;

            PrintWriter wr;

            try {
                //Inicializacion de archivos para escritura de tabla

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

