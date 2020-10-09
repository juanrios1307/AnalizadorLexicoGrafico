package principal;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InterfazMenu extends JFrame {
    public JPanel panel;


    private final JFileChooser selector = new JFileChooser();
    private File codigoAnalizar;


    public InterfazMenu() {
        this.setSize(800, 550); // Establecemos el tamaño de la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Acción que se realiza la cerrar la ventana
        this.setTitle("Taller 2"); // Establecmos el titulo de la ventana
        this.setLocationRelativeTo(null); // Establecemos donde aparece la ventana dentro de la pantalla que en este
        // caso es en en el centro
        this.getContentPane().setBackground(Color.WHITE);

        iniciarComponentes();

    }

    private void iniciarComponentes() {
        colocarPaneles();
        colocarEtiquetas();
        addTxtAndButton();
    }

    private void colocarPaneles() {
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
    }

    private void colocarEtiquetas() {

        JLabel etiqueta = new JLabel();
        etiqueta.setText("TALLER 2");
        etiqueta.setBounds(0, 5, 800, 60);
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        etiqueta.setFont(new Font("arial", 1, 35));
        etiqueta.setForeground(Color.BLACK);
        panel.add(etiqueta);

    }

    private void addTxtAndButton() {

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
                    tablaSimbolos(codigoAnalizar);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        panel.add(btnSimbolos);

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
                    tablaTokens(codigoAnalizar);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        panel.add(btnTokens);

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
                    tablaExpresionesAritmeticas(codigoAnalizar);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        });
        panel.add(btnExpresionesAritmeticas);

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
                    seleccionarArchivo();

                    if(codigoAnalizar.exists()){
                        btnTokens.setEnabled(true);
                        btnSimbolos.setEnabled(true);
                        btnExpresionesAritmeticas.setEnabled(true);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        panel.add(btnFile);


    }

    //Metodo recibirImagen

    public void seleccionarArchivo() throws IOException {
        selector.setDialogTitle("Selecciona el codigo a analizar");
        FileNameExtensionFilter archivo = new FileNameExtensionFilter("txt","txt" );
        selector.setFileFilter(archivo);
        int flag = selector.showOpenDialog(null);

        if(flag == JFileChooser.APPROVE_OPTION) {
            codigoAnalizar = selector.getSelectedFile();

        }
    }

    public void tablaTokens(File file) throws IOException {
        AnalizadorTablaTokens tokens=new AnalizadorTablaTokens(file);

        ArrayList<Token> tabla=tokens.leerAnalizarCodigo();
       // abrirarchivo(file);
        confirmacionExito(tabla.size(),"Tokens");
    }

    public void tablaSimbolos(File file) throws IOException{
        AnalizadorTablaSimbolos simbolos=new AnalizadorTablaSimbolos(file);

        ArrayList<Simbolo> tabla=simbolos.leerAnalizarCodigo();
      //  abrirarchivo(file);
        confirmacionExito(tabla.size(),"Simbolos");
    }

    public void tablaExpresionesAritmeticas(File file) throws IOException{
            AnalizadorOperacionesAritmeticas operaciones=new AnalizadorOperacionesAritmeticas();
            ArrayList<OperacionAritmetica> tabla=operaciones.analisisOperaciones(file);
            confirmacionExito(tabla.size(),"Operaciones");
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
