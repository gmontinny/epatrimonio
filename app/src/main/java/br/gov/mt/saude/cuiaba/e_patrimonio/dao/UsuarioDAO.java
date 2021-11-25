package br.gov.mt.saude.cuiaba.e_patrimonio.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Usuario;

@Dao
public interface UsuarioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void importarUsuario(Usuario usuario);

    @Query("DELETE FROM usuario")
    public void removerTodosUsuarios();

    @Query("SELECT * FROM usuario WHERE cpfusuario = :pCPF")
    public Usuario pesquisaUsuarioPorCPF(String pCPF);

    @Query("SELECT * FROM usuario ORDER BY idusuario LIMIT 1")
    public Usuario pesquisaUsuario();
}
