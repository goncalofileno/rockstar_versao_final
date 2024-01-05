package GUI.Cliente;

import Objetos.Cliente;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PesquisaPanel extends JPanel {

    private JLabel lblPesquisa;
    private JTextField txtPesquisa;
    private JRadioButton radioArtista, radioTitulo;
    private JButton btnPesquisa, btnLimpar;
    private TabelaCliente tabelaCliente;
    private RockstarInc rockstar;
    private InterfaceCliente interfaceCliente;
    private Cliente utilizadorAtual;

    /**Construtor do JPanel que comtém todos os elementos associados à pesquisa de músicas. Este JPanel dá-nos a posibbildiade de escrever no JTextField txtPesquisa a palavra que pretendemos
     * pesquisar escolhendo entre "Título" ou "Álbum" através dos JRadioButtons e ao clicar no botao btnPesquisa ele filtra as músicas que estão na JTable central com
     * os dados inseridos neste JPanel
     */
    public PesquisaPanel(TabelaCliente tabelaCliente, RockstarInc rockstar, InterfaceCliente interfaceCliente, Cliente utilizadorAtual){
        this.tabelaCliente=tabelaCliente;
        this.rockstar=rockstar;
        this.interfaceCliente=interfaceCliente;
        this.utilizadorAtual=utilizadorAtual;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(resizeWidth(180),resizeHeight(150));
        setLayout(null);
        mudarCorRGB(this,0,70,112);

        Font font=new Font("SansSerif",Font.BOLD,12);
        lblPesquisa=new JLabel("Pesquisa:");
        lblPesquisa.setFont(font);
        lblPesquisa.setForeground(new Color(255, 195, 0));
        lblPesquisa.setBounds(resizeWidth(10),resizeHeight(15),resizeWidth(100),resizeHeight(15));
        add(lblPesquisa);

        txtPesquisa=new JTextField(30);
        txtPesquisa.setFont(font);
        txtPesquisa.setBounds(lblPesquisa.getX(),lblPesquisa.getY()+lblPesquisa.getHeight()+resizeHeight(5),resizeWidth(125),resizeHeight(22));
        add(txtPesquisa);

        radioArtista=new JRadioButton("Artista");
        radioArtista.setFont(font);
        radioArtista.setForeground(new Color(255, 195, 0));
        radioArtista.setBounds(txtPesquisa.getX(),txtPesquisa.getY()+txtPesquisa.getHeight()+resizeHeight(10),resizeWidth(90),resizeHeight(20));
        radioArtista.setOpaque(false);
        radioArtista.setSelected(true);
        add(radioArtista);

        radioTitulo=new JRadioButton("Título");
        radioTitulo.setFont(font);
        radioTitulo.setForeground(new Color(255, 195, 0));
        radioTitulo.setBounds(radioArtista.getX()+radioArtista.getWidth()+resizeWidth(5),radioArtista.getY(),radioArtista.getWidth(),radioArtista.getHeight());
        radioTitulo.setOpaque(false);
        add(radioTitulo);

        ButtonGroup grupoBtn=new ButtonGroup();
        grupoBtn.add(radioArtista);
        grupoBtn.add(radioTitulo);

        btnPesquisa=new JButton("\uD83D\uDD0E");
        btnPesquisa.setBounds(txtPesquisa.getX()+txtPesquisa.getWidth()+resizeWidth(5),txtPesquisa.getY(),resizeWidth(50),resizeHeight(22));
        add(btnPesquisa);

        btnPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioArtista.isSelected()){
                    tabelaCliente.setListaMusicasAtual(rockstar.pesquisaArtista(tabelaCliente.getListaMusicasAtual(),txtPesquisa.getText()));
                    if(interfaceCliente.getLblTabela().getText().equals("Loja:")) {

                        tabelaCliente.printMusicasLoja(tabelaCliente.getListaMusicasAtual());
                    }
                    else {
                        tabelaCliente.printMusicas(tabelaCliente.getListaMusicasAtual());
                    }
                }
                else {
                    tabelaCliente.setListaMusicasAtual(rockstar.pesquisaTitulo(tabelaCliente.getListaMusicasAtual(),txtPesquisa.getText()));
                    if(interfaceCliente.getLblTabela().getText().equals("Loja:")) {
                        tabelaCliente.printMusicasLoja(tabelaCliente.getListaMusicasAtual());
                    }
                    else {
                        tabelaCliente.printMusicas(tabelaCliente.getListaMusicasAtual());
                    }
                }
            }
        });

        btnLimpar=new JButton("Limpar Pesquisa");
        btnLimpar.setFont(font);
        btnLimpar.setBounds(radioArtista.getX()+resizeWidth(20),radioArtista.getY()+radioArtista.getHeight()+resizeHeight(15),resizeWidth(140),resizeHeight(25));
        add(btnLimpar);

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (interfaceCliente.getLblTabela().getText().equals("Biblioteca de músicas:")){
                    tabelaCliente.setPlaylist(null);
                    tabelaCliente.printMusicas(utilizadorAtual.getBiblioteca());
                }
                else if (interfaceCliente.getLblTabela().getText().equals("Loja:")){
                    tabelaCliente.setPlaylist(null);
                    tabelaCliente.printMusicasLoja(rockstar.musicasVisiveis());
                }
                else {
                    tabelaCliente.printMusicas(tabelaCliente.getPlaylist().getMusicas());
                }
            }
        });
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

    public JTextField getTxtPesquisa() {
        return txtPesquisa;
    }

    public JRadioButton getRadioArtista() {
        return radioArtista;
    }

    public JRadioButton getRadioTitulo() {
        return radioTitulo;
    }

    public JButton getBtnPesquisa() {
        return btnPesquisa;
    }
}
