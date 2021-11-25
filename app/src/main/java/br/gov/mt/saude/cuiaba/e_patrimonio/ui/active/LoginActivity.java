package br.gov.mt.saude.cuiaba.e_patrimonio.ui.active;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;

import android.view.View;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import javax.inject.Inject;

import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;
import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.annotation.CpfCnpj;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.UsuarioDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Usuario;
import br.gov.mt.saude.cuiaba.e_patrimonio.retrofit.SincronizarDadosServidor;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener{
    private AppCompatButton btnLogin;
    private boolean validated = false;
    private ProgressDialog dialog;

    @NotEmpty(message = "CPF campo Obrigatório !")
    @CpfCnpj(message = "CPF Invalido !", isRequired = true)
    private AppCompatEditText edtCPFLogin;

    @NotEmpty(message = "Senha campo Obgrigatório !")
    @Password(min = 6, message = "Digite pelo menos 6 caracteres !")
    private  AppCompatEditText edtSenhaLogin;

    private Validator validator;

    @Inject
    UsuarioDAOImpl usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        validator = new Validator(this);
        validator.registerAnnotation(CpfCnpj.class);
        validator.setValidationListener(this);

        edtCPFLogin   = (AppCompatEditText) findViewById(R.id.edtCPFLogin);
        edtSenhaLogin = (AppCompatEditText) findViewById(R.id.edtSenhaLogin);

        MaskEditTextChangedListener maskCPF = new MaskEditTextChangedListener("###.###.###-##", edtCPFLogin);
        edtCPFLogin.addTextChangedListener(maskCPF);

        Context context = getApplicationContext();
        usuarioDAO = new UsuarioDAOImpl(context);

        Usuario usuario = usuarioDAO.pesquisaUsuario();

        if(usuario != null){
            btnEnviar();
        }

        btnLogin = (AppCompatButton) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
                if(validated){
                    importarUsuario(edtCPFLogin.getText().toString(), edtSenhaLogin.getText().toString());
                }
            }
        });
    }

    public void btnEnviar(){
            Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
            startActivity(intent);
            finish();
    }

    private void executarDialog(){
        dialog = new ProgressDialog(LoginActivity.this);
        dialog.getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.show();
        dialog.setContentView(R.layout.my_progress);
    }

    public void importarUsuario(String cpf, String senha){
        executarDialog();
        Call<Usuario> call = new SincronizarDadosServidor().validaUsuario().validaUsuario(cpf,senha);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuario = response.body();
                if(usuario != null){
                    usuarioDAO.removerTodosUsuarios();
                    usuarioDAO.importarUsuario(usuario);
                    dialog.dismiss();
                    btnEnviar();
                }else{
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Usuario não cadastrado!")
                            .show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                erroConexao(t);
            }
        });
    }

    public void erroConexao(Throwable t){
        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText(t.getMessage())
                .show();

        dialog.dismiss();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usuarioDAO.close();
    }

    @Override
    public void onValidationSucceeded() {
        validated = true;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {

            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            validated = true;

            if (view instanceof AppCompatEditText) {
                ((AppCompatEditText) view).setError(message);
                validated = false;
            }
        }
    }
}
