package br.com.sankhya.bh.vendas;

import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.event.ModifingFields;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.*;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;


import javax.rmi.CORBA.Util;
import java.math.BigDecimal;
import java.sql.ResultSet;

public class AlterarMedia implements EventoProgramavelJava
{

    JapeWrapper girDAO = JapeFactory.dao("ConsolidacaoGiro");
    JapeWrapper medVendasDAO = JapeFactory.dao("AD_BHGIRMEDVENDA");



    private void gerarMediaCalculada(BigDecimal codProd, int ano, DynamicVO vo) throws Exception {

        NativeSql query = getNativeSql();


       query.setNamedParameter("ANO", ano);
        query.setNamedParameter("CODPROD", codProd);

        query.appendSql("SELECT QTDJAN,QTDFEV,QTDMAR,QTDABR,QTDMAI,QTDJUN,QTDJUL,QTDAGO,QTDSET,QTDOUT,QTDNOV,QTDDEZ FROM SANKHYA.VGF_QTDVENDA_GIR V WHERE V.CODPROD = :CODPROD AND V.ANO = :ANO");
        ResultSet resultSet = query.executeQuery();


        if(resultSet.next()){
            do{
                medVendasDAO.prepareToUpdate(vo)
                        .set("QTDJAN",resultSet.getBigDecimal("QTDJAN"))
                        .set("QTDFEV",resultSet.getBigDecimal("QTDFEV"))
                        .set("QTDMAR",resultSet.getBigDecimal("QTDMAR"))
                        .set("QTDABR",resultSet.getBigDecimal("QTDABR"))
                        .set("QTDMAI",resultSet.getBigDecimal("QTDMAI"))
                        .set("QTDJUN",resultSet.getBigDecimal("QTDJUN"))
                        .set("QTDJUL",resultSet.getBigDecimal("QTDJUL"))
                        .set("QTDAGO",resultSet.getBigDecimal("QTDAGO"))
                        .set("QTDSET",resultSet.getBigDecimal("QTDSET"))
                        .set("QTDOUT",resultSet.getBigDecimal("QTDOUT"))
                        .set("QTDNOV",resultSet.getBigDecimal("QTDNOV"))
                        .set("QTDDEZ",resultSet.getBigDecimal("QTDDEZ")).update();

            }while(resultSet.next());
        }else{
            throw new Exception("<br><b>Não Existe Qtds de Venda para Esse produto na Análise de Giro</b>");
        }
    }


    public NativeSql getNativeSql() {
        JdbcWrapper jdbc = null;
        EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
        jdbc = dwfFacade.getJdbcWrapper();
        return new NativeSql(jdbc);
    }





    @Override
    public void beforeInsert(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void beforeUpdate(PersistenceEvent persistenceEvent) throws Exception {

        ModifingFields modifingFields = persistenceEvent.getModifingFields();
        DynamicVO vo = (DynamicVO) persistenceEvent.getVo();

        String tipomedia = vo.asString("TIPOMEDIA");
        BigDecimal codprod = vo.asBigDecimal("CODPROD");
        int ano = vo.asInt("ANO");

        if (modifingFields.isModifing("TIPOMEDIA")){
            if("C".equals(tipomedia)){
                gerarMediaCalculada(codprod,ano,vo);
            }
        }
        else if (!modifingFields.isModifing("CODPROD") || !modifingFields.isModifing("ANO")){
            if("C".equals(tipomedia)){
                throw new Exception("<br><b>Não é possível Alterar Quantidades Quando o tipo Média for Calculado.</b> ");
            }
        }
    }

    @Override
    public void beforeDelete(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void afterInsert(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void afterUpdate(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void afterDelete(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext transactionContext) throws Exception {

    }
}
