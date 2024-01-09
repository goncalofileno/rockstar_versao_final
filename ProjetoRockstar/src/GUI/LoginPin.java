package GUI;

import GUI.Artista.InterfaceArtista;
import Objetos.Album;
import Objetos.Artista;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginPin extends JPanel implements MouseListener, ActionListener {
    private JLabel lblPin;
    private JPasswordField txtPin;
    private JButton btnValidar;
    private JCheckBox checkPin;
    private Artista utilizadorAtual;
    private JFrame frame, frameArtista;
    private LoginPanel panelLogin;
    private RockstarInc rockstar;
    private InterfaceArtista interfaceArtista;

    /**
     * Construtor para criar a JPanel LoginPin do artista
     */

    public LoginPin(RockstarInc rockstar,Artista utilizadorAtual, JFrame frame, JFrame frameArtista, LoginPanel panelLogin){

        this.utilizadorAtual =utilizadorAtual;
        this.frame=frame;
        this.frameArtista=frameArtista;
        this.panelLogin=panelLogin;
        this.rockstar=rockstar;

        ArrayList< Album> albuns=utilizadorAtual.getAlbuns();

        mudarCorRGB(this,0, 70, 112);
        setLayout(null);
        Font font=new Font("SansSerif",Font.BOLD,13);
        lblPin=new JLabel("Pin:");
        lblPin.setFont(font);
        lblPin.setForeground(new Color(255,195,0));
        lblPin.setBounds(resizeWidth(45),resizeHeight(45),resizeWidth(30),resizeHeight(20));
        add(lblPin);

        txtPin =new JPasswordField(18);
        txtPin.setFont(font);
        txtPin.setBounds(lblPin.getX()+lblPin.getWidth()+resizeWidth(2),lblPin.getY(),resizeWidth(100),resizeHeight(20));
        txtPin.setHorizontalAlignment(JPasswordField.CENTER);
        add(txtPin);

        btnValidar=new JButton("Validar");
        btnValidar.setFont(font);
        btnValidar.setBounds(resizeWidth(80),resizeHeight(100),resizeWidth(90),resizeHeight(40));
        mudarCorRGB(btnValidar,170,210,220);
        btnValidar.addMouseListener(this);
        btnValidar.addActionListener(this);
        add(btnValidar);

        checkPin=new JCheckBox();
        checkPin.setBounds(txtPin.getX()+ txtPin.getWidth()+resizeWidth(5), txtPin.getY(),resizeWidth(20),resizeHeight(20));
        mudarCorRGB(checkPin,0, 70, 112);
        checkPin.addActionListener(this);
        add(checkPin);

        //Criar icon para a Rockstar/////
        JLabel imagem=new JLabel();
        imagem.setBounds(resizeWidth(210),resizeHeight(10),resizeWidth(20),resizeHeight(20));
        ImageIcon icon =new ImageIcon("rockstar.png");
        Image imagem2;
        imagem2=icon.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        imagem.setIcon(new ImageIcon(imagem2));
        add(imagem);
        /////////////////////////////////

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        float[] cor2 =new float[3];
        cor2=Color.RGBtoHSB(100,130,140,cor2);
        btnValidar.setBackground(Color.getHSBColor(cor2[0],cor2[1],cor2[2]));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        float[] cor2 =new float[3];
        cor2=Color.RGBtoHSB(170,210,220,cor2);
        btnValidar.setBackground(Color.getHSBColor(cor2[0],cor2[1],cor2[2]));

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked=e.getSource();

        if (checkPin.isSelected()){
            txtPin.setEchoChar((char)0);
        }
        else {
            txtPin.setEchoChar('•');
        }
        if (clicked==btnValidar){
            String pin=new String(txtPin.getPassword());
            if (utilizadorAtual.verificarLoginPin(pin)){
                panelLogin.setVisible(false);
                interfaceArtista=new InterfaceArtista(rockstar,utilizadorAtual,frame,panelLogin);
                frame.setSize(resizeWidth(950),resizeHeight(650));
                frame.setLocationRelativeTo(null);
                interfaceArtista.setBounds(0,0,frame.getWidth(),frame.getHeight());
                frame.add(interfaceArtista);
                frame.setEnabled(true);
                frameArtista.dispatchEvent(new WindowEvent(frameArtista,WindowEvent.WINDOW_CLOSING));
            }
            else JOptionPane.showMessageDialog(this,"O PIN introduzido está incorreto 😔");
        }
    }

    public JTextField getTxtPin() {
        return txtPin;
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
