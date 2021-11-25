package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Setor;

@Dao
public interface SetorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarSetor(List<Setor> setors);

    @Query("DELETE FROM setor")
    public void removerTodosSetores();

    @Query("SELECT * FROM setor WHERE idsecretaria = :pSECRETARIA ORDER BY nomsetor")
    public List<Setor> pesquisaSetorPorSecretaria(int pSECRETARIA);

    @Query("SELECT * FROM setor WHERE idsetor = :pCODIGO ORDER BY nomsetor")
    public Setor pesquisaSetorPorCodigo(int pCODIGO);

    @Query("SELECT * FROM setor ORDER BY nomsetor")
    public List<Setor> pesquisaTodosSetores();

}
