package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.MarcaDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Marca;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;

public class MarcaDAOImpl implements MarcaDAO {
    private AppDatabase database;

    @Inject
    public MarcaDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarMarca(List<Marca> marcas) {
        this.database.marcaDAO().importarMarca(marcas);
    }

    @Override
    public void removerTodasMarcas() {
        this.database.marcaDAO().removerTodasMarcas();
    }

    @Override
    public Marca pesquisaMarcaCodigo(int pCODIGO) {
        return this.database.marcaDAO().pesquisaMarcaCodigo(pCODIGO);
    }

    @Override
    public List<Marca> pesquisaTodasMarcas() {
        return this.database.marcaDAO().pesquisaTodasMarcas();
    }

    @Override
    public List<Marca> pesquisaDescricaoMarca(String pDESCRICAO) {
        return this.database.marcaDAO().pesquisaDescricaoMarca(pDESCRICAO);
    }

    @Override
    public void inserirMarca(Marca marca) {
        this.database.marcaDAO().inserirMarca(marca);
    }

    @Override
    public void deletarMarca(Marca marca) {
        this.database.marcaDAO().deletarMarca(marca);
    }

    @Override
    public void atualizarMarca(Marca marca) {
        this.database.marcaDAO().atualizarMarca(marca);
    }

    @Override
    public List<Marca> pesquisaMarcaFlag() {
        return this.database.marcaDAO().pesquisaMarcaFlag();
    }

    @Override
    public void deletarMarcaFlag() {
        this.database.marcaDAO().deletarMarcaFlag();
    }

    public void close(){
        database.destroyInstance();
    }
}
