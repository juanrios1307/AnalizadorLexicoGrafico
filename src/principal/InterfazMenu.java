package principal;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InterfazMenu extends JFrame {
    public JPanel panel;


    private final JFileChooser selector = new JFileChooser();
    private File codigoAnalizar;

    //Se crea constructor de la intefaz menu donde se inicializan tanto la ventana y se definen sus tamaños
    //como los diferentes componentes presentes en el menu.
    public InterfazMenu() {
        this.setSize(800, 900); // Establecemos el tamaño de la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Acción que se realiza la cerrar la ventana
        this.setTitle("Compilador"); // Establecmos el titulo de la ventana
        this.setLocationRelativeTo(null); // Establecemos donde aparece la ventana dentro de la pantalla que en este
        // caso es en en el centro
        this.getContentPane().setBackground(Color.WHITE);

        iniciarComponentes();//Se llama al metodo que inicia los diferentes panles, etiquetas y botones de la interfaz

    }


    private void iniciarComponentes() {
        colocarPaneles();
        colocarEtiquetas();
        addTxtAndButton();
    }

    //Se coloca el panel principal en JFrame
    private void colocarPaneles() {
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
    }

    //Se coloca el nombre, el tamaño y la fuente del panel
    private void colocarEtiquetas() {

        JLabel etiqueta = new JLabel();
        etiqueta.setText("COMPILADOR");
        etiqueta.setBounds(0, 5, 800, 60);
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        etiqueta.setFont(new Font("arial", 1, 35));
        etiqueta.setForeground(Color.BLACK);
        panel.add(etiqueta);

    }

    //Se agregan los botones para la funcionalidad del compilador
    private void addTxtAndButton() {

        //Se declara el boton correspondiente a la tablaSimbolos y se setea la ubicacion
        // el tamaño, la fuente, el color
        JButton btnSimbolos = new JButton();
        btnSimbolos.setBounds(155, 305, 500, 60);
        btnSimbolos.setText("Identificar elementos Tabla Simbolos");
        btnSimbolos.setEnabled(false);
        btnSimbolos.setFont(new Font("arial", 3, 20));
        btnSimbolos.setBackground(Color.darkGray);
        btnSimbolos.setForeground(Color.orange);
        btnSimbolos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Si el boton es presionado llama el metodo tablaSimbolos
                    tablaSimbolos(codigoAnalizar);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        panel.add(btnSimbolos);


        //Se declara el boton correspondiente a la tablaTokens y se setea la ubicacion
        // el tamaño, la fuente, el color
        JButton btnTokens = new JButton();
        btnTokens.setBounds(155, 405, 500, 60);
        btnTokens.setText("Identificar elementos Tabla Tokens");

        btnTokens.setEnabled(false);
        btnTokens.setFont(new Font("arial", 3, 20));
        btnTokens.setBackground(Color.darkGray);
        btnTokens.setForeground(Color.orange);
        btnTokens.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    //Si el boton es presionado llama el metodo tablaTokens
                    tablaTokens(codigoAnalizar);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        panel.add(btnTokens);

        //Se declara el boton correspondiente a la tablaExpresionesAritmeticas
        // y se setea la ubicacion, el tamaño, la fuente, el color
        JButton btnExpresionesAritmeticas = new JButton();
        btnExpresionesAritmeticas.setBounds(155, 205, 500, 60);
        btnExpresionesAritmeticas.setText("Identificar Expresiones Aritmeticas");
        btnExpresionesAritmeticas.setEnabled(false);
        btnExpresionesAritmeticas.setFont(new Font("arial", 3, 20));
        btnExpresionesAritmeticas.setBackground(Color.darkGray);
        btnExpresionesAritmeticas.setForeground(Color.orange);
        btnExpresionesAritmeticas.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    //Si el boton es presionado llama el metodo tablaExpresionesAritmeticas
                    tablaExpresionesAritmeticas(codigoAnalizar);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        });
        panel.add(btnExpresionesAritmeticas);


        //Se declara el boton correspondiente a verificarExpresion
        // y se setea la ubicacion, el tamaño, la fuente, el color
        JButton btnExpresion = new JButton();
        btnExpresion.setBounds(155, 505, 500, 60);
        btnExpresion.setText("Verificar 1 Expresion Aritmetica");
        btnExpresion.setEnabled(false);
        btnExpresion.setFont(new Font("arial", 3, 20));
        btnExpresion.setBackground(Color.darkGray);
        btnExpresion.setForeground(Color.orange);
        btnExpresion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    //Si el boton es presionado llama el metodo verificarExpresion
                    verificarExpresionAritmetica(codigoAnalizar);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        });
        panel.add(btnExpresion);

        //Se declara el boton correspondiente a verificarExpresion
        // y se setea la ubicacion, el tamaño, la fuente, el color
        JButton btnConversion = new JButton();
        btnConversion.setBounds(155, 605, 500, 60);
        btnConversion.setText("Prefijo y Posfijo de una expresion");
        btnConversion.setEnabled(false);
        btnConversion.setFont(new Font("arial", 3, 20));
        btnConversion.setBackground(Color.darkGray);
        btnConversion.setForeground(Color.orange);
        btnConversion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    //Si el boton es presionado llama el metodo verificarExpresion
                    conversion(codigoAnalizar);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        });
        panel.add(btnConversion);

        //Se declara el boton correspondiente a cargarArchivo
        // y se setea la ubicacion, el tamaño, la fuente, el color
        JButton btnFile = new JButton();
        btnFile.setBounds(155, 105, 500, 60);
        btnFile.setText("Cargar un archivo");
        btnFile.setEnabled(true);
        btnFile.setBackground(Color.darkGray);
        btnFile.setForeground(Color.orange);
        btnFile.setFont(new Font("arial", 3, 20));
        btnFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {

                    //Si el boton es presionado llama el metodo seleccionarArchvio
                    seleccionarArchivo();

                    //Si el archivo existe habilita el resto de botones correspondientes
                    if(codigoAnalizar.exists()){
                        btnTokens.setEnabled(true);
                        btnSimbolos.setEnabled(true);
                        btnConversion.setEnabled(true);
                        btnExpresionesAritmeticas.setEnabled(true);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        panel.add(btnFile);

        //Se declara el boton correspondiente a AbrirTabla
        // y se setea la ubicacion, el tamaño, la fuente, el color
        JButton btnOpenFile = new JButton();
        btnOpenFile.setBounds(155, 705, 500, 60);
        btnOpenFile.setText("Abrir una tabla");
        btnOpenFile.setEnabled(true);
        btnOpenFile.setBackground(Color.darkGray);
        btnOpenFile.setForeground(Color.orange);
        btnOpenFile.setFont(new Font("arial", 3, 20));
        btnOpenFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Si el boton es presionado llama el metodo abrirArchivo
                abrirarchivo();

            }
        });
        panel.add(btnOpenFile);


    }


    public void seleccionarArchivo() throws IOException {
        selector.setDialogTitle("Selecciona el codigo a analizar");
        FileNameExtensionFilter archivo = new FileNameExtensionFilter("txt","txt" );
        selector.setFileFilter(archivo);
        int flag = selector.showOpenDialog(null);

        if(flag == JFileChooser.APPROVE_OPTION) {
            codigoAnalizar = selector.getSelectedFile();

        }
    }

    public void abrirarchivo(){

        try {
            selector.setDialogTitle("Selecciona tabla a visualizar");
            FileNameExtensionFilter archivo = new FileNameExtensionFilter("txt","txt" );
            selector.setFileFilter(archivo);
            int flag = selector.showOpenDialog(null);

            if(flag == JFileChooser.APPROVE_OPTION) {
                Desktop.getDesktop().open(selector.getSelectedFile());
            }


        }catch (IOException e) {

            System.err.println(e);

        }

    }

    public void tablaTokens(File file) throws IOException {
        AnalizadorTablaTokens tokens=new AnalizadorTablaTokens(file);

        ArrayList<Token> tabla=tokens.leerAnalizarCodigo();

        confirmacionExito(tabla.size(),"Tokens");
    }

    public void tablaSimbolos(File file) throws IOException{
        AnalizadorTablaSimbolos simbolos=new AnalizadorTablaSimbolos(file);

        ArrayList<Simbolo> tabla=simbolos.leerAnalizarCodigo();

        confirmacionExito(tabla.size(),"Simbolos");
    }

    public void tablaExpresionesAritmeticas(File file) throws IOException{
            AnalizadorOperacionesAritmeticas operaciones=new AnalizadorOperacionesAritmeticas();
            ArrayList<OperacionAritmetica> tabla=operaciones.analisisOperaciones(file);
            confirmacionExito(tabla.size(),"Operaciones");
    }

    public void verificarExpresionAritmetica(File file) throws IOException{

        AnalizadorOperacionesAritmeticas operaciones=new AnalizadorOperacionesAritmeticas();

        ArrayList<OperacionAritmetica> tabla=operaciones.analisisOperaciones(file);

        ArrayList<String> expresiones=new ArrayList<>();

        for(int i=0;i<tabla.size();i++){
            expresiones.add(i+ " : " +tabla.get(i).expr +"\n");
        }
        int n=0;

        do {
            Object seleccion=JOptionPane.showInputDialog(null,
                    "Operaciones:\n"+expresiones,
                    JOptionPane.INFORMATION_MESSAGE);

            n=Integer.parseInt(seleccion.toString());

        }while(n>=expresiones.size());

        VerificarExpresion expresion=new VerificarExpresion(tabla.get(n).expr);

        if(expresion.isBad){
            JOptionPane.showMessageDialog(null,
                    "La expresion analizada contiene errores de sintaxis"
                    , "Expresion", JOptionPane.ERROR_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null,
                    "La expresion analizada NO contiene errores de sintaxis"
                    , "Expresion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void conversion(File file) throws IOException{
        AnalizadorOperacionesAritmeticas operaciones=new AnalizadorOperacionesAritmeticas();

        ArrayList<OperacionAritmetica> tabla=operaciones.analisisOperaciones(file);

        ArrayList<String> expresiones=new ArrayList<>();

        for(int i=0;i<tabla.size();i++){
            expresiones.add(i+ " : " +tabla.get(i).expr +"\n");
        }
        int n=0;

        do {
            Object seleccion=JOptionPane.showInputDialog(null,
                    "Operaciones:\n"+expresiones,
                    JOptionPane.INFORMATION_MESSAGE);

            n=Integer.parseInt(seleccion.toString());

        }while(n>=expresiones.size());

        ConversionInfijoPosPre expresion=new ConversionInfijoPosPre(tabla.get(n).expr);


        JOptionPane.showMessageDialog(null,
                expresion.prefijo.toString()
                    , "Prefijo", JOptionPane.ERROR_MESSAGE);

        JOptionPane.showMessageDialog(null,
                expresion.posfijo.toString()
                , "Posfijo", JOptionPane.ERROR_MESSAGE);

    }

    public void confirmacionExito(int num, String tipo){
        if(num>0){
            JOptionPane.showMessageDialog(null, "EL codigo analizado tiene "+num+ " "+tipo
                    , "Tabla de "+tipo+" creada correctamente", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public static void main(String[] args) {
        InterfazMenu IM = new InterfazMenu();
        IM.setVisible(true);
    }

}
