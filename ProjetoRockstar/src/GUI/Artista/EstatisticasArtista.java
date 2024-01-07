package GUI.Artista;

import Objetos.Artista;
import Objetos.Musica;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EstatisticasArtista extends JPanel {
    private RockstarInc rockstar;
    private Artista utilizadorAtual;
    private JLabel lblEstatisticas;
    private JPanel panelEstatisticas;
    private JScrollPane scroll,scrollTopMusicas,scrollTopArtistas;
    private JButton btnTops;
    private JDialog frmTops;
    private JPanel panelTopsMusicas,panelTopsArtistas, panelTops;
    private JFrame frame;

    /**Construtor do JPanel que exibe todas as estatisticas associadas à empresa Rockstar Inc.
     */
    public EstatisticasArtista(RockstarInc rockstar, Artista utilizadorAtual, JFrame frame){
        this.rockstar=rockstar;
        this.utilizadorAtual=utilizadorAtual;
        this.frame=frame;

        mudarCorRGB(this,0,70,112);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(resizeWidth(200),resizeHeight(600));


        Font font=new Font("SansSerif",Font.BOLD,12);
        Font font1=new Font("SansSerif",Font.BOLD,13);
        lblEstatisticas=new JLabel("📊 Estatísticas da Rockstar:");
        lblEstatisticas.setFont(font1);
        lblEstatisticas.setForeground(new Color(255,195,0));
        lblEstatisticas.setBounds(resizeWidth(10),resizeHeight(0),resizeWidth(180),resizeHeight(30));
        add(lblEstatisticas);

        panelEstatisticas=new JPanel();

        panelEstatisticas.setLayout(new BoxLayout(panelEstatisticas,BoxLayout.Y_AXIS));

        panelEstatisticas.setFont(font);

        scroll=new JScrollPane(panelEstatisticas);
        scroll.setBounds(lblEstatisticas.getX(),lblEstatisticas.getY()+resizeHeight(30),resizeWidth(180),resizeHeight(270));
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scroll);

        updateEstatisticas();

        frmTops=new JDialog();
        frmTops.setTitle("Tops de Vendas 🏆");
        btnTops=new JButton("Ver Tops 🏆");
        btnTops.setBounds(scroll.getX()+resizeWidth(40),scroll.getY()+scroll.getHeight()+resizeHeight(10),resizeWidth(110),resizeHeight(30));
        btnTops.setFont(font);
        btnTops.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFrmTops();
            }
        });
        add(btnTops);
    }

    /**Faz o update das estatísticas que são apresentadas no JPanel sempre que existe alguma ação que modifique algum
     * dos valores que lá é apresentado.
     */
    public void updateEstatisticas(){
        panelEstatisticas.removeAll();
        panelEstatisticas.revalidate();

        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Total de utilizadores: "+rockstar.getTotalUtilizadores()));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Total de músicas: "+rockstar.getTotalMusicas()));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Valor total das músicas: "+rockstar.getTotalValor()+" €"));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Valor total das vendas: "+rockstar.getFaturacaoTotal()+" €"));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));

        int[] albunsPorGenero=rockstar.getAlbunsPorGenero();


        panelEstatisticas.add(new JLabel(" ----------------------------------------- "));

        Font font1=new Font("SansSerif",Font.BOLD,13);
        JLabel totalAlbuns=new JLabel(" Total de álbuns por género:");
        totalAlbuns.setFont(font1);
        panelEstatisticas.add(totalAlbuns);

        panelEstatisticas.add(new JLabel(" Rock 🎸: "+albunsPorGenero[0]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Pop 🎤: "+albunsPorGenero[2]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Rap 🔫: "+albunsPorGenero[1]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Clássica 🎻: "+albunsPorGenero[3]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Jazz 🎺: "+albunsPorGenero[4]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Metal 🤘: "+albunsPorGenero[5]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Popular 🥁: "+albunsPorGenero[6]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Eletrónica 🎧: "+albunsPorGenero[7]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));

    }

    /**Criação e atualização da frame e dos JPanels onde são apresentados os dados relativos ao top cinco de artistas e de músicas.
     */
    private void setFrmTops(){
        frmTops.setModal(true);
        frmTops.setLayout(null);
        frmTops.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmTops.setSize(resizeWidth(460),resizeHeight(240));
        frmTops.setLocationRelativeTo(null);
        frmTops.setResizable(false);

        panelTops=new JPanel();
        panelTops.setLayout(null);
        panelTops.setBounds(0,0,frmTops.getWidth(),frmTops.getHeight());
        mudarCorRGB(panelTops,0,70,112);

        panelTopsMusicas=new JPanel();
        panelTopsMusicas.setLayout(new BoxLayout(panelTopsMusicas,BoxLayout.Y_AXIS));

        panelTopsArtistas=new JPanel();
        panelTopsArtistas.setLayout(new BoxLayout(panelTopsArtistas,BoxLayout.Y_AXIS));

        scrollTopArtistas=new JScrollPane(panelTopsArtistas);
        scrollTopMusicas=new JScrollPane(panelTopsMusicas);
        scrollTopMusicas.setBounds(resizeWidth(25),resizeHeight(35),resizeWidth(185),resizeHeight(155));
        scrollTopArtistas.setBounds(resizeWidth(240),scrollTopMusicas.getY(),resizeWidth(185),scrollTopMusicas.getHeight());

        Font font = new Font("SansSerif", Font.BOLD, 12);

        JLabel lblTop5Musicas=new JLabel("Top 5 Músicas mais vendidas:");
        lblTop5Musicas.setFont(font);
        lblTop5Musicas.setForeground(new Color(255,195,0));
        JLabel lblTop5Artistas=new JLabel("Top 5 Artistas com mais vendas:");
        lblTop5Artistas.setFont(font);
        lblTop5Artistas.setForeground(new Color(255,195,0));

        lblTop5Musicas.setBounds(scrollTopMusicas.getX(),resizeHeight(10),resizeWidth(195),resizeHeight(30));
        panelTops.add(lblTop5Musicas);

        lblTop5Artistas.setBounds(scrollTopArtistas.getX(),lblTop5Musicas.getY(),resizeWidth(215),lblTop5Musicas.getHeight());
        panelTops.add(lblTop5Artistas);

        ArrayList<Musica> topMusicas= rockstar.getTop5MusicasVendidas();
        ArrayList<Artista> topArtistas=rockstar.getTopArtistasVendidos();

        panelTopsArtistas.add(Box.createRigidArea(new Dimension(0,3)));
        panelTopsMusicas.add(Box.createRigidArea(new Dimension(0,3)));

        for (int i=0;i<topArtistas.size();i++){
            panelTopsArtistas.add(new JLabel("  "+(i+1)+" - "+topArtistas.get(i).getNome()+" : "+topArtistas.get(i).getVendasTotal()));
            panelTopsArtistas.add(Box.createRigidArea(new Dimension(0,3)));
        }
        for(int i=0;i<topMusicas.size();i++) {
            panelTopsMusicas.add(new JLabel("  " + (i + 1) + " - " + topMusicas.get(i).getTitulo() + " : " + topMusicas.get(i).getVendas()));
            panelTopsMusicas.add(Box.createRigidArea(new Dimension(0, 3)));
        }

        panelTops.add(scrollTopArtistas);
        panelTops.add(scrollTopMusicas);

        frmTops.add(panelTops);

        frmTops.setVisible(true);

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
