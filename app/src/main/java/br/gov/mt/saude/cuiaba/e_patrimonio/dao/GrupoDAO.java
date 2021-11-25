package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;

@Dao
public interface GrupoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarGrupo(List<Grupo> grupos);

    @Query("DELETE FROM grupo")
    public void removerTodosGrupos();

    @Query("SELECT * FROM grupo WHERE idgrupo = :pCODIGO")
    public Grupo pesquisaGrupoPorCodigo(int pCODIGO);

    @Query("SELECT * FROM grupo ORDER BY descgrupo")
    public List<Grupo> pesquisaTodosGrupos();

    @Query("SELECT * FROM grupo WHERE UPPER(descgrupo) LIKE UPPER('%' || :pDESCRICAO || '%') ORDER BY descgrupo")
    public List<Grupo> pesquisaGrupoPorDescricao(String pDESCRICAO);

    @Update
    public void atualizaGrupo(Grupo grupo);

    @Insert
    public void inserirGrupo(Grupo grupo);

    @Delete
    public void deletarGrupo(Grupo grupo);

    @Query("SELECT * FROM grupo WHERE flag = 1 ORDER BY descgrupo")
    public List<Grupo> pesquisaGrupoPorFlag();

    @Query("DELETE FROM grupo WHERE flag = 1")
    public void deletarGrupoFlag();
}
