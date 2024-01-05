package GUI.Cliente;

import Objetos.Cliente;
import Objetos.Playlist;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ClientePlaylists extends JPanel implements MouseListener, ActionListener {
    private ArrayList<JButton> btnListaPlaylists;
    private JPanel panelPlaylists;
    private JScrollPane scrollPanePlaylists;
    private JLabel lblPlaylists;
    private JButton btnCriarPlaylist, btnCriadorAI, btnBiblioteca;
    private Cliente utilizadorAtual;
    private CriarPlaylistPanel panelCriarPlaylist;
    private JFrame frameCriarPlaylist, framePlaylistAI;
    private panelPlaylistAI panelPlaylistAI;
    private RockstarInc rockstar;
    private TabelaCliente tabelaCliente;
    private JFrame frame;

    /**Contrutor do JPanel que contém todos os elementos associados às playlists e à sua criação
     */
    public ClientePlaylists(RockstarInc rockstar,Cliente utilizadorAtual, JFrame frame) {
        this.utilizadorAtual=utilizadorAtual;
        this.rockstar=rockstar;
        this.frame=frame;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(resizeWidth(200), resizeHeight(300));
        setLayout(null);

        mudarCorRGB(this,0,70,112);

        Font font = new Font("SansSerif", Font.BOLD, 12);
        lblPlaylists = new JLabel("\uD83C\uDFB6 Playlists:");
        lblPlaylists.setFont(font);
        lblPlaylists.setForeground(new Color(255, 195, 0));
        lblPlaylists.setBounds(resizeWidth(10), resizeHeight(0), resizeWidth(90), resizeHeight(30));
        add(lblPlaylists);

        Font font1 = new Font("SansSerif", Font.BOLD, 11);
        btnCriarPlaylist = new JButton("Criar Playlist \uD83C\uDFB5");
        btnCriarPlaylist.setFont(font1);
        btnCriarPlaylist.setBounds(lblPlaylists.getX(), resizeHeight(250), resizeWidth(120), resizeHeight(25));
        btnCriarPlaylist.addActionListener(this);
        add(btnCriarPlaylist);

        btnCriadorAI = new JButton("Criar Playlist AI \uD83E\uDD16");
        btnCriadorAI.setFont(font1);
        btnCriadorAI.setBounds(btnCriarPlaylist.getX(), btnCriarPlaylist.getY() + resizeHeight(45), btnCriarPlaylist.getWidth() + resizeWidth(30), btnCriarPlaylist.getHeight());
        btnCriadorAI.addActionListener(this);
        add(btnCriadorAI);

        Font font2 = new Font("SansSerif", Font.BOLD, 12);
        btnBiblioteca = new JButton("Biblioteca \uD83D\uDCC2");
        btnBiblioteca.setFont(font2);
        btnBiblioteca.setBounds(btnCriadorAI.getX(), btnCriadorAI.getY() + resizeHeight(165), resizeWidth(110), resizeHeight(25));
        add(btnBiblioteca);

        ////////////////////// Playlists Ficticias///////////////////////////////////

        btnListaPlaylists=new ArrayList<>();
        panelPlaylists = new JPanel();


        //BoxLayout é bom quando queremos organizar os elementos numa só direcçao, neste caso vamos adiciona-los na direcçao vertical
        panelPlaylists.setLayout(new BoxLayout(panelPlaylists,BoxLayout.Y_AXIS));

        printPlaylists(this.utilizadorAtual.getPlaylistsProprias());

        ///////////////////////Criação da frame de criar playlist/////////////////////////////////////
        frameCriarPlaylist=new JFrame("Nova Playlist");
        panelCriarPlaylist=new CriarPlaylistPanel(rockstar,utilizadorAtual,frameCriarPlaylist,this);
        frameCriarPlaylist.setLayout(null);
        frameCriarPlaylist.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frameCriarPlaylist.setSize(resizeWidth(300),resizeHeight(250));
        frameCriarPlaylist.setLocationRelativeTo(null);
        frameCriarPlaylist.setResizable(false);
        panelCriarPlaylist.getBtnCriar().addActionListener(this);
        panelCriarPlaylist.getBtnCancelar().addActionListener(this);

        frameCriarPlaylist.setVisible(false);
        panelCriarPlaylist.setBounds(0,0,frameCriarPlaylist.getWidth(),frameCriarPlaylist.getHeight());
        frameCriarPlaylist.add(panelCriarPlaylist);

        frameCriarPlaylist.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setEnabled(true);
            }
        });

        ////////////////////////////////////////////////////////////

        //////////////Criaçao da frame PlaylistAI/////////////////////////////////

        framePlaylistAI=new JFrame("Playlist AI");
        framePlaylistAI.setLayout(null);
        framePlaylistAI.setLocationRelativeTo(null);
        framePlaylistAI.setSize(resizeWidth(320),resizeHeight(220));
        framePlaylistAI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        framePlaylistAI.setResizable(false);
        panelPlaylistAI=new panelPlaylistAI(utilizadorAtual,this, framePlaylistAI,rockstar);
        panelPlaylistAI.setBounds(0,0,framePlaylistAI.getWidth(),framePlaylistAI.getHeight());

        panelPlaylistAI.getBtnCancelar().addActionListener(this);
        panelPlaylistAI.getBtnCriar().addActionListener(this);
        framePlaylistAI.add(panelPlaylistAI);
        framePlaylistAI.setVisible(false);
        framePlaylistAI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setEnabled(true);
            }
        });

        ////////////////////////////////////////////////////////////////////////////

        scrollPanePlaylists = new JScrollPane(panelPlaylists);
        scrollPanePlaylists.setBounds(resizeWidth(10), lblPlaylists.getY() + resizeHeight(30), resizeWidth(175), resizeHeight(150));
        scrollPanePlaylists.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(scrollPanePlaylists);
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


    public JButton getBtnBiblioteca() {
        return btnBiblioteca;
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
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    private void mudarCorRGB(Component componente,int red,int green,int blue){ float[] cor = new float[3];
        cor = Color.RGBtoHSB(red, green, blue, cor);
        componente.setBackground(Color.getHSBColor(cor[0], cor[1], cor[2]));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked=e.getSource();

        if (clicked==btnCriarPlaylist){
            frameCriarPlaylist.setLocationRelativeTo(null);
            frameCriarPlaylist.setVisible(true);
            frame.setEnabled(false);
        }
        else if(clicked==panelCriarPlaylist.getBtnCriar()) {

            if (panelCriarPlaylist.getTxtNome().getText().isEmpty()){
                JOptionPane.showMessageDialog(panelCriarPlaylist,"O nome inserido é inválido ");
            }
            else {
                if (panelCriarPlaylist.getCheckVisibilidade().isSelected()) {
                    Playlist playlist = new Playlist(panelCriarPlaylist.getTxtNome().getText(), true);
                    rockstar.addPlaylist(playlist);
                    utilizadorAtual.addPlaylist(playlist);
                    printPlaylists(utilizadorAtual.getPlaylistsProprias());
                    panelCriarPlaylist.getTxtNome().setText("");
                    frame.setEnabled(true);
                    frameCriarPlaylist.dispatchEvent(new WindowEvent(frameCriarPlaylist, WindowEvent.WINDOW_CLOSING));
                    tabelaCliente.setPanelPlaylists(this);
                    tabelaCliente.addPlaylistPopMenu(playlist);
                    tabelaCliente.getMenuBiblioteca11().add(new JMenuItem(playlist.getNome()));
                    tabelaCliente.getMenuBiblioteca1().add(tabelaCliente.getMenuBiblioteca11().get(tabelaCliente.getMenuBiblioteca11().size()-1));
                    tabelaCliente.updateActionsListeners();
                    JOptionPane.showMessageDialog(frameCriarPlaylist, "A playlist " + playlist.getNome() + " foi criada");

                } else {
                    Playlist playlist = new Playlist(panelCriarPlaylist.getTxtNome().getText(), false);
                    rockstar.addPlaylist(playlist);
                    utilizadorAtual.addPlaylist(playlist);
                    printPlaylists(utilizadorAtual.getPlaylistsProprias());
                    panelCriarPlaylist.getTxtNome().setText("");
                    frame.setEnabled(true);
                    frameCriarPlaylist.dispatchEvent(new WindowEvent(frameCriarPlaylist, WindowEvent.WINDOW_CLOSING));
                    tabelaCliente.setPanelPlaylists(this);
                    tabelaCliente.addPlaylistPopMenu(playlist);
                    tabelaCliente.getMenuBiblioteca11().add(new JMenuItem(playlist.getNome()));
                    tabelaCliente.getMenuBiblioteca1().add(tabelaCliente.getMenuBiblioteca11().get(tabelaCliente.getMenuBiblioteca11().size()-1));
                    tabelaCliente.updateActionsListeners();
                    JOptionPane.showMessageDialog(frameCriarPlaylist, "A playlist " + playlist.getNome() + " foi criada");
                }

            }
        }
        else if (clicked==panelCriarPlaylist.getBtnCancelar()){
            frame.setEnabled(true);
            frameCriarPlaylist.dispatchEvent(new WindowEvent(frameCriarPlaylist,WindowEvent.WINDOW_CLOSING));
        }
        else if(clicked==btnCriadorAI){
            frame.setEnabled(false);
            framePlaylistAI.setLocationRelativeTo(null);
            framePlaylistAI.setVisible(true);
        }
        else if (clicked==panelPlaylistAI.getBtnCriar()) {
            String comboGenero = (String) panelPlaylistAI.getCmbGenero().getSelectedItem();
            boolean selectedVisibilidade;
            if (panelPlaylistAI.getCheckVisibilidade().isSelected()) {
                selectedVisibilidade = true;
            } else selectedVisibilidade = false;

            try {
                if (panelPlaylistAI.getTxtNumeroMusicas().getText().isEmpty() || panelPlaylistAI.getTxtNomePlaylist().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(panelPlaylistAI, "Os dados inseridos são inválidos");
                } else {
                    if (Integer.valueOf(panelPlaylistAI.getTxtNumeroMusicas().getText()) > 0) {
                        Playlist playlistAI = utilizadorAtual.criaPlaylistAI(panelPlaylistAI.getTxtNomePlaylist().getText(), Integer.valueOf(panelPlaylistAI.getTxtNumeroMusicas().getText()), comboGenero, selectedVisibilidade);
                        if (playlistAI.getMusicas().size() > 0) {
                            if (!utilizadorAtual.verificarQtdMusicas(playlistAI, Integer.valueOf(panelPlaylistAI.getTxtNumeroMusicas().getText()))) {
                                JOptionPane.showMessageDialog(panelPlaylistAI, "Quantidade de músicas insuficientes. A Playlist foi criada com " + playlistAI.getMusicas().size() + " música(s)");
                            }
                            utilizadorAtual.addPlaylist(playlistAI);
                            rockstar.addPlaylist(playlistAI);
                            printPlaylists(utilizadorAtual.getPlaylistsProprias());
                            tabelaCliente.setPanelPlaylists(this);
                            panelPlaylistAI.getTxtNomePlaylist().setText("");
                            panelPlaylistAI.getTxtNumeroMusicas().setText("");
                            tabelaCliente.addPlaylistPopMenu(playlistAI);
                            tabelaCliente.getMenuBiblioteca11().add(new JMenuItem(playlistAI.getNome()));
                            tabelaCliente.getMenuBiblioteca1().add(tabelaCliente.getMenuBiblioteca11().get(tabelaCliente.getMenuBiblioteca11().size() - 1));
                            tabelaCliente.updateActionsListeners();
                            frame.setEnabled(true);
                            framePlaylistAI.dispatchEvent(new WindowEvent(framePlaylistAI, WindowEvent.WINDOW_CLOSING));
                        }
                        else JOptionPane.showMessageDialog(frame,"Não tem músicas deste género");
                    }else JOptionPane.showMessageDialog(panelPlaylistAI, "Insira um número de músicas válido");
                }
            }
            catch(NumberFormatException j){
                JOptionPane.showMessageDialog(tabelaCliente,"Os dados inseridos são inválidos");
            }
        }
        else if(clicked==panelPlaylistAI.getBtnCancelar()){
            frame.setEnabled(true);
            framePlaylistAI.dispatchEvent(new WindowEvent(framePlaylistAI,WindowEvent.WINDOW_CLOSING));
        }
    }

    public void printPlaylists(ArrayList<Playlist> playlists){
        panelPlaylists.removeAll();
        btnListaPlaylists.clear();
        panelPlaylists.revalidate();
        panelPlaylists.repaint();

        for (int i = 0; i < playlists.size(); i++) {

            Font font = new Font("SansSerif", Font.BOLD, 12);
            btnListaPlaylists.add(new JButton(playlists.get(i).getNome()));
            btnListaPlaylists.get(i).setFont(font);
            btnListaPlaylists.get(i).setBorderPainted(false);
            mudarCorRGB(btnListaPlaylists.get(i),238,238,238);

            System.out.println(playlists.get(i).getNome());
            panelPlaylists.add(btnListaPlaylists.get(i));
        }
    }

    public ArrayList<JButton> getBtnListaPlaylists() {
        return btnListaPlaylists;
    }

    public void setTabelaCliente(TabelaCliente tabelaCliente) {
        this.tabelaCliente = tabelaCliente;
    }

    public void setTabelaPanelAi(TabelaCliente tabela){
        this.panelPlaylistAI.setTabelaCliente(tabela);
    }
}

