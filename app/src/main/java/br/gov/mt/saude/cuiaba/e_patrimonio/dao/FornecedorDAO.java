package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Fornecedor;

@Dao
public interface FornecedorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarFornecedor(List<Fornecedor> fornecedors);

    @Query("DELETE FROM fornecedor")
    public void removerTodosFornecedores();

    @Query("SELECT * FROM fornecedor WHERE razaosocial = :pRAZAO")
    public List<Fornecedor> pesquisaFornecedorRazao(String pRAZAO);

    @Query("SELECT * FROM fornecedor WHERE idfornecedor = :pCODIGO")
    public Fornecedor pesquisaFornecedorPorCodigo(int pCODIGO);

    @Query("SELECT * FROM fornecedor ORDER BY razaosocial")
    public List<Fornecedor> pesquisaTodosFornecedores();

}
