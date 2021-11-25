package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.SubGrupoDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.SubGrupo;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;

public class SubGrupoDAOImpl  implements SubGrupoDAO {

    private AppDatabase database;

    @Inject
    public SubGrupoDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarSubGrupo(List<SubGrupo> subGrupos) {
        this.database.subGrupoDAO().importarSubGrupo(subGrupos);
    }

    @Override
    public void removerTodosSubGrupos() {
        this.database.subGrupoDAO().removerTodosSubGrupos();
    }

    @Override
    public List<SubGrupo> pesquisaSubGrupoPorGrupo(int pGRUPO) {
        return this.database.subGrupoDAO().pesquisaSubGrupoPorGrupo(pGRUPO);
    }

    @Override
    public SubGrupo pesquisaSubGrupoPorCodigo(int pCODIGO) {
        return this.database.subGrupoDAO().pesquisaSubGrupoPorCodigo(pCODIGO);
    }

    @Override
    public List<SubGrupo> pesquisaTodosSubGrupos() {
        return this.database.subGrupoDAO().pesquisaTodosSubGrupos();
    }

    @Override
    public List<SubGrupo> pesquisaSubGruposPorDescricao(String pDESCRICAO) {
        return this.database.subGrupoDAO().pesquisaSubGruposPorDescricao(pDESCRICAO);
    }

    @Override
    public void atualizarSubGrupo(SubGrupo subGrupo) {
        this.database.subGrupoDAO().atualizarSubGrupo(subGrupo);
    }

    @Override
    public void deletarSubGrupo(SubGrupo subGrupo) {
        this.database.subGrupoDAO().deletarSubGrupo(subGrupo);
    }

    @Override
    public void inserirSubGrupo(SubGrupo subGrupo) {
        this.database.subGrupoDAO().inserirSubGrupo(subGrupo);
    }

    @Override
    public List<SubGrupo> pesquisaSubGrupoPorFlag() {
        return this.database.subGrupoDAO().pesquisaSubGrupoPorFlag();
    }

    @Override
    public void deleteSubGrupoPorFlag() {
        this.database.subGrupoDAO().deleteSubGrupoPorFlag();
    }


    public void close(){
        database.destroyInstance();
    }
}
