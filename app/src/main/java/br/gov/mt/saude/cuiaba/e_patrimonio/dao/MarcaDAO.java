package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Marca;

@Dao
public interface MarcaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarMarca(List<Marca> marcas);

    @Query("DELETE FROM marca")
    public void removerTodasMarcas();

    @Query("SELECT * FROM marca WHERE idmarca = :pCODIGO")
    public Marca pesquisaMarcaCodigo(int pCODIGO);

    @Query("SELECT * FROM marca ORDER BY descmarca ")
    public List<Marca> pesquisaTodasMarcas();

    @Query("SELECT * FROM marca WHERE UPPER(descmarca) LIKE UPPER('%' || :pDESCRICAO || '%') ORDER BY descmarca")
    public List<Marca> pesquisaDescricaoMarca(String pDESCRICAO);

    @Insert
    public void inserirMarca(Marca marca);

    @Delete
    public void deletarMarca(Marca marca);

    @Update
    public void atualizarMarca(Marca marca);

    @Query("SELECT * FROM marca WHERE flag = 1 ORDER BY descmarca")
    public List<Marca> pesquisaMarcaFlag();

    @Query("DELETE FROM marca WHERE flag = 1")
    public void deletarMarcaFlag();

}
