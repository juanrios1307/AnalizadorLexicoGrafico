package principal;

import java.util.ArrayList;

public class Simbolo {

    String simbolo;
    Ubicacion ubicacion;
    String tipos;

    public Simbolo(String simbolo, Ubicacion ubicacion, String tipos) {
        this.simbolo = simbolo;
        this.ubicacion = ubicacion;
        this.tipos = tipos;
    }

    @Override
    public String toString() {
        return "\t" +
                "| Simbolo:'" + simbolo + "\'|\t\t " +
                "| Ubicacion:" + ubicacion +"|\t\t " +
                "| Tipos: " + tipos + "|\t\t \n ";
    }
}
