package Objetos;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist extends GrupoDeMusicas implements Serializable {
    //////////////////////////////////////////ATRIBUTOS/////////////////////////////////////////////////////////////////
    private boolean visibilidade;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////CONSTRUTORES//////////////////////////////////////////////////////////////
    /**
     *Cria uma playlist já com um array list de músicas atribuído
     */
    public Playlist(ArrayList<Musica> musicas, String nome, boolean visibilidade) {
        super(musicas, nome);
        this.visibilidade = visibilidade;
    }
    /**
     *Cria uma playlist sem um arraylist de músicas atribuido.
     */
    public Playlist(String nome, boolean visibilidade) {
        super(nome);
        this.visibilidade = visibilidade;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public boolean addMusica(Musica musica) {
        if (getMusicas().contains(musica)){
            return false;
        }
        else {
            getMusicas().add(musica);
            return true;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
