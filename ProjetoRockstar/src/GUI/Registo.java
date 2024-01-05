package GUI;

import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Registo extends JPanel implements ActionListener, MouseListener {
    private JLabel lblRegisto, lblUsername,lblPass,lblNome,lblPin;
    private JTextField txtUsername,txtPass,txtNome,txtPin;
    private JButton btnValidar, btnVoltarAtras;
    private JRadioButton radioBtnArtista, radioBtnCliente;
    private ButtonGroup grpRadioBtn;
    private RockstarInc rockstar;
    private LoginPanel panelLogin;
    private JFrame frame;

    public Registo(RockstarInc rockstar, LoginPanel panelLogin, JFrame frame){
        this.rockstar=rockstar;
        this.panelLogin=panelLogin;
        this.frame=frame;

        mudarCorRGB(this,0, 29, 61);
        setLayout(null);

        Font font=new Font("SansSerif",Font.BOLD,13);
        lblUsername=new JLabel("Username: ");
        lblUsername.setForeground(new Color(255,195,0));
        lblUsername.setFont(font);
        lblUsername.setBounds(resizeWidth(100),resizeHeight(70),resizeWidth(90),resizeHeight(20));
        add(lblUsername);

        lblPass=new JLabel("          Pass: ");
        lblPass.setFont(font);
        lblPass.setForeground(new Color(255,195,0));
        lblPass.setBounds(lblUsername.getX(),lblUsername.getY()+lblUsername.getHeight()+resizeHeight(20),lblUsername.getWidth(),lblUsername.getHeight());
        add(lblPass);

        lblNome=new JLabel("        Nome: ");
        lblNome.setFont(font);
        lblNome.setForeground(new Color(255,195,0));
        lblNome.setBounds(lblUsername.getX(),lblPass.getY()+lblPass.getHeight()+resizeHeight(20),lblUsername.getWidth(),lblUsername.getHeight());
        add(lblNome);

        lblPin=new JLabel("            PIN: ");
        lblPin.setFont(font);
        lblPin.setForeground(new Color(255,195,0));
        lblPin.setBounds(lblUsername.getX(),lblNome.getY()+lblUsername.getHeight()+resizeHeight(20),lblUsername.getWidth(),lblUsername.getHeight());
        lblPin.setVisible(false);
        add(lblPin);

        txtUsername=new JTextField(18);
        txtUsername.setFont(font);
        txtUsername.setBounds(lblUsername.getX()+lblUsername.getWidth()+resizeWidth(10),lblUsername.getY(),resizeWidth(200),lblUsername.getHeight());
        txtUsername.setHorizontalAlignment(JTextField.CENTER);
        add(txtUsername);

        txtPass=new JTextField(18);
        txtPass.setFont(font);
        txtPass.setBounds(txtUsername.getX(),lblPass.getY(),txtUsername.getWidth(),txtUsername.getHeight());
        txtPass.setHorizontalAlignment(JTextField.CENTER);
        add(txtPass);

        txtNome=new JTextField(18);
        txtNome.setFont(font);
        txtNome.setBounds(txtUsername.getX(),lblNome.getY(),txtUsername.getWidth(),txtUsername.getHeight());
        txtNome.setHorizontalAlignment(JTextField.CENTER);
        add(txtNome);

        txtPin=new JTextField(18);
        txtPin.setFont(font);
        txtPin.setBounds(txtUsername.getX(),lblPin.getY(),txtUsername.getWidth(),txtUsername.getHeight());
        txtPin.setHorizontalAlignment(JTextField.CENTER);
        txtPin.setVisible(false);
        add(txtPin);

        Font font2=new Font("SansSerif",Font.BOLD,20);
        lblRegisto=new JLabel("Registo");
        lblRegisto.setFont(font2);
        lblRegisto.setForeground(new Color(255,195,0));
        lblRegisto.setBounds(resizeWidth(20),resizeHeight(10),resizeWidth(100),resizeHeight(30));
        add(lblRegisto);

        btnValidar=new JButton("Registar \uD83D\uDCDD");
        btnValidar.setFont(font);
        btnValidar.setBounds(resizeWidth(205),txtPin.getY()+txtPin.getHeight()+resizeHeight(25),resizeWidth(110),resizeHeight(40));
        mudarCorRGB(btnValidar,170,210,220);
        btnValidar.addMouseListener(this);
        btnValidar.addActionListener(this);

        add(btnValidar);

        radioBtnCliente=new JRadioButton("Cliente");
        radioBtnCliente.setFont(font);
        radioBtnCliente.setForeground(new Color(255,195,0));
        radioBtnCliente.setBounds(resizeWidth(200),resizeHeight(20),resizeWidth(100),resizeHeight(20));
        radioBtnCliente.setSelected(true);
        mudarCorRGB(radioBtnCliente,0, 29, 61);
        radioBtnCliente.addActionListener(this);

        radioBtnArtista=new JRadioButton("Artista");
        radioBtnArtista.setFont(font);
        radioBtnArtista.setForeground(new Color(255,195,0));
        radioBtnArtista.setBounds(radioBtnCliente.getX()+radioBtnCliente.getWidth()+resizeWidth(20),radioBtnCliente.getY(),radioBtnCliente.getWidth(),radioBtnCliente.getHeight());
        mudarCorRGB(radioBtnArtista,0, 29, 61);
        radioBtnArtista.addActionListener(this);

        grpRadioBtn=new ButtonGroup();

        grpRadioBtn.add(radioBtnCliente);
        grpRadioBtn.add(radioBtnArtista);

        add(radioBtnCliente);
        add(radioBtnArtista);

        Font font3=new Font("SansSerif",Font.BOLD,12);
        btnVoltarAtras=new JButton("Voltar");
        btnVoltarAtras.setFont(font3);
        btnVoltarAtras.setBounds(resizeWidth(15),txtPin.getY()+txtPin.getHeight()+resizeHeight(55),resizeWidth(70),resizeHeight(28));
        mudarCorRGB(btnVoltarAtras,170,210,220);
        btnVoltarAtras.addMouseListener(this);
        add(btnVoltarAtras);


        //Criar icon para a Rockstar/////
        JLabel imagem=new JLabel();
        imagem.setBounds(resizeWidth(450),resizeHeight(0),resizeWidth(30),resizeHeight(50));
        ImageIcon icon =new ImageIcon("rockstar.png");
        Image imagem2;
        imagem2=icon.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
        imagem.setIcon(new ImageIcon(imagem2));
        add(imagem);
        /////////////////////////////////

    }

    public JButton getBtnVoltarAtras() {
        return btnVoltarAtras;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked=e.getSource();

        if (radioBtnCliente.isSelected()) {
            lblPin.setVisible(false);
            txtPin.setVisible(false);
        } else {
            lblPin.setVisible(true);
            txtPin.setVisible(true);
        }

        if (clicked==btnValidar){
            if (rockstar.verificarUsername(txtUsername.getText())) {
                if (rockstar.verificarPass(txtPass.getText())) {
                    if (txtNome.getText().length() > 1) {
                        if (radioBtnCliente.isSelected()) {
                            rockstar.addCliente(txtUsername.getText(), txtPass.getText(), txtNome.getText());
                            JOptionPane.showMessageDialog(frame, "Cliente adicionado com sucesso");
                            panelLogin.setVisible(true);
                            txtUsername.setText("");
                            txtPass.setText("");
                            txtNome.setText("");
                            this.setVisible(false);

                        } else if (radioBtnArtista.isSelected()){
                            if (rockstar.verificarPin(txtPin.getText())) {
                                rockstar.addArtista(txtUsername.getText(), txtPass.getText(), txtNome.getText(), txtPin.getText());
                                JOptionPane.showMessageDialog(frame, "Artista adicionado com sucesso");
                                panelLogin.setVisible(true);
                                txtUsername.setText("");
                                txtPass.setText("");
                                txtNome.setText("");
                                txtPin.setText("");
                                this.setVisible(false);
                            }
                            else JOptionPane.showMessageDialog(frame,"O pin deve conter 4 dígitos");
                        }
                    }
                    else JOptionPane.showMessageDialog(frame,"O nome inserido é inválido");
                }
                else JOptionPane.showMessageDialog(frame,"A password inserida tem de ter mais que 4 caracteres");
            }
            else JOptionPane.showMessageDialog(frame,"O username inserido é inválido");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object clicked=e.getSource();

        if (clicked==btnValidar) {
            mudarCorRGB(btnValidar,170,210,220);
        }
        else if(clicked==btnVoltarAtras){
            mudarCorRGB(btnVoltarAtras,170,210,220);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object clicked=e.getSource();

        if (clicked==btnValidar) {
            mudarCorRGB(btnValidar,100,130,140);
        }
        else if(clicked==btnVoltarAtras){
            mudarCorRGB(btnVoltarAtras,100,130,140);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object clicked=e.getSource();

        if (clicked==btnValidar) {
            mudarCorRGB(btnValidar,170,210,220);
        }
        else if(clicked==btnVoltarAtras){
            mudarCorRGB(btnVoltarAtras,170,210,220);
        }
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JTextField getTxtPin() {
        return txtPin;
    }

    public JButton getBtnValidar() {
        return btnValidar;
    }

    public JLabel getLblRegisto() {
        return lblRegisto;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public JTextField getTxtPass() {
        return txtPass;
    }

    private int resizeWidth(int width ){
        Dimension ecra=Toolkit.getDefaultToolkit().getScreenSize();
        int widthAtualizada;
        widthAtualizada=(int)(width*ecra.getWidth())/1536;
        return widthAtualizada;
    }

    private int resizeHeight(int height){
        Dimension ecra=Toolkit.getDefaultToolkit().getScreenSize();
        int heightAtualizada;
        heightAtualizada=(int)(height*ecra.getHeight())/864;
        return heightAtualizada;
    }

    private void mudarCorRGB(Component componente,int red,int green,int blue){ float[] cor = new float[3];
        cor = Color.RGBtoHSB(red, green, blue, cor);
        componente.setBackground(Color.getHSBColor(cor[0], cor[1], cor[2]));
    }
}
