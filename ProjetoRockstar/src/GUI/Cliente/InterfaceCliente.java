package GUI.Cliente;

import GUI.LoginPanel;
import Objetos.Cliente;
import Objetos.Compra;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InterfaceCliente extends JPanel implements ActionListener {
    private ClientePlaylists panelPlaylists;
    private PanelCarrinho panelCarrinho;
    private PesquisaPanel panelPesquisa;
    private JLabel lblUser,lblSaldo,lblTabela,lblAlterarVisibilidade;
    private JButton btnLoja, btnCarregar,btnCancelar,btnCarregar2, btnLogout;
    private JButton btnRemoverPlaylist,btnAlterarVisibilidade;
    private TabelaCliente tabelaCliente;
    private JDialog frmCarregamento;
    private JPanel panelCarregamento;
    private RockstarInc rockstar;
    private Cliente utilizadorAtual;
    private JTextField txtValor;
    private JFrame frame;
    private LoginPanel panelLogin;

    /**Construtor do JPanel principal do Cliente que tem todos os elementos necessários para a aplicação funcionar com sucesso
     */

    public InterfaceCliente(RockstarInc rockstar, Cliente utilizadorAtual, JFrame frame, LoginPanel panelLogin){
        this.rockstar=rockstar;
        this.utilizadorAtual=utilizadorAtual;
        this.frame=frame;
        this.panelLogin=panelLogin;

        mudarCorRGB(this,0, 29, 61);
        setLayout(null);

        panelPlaylists=new ClientePlaylists(rockstar,utilizadorAtual,frame);
        panelPlaylists.setBounds(resizeWidth(10),resizeHeight(50),resizeWidth(200),resizeHeight(500));
        add(panelPlaylists);

        Font font3=new Font("SansSerif",Font.BOLD,13);
        lblTabela=new JLabel("Biblioteca de músicas:");
        lblTabela.setFont(font3);
        lblTabela.setForeground(new Color(255, 195, 0));
        lblTabela.setBounds(panelPlaylists.getX()+panelPlaylists.getWidth()+resizeWidth(25),resizeHeight(20),resizeWidth(400),resizeHeight(20));
        add(lblTabela);

        panelCarrinho=new PanelCarrinho(utilizadorAtual);
        panelCarrinho.setBounds(resizeWidth(725),panelPlaylists.getY(),resizeWidth(200),resizeHeight(320));
        add(panelCarrinho);

        tabelaCliente=new TabelaCliente( rockstar, utilizadorAtual,panelPlaylists,this,frame);
        panelPlaylists.setTabelaCliente(tabelaCliente);
        tabelaCliente.setBounds(lblTabela.getX(),panelPlaylists.getY(),resizeWidth(465),panelPlaylists.getHeight());
        add(tabelaCliente);

        panelPlaylists.setTabelaPanelAi(tabelaCliente);

        tabelaCliente.setPanelCarrinho(panelCarrinho);

        setVisible(true);

        panelPesquisa =new PesquisaPanel(tabelaCliente, rockstar, this,utilizadorAtual);
        panelPesquisa.setBounds(panelCarrinho.getX(),panelCarrinho.getY()+panelCarrinho.getHeight()+resizeHeight(20),panelCarrinho.getWidth(),resizeHeight(145));
        add(panelPesquisa);

        Font font4=new Font("SansSerif",Font.BOLD ,13);
        lblUser=new JLabel("Cliente: "+utilizadorAtual.getUsername());
        lblUser.setFont(font4);
        lblUser.setForeground(new Color(255, 195, 0));
        lblUser.setBounds(panelPlaylists.getX()+resizeWidth(10),lblTabela.getY(),resizeWidth(150),lblTabela.getHeight());
        add(lblUser);

        Font font=new Font("SansSerif",Font.BOLD ,12);
        btnLoja=new JButton("Loja \uD83D\uDED2");
        btnLoja.setFont(font);
        btnLoja.setBounds(panelPlaylists.getX()+panelPlaylists.getBtnBiblioteca().getX(),panelPlaylists.getY()+panelPlaylists.getHeight()+resizeHeight(15),resizeWidth(110),resizeHeight(30));
        btnLoja.addActionListener(this);
        add(btnLoja);

        btnRemoverPlaylist=new JButton("Remover Playlist");
        btnRemoverPlaylist.setFont(font);
        btnRemoverPlaylist.setBounds(panelCarrinho.getX()-resizeWidth(155),btnLoja.getY(),resizeWidth(140),resizeHeight(30));
        btnRemoverPlaylist.addActionListener(this);
        btnRemoverPlaylist.setVisible(false);
        add(btnRemoverPlaylist);

        btnAlterarVisibilidade=new JButton();
        btnAlterarVisibilidade.setFont(font);
        btnAlterarVisibilidade.setBounds(btnRemoverPlaylist.getX()-resizeWidth(190),btnRemoverPlaylist.getY(),resizeWidth(120),btnRemoverPlaylist.getHeight());
        btnAlterarVisibilidade.addActionListener(this);
        btnAlterarVisibilidade.setVisible(false);
        add(btnAlterarVisibilidade);

        lblAlterarVisibilidade=new JLabel("Visibilidade da Playlist:");
        lblAlterarVisibilidade.setFont(font);
        lblAlterarVisibilidade.setForeground(new Color(255, 195, 0));
        lblAlterarVisibilidade.setBounds(btnAlterarVisibilidade.getX()-resizeWidth(140),btnRemoverPlaylist.getY(),resizeWidth(140),resizeHeight(30));
        lblAlterarVisibilidade.setVisible(false);
        add(lblAlterarVisibilidade);

        lblSaldo=new JLabel("Saldo: "+String.valueOf(utilizadorAtual.getSaldo())+"€");
        lblSaldo.setFont(font);
        lblSaldo.setForeground(new Color(255, 195, 0));
        lblSaldo.setBounds(panelCarrinho.getX(),lblUser.getY(),resizeWidth(80),resizeHeight(20));
        add(lblSaldo);

        btnCarregar=new JButton("Carregar 💰");
        btnCarregar.setBounds(lblSaldo.getX()+lblSaldo.getWidth()+resizeWidth(5),lblSaldo.getY()-resizeHeight(2),resizeWidth(100),resizeHeight(25));
        add(btnCarregar);
        btnCarregar.addActionListener(this);

        panelPlaylists.getBtnBiblioteca().addActionListener(this);

        //////////////////////////////// JFrame CARREGAMENTO////////////////////////////////////
        frmCarregamento =new JDialog();
        frmCarregamento.setTitle("Carregamento");
        frmCarregamento.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmCarregamento.setLayout(null);
        frmCarregamento.setSize(resizeWidth(240),resizeHeight(150));
        frmCarregamento.setLocationRelativeTo(null);
        frmCarregamento.setResizable(false);

        frmCarregamento.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setEnabled(true);
            }
        });

        panelCarregamento=new JPanel();
        panelCarregamento.setLayout(null);

        JLabel lblValor=new JLabel("Valor :");
        lblValor.setFont(font);
        txtValor=new JTextField(20);
        txtValor.setFont(font);

        btnCancelar=new JButton("Cancelar");
        btnCancelar.setFont(font);

        btnCarregar2=new JButton("Carregar");
        btnCarregar2.setFont(font);
        btnCarregar2.addActionListener(this);

        lblValor.setBounds(resizeWidth(20),resizeHeight(15),resizeWidth(40),resizeHeight(25));
        panelCarregamento.add(lblValor);

        txtValor.setBounds(lblValor.getX()+lblValor.getWidth()+resizeWidth(1),lblValor.getY()+resizeHeight(1),resizeWidth(50),resizeHeight(21));
        panelCarregamento.add(txtValor);

        btnCancelar.setBounds(lblValor.getX(),lblValor.getY()+lblValor.getHeight()+resizeHeight(20),resizeWidth(85),resizeHeight(25));
        panelCarregamento.add(btnCancelar);

        btnCarregar2.setBounds(btnCancelar.getX()+btnCancelar.getWidth()+resizeWidth(15),btnCancelar.getY(),btnCancelar.getWidth(),btnCancelar.getHeight());
        panelCarregamento.add(btnCarregar2);

        panelCarregamento.setBounds(resizeWidth(0),resizeHeight(0), frmCarregamento.getWidth(), frmCarregamento.getHeight());

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setEnabled(true);
                txtValor.setText("");
                frmCarregamento.dispatchEvent(new WindowEvent(frmCarregamento,WindowEvent.WINDOW_CLOSING));
            }
        });

        btnLogout=new JButton("Logout ⛔");
        btnLogout.setFont(font);
        btnLogout.setBounds(resizeWidth(820),btnRemoverPlaylist.getY(),resizeWidth(100),resizeHeight(30));
        btnLogout.addActionListener(this);
        add(btnLogout);

        frmCarregamento.setVisible(false);
        frmCarregamento.add(panelCarregamento);

        panelCarrinho.getBtnReset().addActionListener(this);
        panelCarrinho.getBtnCheckout().addActionListener(this);


        ////////////////////////////////////////////////////////////////////////

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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked=e.getSource();

        if(clicked==btnCarregar){
            frame.setEnabled(false);
            frmCarregamento.setLocationRelativeTo(null);
            frmCarregamento.setVisible(true);
        }
        else if(clicked==btnLoja){
            lblTabela.setText("Loja:");

            tabelaCliente.printMusicasLoja(rockstar.musicasVisiveis());
            tabelaCliente.setPlaylist(null);
            btnRemoverPlaylist.setVisible(false);
            btnAlterarVisibilidade.setVisible(false);
            lblAlterarVisibilidade.setVisible(false);

        }
        else if(clicked==panelPlaylists.getBtnBiblioteca()){
            lblTabela.setText("Biblioteca de músicas:");
            tabelaCliente.setPlaylist(null);
            tabelaCliente.printMusicas(utilizadorAtual.getBiblioteca());
            btnRemoverPlaylist.setVisible(false);
            btnAlterarVisibilidade.setVisible(false);
            lblAlterarVisibilidade.setVisible(false);

        }
        else if(clicked==btnRemoverPlaylist){
            System.out.println("remover playlist");
            tabelaCliente.getModel().setRowCount(0);
            utilizadorAtual.getPlaylistsProprias().remove(tabelaCliente.getPlaylist());
            rockstar.removerPlaylist(tabelaCliente.getPlaylist());
            panelPlaylists.printPlaylists(utilizadorAtual.getPlaylistsProprias());

            tabelaCliente.setPanelPlaylists(panelPlaylists);
            tabelaCliente.printMusicas(utilizadorAtual.getBiblioteca());
            lblTabela.setText("Biblioteca de músicas:");
            btnRemoverPlaylist.setVisible(false);
            btnAlterarVisibilidade.setVisible(false);
            lblAlterarVisibilidade.setVisible(false);
            int indice=tabelaCliente.getPlaylistsPopMenu().indexOf(tabelaCliente.getPlaylist());
            System.out.println("remover"+tabelaCliente.getMenuBiblioteca11().get(indice).getText());
            tabelaCliente.getMenuBiblioteca1().remove(tabelaCliente.getMenuBiblioteca11().get(indice));

            tabelaCliente.getMenuBiblioteca11().remove(indice);
            tabelaCliente.getPlaylistsPopMenu().remove(indice);
            tabelaCliente.updateActionsListeners();
            tabelaCliente.setPlaylist(null);
        }
        else if(clicked==btnAlterarVisibilidade){
            if(btnAlterarVisibilidade.getText().equals("Privada")){
                tabelaCliente.getPlaylist().setVisibilidade(true);
                btnAlterarVisibilidade.setText("Pública");
            }
            else{
                tabelaCliente.getPlaylist().setVisibilidade(false);
                btnAlterarVisibilidade.setText("Privada");
            }
        }
        else if(clicked==panelCarrinho.getBtnReset()){
           limparCarrinho();
           utilizadorAtual.limparCarrinho();
           panelCarrinho.getBtnCheckout().setEnabled(false);
           panelCarrinho.getBtnReset().setEnabled(false);

        }
        else if(clicked==panelCarrinho.getBtnCheckout()){
            if(utilizadorAtual.verificarSaldo(utilizadorAtual.getTotalCarrinho())){
                Compra compra=new Compra(utilizadorAtual,utilizadorAtual.getCarrinhoDeCompras());
                limparCarrinho();
                atualizarLblSaldo();
                panelCarrinho.getBtnCheckout().setEnabled(false);
                panelCarrinho.getBtnReset().setEnabled(false);
                JOptionPane.showMessageDialog(this,"Compra efetuada com sucesso");
            }
            else{
                JOptionPane.showMessageDialog(this,"Saldo insuficiente");
            }
        }
        else if(clicked==btnCarregar2){

            try{
                if(Double.valueOf(txtValor.getText())>0) {
                    utilizadorAtual.carregarSaldo(Double.valueOf(txtValor.getText()));
                    atualizarLblSaldo();
                    txtValor.setText("");
                    frmCarregamento.dispatchEvent(new WindowEvent(frmCarregamento, WindowEvent.WINDOW_CLOSING));
                    JOptionPane.showMessageDialog(this, "Saldo adicionado com sucesso");
                }
                else{
                    txtValor.setText("");
                    JOptionPane.showMessageDialog(this,"O valor inserido é inválido");
                }
            }
            catch (NumberFormatException q){
                txtValor.setText("");
                JOptionPane.showMessageDialog(this,"Dados inválidos");
            }
        }
        else if(clicked==btnLogout){
            frame.setSize((resizeWidth(500)),resizeHeight(350));
            setVisible(false);
            panelLogin.setVisible(true);
            utilizadorAtual=null;
            frame.setLocationRelativeTo(null);
        }
    }

    public JButton getBtnCarregar() {
        return btnCarregar;
    }

    private void mudarCorRGB(Component componente,int red,int green,int blue){ float[] cor = new float[3];
        cor = Color.RGBtoHSB(red, green, blue, cor);
        componente.setBackground(Color.getHSBColor(cor[0], cor[1], cor[2]));
    }

    public void setLblTabela(String nome) {
        lblTabela.setText(nome);
    }

    public JButton getBtnRemoverPlaylist() {
        return btnRemoverPlaylist;
    }

    public JLabel getLblTabela() {
        return lblTabela;
    }

    public PanelCarrinho getPanelCarrinho() {
        return panelCarrinho;
    }
    public void atualizarLblSaldo(){
        lblSaldo.setText("Saldo: "+String.valueOf(utilizadorAtual.getSaldo())+"€");
    }

    public void limparCarrinho(){
        panelCarrinho.getTitulos().clear();
        panelCarrinho.getPrecos().clear();
        panelCarrinho.resetPrecoTotal();
        panelCarrinho.atualizarLblTotalCompra();
        panelCarrinho.getPanelCarrinho().removeAll();
        panelCarrinho.getPanelCarrinho().revalidate();
        panelCarrinho.getPanelCarrinho().repaint();
    }

    public JButton getBtnAlterarVisibilidade() {
        return btnAlterarVisibilidade;
    }

    public JLabel getLblAlterarVisibilidade() {
        return lblAlterarVisibilidade;
    }
}
