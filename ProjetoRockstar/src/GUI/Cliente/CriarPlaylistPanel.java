package GUI.Cliente;

import Objetos.Cliente;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarPlaylistPanel extends JPanel implements ActionListener {
    private JTextField txtNome;
    private JLabel lblNome;
    private JCheckBox checkVisibilidade;
    private RockstarInc rockstar;
    private Cliente utilizadorAtual;
    private JButton btnCriar,btnCancelar;
    private JDialog frame;
    private ClientePlaylists panelPlaylists;

    /**Construtor do JPanel que surge quando o botão "Criar Playlist" é acionado. Nesta JPanel é possível inserir o nome da playlist, e a sua visibilidade
     */
    public CriarPlaylistPanel(RockstarInc rockstar, Cliente utilizadorAtual,JDialog frame,ClientePlaylists panelPlaylists){
        this.rockstar=rockstar;
        this.utilizadorAtual=utilizadorAtual;
        this.frame=frame;
        this.panelPlaylists=panelPlaylists;

        setLayout(null);
        Font font=new Font("SansSerif",Font.BOLD,12);
        lblNome=new JLabel("Nome da Playlist");
        lblNome.setFont(font);
        lblNome.setBounds(resizeWidth(20),resizeHeight(20),resizeWidth(150),resizeHeight(25));
        add(lblNome);
        txtNome=new JTextField(20);
        txtNome.setBounds(lblNome.getX(),lblNome.getY()+lblNome.getHeight()+resizeHeight(2),resizeWidth(150),resizeHeight(30));
        add(txtNome);
        checkVisibilidade=new JCheckBox("Pública");
        checkVisibilidade.setBounds(txtNome.getX(),txtNome.getY()+txtNome.getHeight()+resizeHeight(5),resizeWidth(100),resizeHeight(40));
        add(checkVisibilidade);
        btnCriar=new JButton("Criar");
        btnCancelar=new JButton("Cancelar");
        btnCancelar.setBounds(checkVisibilidade.getX(),checkVisibilidade.getY()+checkVisibilidade.getHeight()+resizeHeight(5),resizeWidth(100),resizeHeight(30));
        btnCriar.setBounds(btnCancelar.getX()+btnCancelar.getWidth()+resizeWidth(10),btnCancelar.getY(), btnCancelar.getWidth(), btnCancelar.getHeight());
        btnCriar.setFont(font);
        btnCancelar.setFont(font);
        btnCriar.addActionListener(this);
        btnCancelar.addActionListener(this);
        add(btnCancelar);
        add(btnCriar);
    }
    private int resizeWidth(int width) {
        Dimension ecra = Toolkit.getDefaultToolkit().getScreenSize();
        int widthAtualizada;
        widthAtualizada = (int) (width * ecra.getWidth()) / 1536;
        return widthAtualizada;
    }

    private int resizeHeight(int height) {
        Dimension ecra = Toolkit.getDefaultToolkit().getScreenSize();
        int heightAtualizada;
        heightAtualizada = (int) (height * ecra.getHeight()) / 864;
        return heightAtualizada;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public JButton getBtnCriar() {
        return btnCriar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JCheckBox getCheckVisibilidade() {
        return checkVisibilidade;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }
}



