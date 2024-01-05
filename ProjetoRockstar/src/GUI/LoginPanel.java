package GUI;

import GUI.Cliente.InterfaceCliente;
import Objetos.Artista;
import Objetos.Cliente;
import Objetos.RockstarInc;
import Objetos.Utilizador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel implements MouseListener, ActionListener {
    private JLabel lblUsername, lblPass;
    private JLabel lblEmpresa;
    private JTextField txtUsername;
    private JPasswordField txtPass;
    private JButton btnLogin,btnRegisto;
    private JCheckBox checkPass;
    private RockstarInc rockstar;
    private InterfaceCliente panelCliente;
    private JFrame frame,framePinArtista;
    private LoginPin loginPin;
    private Utilizador utilizadorAtual;

    /**
     *Construtor da JPanel inicial da aplicação que contem como elementos principais duas caixas de texto para inserir o username e a password do utilizador,
     *  um botão para validar os dados inseridos, e um outro botão para abrir um JPanel de registo para um novo utilizador
     */

    public LoginPanel(RockstarInc rockstar, JFrame frame){
        this.rockstar=rockstar;
        this.frame=frame;

        Font font1=new Font("SansSererif",Font.BOLD | Font.ITALIC,35);
        lblEmpresa=new JLabel("Rockstar");
        lblEmpresa.setForeground(new Color(255,195,0));
        lblEmpresa.setFont(font1);
        lblEmpresa.setBounds(resizeWidth(175),resizeHeight(10),resizeWidth(250),resizeHeight(50));
        add(lblEmpresa);

        mudarCorRGB(this,0,29,61);

        Font font=new Font("SansSerif",Font.BOLD,13);
        lblUsername=new JLabel("Username: ");
        lblUsername.setForeground(new Color(255,195,0));
        lblUsername.setFont(font);
        lblPass=new JLabel("          Pass: ");
        lblPass.setFont(font);
        lblPass.setForeground(new Color(255,195,0));

        txtPass =new JPasswordField(18);
        txtPass.setFont(font);
        txtPass.setHorizontalAlignment(JPasswordField.CENTER);
        checkPass=new JCheckBox();

        txtUsername=new JTextField(18);
        txtUsername.setFont(font);
        txtUsername.setHorizontalAlignment(JTextField.CENTER);
        //txtPass=new JTextField(18);
        //txtPass.setHorizontalAlignment(JTextField.CENTER);
        //txtPass.setFont(font);

        setLayout(null);

        lblUsername.setBounds(resizeWidth(115), resizeHeight(100), resizeWidth(90), resizeHeight(20));
        add(lblUsername);

        txtUsername.setBounds(lblUsername.getX()+resizeWidth(80),lblUsername.getY(),resizeWidth(150),resizeHeight(25));
        add(txtUsername);

        lblPass.setBounds(lblUsername.getX(),lblUsername.getY()+ resizeHeight(40),lblUsername.getWidth(),lblUsername.getHeight());
        add(lblPass);

        txtPass.setBounds(txtUsername.getX(),txtUsername.getY()+resizeHeight(40),txtUsername.getWidth(),txtUsername.getHeight());
        add(txtPass);

        checkPass.setBounds(txtPass.getX()+ txtPass.getWidth()+resizeWidth(5), txtPass.getY(),resizeWidth(20),resizeWidth(20));
        mudarCorRGB(checkPass,142, 163, 148);
        checkPass.addActionListener(this);
        add(checkPass);

        //txtPass.setBounds(txtUsername.getX(),txtUsername.getY()+40,txtUsername.getWidth(),txtUsername.getHeight());
        //add(txtPass);

        btnRegisto=new JButton("Registo \uD83D\uDCDD");
        btnRegisto.setFont(font);
        btnRegisto.setBounds(resizeWidth(140),resizeHeight(210),resizeWidth(90),resizeHeight(40));
        mudarCorRGB(btnRegisto,170,210,220);
        btnRegisto.addMouseListener(this);

        btnLogin=new JButton("Login \uD83D\uDE80");
        btnLogin.setFont(font);
        btnLogin.setBounds(btnRegisto.getX()+btnRegisto.getWidth()+resizeWidth(30),btnRegisto.getY(),btnRegisto.getWidth(),btnRegisto.getHeight());
        mudarCorRGB(btnLogin,170,210,220);
        btnLogin.addMouseListener(this);
        btnLogin.addActionListener(this);

        add(btnLogin);
        add(btnRegisto);


        //Criar icon para a Rockstar//////////////////////////////////////////
        JLabel imagem=new JLabel();
        int witdh40=resizeWidth(40);
        int witdh30=resizeWidth(30);
        int height50=resizeHeight(50);
        int height30=resizeHeight(30);
        imagem.setBounds(lblEmpresa.getX()-witdh40,lblEmpresa.getY(),witdh30,height50);
        ImageIcon icon =new ImageIcon("rockstar.png");
        Image imagem2;
        imagem2=icon.getImage().getScaledInstance(witdh30,height30,Image.SCALE_SMOOTH);
        imagem.setIcon(new ImageIcon(imagem2));
        add(imagem);
        ////////////////////////////////////////////////////////////////

    }

    public JPasswordField getTxtPass() {
        return txtPass;
    }
    public JButton getRegisto() {
        return btnRegisto;
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

        if (clicked==btnLogin){
            mudarCorRGB(btnLogin,170,210,220);
        }
        else if (clicked==btnRegisto){
            mudarCorRGB(btnRegisto,170,210,220);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object clicked=e.getSource();

        if (clicked==btnLogin){
            mudarCorRGB(btnLogin,100,130,140);
        }
        else if (clicked==btnRegisto){
            mudarCorRGB(btnRegisto,100,130,140);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object clicked=e.getSource();

        if (clicked==btnLogin){
            mudarCorRGB(btnLogin,170,210,220);
        }
        else if (clicked==btnRegisto){
            mudarCorRGB(btnRegisto,170,210,220);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked=e.getSource();
        if (clicked==btnLogin) {
            String username=new String(txtUsername.getText());

            if (rockstar.verificarUtilizador(username)==null){
                JOptionPane.showMessageDialog(this,"Os dados introduzidos não estão corretos");
            }
            else {
                String pass = new String(txtPass.getPassword());
                utilizadorAtual= rockstar.verificarUtilizador(txtUsername.getText());
                if (utilizadorAtual.verificaLoginPass(pass)){
                    if (utilizadorAtual instanceof Cliente){
                        ((Cliente) utilizadorAtual).limparCarrinho();
                        setPanelClienteVisible(rockstar,(Cliente)utilizadorAtual);
                    }
                    else {
                        frame.setEnabled(false);
                        setFramePinArtistaVisible((Artista)utilizadorAtual, frame);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this,"Os dados introduzidos não estão corretos");
                    System.out.println("2");
                }
            }
        }
        else if (clicked==checkPass) {
            if (checkPass.isSelected()) {
                txtPass.setEchoChar((char) 0);
            } else {
                txtPass.setEchoChar('•');
            }
        }
    }

    /**Método que exibe a Interface do Cliente
     */
    private void setPanelClienteVisible(RockstarInc rockstar,Cliente cliente){
        panelCliente=new InterfaceCliente(rockstar,cliente,frame,this);
        frame.setSize(resizeWidth(950),resizeHeight(650));
        panelCliente.setBounds(0,0,frame.getWidth(),frame.getHeight());
        frame.setLocationRelativeTo(null);
        frame.add(panelCliente);
        panelCliente.setVisible(true);
        this.setVisible(false);
        txtUsername.setText("");
        txtPass.setText("");
    }

    /**Método que exibe a Interface do Artista
     */
    private void setFramePinArtistaVisible(Artista artista, JFrame frame){
        framePinArtista =new JFrame("PIN");
        framePinArtista.setSize(resizeWidth(250),resizeHeight(200));
        framePinArtista.setLayout(null);
        framePinArtista.setLocationRelativeTo(null);
        framePinArtista.setResizable(false);
        loginPin=new LoginPin(rockstar,artista, frame, framePinArtista, this);
        loginPin.setBounds(0,0, framePinArtista.getWidth(), frame.getHeight());
        framePinArtista.add(loginPin);
        framePinArtista.setVisible(true);
        txtUsername.setText("");
        txtPass.setText("");
        framePinArtista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setEnabled(true);
            }
        });
        framePinArtista.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
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

    private void mudarCorTxtoRGB(Component componente,int red,int green,int blue){ float[] cor = new float[3];
        cor = Color.RGBtoHSB(red, green, blue, cor);
        componente.setForeground(Color.getHSBColor(cor[0], cor[1], cor[2]));
    }
}


