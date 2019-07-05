package br.com.sankhya.bh.pedido;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;

public class GerarPedidos implements AcaoRotinaJava {

    public void gerarPedidos(ContextoAcao ct) throws Exception {
        throw new Exception("Ainda não Implementado.");
    }

    @Override
    public void doAction(ContextoAcao contextoAcao) throws Exception {
        gerarPedidos(contextoAcao);
    }
}
