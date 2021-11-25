package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.SetorDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Setor;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;

public class SetorDAOImpl  implements SetorDAO {
    private AppDatabase database;

    @Inject
    public SetorDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarSetor(List<Setor> setors) {
        this.database.setorDAO().importarSetor(setors);
    }

    @Override
    public void removerTodosSetores() {
        this.database.setorDAO().removerTodosSetores();
    }

    @Override
    public List<Setor> pesquisaSetorPorSecretaria(int pSECRETARIA) {
        return this.database.setorDAO().pesquisaSetorPorSecretaria(pSECRETARIA);
    }

    @Override
    public Setor pesquisaSetorPorCodigo(int pCODIGO) {
        return this.database.setorDAO().pesquisaSetorPorCodigo(pCODIGO);
    }

    @Override
    public List<Setor> pesquisaTodosSetores() {
        return this.database.setorDAO().pesquisaTodosSetores();
    }

    public void close(){
        database.destroyInstance();
    }
}
