package Objetos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Compra implements Serializable {
    ///////////////////////////////////ATRIBUTOS////////////////////////////////////////////////////////////////////////
    private Cliente cliente;
    private ArrayList<Musica> listaMusicas;
    private LocalDate dataCompra;
    private double valorCompra;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////CONSTRUTORES//////////////////////////////////////////////////////////////

    /**
     * Contrutor de compra, que ao gerar uma nova compra irá calcular o seu valor puxando o ultimo preço de cada música
     * que está inserida no lista de músicas da mesma compra.
     */
    public Compra(Cliente cliente, ArrayList<Musica> listaMusicas) {
        this.cliente = cliente;

        this.listaMusicas = cliente.getCarrinhoDeCompras();
        double total = 0;

        for (int i = 0; i < listaMusicas.size(); i++) {
            cliente.addBiblioteca(listaMusicas.get(i));
            total+=listaMusicas.get(i).getPrecoMusica();
            listaMusicas.get(i).addVendas();
            listaMusicas.get(i).setFaturacao(listaMusicas.get(i).getPrecoMusica());
        }

        cliente.setCarrinhoDeCompras(new ArrayList<Musica>());

        this.dataCompra = LocalDate.now();

        this.valorCompra = total;

        cliente.descontarSaldo(valorCompra);

        cliente.setComprasEfetuadas(this);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////GETTTERS//////////////////////////////////////////////////////////////////
    public Cliente getCliente() {
        return cliente;
    }
    public LocalDate getDataCompra() {
        return dataCompra;
    }
    public double getValorCompra() {
        return valorCompra;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}


