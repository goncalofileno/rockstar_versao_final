package GUI.Cliente;

import Objetos.Cliente;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PanelCarrinho extends JPanel {
    private JLabel lblCarrinho,lblTotalCompra;
    private JPanel panelCarrinho;
    private JScrollPane scroll;
    private JButton btnCheckout,btnReset;
    private JPanel filtros;
    private Cliente utilizadorAtual;
    private ArrayList<String> titulos;
    private ArrayList<Double> precos;
    private double precoTotal;


    /**Contrutor do JPanel que contém todos os elementos associados à compra de músicas por parte do cliente. Os principais elementos são o JPanel onde são mostradas
     * todas as músicas adicionadas ao carrinho de compras, o botão checkout que cria um objeto Compra com as músicas que estão no carrinho de compras, e o botão "Reset"
     * que retira todas as músicas do carrinho
     */

    public PanelCarrinho(Cliente utilizadorAtual){
        this.utilizadorAtual=utilizadorAtual;
        titulos=new ArrayList<>();
        precos=new ArrayList<>();
        precoTotal=0;

        mudarCorRGB(this,0,70,112);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(resizeWidth(200),resizeHeight(400));

        Font font=new Font("SansSerif",Font.BOLD,12);
        lblCarrinho=new JLabel("🛒 Carrinho:");
        lblCarrinho.setFont(font);
        lblCarrinho.setForeground(new Color(255, 195, 0));
        lblCarrinho.setBounds(resizeWidth(10),resizeHeight(0),resizeWidth(90),resizeHeight(30));
        add(lblCarrinho);

        ///////////////////////////Painel do carrinho de compras///////////////////////////////
        panelCarrinho=new JPanel();

        panelCarrinho.setLayout(new BoxLayout(panelCarrinho,BoxLayout.Y_AXIS));

        scroll=new JScrollPane(panelCarrinho);
        scroll.setBounds(lblCarrinho.getX(),lblCarrinho.getY()+resizeHeight(30),resizeWidth(180),resizeHeight(150));
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scroll);

        lblTotalCompra=new JLabel("Total: "+limitarCasasDecimais(precoTotal)+"€");
        lblTotalCompra.setFont(font);
        lblTotalCompra.setForeground(new Color(255, 195, 0));
        lblTotalCompra.setBounds(lblCarrinho.getX(),scroll.getY()+scroll.getHeight()+resizeHeight(5),resizeWidth(100),resizeHeight(20));
        add(lblTotalCompra);

        btnCheckout =new JButton("Comprar 💸");
        btnCheckout.setFont(font);
        btnCheckout.setBounds(lblCarrinho.getX()+resizeWidth(40),lblTotalCompra.getY()+lblTotalCompra.getHeight()+resizeHeight(15),resizeWidth(110),resizeHeight(30));
        btnCheckout.setEnabled(false);
        add(btnCheckout);

        btnReset=new JButton("Limpar 🧹");
        btnReset.setFont(font);
        btnReset.setBounds(btnCheckout.getX(),btnCheckout.getY()+btnCheckout.getHeight()+resizeHeight(10),btnCheckout.getWidth(),btnCheckout.getHeight());
        btnReset.setEnabled(false);
        add(btnReset);

        /////////////////////////////////////////////////////////////////////////////////////////

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

    public JPanel getPanelCarrinho() {
        return panelCarrinho;
    }

    public ArrayList<String> getTitulos() {
        return titulos;
    }

    public void addPrecoTotal(double precoMusica){
        precos.add(precoMusica);
        precoTotal+=precoMusica;
    }

    public ArrayList<Double> getPrecos() {
        return precos;
    }

    public void atualizarLblTotalCompra() {
        lblTotalCompra.setText(("Total: "+limitarCasasDecimais(precoTotal)+"€"));
    }

    public double getPrecoTotal(){
        return precoTotal;
    }

    public JButton getBtnCheckout() {
        return btnCheckout;
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    public void resetPrecoTotal(){
        precoTotal=0;
    }

    public String limitarCasasDecimais(double valor){
        DecimalFormat df=new DecimalFormat("#.##");
        return df.format(valor);
    }
}
