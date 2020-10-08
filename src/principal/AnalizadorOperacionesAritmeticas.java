package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnalizadorOperacionesAritmeticas {

    public static void main(String[] args) {


    }

    public ArrayList<OperacionAritmetica> analisisOperaciones(ArrayList<Token> tokens, BufferedReader br) throws IOException {

        //Declaro variables
        String linea;
        String token = "";
        int idToken = 0;
        ArrayList<OperacionAritmetica> operacionAritmeticas = new ArrayList<>();


        //Se lee linea por linea del fichero
        while ((linea = br.readLine()) != null) {


            //Se verifica que la linea contenga un operador aritmetico
            if(linea.contains("+") || linea.contains("-") || linea.contains("*")
                    || linea.contains("/") || linea.contains("%")){

                // Volumen = 7 + (diametro/2) * (hola-1)
                // -var + x * y/t
                // r*r*r + (q -8)*6
                // if ( 4+diametro == altura)

                //Se lee caracter por caracter de cada linea
                for (int i = 0; i < linea.length(); i++) {

                    int h= 4+ ( 5 ) ;

                    //Corremos un ciclo mientras no encuentre operadores para guardar palabras
                    while (i < linea.length() && !linea.substring(i, i + 1).equals(" ")) {



                    }//cierre while




                }//Cierre for
            }




        }//Cierre While

        //retorno y creacion del fichero de la tabla de simbolos

        return operacionAritmeticas;
    }
}
