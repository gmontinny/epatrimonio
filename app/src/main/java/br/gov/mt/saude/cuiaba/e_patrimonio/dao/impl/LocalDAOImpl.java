package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.LocalDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Local;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;

public class LocalDAOImpl implements LocalDAO {
    private AppDatabase database;

    @Inject
    public LocalDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarLocal(List<Local> locals) {
        this.database.localDAO().importarLocal(locals);
    }

    @Override
    public void removerTodosLocais() {
        this.database.localDAO().removerTodosLocais();
    }

    @Override
    public List<Local> pesquisaLocalPorSetor(int pSETOR) {
        return this.database.localDAO().pesquisaLocalPorSetor(pSETOR);
    }

    @Override
    public Local pesquisaLocalPorCodigo(int pCODIGO) {
        return this.database.localDAO().pesquisaLocalPorCodigo(pCODIGO);
    }

    @Override
    public List<Local> pesquisaTodosLocais() {
        return this.database.localDAO().pesquisaTodosLocais();
    }

    public void close(){
        database.destroyInstance();
    }
}
