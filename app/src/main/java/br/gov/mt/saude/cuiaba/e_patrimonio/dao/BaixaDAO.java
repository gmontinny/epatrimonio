package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Baixa;

@Dao
public interface BaixaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarBaixa(List<Baixa> baixas);

    @Query("DELETE FROM baixa")
    public void removerTodasBaixas();

    @Query("SELECT * FROM baixa WHERE idpatrimonio = :pNUMERO ")
    public Baixa pesquisaBaixaPorPatrimonio(String pNUMERO);

    @Query("SELECT b.* FROM baixa b INNER JOIN patrimonio p ON p.idpatrimonio = b.idpatrimonio " +
            " WHERE p.serialpatrimonio = UPPER(:pSERIAL) ")
    public Baixa pesquisaBaixaPorSerial(String pSERIAL);

    @Query("SELECT b.* FROM baixa b INNER JOIN patrimonio p ON p.idpatrimonio = b.idpatrimonio " +
            " WHERE  p.idpatrimonio LIKE '%' || :pNUMERO || '%' OR  p.serialpatrimonio = UPPER(:pSERIAL) ")
    public List<Baixa> pesquisaBaixaPorSerialPatrimonio(String pNUMERO,String pSERIAL);

    @Insert
    public void inserirBaixa(Baixa baixa);

    @Delete
    public void removerBaixaPorCodigo(Baixa baixa);

    @Query("SELECT * FROM baixa ORDER BY idbaixa ")
    public List<Baixa> pesquisaBaixaGeral();

    @Query("SELECT * FROM baixa WHERE flag = 1 ORDER BY idbaixa ")
    public List<Baixa> pesquisaBaixaFlag();

    @Query("DELETE FROM baixa WHERE flag = 1")
    public void deletarBaixaFlag();


}
