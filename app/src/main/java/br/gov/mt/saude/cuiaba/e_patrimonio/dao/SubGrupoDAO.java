package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.SubGrupo;

@Dao
public interface SubGrupoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarSubGrupo(List<SubGrupo> subGrupos);

    @Query("DELETE FROM subgrupo")
    public void removerTodosSubGrupos();

    @Query("SELECT * FROM subgrupo WHERE idgrupo = :pGRUPO")
    public List<SubGrupo> pesquisaSubGrupoPorGrupo(int pGRUPO);

    @Query("SELECT * FROM subgrupo WHERE idsubgrupo = :pCODIGO")
    public SubGrupo pesquisaSubGrupoPorCodigo(int pCODIGO);

    @Query("SELECT * FROM subgrupo ORDER BY descsubgrupo LIMIT 30")
    public List<SubGrupo> pesquisaTodosSubGrupos();

    @Query("SELECT * FROM subgrupo WHERE UPPER(descsubgrupo) LIKE UPPER('%' || :pDESCRICAO || '%') ORDER BY descsubgrupo")
    public List<SubGrupo> pesquisaSubGruposPorDescricao(String pDESCRICAO);

    @Update
    public void atualizarSubGrupo(SubGrupo subGrupo);

    @Delete
    public void deletarSubGrupo(SubGrupo subGrupo);

    @Insert
    public void inserirSubGrupo(SubGrupo subGrupo);

    @Query("SELECT * FROM subgrupo WHERE flag = 1 ORDER BY descsubgrupo")
    public List<SubGrupo> pesquisaSubGrupoPorFlag();

    @Query("DELETE FROM subgrupo WHERE flag = 1")
    public void deleteSubGrupoPorFlag();
}
