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
        return "Simbolo{" +
                "simbolo='" + simbolo + '\'' +
                ", ubicacion=" + ubicacion +
                ", tipos='" + tipos + '\'' +
                "} \n ";
    }
}
