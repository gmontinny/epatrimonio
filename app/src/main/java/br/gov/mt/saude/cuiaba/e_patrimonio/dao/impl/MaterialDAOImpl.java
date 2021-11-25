package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.MaterialDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Material;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;

public class MaterialDAOImpl implements MaterialDAO {
    private AppDatabase database;

    @Inject
    public MaterialDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarMaterial(List<Material> materials) {
        this.database.materialDAO().importarMaterial(materials);
    }

    @Override
    public void removerTodosMaterias() {
        this.database.materialDAO().removerTodosMaterias();
    }

    @Override
    public List<Material> pesquisaMaterialPOrSubGrupo(int pSUBGRUPO) {
        return this.database.materialDAO().pesquisaMaterialPOrSubGrupo(pSUBGRUPO);
    }

    @Override
    public Material pesquisaMaterialPorCodigo(int pCODIGO) {
        return this.database.materialDAO().pesquisaMaterialPorCodigo(pCODIGO);
    }

    @Override
    public List<Material> pesquisaTodosMaterials() {
        return this.database.materialDAO().pesquisaTodosMaterials();
    }

    @Override
    public List<Material> pesquisaMaterialPorDescricao(String pDESCRICAO) {
        return this.database.materialDAO().pesquisaMaterialPorDescricao(pDESCRICAO);
    }

    @Override
    public void inserirMaterial(Material material) {
        this.database.materialDAO().inserirMaterial(material);
    }

    @Override
    public void deletarMaterial(Material material) {
        this.database.materialDAO().deletarMaterial(material);
    }

    @Override
    public void atualizarMaterial(Material material) {
        this.database.materialDAO().atualizarMaterial(material);
    }

    @Override
    public List<Material> pesquisaMaterialPorFlag() {
        return this.database.materialDAO().pesquisaMaterialPorFlag();
    }

    @Override
    public void deleteMaterialPorFlag() {
        this.database.materialDAO().deleteMaterialPorFlag();
    }


    public void close(){
        database.destroyInstance();
    }

}
