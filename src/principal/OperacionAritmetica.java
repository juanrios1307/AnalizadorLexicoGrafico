package principal;

import java.util.ArrayList;

public class OperacionAritmetica {

    ArrayList<Simbolo> operacion;

    public OperacionAritmetica() {
        operacion=new ArrayList<>();
    }

    @Override
    public String toString() {
        return "OperacionAritmetica{\n" + operacion + "}\n";
    }
}
