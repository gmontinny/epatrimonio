package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.FornecedorDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Fornecedor;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;

public class FornecedorDAOImpl implements FornecedorDAO {

    private AppDatabase database;

    @Inject
    public FornecedorDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarFornecedor(List<Fornecedor> fornecedors) {
        database.fornecedorDAO().importarFornecedor(fornecedors);
    }

    @Override
    public void removerTodosFornecedores() {
        database.fornecedorDAO().removerTodosFornecedores();
    }

    @Override
    public List<Fornecedor> pesquisaFornecedorRazao(String pRAZAO) {
        return database.fornecedorDAO().pesquisaFornecedorRazao(pRAZAO);
    }

    @Override
    public Fornecedor pesquisaFornecedorPorCodigo(int pCODIGO) {
        return database.fornecedorDAO().pesquisaFornecedorPorCodigo(pCODIGO);
    }

    @Override
    public List<Fornecedor> pesquisaTodosFornecedores() {
        return this.database.fornecedorDAO().pesquisaTodosFornecedores();
    }

    public void close(){
        database.destroyInstance();
    }

}
