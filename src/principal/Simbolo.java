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
        return "[\t" +
                "simbolo:'" + simbolo + "\'\t\t " +
                "ubicacion:" + ubicacion +"\t\t " +
                "tipos: " + tipos + "\t\t ] \n ";
    }
}
