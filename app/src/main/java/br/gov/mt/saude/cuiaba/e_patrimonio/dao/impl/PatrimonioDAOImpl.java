package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.PatrimonioDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;

public class PatrimonioDAOImpl implements PatrimonioDAO {

    private AppDatabase database;

    @Inject
    public PatrimonioDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarPatrimonio(List<Patrimonio> patrimonios) {
        this.database.patrimonioDAO().importarPatrimonio(patrimonios);
    }

    @Override
    public void removerTodosPatrimonio() {
        this.database.patrimonioDAO().removerTodosPatrimonio();
    }

    @Override
    public Patrimonio pesquisaPorPatrimonio(String pNUMERO) {
        return this.database.patrimonioDAO().pesquisaPorPatrimonio(pNUMERO);
    }

    @Override
    public Patrimonio pesquisaPorPatrimonio(String pNUMERO, String pSERIAL) {
        return database.patrimonioDAO().pesquisaPorPatrimonio(pNUMERO,pSERIAL);
    }

    @Override
    public List<Patrimonio> pesquisaPorNumeroPatrimonioESerial(String pNUMERO, String pSERIAL) {
        return this.database.patrimonioDAO().pesquisaPorNumeroPatrimonioESerial(pNUMERO,pSERIAL);
    }

    @Override
    public List<Patrimonio> pesquisaTodosPatrimonios() {
        return this.database.patrimonioDAO().pesquisaTodosPatrimonios();
    }

    @Override
    public List<Patrimonio> pesquisaPatrimonioFlag() {
        return this.database.patrimonioDAO().pesquisaPatrimonioFlag();
    }

    @Override
    public void atualizarPatrimonio(Patrimonio patrimonio) {
        this.database.patrimonioDAO().atualizarPatrimonio(patrimonio);
    }

    @Override
    public void deletarPatrimonio(Patrimonio patrimonio) {
        this.database.patrimonioDAO().deletarPatrimonio(patrimonio);
    }

    @Override
    public void inserirPatrimonio(Patrimonio patrimonio) {
        this.database.patrimonioDAO().inserirPatrimonio(patrimonio);
    }

    public void close(){
        database.destroyInstance();
    }

}
