package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.ModeloDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Modelo;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;

public class ModeloDAOImpl implements ModeloDAO {

    private AppDatabase database;

    @Inject
    public ModeloDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarModelo(List<Modelo> modelos) {
        this.database.modeloDAO().importarModelo(modelos);
    }

    @Override
    public void removerTodosModelos() {
        this.database.modeloDAO().removerTodosModelos();
    }

    @Override
    public List<Modelo> pesquisaModeloPorMarca(int pMARCA) {
        return this.database.modeloDAO().pesquisaModeloPorMarca(pMARCA);
    }

    @Override
    public Modelo pesquisaModeloPorCodigo(int pMODELO) {
        return this.database.modeloDAO().pesquisaModeloPorCodigo(pMODELO);
    }

    @Override
    public List<Modelo> pesquisaTodosModelos() {
        return this.database.modeloDAO().pesquisaTodosModelos();
    }

    @Override
    public void inserirModelo(Modelo modelo) {
        this.database.modeloDAO().inserirModelo(modelo);
    }

    @Override
    public void atualizarModelo(Modelo modelo) {
        this.database.modeloDAO().atualizarModelo(modelo);
    }

    @Override
    public void deletarModelo(Modelo modelo) {
        this.database.modeloDAO().deletarModelo(modelo);
    }

    @Override
    public List<Modelo> pesquisaDescricaoModelo(String pDESCRICAO) {
        return this.database.modeloDAO().pesquisaDescricaoModelo(pDESCRICAO);
    }

    @Override
    public List<Modelo> pesquisaModeloFlag() {
        return this.database.modeloDAO().pesquisaModeloFlag();
    }

    @Override
    public void deletarModeloPorFlag() {
        this.database.modeloDAO().deletarModeloPorFlag();
    }

    public void close(){
        database.destroyInstance();
    }
}
