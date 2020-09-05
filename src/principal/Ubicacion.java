package principal;

public class Ubicacion {
        //Declaracion variables fila y columna
        int fila;
        int columna;

        //Constructor de clase u objeto
        public Ubicacion(int fila,int columna){
            this.fila=fila;
            this.columna=columna;
        }

        //Metodo toString para visualizacion de objeto
        @Override
        public String toString() {
            return "(" + fila + ", " + columna + ')';
        }
}
