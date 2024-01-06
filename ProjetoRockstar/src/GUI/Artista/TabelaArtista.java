package GUI.Artista;

import Objetos.Album;
import Objetos.Artista;
import Objetos.Musica;
import Objetos.RockstarInc;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class TabelaArtista extends JPanel implements ActionListener, MouseListener {
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    private RockstarInc rockstar;
    private Artista utilizadorAtual;
    private ArrayList<Musica> listaMusicasAtual;
    private ArtistaAlbuns panelAlbuns;
    private ArrayList<JButton> btnAlbuns;
    private InterfaceArtista interfaceArtista;
    private Album album;
    private JPopupMenu popmenuArtista;
    private JMenuItem menuArtista4;
    private ArrayList<JMenuItem> menuArtista41;
    private Musica musicaSelecionada;
    private JDialog frmTitulo,frmPreco;
    private JPanel panelTitulo,panelPreco;
    private JButton btnCancelarTitulo,btnTitulo,btnCancelarPreco,btnPreco;
    private JTextField txtTitulo, txtPreco;
    private JFrame frame;

    /**Construtor do JPanel que contém como elemento principal a JTabel onde as músicas são apresentadas.
     */
    public TabelaArtista(RockstarInc rockstar, Artista utilizadorAtual, ArtistaAlbuns panelAlbuns, InterfaceArtista interfaceArtista, JFrame frame) {
        this.rockstar = rockstar;
        this.utilizadorAtual = utilizadorAtual;
        this.interfaceArtista = interfaceArtista;
        this.frame=frame;

        setPanelAlbuns(panelAlbuns);

        frmPreco =new JDialog();
        frmPreco.setTitle("Mudar o Preço da Música");
        frmTitulo =new JDialog();
        frmTitulo.setTitle("Mudar o Título da Música");

        setFramePreco();
        setFrameTitulo();

        String[] headers = {"Título", "Album", "Rating", "Preço", "Visibilidade"};
        // Criar o modelo da tabela
        model = new DefaultTableModel();

        model.setColumnIdentifiers(headers);

        setLayout(null);

        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setReorderingAllowed(false);

        table.setEnabled(true);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setOrdemTabela();

        mudarCorRGB(this, 0 ,29, 61);
        mudarCorRGB(table, 0, 70, 112);

        Font font = new Font("SansSerif", Font.BOLD, 11);
        table.setFont(font);
        table.setForeground(new Color(255,195,0));

        scrollPane = new JScrollPane(table);

        printMusicas(utilizadorAtual.getTotalMusicas());

        int scrollPaneHeight;
        if ((listaMusicasAtual.size() + 1) * resizeHeight(23)<=500){
            scrollPaneHeight=(listaMusicasAtual.size() + 1) * resizeHeight(23);
        }
        else scrollPaneHeight=resizeHeight(500);

        scrollPane.setBounds(resizeWidth(0), resizeHeight(0), resizeWidth(465), scrollPaneHeight);
        add(scrollPane);

        table.setRowHeight(23);

        table.getTableHeader().setResizingAllowed(false);

        popmenuArtista = new JPopupMenu();

        JMenuItem menuArtista1 = new JMenuItem("Alterar visibilidade");
        menuArtista1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if (row != -1 && row < listaMusicasAtual.size()) {
                    row = table.convertRowIndexToModel(row);
                    musicaSelecionada = rockstar.musicaSelecionada(utilizadorAtual.getUsername(), table.getModel().getValueAt(row, 0).toString());
                    if (musicaSelecionada.isEstadoAtividade()) {
                        musicaSelecionada.setEstadoAtividade(false);
                    } else {
                        musicaSelecionada.setEstadoAtividade(true);
                    }
                    printMusicas(listaMusicasAtual);
                }
            }
        });

        JMenuItem menuArtista2 =new JMenuItem("Alterar título");

        menuArtista2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if (row != -1 && row < listaMusicasAtual.size()) {
                    row = table.convertRowIndexToModel(row);
                    musicaSelecionada = rockstar.musicaSelecionada(utilizadorAtual.getUsername(), table.getModel().getValueAt(row, 0).toString());
                    txtTitulo.setText(musicaSelecionada.getTitulo());
                    frmTitulo.setLocationRelativeTo(null);
                    frmTitulo.setVisible(true);
                }
            }
        });

        JMenuItem menuArtista3 =new JMenuItem("Alterar Preço");
        menuArtista3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if (row != -1 && row < listaMusicasAtual.size()) {
                    row = table.convertRowIndexToModel(row);
                    musicaSelecionada = rockstar.musicaSelecionada(utilizadorAtual.getUsername(), table.getModel().getValueAt(row, 0).toString());
                    txtPreco.setText(Double.toString(musicaSelecionada.getPrecoMusica()));
                    frmPreco.setLocationRelativeTo(null);
                    frmPreco.setVisible(true);
                }
            }
        });

        menuArtista4=new JMenu("Adicionar a Album");

        menuArtista41 = new ArrayList<>();

        updatePopMenuArtista();

        popmenuArtista.add(menuArtista1);
        popmenuArtista.add(menuArtista2);
        popmenuArtista.add(menuArtista3);
        popmenuArtista.add(menuArtista4);
        popmenuArtista.setSize(resizeWidth(0),resizeHeight(0));
        add(popmenuArtista);

        table.addMouseListener(this);
    }

    public void setHeader(String[] headers) {
        model.setColumnIdentifiers(headers);
    }


    private void setOrdemTabela() {

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        sorter.setSortable(0, false);
        sorter.setSortable(1, false);
        sorter.setSortable(2, true);
        sorter.setSortable(3, true);
    }

    public void printMusicas(ArrayList<Musica> musicas) {
        listaMusicasAtual = musicas;

        int scrollPaneHeight;
        if ((listaMusicasAtual.size() + 1) * resizeHeight(23)<=500){
            scrollPaneHeight=(listaMusicasAtual.size() + 1) * resizeHeight(23);
        }
        else scrollPaneHeight=resizeHeight(500);

        scrollPane.setSize(resizeWidth(465), scrollPaneHeight);
        model.setRowCount(0);

        setOrdemTabela();

        for (int i = 0; i < musicas.size(); i++) {
            model.addRow(new Object[]{"", "", "", "",""});
        }
        for (int i = 0; i < musicas.size(); i++) {
            model.setValueAt(musicas.get(i).getTitulo(), i, 0);
            if(musicas.get(i).getAlbum()!=null) {
                model.setValueAt(musicas.get(i).getAlbum().getNome(), i, 1);
            }
            else  model.setValueAt("Sem Álbum", i, 1);
            if (musicas.get(i).getRatingMedio() > 0) {
                model.setValueAt(limitarCasasDecimais(musicas.get(i).getRatingMedio()), i, 2);
            } else {
                model.setValueAt("Sem Rating", i, 2);
            }
            model.setValueAt(musicas.get(i).getPrecoMusica()+" €", i, 3);
            if(musicas.get(i).isEstadoAtividade()){
                model.setValueAt("Ativa",i,4);
            }
            else {
                model.setValueAt("Inativa", i, 4);
            }
        }
        alinharTable();
    }

    public void alinharTable() {
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(render);
        }
    }

    public void setPanelAlbuns(ArtistaAlbuns panelAlbuns) {
        this.panelAlbuns = panelAlbuns;
        this.btnAlbuns = panelAlbuns.getBtnListaAlbuns();
        for (int i = 0; i < btnAlbuns.size(); i++) {
            this.btnAlbuns.get(i).addActionListener(this);
        }
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

    private void mudarCorRGB(Component componente, int red, int green, int blue) {
        float[] cor = new float[3];
        cor = Color.RGBtoHSB(red, green, blue, cor);
        componente.setBackground(Color.getHSBColor(cor[0], cor[1], cor[2]));
    }

    public String limitarCasasDecimais(double valor){
        DecimalFormat df=new DecimalFormat("#.#");
        return df.format(valor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked = e.getSource();

        if(clicked==btnCancelarTitulo){
            frmTitulo.dispatchEvent(new WindowEvent(frmTitulo,WindowEvent.WINDOW_CLOSING));
        }
        else if(clicked==btnTitulo){
            if(!txtTitulo.getText().isEmpty()) {
                musicaSelecionada.setTitulo(txtTitulo.getText());
                printMusicas(listaMusicasAtual);
                frmTitulo.dispatchEvent(new WindowEvent(frmTitulo, WindowEvent.WINDOW_CLOSING));
            }
            else JOptionPane.showMessageDialog(interfaceArtista,"Tem de inserir um título na música");

        }
        else if(clicked==btnCancelarPreco){
            frmPreco.dispatchEvent(new WindowEvent(frmPreco,WindowEvent.WINDOW_CLOSING));
        }
        else if(clicked==btnPreco){
            try {
                if (txtPreco.getText().matches("\\d+(\\.\\d+)*")) {
                    if (Double.valueOf(txtPreco.getText()) >= 0) {
                        musicaSelecionada.novoPreco(Double.valueOf(txtPreco.getText()));
                        printMusicas(listaMusicasAtual);
                        frmPreco.dispatchEvent(new WindowEvent(frmPreco, WindowEvent.WINDOW_CLOSING));
                    } else JOptionPane.showMessageDialog(interfaceArtista, "Dados inseridos inválidos");
                }
                else JOptionPane.showMessageDialog(interfaceArtista,"Dados inseridos inválidos");
            }
            catch(NumberFormatException j){
                    JOptionPane.showMessageDialog(interfaceArtista, "Dados inseridos inválidos");
                }
        }
        for (int i = 0; i < btnAlbuns.size(); i++) {
            if (clicked == btnAlbuns.get(i)) {
                interfaceArtista.setLblTabela("Album: " + utilizadorAtual.getAlbuns().get(i).getNome());
                album=utilizadorAtual.getAlbuns().get(i);
                printMusicas(utilizadorAtual.getAlbuns().get(i).getMusicas());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON3){
            int row = table.getSelectedRow();

            if (row != -1 && row<listaMusicasAtual.size()) {
                row = table.convertRowIndexToModel(row);

                popmenuArtista.show(e.getComponent(), e.getX(), e.getY());

                int r = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(r, r);
                musicaSelecionada = rockstar.musicaSelecionada(utilizadorAtual.getUsername(), table.getModel().getValueAt(row, 0).toString());

            }
        }
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

    public ArrayList<Musica> getListaMusicasAtual() {
        return listaMusicasAtual;
    }

    public void setListaMusicasAtual(ArrayList<Musica> listaMusicasAtual) {
        this.listaMusicasAtual = listaMusicasAtual;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }

    public void updatePopMenuArtista(){
        menuArtista4.removeAll();
        menuArtista41.removeAll(menuArtista41);
        for (int i = 0; i < utilizadorAtual.getAlbuns().size(); i++) {
            menuArtista41.add(new JMenuItem(utilizadorAtual.getAlbuns().get(i).getNome()));

            Album album=utilizadorAtual.getAlbuns().get(i);
            menuArtista4.add(menuArtista41.get(i));
            menuArtista41.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(album.addMusica(musicaSelecionada)){
                        printMusicas(utilizadorAtual.getTotalMusicas());
                    }
                    else{
                        JOptionPane.showMessageDialog(interfaceArtista,"Esta música já tem um álbum atribuido");
                    }

                }
            });

        }
    }

    public void setFrameTitulo(){
        frmTitulo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmTitulo.setModal(true);
        frmTitulo.setLayout(null);
        frmTitulo.setBounds(resizeWidth(1000),resizeHeight(150),resizeWidth(280),resizeHeight(180));
        frmTitulo.setResizable(false);
        frmTitulo.setVisible(false);
        frmTitulo.setLocationRelativeTo(null);

        panelTitulo=new JPanel();
        panelTitulo.setLayout(null);

        Font font = new Font("SansSerif", Font.BOLD, 12);

        JLabel lblTitulo=new JLabel("Novo Título:");
        lblTitulo.setFont(font);

        btnCancelarTitulo=new JButton("Cancelar");
        btnCancelarTitulo.setFont(font);
        btnCancelarTitulo.addActionListener(this);

        btnTitulo=new JButton("Submeter");
        btnTitulo.setFont(font);
        btnTitulo.addActionListener(this);

        txtTitulo=new JTextField(20);
        txtTitulo.setFont(font);

        lblTitulo.setBounds(resizeWidth(20),resizeHeight(5),resizeWidth(100),resizeHeight(25));
        panelTitulo.add(lblTitulo);

        txtTitulo.setBounds(lblTitulo.getX(),lblTitulo.getY()+lblTitulo.getHeight()+resizeHeight(5),resizeWidth(180),resizeHeight(21));
        panelTitulo.add(txtTitulo);

        btnCancelarTitulo.setBounds(lblTitulo.getX(),lblTitulo.getY()+lblTitulo.getHeight()+lblTitulo.getHeight()+resizeHeight(20),resizeWidth(100),resizeHeight(25));
        panelTitulo.add(btnCancelarTitulo);

        btnTitulo.setBounds(btnCancelarTitulo.getX()+btnCancelarTitulo.getWidth()+resizeWidth(15),btnCancelarTitulo.getY(),btnCancelarTitulo.getWidth(),btnCancelarTitulo.getHeight());
        panelTitulo.add(btnTitulo);

        panelTitulo.setBounds(resizeWidth(0),resizeHeight(0), frmTitulo.getWidth(), frmTitulo.getHeight());

        frmTitulo.add(panelTitulo);
    }
    public void setFramePreco(){
        frmPreco.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmPreco.setModal(true);
        frmPreco.setLayout(null);
        frmPreco.setSize(resizeWidth(280),resizeHeight(180));
        frmPreco.setResizable(false);
        frmPreco.setVisible(false);
        frmPreco.setLocationRelativeTo(null);

        panelPreco=new JPanel();
        panelPreco.setLayout(null);

        Font font = new Font("SansSerif", Font.BOLD, 12);

        JLabel lblPreco=new JLabel("Novo Preço:");
        lblPreco.setFont(font);

        btnCancelarPreco=new JButton("Cancelar");
        btnCancelarPreco.setFont(font);
        btnCancelarPreco.addActionListener(this);

        btnPreco=new JButton("Submeter");
        btnPreco.setFont(font);
        btnPreco.addActionListener(this);

        txtPreco=new JTextField(20);
        txtPreco.setFont(font);

        lblPreco.setBounds(resizeWidth(20),resizeHeight(5),resizeWidth(100),resizeHeight(25));
        panelPreco.add(lblPreco);

        txtPreco.setBounds(lblPreco.getX(),lblPreco.getY()+lblPreco.getHeight()+resizeHeight(5),resizeWidth(180),resizeHeight(21));
        panelPreco.add(txtPreco);

        btnCancelarPreco.setBounds(lblPreco.getX(),lblPreco.getY()+lblPreco.getHeight()+lblPreco.getHeight()+resizeHeight(20),resizeWidth(100),resizeHeight(25));
        panelPreco.add(btnCancelarPreco);

        btnPreco.setBounds(btnCancelarPreco.getX()+btnCancelarPreco.getWidth()+resizeWidth(15),btnCancelarPreco.getY(),btnCancelarPreco.getWidth(),btnCancelarPreco.getHeight());
        panelPreco.add(btnPreco);

        panelPreco.setBounds(resizeWidth(0),resizeHeight(0), frmPreco.getWidth(), frmPreco.getHeight());

        frmPreco.add(panelPreco);
    }


}
