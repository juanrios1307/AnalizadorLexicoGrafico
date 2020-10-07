package principal;

public class Token {

    //Declaracion de variables almacenadas en la estructura Token
    String lexema;
    String token;
    int idToken;

    //Constructor del objeto
    public Token(String lexema, String token, int idToken) {
        this.lexema = lexema;
        this.token = token;
        this.idToken = idToken;
    }

    //Metodo toString para generacion de cadenas y visibilizacion
    @Override
    public String toString() {
        return "\t" +
                "[ IdToken: " + idToken + "\t|\t\t" +
                "| Token: " + token + "|\t\t " +
                "| Lexema: '" + lexema +" |\']\t\t\n";

    }
}
