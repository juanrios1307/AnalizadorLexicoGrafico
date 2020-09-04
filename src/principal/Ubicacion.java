package principal;

public class Ubicacion {
        int fila;
        int columna;

        public Ubicacion(int fila,int columna){
            this.fila=fila;
            this.columna=columna;
        }

        @Override
        public String toString() {
            return "(" + fila +
                    ", " + columna +
                    ')';
        }
}
