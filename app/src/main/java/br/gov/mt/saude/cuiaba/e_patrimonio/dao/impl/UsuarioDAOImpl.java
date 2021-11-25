package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.UsuarioDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Usuario;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;

public class UsuarioDAOImpl implements UsuarioDAO {
    private AppDatabase database;

    @Inject
    public UsuarioDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarUsuario(Usuario usuario) {
        database.usuarioDAO().importarUsuario(usuario);
    }

    @Override
    public void removerTodosUsuarios() {
        database.usuarioDAO().removerTodosUsuarios();
    }

    @Override
    public Usuario pesquisaUsuarioPorCPF(String pCPF) {
        return database.usuarioDAO().pesquisaUsuarioPorCPF(pCPF);
    }

    @Override
    public Usuario pesquisaUsuario() {
        return database.usuarioDAO().pesquisaUsuario();
    }

    public void close(){
        database.destroyInstance();
    }
}
