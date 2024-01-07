package Objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Cliente extends Utilizador implements Serializable {
    ///////////////////////////////////ATRIBUTOS////////////////////////////////////////////////////////////////////////
    private ArrayList<Musica> carrinhoDeCompras;
    private ArrayList<Musica> biblioteca;
    private ArrayList<Playlist> playlistsProprias;
    private ArrayList<Compra> comprasEfetuadas;
    private double saldo;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////CONSTRUTORES//////////////////////////////////////////////////////////////
    public Cliente(String username, String pass, String nome) {
        super(username, pass, nome);
        carrinhoDeCompras = new ArrayList<>();
        playlistsProprias = new ArrayList<>();
        comprasEfetuadas = new ArrayList<>();
        biblioteca = new ArrayList<>();
        saldo = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////GETTTERS//////////////////////////////////////////////////////////////////
    public ArrayList<Musica> getCarrinhoDeCompras() {
        return carrinhoDeCompras;
    }

    public ArrayList<Playlist> getPlaylistsProprias() {
        return playlistsProprias;
    }

    public ArrayList<Musica> getBiblioteca() {
        return biblioteca;
    }

    public double getSaldo() {
        return saldo;
    }

    /**
     * Getter que devolve o preço total das musicas presentes no ArrayList de Músicas presente no carrinho.
     */
    public double getTotalCarrinho() {
        double precoTotal = 0;
        for (int i = 0; i < carrinhoDeCompras.size(); i++) {
            precoTotal += carrinhoDeCompras.get(i).getPrecoMusica();
        }
        return precoTotal;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////SETTERS//////////////////////////////////////////////////////////////////

    public void setCarrinhoDeCompras(ArrayList<Musica> carrinhoDeCompras) {
        this.carrinhoDeCompras = carrinhoDeCompras;
    }
    /**
     * Metodo criado para adicionar uma compra ao arrayList de Compras do Cliente.
     */
    public void setComprasEfetuadas(Compra compra) {
        this.comprasEfetuadas.add(compra);
    }
    /**
     * Metodo criado para carregar o saldo do cliente, o mesmo recebe um valor e incrementa o atributo saldo do cliente.
     */
    public void carregarSaldo(double carregamento) {this.saldo += carregamento;
    }
    /**
     * Metodo criado para descontar o saldo do Cliente, o mesmo recebe um valor e desconta no atributo Saldo do mesmo.
     */

    public boolean verificarSaldo (double precoMusicas){
        if(saldo>=precoMusicas){
            return true;
        }
        else return false;
    }
    public void descontarSaldo(double precoMusicas) {
        saldo -= precoMusicas;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////ADDERS//////////////////////////////////////////////////////////////////
    /**
     *Metodo criado para adicionar uma playlist ao arrayList de Playlists presente como atributo de Cliente.
     */
    public void addPlaylist(Playlist playlist) {
        playlistsProprias.add(playlist);
    }
    /**
     * Metodo criado para adicionar uma Música à biblioteca presente como atributo no Cliente, sendo a biblioteca um
     * arrayList de Músicas.
     */
    public void addBiblioteca(Musica musica) {
        biblioteca.add(musica);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////METODOS DIVERSOS////////////////////////////////////////////////////////
    /**
     * Método criado para receber um nome de uma playlist, uma quantidade músicas a adicionar, um genero e visibilidade
     * Metodo percorre a arraylist de musicas adquiridas do utilizador e seleciona as primeiras que encontrar do mesmo
     * genero, até não encontrar mais ou até o tamanho ser atingido.
     */
    public Playlist criaPlaylistAI(String nome, int quantidade, String genero, boolean visibilidade) {
        Random random = new Random();

        Playlist playlistGenero = new Playlist(nome, visibilidade);
        Playlist playlistAI = new Playlist(nome, visibilidade);


        for (Musica musica : biblioteca) {
            if (musica.getGenero().equals(genero)) {
                playlistGenero.addMusica(musica);
            }
        }

        while(playlistAI.getMusicas().size()<quantidade && playlistGenero.getMusicas().size()>0){
            int i = random.nextInt((playlistGenero.getMusicas().size()));
            playlistAI.addMusica(playlistGenero.getMusicas().get(i));
            playlistGenero.getMusicas().remove(i);
        }
        return playlistAI;
    }
    /**
     * Método criado para saber se uma playlist tem um determinado número de músicas, o mesmo recebe uma playlist e um inteiro com a
     * quantidade a comparar, devolve FALSE se a playlist tiver menos músicas que o contrário e TRUE se o contrário.
     */
    public boolean verificarQtdMusicas(Playlist playlist, int quantidade) {
        if (playlist.getMusicas().size() < quantidade) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Metodo criado para devolver acção ao POPMENU de 'Comprar Música', o mesmo verfica o preço da música e se for diferente de 0€ acrescenta
     * a musica clicada ao carrinho para posterior validação da compra, se a mesma não tiver preço adiciona diretamente a música à biblioteca
     * do cliente que estiver loggado.
     */
    public void comprarMusica(Musica musica) {
        if (musica.getPrecoMusica() != 0) {
            carrinhoDeCompras.add(musica);

        } else {
            biblioteca.add(musica);
            musica.addVendas();
        }
    }
    /**
     * Metodo criado para verificar se a música que o mesmo recebe já se encontra na biblioteca do utilizador loggado
     * , o metodo devolve TRUE se encontrar a musica na biblioteca e FALSE se não encontrar.
     */
    public boolean verificarBiblioteca(Musica musica) {
        for (int i = 0; i < biblioteca.size(); i++) {
            if (biblioteca.get(i).equals(musica)) {
                return true;
            }
        }
        return false;
    }
    /**
     *Metodo criado para verificar se uma música já se encontra no carrinho de compras, devolve TRUE se a música já se
     * encontrar dentro do carrinho e FALSE se o contrário.
     */
    public boolean verificarMusicaCarrinho(Musica musica) {
        for (int i = 0; i < carrinhoDeCompras.size(); i++) {
            if (carrinhoDeCompras.get(i).equals(musica)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Método criado para limpar o carrinho de compras, limpando todas as músicas que lá se encontram dentro.
     */
    public void limparCarrinho() {
        carrinhoDeCompras.clear();
    }
    /**
     * Metodo criado para verificar se um cliente já efetuou a avaliação por Rating a uma Música, o metodo vai receber
     * uma Musica para que possa percorrer o arrayList de Ratings presentes na mesma e caso encontre um rating a que
     * pertença o Cliente loggado, a mesma vai devolver TRUE, caso contrário devolver FALSE.
     */
    public boolean verificaRating(Musica musica) {
        for (Rating rating : musica.getListaRatings()) {
            if (rating.getCliente().equals(this)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Metodo criado para adicionar ou alterar o Rating de uma música, o metodo vai fazer a verificação se o Cliente já
     * avaliou a musica e caso já tenha vai alterar apenas o valor no Rating já presente, caso negativo vai criar um novo
     * Rating e adiciona-lo ao arraylList de Ratings presente na Musica.
     */
    public boolean adicionaRating(Musica musica, int avaliacao) {
        if (verificaRating(musica)) {
            musica.getRatingCliente(this).setAvaliacao(avaliacao);
            return true;
        } else {
            musica.addRating(this, avaliacao);
            return false;
        }
    }
}
