package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.BaixaDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Baixa;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;

public class BaixaDAOImpl implements BaixaDAO {

    private AppDatabase database;

    @Inject
    public BaixaDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarBaixa(List<Baixa> baixas) {
        database.baixaDAO().importarBaixa(baixas);
    }

    @Override
    public void removerTodasBaixas() {
        database.baixaDAO().removerTodasBaixas();
    }

    @Override
    public Baixa pesquisaBaixaPorPatrimonio(String pNUMERO) {
        return database.baixaDAO().pesquisaBaixaPorPatrimonio(pNUMERO);
    }

    @Override
    public Baixa pesquisaBaixaPorSerial(String pSERIAL) {
        return database.baixaDAO().pesquisaBaixaPorSerial(pSERIAL);
    }

    @Override
    public List<Baixa> pesquisaBaixaPorSerialPatrimonio(String pNUMERO, String pSERIAL) {
        return database.baixaDAO().pesquisaBaixaPorSerialPatrimonio(pNUMERO,pSERIAL);
    }

    @Override
    public void inserirBaixa(Baixa baixa) {
        this.database.baixaDAO().inserirBaixa(baixa);
    }

    @Override
    public void removerBaixaPorCodigo(Baixa baixa) {
        this.database.baixaDAO().removerBaixaPorCodigo(baixa);
    }

    @Override
    public List<Baixa> pesquisaBaixaGeral() {
        return this.database.baixaDAO().pesquisaBaixaGeral();
    }

    @Override
    public List<Baixa> pesquisaBaixaFlag() {
        return this.database.baixaDAO().pesquisaBaixaFlag();
    }

    @Override
    public void deletarBaixaFlag() {
        this.database.baixaDAO().deletarBaixaFlag();
    }

    public void close(){
        database.destroyInstance();
    }

}
