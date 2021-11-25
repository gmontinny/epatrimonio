package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.SecretariaDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Secretaria;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;

public class SecretariaDAOImpl  implements SecretariaDAO {

    private AppDatabase database;

    @Inject
    public SecretariaDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarSecretaria(List<Secretaria> secretarias) {
        this.database.secretariaDAO().importarSecretaria(secretarias);
    }

    @Override
    public void removerTodasSecretarias() {
        this.database.secretariaDAO().removerTodasSecretarias();
    }

    @Override
    public Secretaria pesquisaSecretariaPorCodigo(int pCODIGO) {
        return this.database.secretariaDAO().pesquisaSecretariaPorCodigo(pCODIGO);
    }

    @Override
    public List<Secretaria> pesquisaTodasSecretarias() {
        return this.database.secretariaDAO().pesquisaTodasSecretarias();
    }

    public void close(){
        database.destroyInstance();
    }
}
