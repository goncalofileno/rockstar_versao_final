package Objetos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Musica implements Serializable {
    ///////////////////////////////////ATRIBUTOS////////////////////////////////////////////////////////////////////////
    private String titulo;
    private Artista compositor;
    private String genero;
    private ArrayList<Preco> listaPrecos;
    private ArrayList<Rating> listaRatings;
    private LocalDate dataAdicionada;
    private boolean estadoAtividade;
    private Album album;
    private int vendas;
    private double faturacao;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////CONSTRUTORES//////////////////////////////////////////////////////////////
    /**
     * Construtor criado para criar Músicas sem Album atribuído, ou seja Singles.
     */
    public Musica(String titulo, Artista compositor, String genero, double valor, boolean estadoAtividade) {

        this.titulo = titulo;
        this.compositor = compositor;
        this.genero = genero;

        listaPrecos = new ArrayList<>();
        listaPrecos.add(new Preco(valor));

        listaRatings = new ArrayList<>();

        this.dataAdicionada = LocalDate.now();
        this.estadoAtividade = estadoAtividade;

        this.vendas = 0;
        this.faturacao = 0.00;

        compositor.addSingles(this);
    }
    /**
     * Construtor criado para criar músicas com album já atribuido.
     */
    public Musica(String titulo, Artista compositor, String genero, double valor, Album album, boolean estadoAtividade) {
        this.titulo = titulo;
        this.compositor = compositor;
        this.genero = genero;

        listaPrecos = new ArrayList<>();
        listaPrecos.add(new Preco(valor));

        listaRatings = new ArrayList<>();

        this.dataAdicionada = LocalDate.now();
        this.estadoAtividade = estadoAtividade;

        this.vendas = 0;
        this.faturacao = 0.00;
        album.addMusica(this);
        this.album = album;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////GETTERS//////////////////////////////////////////////////////////////////////
    public String getTitulo() {
        return titulo;
    }

    public Artista getCompositor() {
        return compositor;
    }

    public String getGenero() {
        return genero;
    }

    public double getPrecoMusica() {return listaPrecos.get(listaPrecos.size() - 1).getValor();}

    public ArrayList<Rating> getListaRatings() {
        return listaRatings;
    }

    public ArrayList<Preco> getListaPrecos() {
        return listaPrecos;
    }

    /**
     * Getter criado para devolver um valor médio dos Ratings presentes
     */
    public double getRatingMedio() {
        int totalRatings = 0;
        for (Rating rating : listaRatings) {
            totalRatings += rating.getAvaliacao();
        }
        if (totalRatings > 0) {
            return (double)totalRatings / listaRatings.size();
        } else {
            return 0;
        }
    }


    public int getVendas() {
        return vendas;
    }

    public double getFaturacao() {
        return faturacao;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////SETTERS///////////////////////////////////////////////////////////////////////

    /**
     * Adiciona um novo preço à música
     */
    public void novoPreco(double valor) {
        listaPrecos.add(new Preco(valor));
    }
    //public boolean validarPreco(String valor){
 //
    //}
    /**
     * Adiciona um novo rating à musica
     */
    public void atribuiRating(Cliente cliente, int valor) {
        listaRatings.add(new Rating(cliente, this, valor));
    }

    /**
     * Altera o titulo da música.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Altera a visibilidade de uma musica.
     */
    public void setEstadoAtividade(boolean estadoAtividade) {
        this.estadoAtividade = estadoAtividade;
    }


    /**
     * Incrementa as vendas.
     */
    public void addVendas() {
        vendas++;
    }

    /**
     * Incrementará a faturação.
     */
    public void setFaturacao(double faturacao) {
        this.faturacao = this.faturacao + faturacao;
    }
    public Rating getRatingCliente(Cliente cliente){
        Rating ratingCliente = null;
        for(Rating rating: listaRatings){
            if(rating.getCliente().equals(cliente)){
                ratingCliente = rating;
            }
        }
        return ratingCliente;
    }

    public void addRating(Cliente cliente, int avaliacao){
        listaRatings.add(new Rating(cliente, this, avaliacao));
    }


    public boolean isEstadoAtividade() {
        return estadoAtividade;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public boolean verificarExistenciaAlbum(){
        if(album==null){
            return false;
        }
        else{
            return true;
        }
    }
}
