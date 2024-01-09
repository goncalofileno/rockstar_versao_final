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
        criarTeste();
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
     * @param username username a verificar se existe
     * @return retorna o objeto Utilizador caso encontre um.
     */
    public Utilizador verificarUtilizador(String username) {
        for (int i = 0; i < artistasList.size(); i++) {
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
            } else if (albunsList.get(i).getGenero().equals("Rap 🔫")) {
                albunsPorGenero[1]++;
            } else if (albunsList.get(i).getGenero().equals("Pop 🎤")) {
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

    public void criarTeste(){
        addCliente("admin", "admin", "Cliente Teste");

        addArtista("as", "as", "Artista Teste", "1111");

        Artista anamalhoa = new Artista("anamalhoa", "anamalhoa", "Ana Malhoa", "1234");
        artistasList.add(anamalhoa);

        Musica turbinada = new Musica("Turbinada", anamalhoa, "Popular 🥁", 2.5, true);
        musicasList.add(turbinada);
        Musica rebolation = new Musica("Rebolation", anamalhoa, "Popular 🥁", 3.0, true);
        musicasList.add(rebolation);
        Musica chuvaEstrelas = new Musica("Chuva de Estrelas", anamalhoa, "Popular 🥁", 2.8, true);
        musicasList.add(chuvaEstrelas);
        Musica poderosa = new Musica("Poderosa", anamalhoa, "Popular 🥁", 2.7, true);
        musicasList.add(poderosa);
        Musica vemSerFeliz = new Musica("Vem Ser Feliz", anamalhoa, "Popular 🥁", 2.9, true);
        musicasList.add(vemSerFeliz);
        Musica dancaDoTchu = new Musica("Dança do Tchu Tcha Tcha", anamalhoa, "Popular 🥁", 3.2, true);
        musicasList.add(dancaDoTchu);
        Musica lokaLoka = new Musica("Loka Loka", anamalhoa, "Popular 🥁", 2.6, true);
        musicasList.add(lokaLoka);
        Musica telemovel = new Musica("Telemóvel", anamalhoa, "Popular 🥁", 3.1, true);
        musicasList.add(telemovel);
        Musica confiaEmMim = new Musica("Confia em Mim", anamalhoa, "Popular 🥁", 2.4, true);
        musicasList.add(confiaEmMim);
        Musica vouPartir = new Musica("Vou Partir", anamalhoa, "Popular 🥁", 2.8, true);
        musicasList.add(vouPartir);

        Artista xutosEPontapes = new Artista("xutosEPontapes", "xutosEPontapes", "Xutos e Pontapés", "1234");
        artistasList.add(xutosEPontapes);

        Musica contentores = new Musica("Contentores", xutosEPontapes, "Rock 🎸", 4.0, true);
        Musica aiSeEleCai = new Musica("Ai Se Ele Cai", xutosEPontapes, "Rock 🎸", 3.5, true);
        Musica circoDeFeras = new Musica("Circo de Feras", xutosEPontapes, "Rock 🎸", 3.8, true);
        Musica maria = new Musica("Maria", xutosEPontapes, "Rock 🎸", 3.2, true);
        Musica homensDoLeme = new Musica("Homens do Leme", xutosEPontapes, "Rock 🎸", 4.2, true);
        Musica paraTiMaria = new Musica("Para Ti Maria", xutosEPontapes, "Rock 🎸", 3.7, true);
        Musica chuvaDissolvente = new Musica("Chuva Dissolvente", xutosEPontapes, "Rock 🎸", 3.9, true);
        Musica remarRemar = new Musica("Remar, Remar", xutosEPontapes, "Rock 🎸", 3.6, true);
        Musica nAoSouOunico = new Musica("Não Sou o Único", xutosEPontapes, "Rock 🎸", 4.1, true);
        Musica vicio = new Musica("Vício", xutosEPontapes, "Rock 🎸", 3.4, true);
        musicasList.add(contentores);
        musicasList.add(aiSeEleCai);
        musicasList.add(circoDeFeras);
        musicasList.add(maria);
        musicasList.add(homensDoLeme);
        musicasList.add(paraTiMaria);
        musicasList.add(chuvaDissolvente);
        musicasList.add(remarRemar);
        musicasList.add(nAoSouOunico);
        musicasList.add(vicio);

        Artista samTheKid = new Artista("samTheKid", "samTheKid", "Sam The Kid", "1234");
        artistasList.add(samTheKid);

        Musica poetasDeKaraokes = new Musica("Poetas de Karaokes", samTheKid, "Rap 🔫", 3.8, true);
        musicasList.add(poetasDeKaraokes);
        Musica estamosAqui = new Musica("Estamos Aqui", samTheKid, "Rap 🔫", 4.2, true);
        musicasList.add(estamosAqui);
        Musica retrato = new Musica("Retrato", samTheKid, "Rap 🔫", 3.5, true);
        musicasList.add(retrato);
        Musica sofia = new Musica("Sofia", samTheKid, "Rap 🔫", 3.9, true);
        musicasList.add(sofia);
        Musica tudoMuda = new Musica("Tudo Muda", samTheKid, "Rap 🔫", 3.7, true);
        musicasList.add(tudoMuda);
        Musica naoPercebes = new Musica("Não Percebes", samTheKid, "Rap 🔫", 4.1, true);
        musicasList.add(naoPercebes);
        Musica oDeusNaoQuis = new Musica("O Deus Não Quis", samTheKid, "Rap 🔫", 3.6, true);
        musicasList.add(oDeusNaoQuis);
        Musica oRelatorio = new Musica("O Relatório", samTheKid, "Rap 🔫", 4.0, true);
        musicasList.add(oRelatorio);
        Musica kandandu = new Musica("Kandandu", samTheKid, "Rap 🔫", 3.4, true);
        musicasList.add(kandandu);
        Musica retrospectivaDeUmAmorProfundo = new Musica("Retrospectiva de um Amor Profundo", samTheKid, "Rap 🔫", 3.8, true);
        musicasList.add(retrospectivaDeUmAmorProfundo);

        Artista beethoven = new Artista("beethoven", "beethoven", "Beethoven", "1234");
        artistasList.add(beethoven);

        Musica sonataAoLuarmoonlight = new Musica("Sonata ao Luar (Moonlight Sonata)", beethoven, "Clássica 🎻", 6.5, true);
        musicasList.add(sonataAoLuarmoonlight);
        Musica sinfoniaNona = new Musica("Sinfonia Nona (Choral)", beethoven, "Clássica 🎻", 8.0, true);
        musicasList.add(sinfoniaNona);
        Musica paraElisa = new Musica("Para Elisa", beethoven, "Clássica 🎻", 4.2, true);
        musicasList.add(paraElisa);
        Musica concertoParaPianoNumero5 = new Musica("Concerto para Piano Número 5 (Imperador)", beethoven, "Clássica 🎻", 7.3, true);
        musicasList.add(concertoParaPianoNumero5);
        Musica sonataClaroDeLua = new Musica("Sonata Claro de Lua", beethoven, "Clássica 🎻", 5.8, true);
        musicasList.add(sonataClaroDeLua);
        Musica sonataApassionata = new Musica("Sonata Apassionata", beethoven, "Clássica 🎻", 6.0, true);
        musicasList.add(sonataApassionata);
        Musica trioArchduke = new Musica("Trio Archduke", beethoven, "Clássica 🎻", 6.8, true);
        musicasList.add(trioArchduke);
        Musica concertoViolino = new Musica("Concerto para Violino em Ré Maior", beethoven, "Clássica 🎻", 7.5, true);
        musicasList.add(concertoViolino);
        Musica missaSolemnis = new Musica("Missa Solemnis", beethoven, "Clássica 🎻", 9.0, true);
        musicasList.add(missaSolemnis);
        Musica coriolano = new Musica("Abertura Coriolano", beethoven, "Clássica 🎻", 5.7, true);
        musicasList.add(coriolano);

        Artista milesDavis = new Artista("milesDavis", "milesDavis", "Miles Davis", "1234");
        artistasList.add(milesDavis);

        Musica soWhat = new Musica("So What", milesDavis, "Jazz 🎺", 9.0, true);
        musicasList.add(soWhat);
        Musica kindOfBlue = new Musica("Kind of Blue", milesDavis, "Jazz 🎺", 11.2, true);
        musicasList.add(kindOfBlue);
        Musica freddieFreeloader = new Musica("Freddie Freeloader", milesDavis, "Jazz 🎺", 6.7, true);
        musicasList.add(freddieFreeloader);
        Musica blueInGreen = new Musica("Blue in Green", milesDavis, "Jazz 🎺", 5.4, true);
        musicasList.add(blueInGreen);
        Musica allBlues = new Musica("All Blues", milesDavis, "Jazz 🎺", 11.8, true);
        musicasList.add(allBlues);
        Musica summertime = new Musica("Summertime", milesDavis, "Jazz 🎺", 3.9, true);
        musicasList.add(summertime);
        Musica milestones = new Musica("Milestones", milesDavis, "Jazz 🎺", 7.5, true);
        musicasList.add(milestones);
        Musica roundMidnight = new Musica("Round Midnight", milesDavis, "Jazz 🎺", 5.6, true);
        musicasList.add(roundMidnight);
        Musica autumnLeaves = new Musica("Autumn Leaves", milesDavis, "Jazz 🎺", 8.2, true);
        musicasList.add(autumnLeaves);
        Musica nardis = new Musica("Nardis", milesDavis, "Jazz 🎺", 6.3, true);
        musicasList.add(nardis);

        Artista metallica = new Artista("metallica", "metallica", "Metallica", "1234");
        artistasList.add(metallica);

        Musica enterSandman = new Musica("Enter Sandman", metallica, "Metal 🤘", 5.5, true);
        musicasList.add(enterSandman);
        Musica masterOfPuppets = new Musica("Master of Puppets", metallica, "Metal 🤘", 8.4, true);
        musicasList.add(masterOfPuppets);
        Musica one = new Musica("One", metallica, "Metal 🤘", 7.1, true);
        musicasList.add(one);
        Musica nothingElseMatters = new Musica("Nothing Else Matters", metallica, "Metal 🤘", 6.2, true);
        musicasList.add(nothingElseMatters);
        Musica seekAndDestroy = new Musica("Seek & Destroy", metallica, "Metal 🤘", 6.8, true);
        musicasList.add(seekAndDestroy);
        Musica fadeToBlack = new Musica("Fade to Black", metallica, "Metal 🤘", 7.5, true);
        musicasList.add(fadeToBlack);
        Musica sadButTrue = new Musica("Sad But True", metallica, "Metal 🤘", 5.9, true);
        musicasList.add(sadButTrue);
        Musica theUnforgiven = new Musica("The Unforgiven", metallica, "Metal 🤘", 6.7, true);
        musicasList.add(theUnforgiven);
        Musica forWhomTheBellTolls = new Musica("For Whom the Bell Tolls", metallica, "Metal 🤘", 5.8, true);
        musicasList.add(forWhomTheBellTolls);
        Musica rideTheLightning = new Musica("Ride the Lightning", metallica, "Metal 🤘", 6.6, true);
        musicasList.add(rideTheLightning);

        Artista avicii = new Artista("avicii", "avicii", "Avicii", "1234");
        artistasList.add(avicii);
        Musica wakeMeUp = new Musica("Wake Me Up", avicii, "Electrónica 🎧", 4.7, true);
        musicasList.add(wakeMeUp);
        Musica levels = new Musica("Levels", avicii, "Electrónica 🎧", 3.9, true);
        musicasList.add(levels);
        Musica heyBrother = new Musica("Hey Brother", avicii, "Electrónica 🎧", 4.2, true);
        musicasList.add(heyBrother);
        Musica theNights = new Musica("The Nights", avicii, "Electrónica 🎧", 3.5, true);
        musicasList.add(theNights);
        Musica waitingForLove = new Musica("Waiting for Love", avicii, "Electrónica 🎧", 3.8, true);
        musicasList.add(waitingForLove);
        Musica withoutYou = new Musica("Without You", avicii, "Electrónica 🎧", 3.6, true);
        musicasList.add(withoutYou);
        Musica silhouettes = new Musica("Silhouettes", avicii, "Electrónica 🎧", 4.1, true);
        musicasList.add(silhouettes);
        Musica iCouldBeTheOne = new Musica("I Could Be the One", avicii, "Electrónica 🎧", 4.0, true);
        musicasList.add(iCouldBeTheOne);
        Musica levelsInTheAir = new Musica("Levels (In the Air)", avicii, "Electrónica 🎧", 3.7, true);
        musicasList.add(levelsInTheAir);
        Musica addictedToYou = new Musica("Addicted to You", avicii, "Electrónica 🎧", 3.4, true);
        musicasList.add(addictedToYou);
    }
}



