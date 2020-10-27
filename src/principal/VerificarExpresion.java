package principal;

import java.util.Stack;

public class VerificarExpresion {

    //Se declaran variables a usar
    //posicion actual de la cadena
    int PosicionCinta ;

    //caracter actual analizado
    Character TokenEntrada ;

    //Cadena a analizar
    String CadenaAnalizada = " ";

    //Abecedario para identificadores
    String abecedario="abcdefghijklmnopqrstuvwxyz";

    //Booleano para verificar sintaxis
    boolean isBad =false;

    //Constructor de la GLC
    public VerificarExpresion(String operacion){
        AnalizarCadena(operacion);
    }

    //Muestra error en caso de haberlo y da isBad como cierto
    public void PresentarError(String complemento) {
        System.out.println("ERROR en token: "+TokenEntrada+" "+complemento);
        isBad =true;
    }

    //Se verifica antes de usar la GLC la ubicacion de los parentesis,
    //de que esten en el orden que deben estar
    public boolean VerificarParentesis(){
        //Se declara Pila para almacenamiento
        Stack<Character> parentesis=new Stack<Character>();

        //Se inicializa contador y empieza un while a rrecorrer la cadena
        int i=0;
        while(i<CadenaAnalizada.length()) {
            //Se verifican los parentesis en la posicion i
            switch (verificarP(String.valueOf(CadenaAnalizada.charAt(i)))) {
                case 1:
                    //Caso 1 abre parentesis
                    parentesis.push(CadenaAnalizada.charAt(i));
                    break;
                case 2:
                    //Caso 2 cierra parentesis

                    //Si no hay parentesis que abre, retorna falso
                    if(parentesis.isEmpty()) {
                        return false;
                    }else if(parentesis.peek().equals('(')) {
                        //Elimina el ultimo parentesis que abria
                        parentesis.pop();
                    }
                    break;

                default:
                    break;
            }

            //Incrementa el i
            i++;

        }

        //Si no  sobran parentesis retorna verdadero
        if(parentesis.isEmpty()) {
            return true;
        }else { //En otro caso retorna falso
            return false;
        }

    }


    //Verifica si el parentesis abre cierra, o no  es parentesis
    public static int verificarP(String msg) {
        if(msg.equals("("))
            return 1;
        else if(msg.equals(")"))
            return 2;
        else
            return 10;
    }


    //Metodo expresion llama a Termin y ExpresionPrima
    public void Expresion() {
        Termino();
        ExpresionPrima();
    }//Cierra Expresion

    //Metodo Expresion prima
    public void ExpresionPrima() {

        if(PosicionCinta<CadenaAnalizada.length()) {
            //Si el tokenEntrada es + HaceMatch y llama a termino y tambien a ExpresionPrima
            if (TokenEntrada == '+') {
                HacerMatch('+');
                Termino();
                ExpresionPrima();

            } else if (TokenEntrada == '-') {
                    HacerMatch('-');
                    Termino();
                    ExpresionPrima();
                //Si el tokenEntrada es - HaceMatch y llama a termino y tambien a ExpresionPrima
            } else {

                //No hacer nada: Epsilon
            }


        }
    }//Cierra ExpresionPrima

    //El metodo termino llama a Factor y TerminoPrima
    public void Termino() {

        Factor();
        TerminoPrima();
    }//Cierra Termino

    public void TerminoPrima() {

        if(PosicionCinta<CadenaAnalizada.length()) {
            //Si el tokenEntrada es * HaceMatch y llama a Factor y tambien a TerminoPrima
            if (TokenEntrada == '*') {
                HacerMatch('*');
                Factor();
                TerminoPrima();
            } else if (TokenEntrada == '/') {
                    HacerMatch('/');
                    Factor();
                    TerminoPrima();
                //Si el tokenEntrada es / HaceMatch y llama a Factor y tambien a TerminoPrima
            } else {
                //No hacer nada: Epsilon

            }

        }
    }//Cierra TerminoPrima

    //El metodo Factor verifica si el tokenEntrada es parentesis, numero o identificador
    public void Factor() {

        //Si el Token es ( haceMatch llama a Expresion y cierra )
        if(TokenEntrada=='('){
            HacerMatch(TokenEntrada);

            Expresion();

            if(TokenEntrada==')'){

                HacerMatch(TokenEntrada);

            }else{
                //Si el Token luego de la expresion no es parentesis presenta error
                PresentarError("Se esperaba parentesis de cierre");
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

        }

    }//Cierra factor

    //Hacer Match verifica que la posicionCinta sea menor que
    //La longitud de la CadenaAnalizada
    public void HacerMatch(char t) {

        if(PosicionCinta<CadenaAnalizada.length()) {
            TokenEntrada = ObtenerToken();
        }

    }


    //Numero llama a digito y numero prima
    public void Numero() {

        Digito();
        NumeroPrima();
    }//Cierra Numerp


    public void NumeroPrima() {
        if(PosicionCinta<CadenaAnalizada.length()) {

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
        }
    }//Cierra NumeroPrima


    public void Digito() {
        //Digito verifica si el token es un numero y HaceMatch, en otro caso presenta error
            if (TokenEntrada=='1' || TokenEntrada=='2' || TokenEntrada=='3' ||
                    TokenEntrada=='4' || TokenEntrada=='5' || TokenEntrada=='6' ||
                    TokenEntrada=='7' || TokenEntrada=='8' ||TokenEntrada=='9'
                    || TokenEntrada=='0')
            {

                HacerMatch(TokenEntrada);
            }
            else
            {
                PresentarError("Se esperaba un digito");
            }

    }//Cierra Digito

    //Identificador llama a Letra e IdentificadorPrima
    public void Identificador() {

        Letra();
        IdentificadorPrima();
    }//Cierra Identificador

    public void IdentificadorPrima() {
        if(PosicionCinta<CadenaAnalizada.length()) {
            //IdentificadorPrima verifica si hay una Letra y llama a Letra y a IdentificadorPrima
            if (abecedario.contains(TokenEntrada.toString())) {
                Letra();
                IdentificadorPrima();
            } else {
                //No hacer nada: Epsilon
            }
        }
    }//Cierra IdentificadorPrima

    public void Letra() {

        //Letra verifica si el token es una letra y HaceMatch, en otro caso presenta error
        if (abecedario.contains(TokenEntrada.toString())) {
            HacerMatch(TokenEntrada);
        } else {
            PresentarError("Se esperaba una letra");
        }

    }//Cierra Letra

    //ObtenerToken incrementa PosicionCinta y retorna la CadenaAnalizada en una posicion menor
    public Character ObtenerToken() {

        if (PosicionCinta<CadenaAnalizada.length() && CadenaAnalizada.charAt(PosicionCinta) != ' ')

        PosicionCinta++;
        return (CadenaAnalizada.charAt(PosicionCinta - 1));
    }//Cierra ObtenerToken

    //AnalizarCadena recibe un String, e inicializa las variables
    public void AnalizarCadena(String cadena) {
        PosicionCinta = 0;
        CadenaAnalizada = cadena;
        TokenEntrada = ObtenerToken();

        //Primero se verifican los parentesis y si estos cumplen va a Expresion
        if(VerificarParentesis()){
            Expresion();
        }else{
            //En otro caso no analiza la expresion y da un estado de no aceptacion
            isBad=true;
        }


    }//Cierra AnalizarCadena



}
