package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Modelo;

@Dao
public interface ModeloDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarModelo(List<Modelo> modelos);

    @Query("DELETE FROM modelo")
    public void removerTodosModelos();

    @Query("SELECT * FROM modelo WHERE idmarca = :pMARCA")
    public List<Modelo> pesquisaModeloPorMarca(int pMARCA);

    @Query("SELECT * FROM modelo WHERE idmodelo = :pMODELO")
    public Modelo pesquisaModeloPorCodigo(int pMODELO);

    @Query("SELECT * FROM modelo ORDER BY descmodelo")
    public List<Modelo> pesquisaTodosModelos();

    @Insert
    public void inserirModelo(Modelo modelo);

    @Update
    public void atualizarModelo(Modelo modelo);

    @Delete
    public void deletarModelo(Modelo modelo);

    @Query("SELECT * FROM modelo WHERE UPPER(descmodelo) LIKE UPPER( '%' || :pDESCRICAO || '%') ORDER BY descmodelo")
    public List<Modelo> pesquisaDescricaoModelo(String pDESCRICAO);

    @Query("SELECT * FROM modelo WHERE flag = 1 ORDER BY descmodelo")
    public List<Modelo> pesquisaModeloFlag();

    @Query("DELETE FROM modelo WHERE flag = 1")
    public void deletarModeloPorFlag();

}
