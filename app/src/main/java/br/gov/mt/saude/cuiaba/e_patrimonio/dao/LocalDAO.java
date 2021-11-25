package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Local;

@Dao
public interface LocalDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarLocal(List<Local> locals);

    @Query("DELETE FROM local")
    public void removerTodosLocais();

    @Query("SELECT * FROM local WHERE idsetor = :pSETOR ORDER BY desclocal")
    public List<Local>  pesquisaLocalPorSetor(int pSETOR);


    @Query("SELECT * FROM local WHERE idlocal = :pCODIGO ORDER BY desclocal")
    public Local  pesquisaLocalPorCodigo(int pCODIGO);

    @Query("SELECT * FROM local ORDER BY desclocal")
    public List<Local> pesquisaTodosLocais();

}
