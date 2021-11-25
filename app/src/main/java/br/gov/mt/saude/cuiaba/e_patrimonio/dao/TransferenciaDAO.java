package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Transferencia;

@Dao
public interface TransferenciaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarTransferencia(List<Transferencia> transferencias);

    @Query("DELETE FROM transferencia")
    public void removerTodasTRansferencia();

    @Query("SELECT * FROM transferencia WHERE idpatrimonio = :pNUMERO")
    public Transferencia pesquisaTransferenciaPorPatrimonio(String pNUMERO);

    @Query("SELECT t.* FROM transferencia t " +
            " INNER JOIN patrimonio p ON p.idpatrimonio = t.idpatrimonio " +
            " WHERE p.idpatrimonio LIKE '%' || :pNUMERO || '%' OR p.serialpatrimonio = :pSERIAL ")
    public List<Transferencia> pesquisaTransferenciaPorSerialPatrimonio(String pNUMERO, String pSERIAL);

    @Query("SELECT t.* FROM transferencia t " +
            " INNER JOIN patrimonio p ON p.idpatrimonio = t.idpatrimonio " +
            " WHERE p.serialpatrimonio = :pSERIAL")
    public Transferencia pesquisaTransferenciaPorSerial(String pSERIAL);

    @Query("SELECT * FROM transferencia ORDER BY idtransferencia")
    public List<Transferencia> pesquisaTodasTransferencia();

    @Delete
    public void removerTransferenciaPorCodigo(Transferencia transferencia);

    @Insert
    public void inserirTransferencia(Transferencia transferencia);

    @Query("SELECT * FROM transferencia WHERE flag = 1 ORDER BY idtransferencia")
    public List<Transferencia> pesquisaTransferenciaFlag();

    @Query("DELETE FROM transferencia WHERE flag = 1")
    public void deletarTransferenciaPorFlag();

}
