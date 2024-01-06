package Objetos;

import java.io.Serializable;
import java.util.ArrayList;

public class Artista extends Utilizador implements Serializable {
    ///////////////////////////////////ATRIBUTOS////////////////////////////////////////////////////////////////////////
    private String pin;
    private ArrayList<Album> albuns;
    private ArrayList<Musica> singles;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////CONSTRUTORES//////////////////////////////////////////////////////////////
    public Artista(String username, String pass, String nome, String pin) {
        super(username, pass, nome);
        this.albuns = new ArrayList<>();
        this.singles = new ArrayList<>();
        this.pin = pin;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////GETTTERS//////////////////////////////////////////////////////////////////
    public String getPin() {
        return pin;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////ADDERS///////////////////////////////////////////////////
    public void addAlbum(Album album) {
        albuns.add(album);
    }

    public void removerMusicaDeSingles(Musica musica){
        singles.remove(musica);
    }

    public void addSingles(Musica musica) {
        singles.add(musica);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////METODOS DIVERSOS///////////////////////////////////////////////////

    /**
     * Metodo criado para receber um pin em formato String e verificar se o mesmo equivale ao artista em questão, devolve
     * variavel booleana, true se corresponder e false se não corresponder
     */
    public boolean verificarLoginPin(String pin) {
        if (this.pin.equals(pin)) {
            return true;
        }
        return false;
    }

    public ArrayList<Album> getAlbuns() {
        return albuns;
    }

    /**Verifica se o Artista tem algum Album com o nome igual ao titulo inserido.
     * @param titulo Título a ser verificado.
     * @return true caso exista um Album com o título inserido ou false caso não exista.
     */
    public boolean verificarAlbum(String titulo){
        for (int i=0;i<albuns.size();i++){
            if(albuns.get(i).getNome().equals(titulo)){
                return true;
            }
        }
        return false;
    }

    /**Verifica se o Artista tem alguma Musica com o nome igual ao nome inserido.
     * @param nomeMusica Nome a ser verificado.
     * @return true caso exista uma Musica com o título inserido ou false caso não exista.
     */
    public boolean verificarMusica(String nomeMusica){
        for (int i=0;i<albuns.size();i++){
            for(int j=0;j<albuns.get(i).getMusicas().size();j++) {
                if (albuns.get(i).getMusicas().get(j).getTitulo().equals(nomeMusica)){
                    return true;
                }
            }
        }

        for(int i=0;i<singles.size();i++){
            if(singles.get(i).getTitulo().equals(nomeMusica)){
                return true;
            }
        }
        return false;
    }

    /**Método que vai buscar os títulos de todos os Objetos Album do Artista.
     * @return Array de String com o nome "Singles" associado ao ídice 0 e com o nome de todos os albuns do Artista.
     */
    public String[] titulosAlbuns(){
        String[] titulos=new String[albuns.size()+1];

        titulos[0]="Singles";

        for(int i=0;i<albuns.size();i++){
            titulos[i+1]=albuns.get(i).getNome();
        }
        return titulos;
    }

    public void removeMusicaSingles(Musica musica){
        singles.remove(musica);
    }

    /**Método que devolve todas as músicas do Artista, estejam estas inseridas em albuns ou não.
     * @return ArrayList de todas as músicas do Artista.
     */
    public ArrayList<Musica> getTotalMusicas(){
        ArrayList<Musica> totalMusicas=new ArrayList<>();

        for (int i=0;i<albuns.size();i++){
            totalMusicas.addAll(albuns.get(i).getMusicas());
        }

        totalMusicas.addAll(singles);
      return totalMusicas;
    }

    /**Método que faz o somatório das vendas de todas as músicas do Artista.
     * @return Total de músicas vendidas pelo Artista.
     */
    public int getVendasTotal(){
        ArrayList<Musica> musicasTotal=new ArrayList<>();
        musicasTotal.addAll(getTotalMusicas());
        int vendasTotal=0;
        for (int i=0;i<musicasTotal.size();i++){
            vendasTotal+=musicasTotal.get(i).getVendas();
        }
        return vendasTotal;
    }
}
