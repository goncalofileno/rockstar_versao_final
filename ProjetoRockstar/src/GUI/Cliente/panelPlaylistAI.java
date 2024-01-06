package GUI.Cliente;

import Objetos.Cliente;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelPlaylistAI extends JPanel implements ActionListener {
    private JLabel lblNomePlaylist,lblNumeroMusicas,lblGenero;
    private JCheckBox checkVisibilidade;
    private JComboBox cmbGenero;
    private JButton btnCancelar,btnCriar;
    private JTextField txtNomePlaylist,txtNumeroMusicas;
    private Cliente utilizadorAtual;
    private ClientePlaylists panelPlaylists;
    private JDialog frame;
    private TabelaCliente tabelaCliente;
    private RockstarInc rockstar;

    /**Construtor do JPanel que surge quando o botão "Criar Playlist AI" é carregado. Neste JPanel é possivel inserir o nome da playlist, a sua visibilidade,
     *  o número de músicas que esta deve conter e o género pretentido para as músicas inseridas
     */
    public panelPlaylistAI(Cliente utilizadorAtual, ClientePlaylists panelPlaylists, JDialog frame, RockstarInc rockstar) {
        this.panelPlaylists=panelPlaylists;
        this.utilizadorAtual=utilizadorAtual;
        this.frame=frame;
        this.rockstar=rockstar;

        setLayout(null);

        Font font = new Font("SansSerif", Font.BOLD, resizeHeight(12));
        lblNomePlaylist=new JLabel("Nome da Playlist");
        lblNomePlaylist.setFont(font);
        lblNomePlaylist.setBounds(resizeWidth(5),resizeHeight(5),resizeWidth(120),resizeHeight(25));
        add(lblNomePlaylist);

        txtNomePlaylist=new JTextField(30);
        txtNomePlaylist.setFont(font);
        txtNomePlaylist.setBounds(lblNomePlaylist.getX()+lblNomePlaylist.getWidth(),lblNomePlaylist.getY(),resizeWidth(100),resizeHeight(30));
        add(txtNomePlaylist);

        checkVisibilidade=new JCheckBox("Pública");
        checkVisibilidade.setFont(font);
        checkVisibilidade.setBounds(lblNomePlaylist.getX(),lblNomePlaylist.getY()+lblNomePlaylist.getHeight()+resizeHeight(10),resizeWidth(90),resizeHeight(30));
        add(checkVisibilidade);

        lblNumeroMusicas=new JLabel("Nº de músicas");
        lblNumeroMusicas.setFont(font);
        lblNumeroMusicas.setBounds(lblNomePlaylist.getX(),checkVisibilidade.getY()+checkVisibilidade.getHeight()+resizeHeight(6),resizeWidth(100),resizeHeight(30));
        add(lblNumeroMusicas);

        txtNumeroMusicas=new JTextField(15);
        txtNumeroMusicas.setFont(font);
        txtNumeroMusicas.setBounds(lblNumeroMusicas.getX()+lblNumeroMusicas.getWidth()+resizeWidth(4),checkVisibilidade.getY()+checkVisibilidade.getHeight()+resizeHeight(10),resizeWidth(30),resizeHeight(30));
        add(txtNumeroMusicas);

        lblGenero=new JLabel("Género");
        lblGenero.setFont(font);
        lblGenero.setBounds(txtNumeroMusicas.getX()+txtNumeroMusicas.getWidth()+resizeWidth(10),txtNumeroMusicas.getY(),resizeWidth(50),resizeHeight(30));
        add(lblGenero);

        String[] generos={"Rock 🎸","Pop 🎤","Rap 🔫","Clássica 🎻","Jazz 🎺","Metal 🤘","Popular 🥁","Eletrónica 🎧"};
        cmbGenero=new JComboBox(generos);
        cmbGenero.setFont(font);
        cmbGenero.setBounds(lblGenero.getX()+lblGenero.getWidth()+resizeWidth(4),lblGenero.getY(),resizeWidth(100),resizeHeight(30));
        add(cmbGenero);

        btnCancelar=new JButton("Cancelar");
        btnCancelar.setFont(font);
        btnCancelar.setBounds(lblNumeroMusicas.getX()+resizeWidth(30),lblNumeroMusicas.getY()+lblNumeroMusicas.getHeight()+resizeHeight(22),resizeWidth(95),resizeHeight(25));
        btnCancelar.addActionListener(this);
        add(btnCancelar);

        btnCriar=new JButton("Criar");
        btnCriar.setFont(font);
        btnCriar.setBounds(btnCancelar.getX()+btnCancelar.getWidth()+resizeWidth(30),btnCancelar.getY(),btnCancelar.getWidth(),btnCancelar.getHeight());
        btnCriar.addActionListener(this);
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
        Object clicked=e.getSource();
    }

    public void setTabelaCliente(TabelaCliente tabelaCliente) {
        this.tabelaCliente = tabelaCliente;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnCriar() {
        return btnCriar;
    }

    public JCheckBox getCheckVisibilidade() {
        return checkVisibilidade;
    }

    public JComboBox getCmbGenero() {
        return cmbGenero;
    }

    public JTextField getTxtNomePlaylist() {
        return txtNomePlaylist;
    }

    public JTextField getTxtNumeroMusicas() {
        return txtNumeroMusicas;
    }
}
