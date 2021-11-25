package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Secretaria;

@Dao
public interface SecretariaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarSecretaria(List<Secretaria> secretarias);

    @Query("DELETE FROM secretaria")
    public void removerTodasSecretarias();

    @Query("SELECT * FROM secretaria WHERE idsecretaria = :pCODIGO")
    public Secretaria pesquisaSecretariaPorCodigo(int pCODIGO);

    @Query("SELECT * FROM secretaria ORDER BY nomsecretaria")
    public List<Secretaria> pesquisaTodasSecretarias();

}
