package principal;

public class Simbolo {

    //Declaracion de variables almacenadas en la estructura Simbolo
    String simbolo;
    Ubicacion ubicacion;
    String tipos;

    //Constructor del objeto o clase
    public Simbolo(String simbolo, Ubicacion ubicacion, String tipos) {
        this.simbolo = simbolo;
        this.ubicacion = ubicacion;
        this.tipos = tipos;
    }



    //Metodo toString para generacion de cadenas y visibilizacion
    @Override
    public String toString() {
        return "\t" +
                "[ Simbolo:'" + simbolo + "\'|\t\t " +
                "| Ubicacion:" + ubicacion +"|\t\t " +
                "| Tipos: " + tipos + "|]\t\t\n ";
    }
}
