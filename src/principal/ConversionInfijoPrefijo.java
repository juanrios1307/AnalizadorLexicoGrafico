package principal;

import java.util.ArrayList;
import java.util.Stack;

public class ConversionInfijoPrefijo {

    //Se declaran variables a usar
    //posicion actual de la cadena
    int PosicionCinta ;

    //Se declaran dos variables temporales, con contadores para
    //parentesis y espacios
    int tempTerm, tempExpr ,e=0,p=0;
    //caracter actual analizado
    Character TokenEntrada ;

    //Cadena a analizar
    String CadenaAnalizada = " ";

    //Abecedario para identificadores
    String abecedario="abcdefghijklmnopqrstuvwxyz";

    //Listas que almacenan notacion Prefija y PosFija

    ArrayList<Character> prefijo =new ArrayList<>();

    Stack<Character> parentesis=new Stack<>();

    //Constructor de la GLC
    public ConversionInfijoPrefijo(String operacion){
        AnalizarCadena(operacion);
    }

    //Metodo expresion llama a Termino y ExpresionPrima
    public void Expresion() {

        tempExpr =PosicionCinta+e-p;

        Termino();
        ExpresionPrima();
    }//Cierra Expresion

    //Metodo Expresion prima
    public void ExpresionPrima() {

            //Si el tokenEntrada es + HaceMatch y llama a termino y tambien a ExpresionPrima
            if (TokenEntrada == '+') {

                HacerMatch('+');

                prefijo.add(tempExpr ==0? tempExpr : tempExpr >1? tempExpr -2: tempExpr -1,'+');

                Termino();

                ExpresionPrima();

            } else if (TokenEntrada == '-') {

                HacerMatch('-');

                prefijo.add(tempExpr ==0? tempExpr : tempExpr >1? tempExpr -2: tempExpr -1,'-');

                Termino();

                ExpresionPrima();
                //Si el tokenEntrada es - HaceMatch y llama a termino y tambien a ExpresionPrima
            } else {

                    //No hacer nada: Epsilon
            }



    }//Cierra ExpresionPrima

    //El metodo termino llama a Factor y TerminoPrima
    public void Termino() {

        tempTerm =PosicionCinta+e-p;

        Factor();

        TerminoPrima();
    }//Cierra Termino

    public void TerminoPrima() {

            //Si el tokenEntrada es * HaceMatch y llama a Factor y tambien a TerminoPrima
            if (TokenEntrada == '*') {

                HacerMatch('*');


                prefijo.add(tempTerm ==0? tempTerm : tempTerm >1? tempTerm -2: tempTerm -1,'*');

                Factor();

                TerminoPrima();
            } else if (TokenEntrada == '/') {


                HacerMatch('/');

                prefijo.add(tempTerm ==0? tempTerm : tempTerm >1? tempTerm -2: tempTerm -1,'/');

                Factor();

                TerminoPrima();
                //Si el tokenEntrada es / HaceMatch y llama a Factor y tambien a TerminoPrima
            } else {
                //No hacer nada: Epsilon

            }


    }//Cierra TerminoPrima

    //El metodo Factor verifica si el tokenEntrada es parentesis, numero o identificador
    public void Factor() {


        //Si el Token es ( haceMatch llama a Expresion y cierra )
        if(TokenEntrada=='('){
            parentesis.push(TokenEntrada);

            HacerMatch(TokenEntrada);

            p++;

            Expresion();

            if(TokenEntrada==')'){

                HacerMatch(TokenEntrada);

                p++;

                parentesis.pop();
            }else{

            }

            //Si el token es un numero llama a numero
        }else if (TokenEntrada=='1' || TokenEntrada=='2' || TokenEntrada=='3' ||
                    TokenEntrada=='4' || TokenEntrada=='5' || TokenEntrada=='6' ||
                    TokenEntrada=='7' || TokenEntrada=='8' ||TokenEntrada=='9'
                    || TokenEntrada=='0') {
                Numero();

            //Si el token es una letra llama a identificador
        }else if(abecedario.contains(TokenEntrada.toString())) {

            Identificador();

        }else {
            HacerMatch(TokenEntrada);
        }

    }//Cierra factor

    //Hacer Match verifica que la posicionCinta sea menor que
    //La longitud de la CadenaAnalizada
    public void HacerMatch(char t) {

        TokenEntrada = ObtenerToken();

    }


    //Numero llama a digito y numero prima
    public void Numero() {

        Digito();
        NumeroPrima();

        prefijo.add(' ');
        e++;

    }//Cierra Numerp


    public void NumeroPrima() {

            //NumeroPrima verifica si hay un numero y llama a Digito y a NumeroPrima
            if (TokenEntrada == '1' || TokenEntrada == '2' || TokenEntrada == '3' ||
                    TokenEntrada == '4' || TokenEntrada == '5' || TokenEntrada == '6' ||
                    TokenEntrada == '7' || TokenEntrada == '8' || TokenEntrada == '9'
                    || TokenEntrada == '0') {
                Digito();
                NumeroPrima();
            } else {
                //No hacer nada: Epsilon

            }

    }//Cierra NumeroPrima


    public void Digito() {
        //Digito verifica si el token es un numero y HaceMatch, en otro caso presenta error
            if (TokenEntrada=='1' || TokenEntrada=='2' || TokenEntrada=='3' ||
                    TokenEntrada=='4' || TokenEntrada=='5' || TokenEntrada=='6' ||
                    TokenEntrada=='7' || TokenEntrada=='8' ||TokenEntrada=='9'
                    || TokenEntrada=='0')
            {

                prefijo.add(TokenEntrada);

                HacerMatch(TokenEntrada);
            }
            else
            {

            }

    }//Cierra Digito

    //Identificador llama a Letra e IdentificadorPrima
    public void Identificador() {

        Letra();
        IdentificadorPrima();

        prefijo.add(' ');
        e++;
    }//Cierra Identificador

    public void IdentificadorPrima() {

            //IdentificadorPrima verifica si hay una Letra y llama a Letra y a IdentificadorPrima
            if (abecedario.contains(TokenEntrada.toString())) {
                Letra();
                IdentificadorPrima();
            } else {
                //No hacer nada: Epsilon
            }

    }//Cierra IdentificadorPrima

    public void Letra() {

        //Letra verifica si el token es una letra y HaceMatch, en otro caso presenta error
        if (abecedario.contains(TokenEntrada.toString())) {


            prefijo.add(TokenEntrada);

            HacerMatch(TokenEntrada);
        } else {

        }

    }//Cierra Letra

    //ObtenerToken incrementa PosicionCinta y retorna la CadenaAnalizada en una posicion menor
    public Character ObtenerToken() {
        if(PosicionCinta < CadenaAnalizada.length()) {
            PosicionCinta++;
            return (CadenaAnalizada.charAt(PosicionCinta - 1));
        }else{
            return ' ';
        }
    }//Cierra ObtenerToken


    //AnalizarCadena recibe un String, e inicializa las variables
    public void AnalizarCadena(String cadena) {
        PosicionCinta = 0;

        CadenaAnalizada = cadena;
        TokenEntrada = ObtenerToken();

        //La GLC comienza con <EXPRESION>
        Expresion();


    }//Cierra AnalizarCadena



}
