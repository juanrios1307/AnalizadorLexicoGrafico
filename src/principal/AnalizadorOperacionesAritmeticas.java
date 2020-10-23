package principal;

import java.io.*;

import java.util.ArrayList;

public class AnalizadorOperacionesAritmeticas {

    public ArrayList<OperacionAritmetica> analisisOperaciones(File file) throws IOException {


        //Instanciamos tabla de simbolos
        AnalizadorTablaSimbolos simbolos=new AnalizadorTablaSimbolos(file);
        ArrayList<Simbolo> elementos=simbolos.leerAnalizarCodigo();
        ArrayList<OperacionAritmetica> operaciones=new ArrayList<>();

        //Recorremos toda la tabla de simbolos y separamos por sentencias
        for(int i=0;i<elementos.size();i++){
            ArrayList<Simbolo> sentencia=new ArrayList<>();

            //Hacemos una sentencia
            while(i<elementos.size() && !elementos.get(i).simbolo.equals(";")){
                sentencia.add(elementos.get(i));
                i++;
            }

            //Verificacmos que la sentencia tenga un operador
            if(verificarOperadores(sentencia)){
                OperacionAritmetica opAux=new OperacionAritmetica();

                int j=0;


                //Se eliminaran espacios y saltos de linea de la sentencia
                while(j<sentencia.size()){
                    if(sentencia.get(j).simbolo.equals(" ") || sentencia.get(j).simbolo.equals("\\n")){
                        sentencia.remove(j);
                       j--;
                    }
                    j++;
                }

                //boolean para verificar que hay dos operadores entre una constante o identificador
                boolean seguido=false;

                for(j=0;j<sentencia.size();j++){

                    //Si encuentra un parentesis lo agrega
                    if(sentencia.get(j).simbolo.equals("("))
                        opAux.operacion.add(sentencia.get(j));

                    if(j>0 && verificarOperadores(sentencia.get(j))){
                        //Si no hay dos operadores entre una constante o identificador se agrega el simbolo anterior
                        if(!seguido)
                            opAux.operacion.add(sentencia.get(j-1));

                        //Se agrega el operador ubicado en J
                        opAux.operacion.add(sentencia.get(j));

                        //Si el siguiente simbolo es un ( se guarda toda la expresion adentro de este
                        //Sino se guarda el siguiente simbolo
                        if(sentencia.get(j+1).simbolo.equals("("))
                            j=guardarExpParentesis(opAux,sentencia,j+1);
                        else
                            opAux.operacion.add(sentencia.get(j+1));


                        //Se verifica que el simbolo ubicado en J+2 sea un operador y se activa
                        //el booleano seguido
                       seguido=((j+2)<sentencia.size() && verificarOperadores(sentencia.get(j+2)))?true:false;

                       //Se aumenta en 1 j, ya que ya fue agregado
                        j++;
                    }
                }

                //Se convierte la estructura operacionAritmetica en un string expresion
                opAux.expr=convertToString(opAux);


                //Se agrea la operacionAritmeticaAuxiliar al arraylist de operaciones
                operaciones.add(opAux);

            }//Cierra IF de sentencia


        }//Cierra for que corre toda la tabla
        tablaCodigo(operaciones);
        //retorno y creacion del fichero de la tabla de simbolos
        return operaciones;
    }//Cierra metodo

    //Metodo para convertir la estructura OperacionAritmetica a String
    public String convertToString(OperacionAritmetica op){
        //Se declara el String resultante
        String result="";

        //Se reccorre toda la OperacionAritmetica y se añade cada simbolo al string resultado
        for(int i=0;i<op.operacion.size();i++){
            result+=op.operacion.get(i).simbolo;
        }

        //Se cierra el resultado
        return result;
    }//Cierra metodo

    //Este metodo guardara la expresion que este dentro de un parentesis
    public int guardarExpParentesis(OperacionAritmetica opAux, ArrayList<Simbolo> sentencia , int i ){
        //Mientras la sentencia entrante sea distinta de ), agregara a la Operacion
        //el Simbolo del Arraylist
        while(!sentencia.get(i).simbolo.equals(")")){
            opAux.operacion.add(sentencia.get(i));
            i++;
        }
        //Agrega el parentesis de cierre
        opAux.operacion.add(sentencia.get(i));

        //retorna i-1, para no saltar simbolos, ni agregarlos dos veces
        return i-1;
    }

    //Este metodo verificara si un simbolo es un operador aritmetico
    public boolean verificarOperadores(Simbolo simbolo){
            if(simbolo.simbolo.equals("+") || simbolo.simbolo.equals("-") ||
                    simbolo.simbolo.equals("*") || simbolo.simbolo.equals("/") ||
                    simbolo.simbolo.equals("%") )
                return true;

            return false;

    }

    //Este metodo verificara si en una sentencia hay una expresion
    public boolean verificarOperadores(ArrayList<Simbolo> linea){
        for(int i=0;i<linea.size();i++){
            if(linea.get(i).simbolo.equals("+") || linea.get(i).simbolo.equals("-") ||
                    linea.get(i).simbolo.equals("*") || linea.get(i).simbolo.equals("/") ||
                    linea.get(i).simbolo.equals("%") )
                return true;
        }
        return false;
    }

    public void tablaCodigo(ArrayList<OperacionAritmetica> a){
        //Declaracion de variables para escribir en fichero

        FileWriter w;
        BufferedWriter bw;

        PrintWriter wr;

        try {
            //Inicializacion de archivos para escritura de tabla
            w = new FileWriter("tablaAnalisisOperaciones.txt");
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);

            //Se escribe la lista de simbolos en archivo
            wr.write(a.toString());
            System.out.println("Tabla de operaciones creada correctamente...");

            //Se cierra archivos
            wr.close();
            bw.close();
            w.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }//Cierre metodo
}
