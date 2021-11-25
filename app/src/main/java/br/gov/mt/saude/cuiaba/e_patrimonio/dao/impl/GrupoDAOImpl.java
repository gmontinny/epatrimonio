package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.GrupoDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;


public class GrupoDAOImpl implements GrupoDAO {

    private AppDatabase database;

    @Inject
    public GrupoDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarGrupo(List<Grupo> grupos) {
        this.database.grupoDAO().importarGrupo(grupos);
    }

    @Override
    public void removerTodosGrupos() {
        this.database.grupoDAO().removerTodosGrupos();
    }

    @Override
    public Grupo pesquisaGrupoPorCodigo(int pCODIGO) {
        return this.database.grupoDAO().pesquisaGrupoPorCodigo(pCODIGO);
    }

    @Override
    public List<Grupo> pesquisaTodosGrupos() {
        return this.database.grupoDAO().pesquisaTodosGrupos();
    }

    @Override
    public List<Grupo> pesquisaGrupoPorDescricao(String pDESCRICAO) {
        return this.database.grupoDAO().pesquisaGrupoPorDescricao(pDESCRICAO);
    }

    @Override
    public void atualizaGrupo(Grupo grupo) {
        this.database.grupoDAO().atualizaGrupo(grupo);
    }

    @Override
    public void inserirGrupo(Grupo grupo) {
        this.database.grupoDAO().inserirGrupo(grupo);
    }

    @Override
    public void deletarGrupo(Grupo grupo) {
        this.database.grupoDAO().deletarGrupo(grupo);
    }

    @Override
    public List<Grupo> pesquisaGrupoPorFlag() {
        return this.database.grupoDAO().pesquisaGrupoPorFlag();
    }

    @Override
    public void deletarGrupoFlag() {
        this.database.grupoDAO().deletarGrupoFlag();
    }

    public void close(){
        database.destroyInstance();
    }

}
