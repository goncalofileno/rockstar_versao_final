package Objetos;

import java.io.Serializable;
import java.util.ArrayList;

public class RockstarInc implements Serializable {

    ///////////////////////////////////ATRIBUTOS////////////////////////////////////////////////////////////////////////
    private ArrayList<Artista> artistasList;
    private ArrayList<Cliente> clientesList;
    private ArrayList<Playlist> playlistsList;
    private ArrayList<Album> albunsList;
    private ArrayList<Musica> musicasList;
    private Utilizador utilizadorAtual;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////CONSTRUTORES//////////////////////////////////////////////////////////////
    public RockstarInc() {
        artistasList = new ArrayList<>();
        clientesList = new ArrayList<>();
        playlistsList = new ArrayList<>();
        albunsList = new ArrayList<>();
        musicasList = new ArrayList<>();

        //////////////////////////////////////VALORES PARA TESTE DA APP/////////////////////////////////////////////////
        addCliente("admin", "admin", "admin");
        //Artista as = new Artista("as", "as", "as", "1111");
        addArtista("as", "as", "assss", "1111");

        Album album1 = new Album("Rock in Rio", "Rock 🎸", artistasList.get(0));
        albunsList.add(album1);
        //as.addAlbum(album1);

        //Artista zecabra = new Artista("zecabra", "zecabra", "Zé Cabra", "1234");
        // artistasList.add(zecabra);
        Artista marialeal = new Artista("marialeal", "marialeal", "Maria Leal", "1234");
        artistasList.add(marialeal);
        Artista anamalhoa = new Artista("anamalhoa", "anamalhoa", "Ana Malhoa", "1234");
        artistasList.add(anamalhoa);/*
        Artista quimbarreiros = new Artista("quimbarreiros", "quimbarreiros", "Quim Barreiros", "1234");
        artistasList.add(quimbarreiros);
        Artista rosinha = new Artista("rosinha", "rosinha", "Rosinha", "1234");
        artistasList.add(rosinha);*/

        Musica deixei = new Musica("Deixei tudo por ela", artistasList.get(0), "Popular 🥁", 0.00, true);
        musicasList.add(deixei);
        deixei.novoPreco(1.5);
        deixei.novoPreco(2.5);
       /* Musica voltei = new Musica("Voltei para ela", zecabra, "Popular", 0.00,true);
        musicasList.add(voltei);*/
        Musica aqui = new Musica("Aqui só para ti", marialeal, "Popular 🥁", 0.00, true);
        musicasList.add(aqui);
        Musica traidora = new Musica("Traidora", marialeal, "Popular 🥁", 0.00, true);
        musicasList.add(traidora);
        Musica turbinada = new Musica("Turbinada", anamalhoa, "Popular 🥁", 2.5, true);
        musicasList.add(turbinada);
        turbinada.novoPreco(1.5);
        turbinada.novoPreco(1);
        /*Musica elamexe = new Musica("Ela mexe", anamalhoa, "Pop Popular", 1.5,true);
        musicasList.add(elamexe);
        Musica bacalhau = new Musica("Deixa-me cheirar teu bacalhau", quimbarreiros, "Popular", 3.00,true);
        musicasList.add(bacalhau);*/
        Musica casar = new Musica("Qual o melhor dia para casar", artistasList.get(0), "Popular 🥁", 2.5, true);
        musicasList.add(casar);
       /* Musica chupo = new Musica("Eu chupo", artistasList.get(0), "Popular", 0.00,true);
        musicasList.add(chupo);
        Musica pacote = new Musica("Eu levo no pacote", rosinha, "Popular", 1.5,true);
        musicasList.add(pacote);*/

        ArrayList musicaPopPopular = new ArrayList<Musica>();
        musicaPopPopular.add(aqui);
        musicaPopPopular.add(traidora);

        ArrayList musicaPopular = new ArrayList<Musica>();
        musicaPopular.add(deixei);
        musicaPopular.add(casar);

        Album album2 = new Album("Popular in Rio", "Popular 🥁", artistasList.get(0));
        albunsList.add(album2);
        album2.addMusica(deixei);
        artistasList.get(0).removerMusicaDeSingles(deixei);
        album2.addMusica(casar);
        artistasList.get(0).removerMusicaDeSingles(casar);

        //as.addAlbum(album2);

        Playlist PopPopular = new Playlist(musicaPopPopular, "Musicas de Pop Popular", true);
        Playlist Popular = new Playlist(musicaPopular, "Musica Popular", true);

        clientesList.get(0).addPlaylist(PopPopular);
        clientesList.get(0).addPlaylist(Popular);

        clientesList.get(0).addBiblioteca(aqui);
        clientesList.get(0).addBiblioteca(traidora);
        clientesList.get(0).addBiblioteca(deixei);
        clientesList.get(0).addBiblioteca(casar);

        getTopArtistasVendidos();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////ADDERS////////////////////////////////////////////////////////////////////
    public void addArtista(String username, String pass, String nome, String pin) {
        artistasList.add(new Artista(username, pass, nome, pin));
    }

    public void addCliente(String username, String pass, String nome) {
        clientesList.add(new Cliente(username, pass, nome));
    }

    public void addPlaylist(Playlist playlist) {
        playlistsList.add(playlist);
    }

    public void addAlbum(Album album) {
        albunsList.add(album);
    }

    public void addMusica(Musica musica) {
        musicasList.add(musica);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////MÉTODOS DIVERSOS//////////////////////////////////////////////////////////

    /**
     * Método verifica a existencia de utilizador através de username e caso o utilizador exista devolve o objeto do mesmo,
     * devolve NULL caso nao encontre.
     */
    public Utilizador verificarUtilizador(String username) {
        for (int i = 0; i < artistasList.size(); i++) {
            System.out.println(artistasList.get(i).getUsername());
            if (artistasList.get(i).getUsername().equals(username)) {
                return artistasList.get(i);
            }
        }
        for (int i = 0; i < clientesList.size(); i++) {
            if (clientesList.get(i).getUsername().equals(username)) {
                return clientesList.get(i);
            }
        }
        return null;
    }

    /**Verifica a validade do username inserido.
     * @param username Username a ser validado.
     * @return true caso seja válido ou false caso não seja.
     */
    public boolean verificarUsername(String username) {
            if (!verificarExistenciaUser(username) && !username.contains(" ")) {
                if (username.length() > 3) {
                    return true;
                } else return false;
            } else return false;
    }

    /**Verifica a validade da pass.
     * @param pass Pass a ser validada.
     * @return true caso seja uma pass válida ou false caso não seja.
     */
    public boolean verificarPass(String pass) {
        if (pass.length() > 3) {
            return true;
        } else return false;
    }

    /**Verifica a validade do pin.
     * @param pin Pin a ser validada.
     * @return true caso seja um pin válido ou false caso não seja.
     */
    public boolean verificarPin(String pin) {
        if (pin.length() == 4 && pin.matches("\\d+")) {
            return true;
        } else return false;
    }

    /**Verifica se o username inserido já existe no Objeto Rockstar.
     * @param username Username a ser verificado.
     * @return true caso o username inserido já exista no Objeto Rockstar ou false caso não exista.
     */
    public boolean verificarExistenciaUser(String username) {
        for (Artista artista : artistasList) {
            if (artista.getUsername().equals(username)) {
                return true;
            }
        }
        for (Cliente cliente : clientesList) {
            if (cliente.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**Encontra o Objeto Música através do seu título e do username do Artista que a criou.
     * @param username Username do artista que criou a música.
     * @param titulo Título da música a ser encontrada.
     * @return Objeto Música procurado.
     */
    public Musica musicaSelecionada(String username, String titulo) {
        for (int i = 0; i < musicasList.size(); i++) {
            if (musicasList.get(i).getTitulo().equals(titulo) && musicasList.get(i).getCompositor().getUsername().equals(username)) {
                return musicasList.get(i);
            }
        }

        return null;
    }

    public void removerPlaylist(Playlist playlist) {
        playlistsList.remove(playlist);
    }

    public ArrayList<Musica> getMusicasList() {
        return musicasList;
    }

    /**Método que faz o filtro das músicas que contém o conjunto de caracteres inserido.
     * @param listaDeMusicas ArrayList das músicas a serem filtradas.
     * @param titulo Texto que vai filtrar as músicas.
     * @return ArrayList de músicas filtradas.
     */
    public ArrayList<Musica> pesquisaTitulo(ArrayList<Musica> listaDeMusicas, String titulo) {
        ArrayList listaPesquisada = new ArrayList<Musica>();
        titulo = titulo.toLowerCase();
        for (Musica musica : listaDeMusicas) {
            if (musica.getTitulo().toLowerCase().contains(titulo)) {
                listaPesquisada.add(musica);
            }
        }
        return listaPesquisada;
    }

    /**Método que faz o filtro das artistas que contém o conjunto de caracteres inserido.
     * @param listaDeMusicas ArrayList dos artistas a serem filtradas.
     * @param artista Texto que vai filtrar os artistas.
     * @return ArrayList de artistas filtrados.
     */
    public ArrayList<Musica> pesquisaArtista(ArrayList<Musica> listaDeMusicas, String artista) {
        ArrayList listaPesquisada = new ArrayList<Musica>();
        artista = artista.toLowerCase();
        for (Musica musica : listaDeMusicas) {
            if (musica.getCompositor().getNome().toLowerCase().contains(artista)) {
                listaPesquisada.add(musica);
            }
        }
        return listaPesquisada;
    }
    /**Método que faz o filtro dos albuns que contém o conjunto de caracteres inserido.
     * @param listaDeMusicas ArrayList dos albuns a serem filtradas.
     * @param titulo Texto que vai filtrar os albuns.
     * @return ArrayList de albuns filtrados.
     */
    public ArrayList<Musica> pesquisaAlbum(ArrayList<Musica> listaDeMusicas, String titulo) {
        ArrayList listaPesquisada = new ArrayList<Musica>();
        titulo = titulo.toLowerCase();
        for (Musica musica : listaDeMusicas) {
            if (musica.getAlbum() != null) {
                if (musica.getAlbum().getNome().toLowerCase().contains(titulo)) {
                    listaPesquisada.add(musica);
                }
            }
        }
        return listaPesquisada;
    }

    /**Método para filtrar apenas as músicas que têm o valor do atributo EstadoAtividade como true.
     * @return ArrayList de músicas com o valor do atributo EstadoAtividade igual a true.
     */
    public ArrayList<Musica> musicasVisiveis() {
        ArrayList<Musica> musicasVisiveis = new ArrayList<>();
        for (int i = 0; i < musicasList.size(); i++) {
            if (musicasList.get(i).isEstadoAtividade()) {
                musicasVisiveis.add(musicasList.get(i));
            }
        }
        return musicasVisiveis;
    }

    public int getTotalUtilizadores() {
        int i = 0, j = 0;
        for (Cliente cliente : clientesList) {
            i = i + 1;
        }
        for (Artista artista : artistasList) {
            j = j + 1;
        }
        return i + j;
    }

    public int getTotalMusicas() {
        int i = 0;
        for (Musica musica : musicasList) {
            i = i + 1;
        }
        return i;
    }

    public double getTotalValor() {
        double valor = 0;
        for (Musica musica : musicasList) {
            valor = valor + musica.getPrecoMusica();
        }
        return valor;
    }

    public double getFaturacaoTotal() {
        double faturacao = 0;
        for (Musica musica : musicasList) {
            faturacao = faturacao + musica.getFaturacao();
        }
        return faturacao;
    }

    public int[] getAlbunsPorGenero() {
        int[] albunsPorGenero = new int[8];

        for (int i = 0; i < albunsList.size(); i++) {
            if (albunsList.get(i).getGenero().equals("Rock 🎸")) {
                albunsPorGenero[0]++;
            } else if (albunsList.get(i).getGenero().equals("Rap 🎤")) {
                albunsPorGenero[1]++;
            } else if (albunsList.get(i).getGenero().equals("Pop 🔫")) {
                albunsPorGenero[2]++;
            } else if (albunsList.get(i).getGenero().equals("Clássica 🎻")) {
                albunsPorGenero[3]++;
            } else if (albunsList.get(i).getGenero().equals("Jazz 🎺")) {
                albunsPorGenero[4]++;
            } else if (albunsList.get(i).getGenero().equals("Metal 🤘")) {
                albunsPorGenero[5]++;
            } else if (albunsList.get(i).getGenero().equals("Popular 🥁")) {
                albunsPorGenero[6]++;
            } else if (albunsList.get(i).getGenero().equals("Eletrónica 🎧")) {
                albunsPorGenero[7]++;
            }
        }
        return albunsPorGenero;
    }

    /**Método que, através da lista guardada no atributo artistasList, vai filtrar os artistas com mais vendas.
     * @return ArrayList dos artistas com mais vendas.
     */
    public ArrayList<Artista> getTopArtistasVendidos() {
        boolean verificar = false;
        ArrayList<Artista> artistasTotal = new ArrayList<>();
        artistasTotal.addAll(artistasList);

        for (int i = 0; i < artistasTotal.size() - 1; i++) {
            if (artistasTotal.get(i).getVendasTotal() < artistasTotal.get(i + 1).getVendasTotal()) {
                verificar = true;
                Artista aux = artistasTotal.get(i);
                artistasTotal.set(i, artistasTotal.get(i + 1));
                artistasTotal.set(i + 1, aux);
            }
            if ((i == artistasTotal.size() - 2) && verificar == true) {
                verificar = false;
                i = -1;
            }
        }

        int tamanho;
        if (artistasTotal.size() >= 5) {
            tamanho = 5;
        } else {
            tamanho = artistasTotal.size();
        }

        ArrayList<Artista> top5Artistas = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            top5Artistas.add(artistasTotal.get(i));
        }
        return top5Artistas;
    }

    /**Método que, através da lista guardada no atributo musicasList, vai filtrar as músicas com mais vendas.
     * @return ArrayList dos músicas com mais vendas.
     */
    public ArrayList<Musica> getTop5MusicasVendidas() {
        ArrayList<Musica> totalMusicas = new ArrayList<>();
        totalMusicas.addAll(musicasList);

        boolean verificar = false;
        for (int i = 0; i < totalMusicas.size() - 1; i++) {
            if (totalMusicas.get(i).getVendas() < totalMusicas.get(i + 1).getVendas()) {
                verificar = true;
                Musica aux = totalMusicas.get(i);
                totalMusicas.set(i, totalMusicas.get(i + 1));
                totalMusicas.set(i + 1, aux);
            }
            if ((i == totalMusicas.size() - 2) && verificar == true) {
                verificar = false;
                i = -1;
            }
        }

        int tamanho;
        if (totalMusicas.size() >= 5) {
            tamanho = 5;
        } else {
            tamanho = totalMusicas.size();
        }

        ArrayList<Musica> top5 = new ArrayList<>();

        for (int i = 0; i < tamanho; i++) {
            top5.add(totalMusicas.get(i));
        }
        return top5;
    }
}



