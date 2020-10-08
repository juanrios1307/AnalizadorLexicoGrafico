package principal;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InterfazMenu extends JFrame {
    public JPanel panel;

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
        etiqueta.setForeground(Color.CYAN);
        panel.add(etiqueta);

    }

    private void addTxtAndButton() {

        JButton btnIngr = new JButton();
        btnIngr.setBounds(155, 105, 500, 60);
        btnIngr.setText("Cargar un archivo");
        btnIngr.setEnabled(true);
        btnIngr.setBackground(Color.darkGray);
        btnIngr.setForeground(Color.orange);
        btnIngr.setFont(new Font("arial", 3, 20));
        btnIngr.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                setVisible(false);
            }
        });
        panel.add(btnIngr);

        JButton btnGenr = new JButton();
        btnGenr.setBounds(155, 305, 500, 60);
        btnGenr.setText("Identificar elementos Tabla Simbolos");
        btnGenr.setEnabled(true);
        btnGenr.setFont(new Font("arial", 3, 20));
        btnGenr.setBackground(Color.darkGray);
        btnGenr.setForeground(Color.orange);
        btnGenr.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        panel.add(btnGenr);

        JButton btnDisp = new JButton();
        btnDisp.setBounds(155, 405, 500, 60);
        btnDisp.setText("Identificar elementos Tabla Tokens");
        btnDisp.setEnabled(true);
        btnDisp.setFont(new Font("arial", 3, 20));
        btnDisp.setBackground(Color.darkGray);
        btnDisp.setForeground(Color.orange);
        btnDisp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                setVisible(false);
            }
        });
        panel.add(btnDisp);

        JButton btnBuscar = new JButton();
        btnBuscar.setBounds(155, 205, 500, 60);
        btnBuscar.setText("Identificar Expresiones Aritmeticas");
        btnBuscar.setEnabled(true);
        btnBuscar.setFont(new Font("arial", 3, 20));
        btnBuscar.setBackground(Color.darkGray);
        btnBuscar.setForeground(Color.orange);
        btnBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }

        });
        panel.add(btnBuscar);
    }

    public static void main(String[] args) {
        InterfazMenu IM = new InterfazMenu();
        IM.setVisible(true);
    }

}
