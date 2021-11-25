package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;

@Dao
public interface PatrimonioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarPatrimonio(List<Patrimonio> patrimonios);

    @Query("DELETE FROM patrimonio")
    public void removerTodosPatrimonio();

    @Query("SELECT * FROM patrimonio WHERE idpatrimonio = :pNUMERO")
    public Patrimonio pesquisaPorPatrimonio(String pNUMERO);

    @Query("SELECT * FROM patrimonio WHERE idpatrimonio = :pNUMERO OR serialpatrimonio = UPPER(:pSERIAL)")
    public Patrimonio pesquisaPorPatrimonio(String pNUMERO, String pSERIAL);

    @Query("SELECT * FROM patrimonio WHERE idpatrimonio LIKE '%' || :pNUMERO || '%' OR serialpatrimonio = UPPER(:pSERIAL)")
    public List<Patrimonio> pesquisaPorNumeroPatrimonioESerial(String pNUMERO, String pSERIAL);

    @Query("SELECT * FROM patrimonio ORDER BY idpatrimonio")
    public List<Patrimonio> pesquisaTodosPatrimonios();

    @Query("SELECT * FROM patrimonio WHERE flag = 1 ORDER BY idpatrimonio")
    public List<Patrimonio> pesquisaPatrimonioFlag();

    @Update
    public void atualizarPatrimonio(Patrimonio patrimonio);

    @Delete
    public  void deletarPatrimonio(Patrimonio patrimonio);

    @Insert
    public void inserirPatrimonio(Patrimonio patrimonio);
}
