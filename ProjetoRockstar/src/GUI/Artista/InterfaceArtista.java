package GUI.Artista;

import GUI.LoginPanel;
import Objetos.Artista;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Construtor do JPanel principal do Artista que tem todos os elementos necessários para a aplicação funcionar com sucesso
 */
public class InterfaceArtista extends JPanel implements ActionListener {
    private RockstarInc rockstar;
    private Artista utilizadorAtual;
    private ArtistaAlbuns panelAlbuns;
    private JLabel lblUser,lblTabela;
    private TabelaArtista tabelaArtista;
    private JButton btnLogout;
    private  JFrame frame;
    private LoginPanel panelLogin;
    private EstatisticasArtista estatisticasArtista;
    private PesquisaArtista pesquisaArtista;

    public InterfaceArtista(RockstarInc rockstar, Artista utilizadorAtual, JFrame frame, LoginPanel panelLogin){
        this.rockstar=rockstar;
        this.utilizadorAtual=utilizadorAtual;
        this.frame=frame;
        this.panelLogin=panelLogin;

        mudarCorRGB(this,0,29,61);
        setLayout(null);

        estatisticasArtista=new EstatisticasArtista(rockstar,utilizadorAtual,frame);

        panelAlbuns=new ArtistaAlbuns(rockstar,utilizadorAtual,this, estatisticasArtista,frame);

        panelAlbuns.setBounds(resizeWidth(10),resizeHeight(50),resizeWidth(200),resizeHeight(500));
        estatisticasArtista.setBounds(resizeWidth(725),panelAlbuns.getY(),resizeWidth(200),resizeHeight(350));
        add(estatisticasArtista);
        add(panelAlbuns);

        Font font3=new Font("SansSerif",Font.BOLD,13);
        lblTabela=new JLabel("As minhas Músicas:");
        lblTabela.setFont(font3);
        lblTabela.setForeground(new Color(255,195,0));
        lblTabela.setBounds(panelAlbuns.getX()+panelAlbuns.getWidth()+resizeWidth(25),resizeHeight(20),resizeWidth(400),resizeHeight(20));
        add(lblTabela);


        lblUser=new JLabel("Artista: "+utilizadorAtual.getUsername());
        lblUser.setFont(font3);
        lblUser.setForeground(new Color(255,195,0));
        lblUser.setBounds(panelAlbuns.getX()+resizeWidth(10),lblTabela.getY(),resizeWidth(150),lblTabela.getHeight());
        add(lblUser);


        tabelaArtista=new TabelaArtista( rockstar, utilizadorAtual, panelAlbuns,this,frame);
        tabelaArtista.setBounds(lblTabela.getX(),panelAlbuns.getY(),resizeWidth(465),panelAlbuns.getHeight());
        add(tabelaArtista);

        panelAlbuns.setTabelaArtista(tabelaArtista);

        Font font4=new Font("SansSerif",Font.BOLD ,12);
        btnLogout=new JButton("Logout ⛔");
        btnLogout.setFont(font4);
        btnLogout.setBounds(resizeWidth(840),resizeHeight(565),resizeWidth(100),resizeHeight(30));
        btnLogout.addActionListener(this);
        add(btnLogout);

        pesquisaArtista =new PesquisaArtista(tabelaArtista,rockstar,this,utilizadorAtual);
        pesquisaArtista.setBounds(estatisticasArtista.getX(),estatisticasArtista.getY()+estatisticasArtista.getHeight()+resizeHeight(10),estatisticasArtista.getWidth(),resizeHeight(140));
        add(pesquisaArtista);
    }


    public void setLblTabela(String lblTabela) {
        this.lblTabela.setText(lblTabela);
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

    private void mudarCorRGB(Component componente, int red, int green, int blue){ float[] cor = new float[3];
        cor = Color.RGBtoHSB(red, green, blue, cor);
        componente.setBackground(Color.getHSBColor(cor[0], cor[1], cor[2]));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked=e.getSource();

        if(clicked==btnLogout){
            frame.setSize((resizeWidth(500)),resizeHeight(350));
            panelLogin.setVisible(true);
            setVisible(false);
            utilizadorAtual=null;
            frame.setLocationRelativeTo(null);
        }
    }

    public JLabel getLblTabela() {
        return lblTabela;
    }
}
