package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Material;

@Dao
public interface MaterialDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarMaterial(List<Material> materials);

    @Query("DELETE FROM material")
    public void removerTodosMaterias();

    @Query("SELECT * FROM material WHERE idsubgrupo = :pSUBGRUPO")
    public List<Material> pesquisaMaterialPOrSubGrupo(int pSUBGRUPO);

    @Query("SELECT * FROM material WHERE idmaterial = :pCODIGO")
    public Material pesquisaMaterialPorCodigo(int pCODIGO);

    @Query("SELECT * FROM material ORDER BY descmaterial")
    public List<Material> pesquisaTodosMaterials();

    @Query("SELECT * FROM material WHERE UPPER(descmaterial) LIKE UPPER('%' || :pDESCRICAO || '%') ORDER BY descmaterial")
    public List<Material> pesquisaMaterialPorDescricao(String pDESCRICAO);

    @Insert
    public void inserirMaterial(Material material);

    @Delete
    public void deletarMaterial(Material material);

    @Update
    public void atualizarMaterial(Material material);

    @Query("SELECT * FROM material WHERE flag = 1 ORDER BY descmaterial")
    public List<Material> pesquisaMaterialPorFlag();

    @Query("DELETE FROM material WHERE flag = 1")
    public void deleteMaterialPorFlag();

}
