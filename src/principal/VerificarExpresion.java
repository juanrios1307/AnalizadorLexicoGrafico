package principal;

import java.util.Stack;

public class VerificarExpresion {

    int PosicionCinta ;
    String DetectadoErrorSintaxis = "0";
    Character TokenEntrada ;
    String CadenaAnalizada = " ";
    String abecedario="abcdefghijklmnopqrstuvwxyz";
    boolean estado=false;
    Stack<Character> parentesis=new Stack<Character>();

    public VerificarExpresion(String operacion){
        AnalizarCadena(operacion);
    }

    public void LlevarLog(String mensaje)
    {
        System.out.println("Para "+TokenEntrada+" "+mensaje);
    }

    public void PresentarMensaje(String mensaje)
    {
        System.out.println(mensaje);
    }

    public void PresentarError(String complemento)
    {

        PresentarMensaje("ERROR en token: "+TokenEntrada+" "+complemento);
        estado=true;
    }

    public void Expresion()
    {
        Termino();
        ExpresionPrima();
    }

    public void ExpresionPrima()
    {
        if(PosicionCinta<CadenaAnalizada.length()) {

            if (TokenEntrada == '+') {
                HacerMatch('+');
                Termino();
                ExpresionPrima();
            } else {
                if (TokenEntrada == '-') {
                    HacerMatch('-');
                    Termino();
                    ExpresionPrima();
                } else {

                    if (TokenEntrada == ')') {
                        System.out.println("Nada");
                        HacerMatch(TokenEntrada);

                    } else
                        PresentarError("ERROR en token: " + TokenEntrada + " ");
                    //No hacer nada: Epsilon
                }
            }
        }
    }

    public void Termino()
    {

        Factor();
        TerminoPrima();
    }

    public void TerminoPrima()
    {
        if(PosicionCinta<CadenaAnalizada.length()) {

            if (TokenEntrada == '*') {
                HacerMatch('*');
                Factor();
                TerminoPrima();
            } else {
                if (TokenEntrada == '/') {
                    HacerMatch('/');
                    Factor();
                    TerminoPrima();
                } else {
                    //No hacer nada: Epsilon

                }
            }
        }
    }

    public void Factor()
    {
            if (TokenEntrada=='1' || TokenEntrada=='2' || TokenEntrada=='3' ||
                    TokenEntrada=='4' || TokenEntrada=='5' || TokenEntrada=='6' ||
                    TokenEntrada=='7' || TokenEntrada=='8' ||TokenEntrada=='9'
                    || TokenEntrada=='0')
            {
                Numero();

            }else if(abecedario.contains(TokenEntrada.toString())) {
                Identificador();

            } else{
                if(TokenEntrada=='('){
                    parentesis.push(TokenEntrada);

                    HacerMatch(TokenEntrada);

                    Expresion();


                }

                if(TokenEntrada==')'){

                    if(parentesis.isEmpty()) {

                        PresentarError("Se esperaba parentesis de abertura");

                    }else if(parentesis.peek().equals('(')) {
                        parentesis.pop();
                    }

                    HacerMatch(TokenEntrada);

                }
            }


    }

    public void HacerMatch(char t)
    {

        if(PosicionCinta<CadenaAnalizada.length()){
            TokenEntrada = ObtenerToken();
        }else {
            PosicionCinta++;
            //System.out.println("Cadena analizada");
        }


    }

    public void Numero()
    {

        Digito();
        NumeroPrima();
    }

    public void NumeroPrima()
    {
        if(PosicionCinta<CadenaAnalizada.length()) {

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
    }

    public void Digito()
    {


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

    }

    public void Identificador()
    {
        LlevarLog("Numero");
        Letra();
        IdentificadorPrima();
    }

    public void IdentificadorPrima()
    {
        if(PosicionCinta<CadenaAnalizada.length()) {
            // LlevarLog("NumeroPrima");
            if (abecedario.contains(TokenEntrada.toString())) {
                Letra();
                IdentificadorPrima();
            } else {
                //No hacer nada: Epsilon
            }
        }
    }

    public void Letra()
    {

        // LlevarLog("Letra");
        if (abecedario.contains(TokenEntrada.toString()))
        {
            HacerMatch(TokenEntrada);
        }
        else
        {
            PresentarError("Se esperaba una letra");
        }

    }

    public Character ObtenerToken()
    {

        if (PosicionCinta<CadenaAnalizada.length() && CadenaAnalizada.charAt(PosicionCinta) != ' ')
            PresentarMensaje("Analizando token: "+CadenaAnalizada.charAt(PosicionCinta));

        PosicionCinta++;
        return (CadenaAnalizada.charAt(PosicionCinta - 1));
    }

    public void AnalizarCadena(String cadena)
    {
        PosicionCinta = 0;
        CadenaAnalizada = cadena;
        PresentarMensaje("Verificando sintaxis para: "+CadenaAnalizada+"\n---------------------------------------------------------------");
        TokenEntrada = ObtenerToken();
        Expresion();

    }



}
