package br.gov.mt.saude.cuiaba.e_patrimonio.ui.active;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.BaixaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.GrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MarcaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MaterialDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.ModeloDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.SubGrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.TransferenciaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Baixa;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Marca;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Material;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Modelo;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.SubGrupo;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Transferencia;
import br.gov.mt.saude.cuiaba.e_patrimonio.retrofit.SincronizarDadosServidor;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnviarActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    @Inject
    GrupoDAOImpl grupoDAO;
    @Inject
    SubGrupoDAOImpl subGrupoDAO;
    @Inject
    MaterialDAOImpl materialDAO;
    @Inject
    MarcaDAOImpl marcaDAO;
    @Inject
    ModeloDAOImpl modeloDAO;
    @Inject
    BaixaDAOImpl baixaDAO;
    @Inject
    TransferenciaDAOImpl transferenciaDAO;
    @Inject
    PatrimonioDAOImpl patrimonioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar);

        grupoDAO = new GrupoDAOImpl(getContext());
        subGrupoDAO = new SubGrupoDAOImpl(getContext());
        materialDAO = new MaterialDAOImpl(getContext());
        marcaDAO = new MarcaDAOImpl(getContext());
        modeloDAO = new ModeloDAOImpl(getContext());
        baixaDAO = new BaixaDAOImpl(getContext());
        transferenciaDAO = new TransferenciaDAOImpl(getContext());
        patrimonioDAO = new PatrimonioDAOImpl(getContext());

        enviarGrupo();
    }

    public void erroConexao(Throwable t){
        new SweetAlertDialog(EnviarActivity.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText(t.getMessage())
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismiss();
                        sweetAlertDialog.dismiss();
                        Intent intent = new Intent(EnviarActivity.this, PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .show();



    }

    private void enviarGrupo(){
        executarDialog();
        List<Grupo> grupos = grupoDAO.pesquisaGrupoPorFlag();

        if(grupos.isEmpty()){
            enviarSubGrupo();

        }else {

            Call<Void> call = new SincronizarDadosServidor().enviarGrupo().envioGrupo(grupos);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 202) {
                        enviarSubGrupo();
                    }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    erroConexao(t);
                }
            });
        }
    }

    private void enviarSubGrupo(){
        List<SubGrupo> subGrupos = subGrupoDAO.pesquisaSubGrupoPorFlag();

        if(subGrupos.isEmpty()){
            enviarMaterial();

        }else {

            Call<Void> call = new SincronizarDadosServidor().enviarSubGrupo().envioSubGrupo(subGrupos);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 202) {
                        enviarMaterial();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    erroConexao(t);
                }
            });
        }
    }

    private void enviarMaterial(){
        List<Material> materials = materialDAO.pesquisaMaterialPorFlag();
        if(materials.isEmpty()){
            enviarMarca();
        }else {

            Call<Void> call = new SincronizarDadosServidor().enviarMaterial().envioMaterial(materials);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 202) {
                        enviarMarca();
                    }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    erroConexao(t);
                }
            });
        }

    }

    private void enviarMarca(){
        List<Marca> marcas = marcaDAO.pesquisaMarcaFlag();
        if(marcas.isEmpty()){
            enviarModelo();
        }else {

            Call<Void> call = new SincronizarDadosServidor().enviarMarca().envioMarca(marcas);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 202) {
                        enviarModelo();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    erroConexao(t);
                }
            });
        }
    }

    private void enviarModelo(){
        List<Modelo> modelos = modeloDAO.pesquisaModeloFlag();

        if(modelos.isEmpty()){
            enviarBaixa();
        }else {

            Call<Void> call = new SincronizarDadosServidor().enviarModelo().envioModelo(modelos);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 202) {
                        enviarBaixa();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    erroConexao(t);
                }
            });
        }
    }

    private void enviarBaixa(){
        List<Baixa> baixas = baixaDAO.pesquisaBaixaFlag();

        if(baixas.isEmpty()){
            enviarTransferencia();
        }else {
            Call<Void> call = new SincronizarDadosServidor().enviarBaixa().envioBaixa(baixas);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 202) {
                        enviarTransferencia();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    erroConexao(t);
                }
            });
        }
    }

    private void enviarTransferencia(){
        List<Transferencia> transferencias = transferenciaDAO.pesquisaTransferenciaFlag();

        if(transferencias.isEmpty()){
            enviarPatrimonio();
        }else {
            Call<Void> call = new SincronizarDadosServidor().enviarTransferencia().envioTransferencia(transferencias);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 202) {
                        enviarPatrimonio();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    erroConexao(t);
                }
            });
        }
    }

    private void enviarPatrimonio(){
        List<Patrimonio> patrimonios = patrimonioDAO.pesquisaPatrimonioFlag();

        if(patrimonios.isEmpty()){
            dialog.dismiss();
            voltarPrincipal();
        }else {
            Call<Void> call = new SincronizarDadosServidor().enviarPatrimonio().envioPatrimonio(patrimonios);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 202) {
                        dialog.dismiss();
                        voltarPrincipal();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    erroConexao(t);
                }
            });
        }
    }

    private void executarDialog(){
        dialog = new ProgressDialog(EnviarActivity.this);
        dialog.getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
        dialog.setContentView(R.layout.my_progress);
    }

    private Context getContext(){
        return this;
    }

    private void voltarPrincipal(){

        new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Sincronizar !")
                .setContentText("Os dados vão ser SINCRONIZADOS !")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        Intent intent = new Intent(EnviarActivity.this, SincronizarActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        grupoDAO.close();
        subGrupoDAO.close();
        materialDAO.close();
        marcaDAO.close();
        modeloDAO.close();
        baixaDAO.close();
        transferenciaDAO.close();
        patrimonioDAO.close();
    }
}
