package br.com.sankhya.bh.pedido;

import br.com.sankhya.extensions.actionbutton.QueryExecutor;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.Jape;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

import java.math.BigDecimal;
import java.sql.ResultSet;

public class ValidacaoPesoMontagem implements EventoProgramavelJava {
    @Override
    public void beforeInsert(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void beforeUpdate(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void beforeDelete(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void afterInsert(PersistenceEvent persistenceEvent) throws Exception {

    }


    @Override
    public void afterUpdate(PersistenceEvent persistenceEvent) throws Exception {
        DynamicVO pedidoVo = (DynamicVO) persistenceEvent.getVo();
        BigDecimal codprod = pedidoVo.asBigDecimal("CODPROD");
        BigDecimal nucab = pedidoVo.asBigDecimal("NUCAB");
        JapeWrapper proDAO = JapeFactory.dao("Produto");
        JapeWrapper cabDAO = JapeFactory.dao("AD_BHCNTRCAB");
        DynamicVO cabVo = cabDAO.findByPK(nucab);
        DynamicVO produtoVo = proDAO.findByPK(codprod);
        BigDecimal pesoLiq = produtoVo.asBigDecimal("PESOLIQ");
        BigDecimal pesoCab = cabVo.asBigDecimal("QTDPESO");

        NativeSql sql = getNativeSql();
        sql.appendSql("SELECT\n" +
                "SUM(NVL(PED.PED1,0)*PRO.PESOLIQ) AS TOTAL_PED1,\n" +
                "SUM(NVL(PED.PED2,0)*PRO.PESOLIQ) AS TOTAL_PED2,\n" +
                "SUM(NVL(PED.PED3,0)*PRO.PESOLIQ) AS TOTAL_PED3,\n" +
                "SUM(NVL(PED.PED4,0)*PRO.PESOLIQ) AS TOTAL_PED4,\n" +
                "SUM(NVL(PED.PED5,0)*PRO.PESOLIQ) AS TOTAL_PED5,\n" +
                "SUM(NVL(PED.PED6,0)*PRO.PESOLIQ) AS TOTAL_PED6,\n" +
                "SUM(NVL(PED.PED7,0)*PRO.PESOLIQ) AS TOTAL_PED7,\n" +
                "SUM(NVL(PED.PED8,0)*PRO.PESOLIQ) AS TOTAL_PED8,\n" +
                "SUM(NVL(PED.PED9,0)*PRO.PESOLIQ) AS TOTAL_PED9,\n" +
                "SUM(NVL(PED.PED10,0)*PRO.PESOLIQ) AS TOTAL_PED10\n" +
                "FROM AD_BHCNTRPED PED\n" +
                "INNER JOIN TGFPRO PRO ON PRO.CODPROD = PED.CODPROD\n" +
                "INNER JOIN AD_BHCNTRCAB CAB ON CAB.NUCAB = PED.NUCAB");

        ResultSet resultSet = sql.executeQuery();

        while(resultSet.next()){
            if(resultSet.getBigDecimal("TOTAL_PED1").compareTo(pesoCab) > 0){
                throw new Exception("Peso do Pedido 1 está acima da capacidade do container!");
            }
            else if(resultSet.getBigDecimal("TOTAL_PED2").compareTo(pesoCab) > 0){
                throw new Exception("Peso do Pedido 2 está acima da capacidade do container!");
            }
            else if(resultSet.getBigDecimal("TOTAL_PED3").compareTo(pesoCab) > 0){
                throw new Exception("Peso do Pedido 3 está acima da capacidade do container!");
            }
            else if(resultSet.getBigDecimal("TOTAL_PED4").compareTo(pesoCab) > 0){
                throw new Exception("Peso do Pedido 4 está acima da capacidade do container!");
            }
            else if(resultSet.getBigDecimal("TOTAL_PED5").compareTo(pesoCab) > 0){
                throw new Exception("Peso do Pedido 5 está acima da capacidade do container!");
            }
            else if(resultSet.getBigDecimal("TOTAL_PED6").compareTo(pesoCab) > 0){
                throw new Exception("Peso do Pedido 6 está acima da capacidade do container!");
            }
            else if(resultSet.getBigDecimal("TOTAL_PED7").compareTo(pesoCab) > 0){
                throw new Exception("Peso do Pedido 7 está acima da capacidade do container!");
            }
            else if(resultSet.getBigDecimal("TOTAL_PED8").compareTo(pesoCab) > 0){
                throw new Exception("Peso do Pedido 8 está acima da capacidade do container!");
            }
            else if(resultSet.getBigDecimal("TOTAL_PED9").compareTo(pesoCab) > 0){
                throw new Exception("Peso do Pedido 9 está acima da capacidade do container!");
            }
            else if(resultSet.getBigDecimal("TOTAL_PED10").compareTo(pesoCab) > 0){
                throw new Exception("Peso do Pedido 10 está acima da capacidade do container!");
            }
        }

    }

    @Override
    public void afterDelete(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext transactionContext) throws Exception {

    }
    public NativeSql getNativeSql() {
        JdbcWrapper jdbc = null;
        EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
        jdbc = dwfFacade.getJdbcWrapper();
        return new NativeSql(jdbc);
    }
}
