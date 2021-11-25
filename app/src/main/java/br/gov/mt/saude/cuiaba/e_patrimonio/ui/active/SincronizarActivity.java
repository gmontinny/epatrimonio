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
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.FornecedorDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.GrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.LocalDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MarcaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MaterialDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.ModeloDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.SecretariaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.SetorDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.SubGrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.TransferenciaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Baixa;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Fornecedor;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Local;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Marca;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Material;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Modelo;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Secretaria;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Setor;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.SubGrupo;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Transferencia;
import br.gov.mt.saude.cuiaba.e_patrimonio.retrofit.SincronizarDadosServidor;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SincronizarActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    @Inject
    PatrimonioDAOImpl patrimonioDAO;
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
    SecretariaDAOImpl secretariaDAO;
    @Inject
    SetorDAOImpl setorDAO;
    @Inject
    LocalDAOImpl localDAO;
    @Inject
    BaixaDAOImpl baixaDAO;
    @Inject
    TransferenciaDAOImpl transferenciaDAO;
    @Inject
    FornecedorDAOImpl fornecedorDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizar);

        Context context = getApplicationContext();
        patrimonioDAO = new PatrimonioDAOImpl(context);
        grupoDAO = new GrupoDAOImpl(context);
        subGrupoDAO = new SubGrupoDAOImpl(context);
        materialDAO = new MaterialDAOImpl(context);
        marcaDAO = new MarcaDAOImpl(context);
        modeloDAO = new ModeloDAOImpl(context);
        localDAO = new LocalDAOImpl(context);
        secretariaDAO = new SecretariaDAOImpl(context);
        setorDAO = new SetorDAOImpl(context);
        fornecedorDAO = new FornecedorDAOImpl(context);
        baixaDAO = new BaixaDAOImpl(context);
        transferenciaDAO = new TransferenciaDAOImpl(context);

        importarPatrimonio();


    }

    private void importarPatrimonio(){
        executarDialog();
        Call<List<Patrimonio>> callPatrimonio = new SincronizarDadosServidor().importarPatrimonio().importarPatrimonio();
        callPatrimonio.enqueue(new Callback<List<Patrimonio>>() {
            @Override
            public void onResponse(Call<List<Patrimonio>> call, Response<List<Patrimonio>> response) {
                List<Patrimonio> patrimonios = response.body();
                patrimonioDAO.removerTodosPatrimonio();
                patrimonioDAO.importarPatrimonio(patrimonios);
                importarGrupo();
            }

            @Override
            public void onFailure(Call<List<Patrimonio>> call, Throwable t) {
                erroConexao(t);
            }
        });
    }

    private void importarGrupo(){

        Call<List<Grupo>> callGrupo = new SincronizarDadosServidor().importarGrupo().importarGrupo();
        callGrupo.enqueue(new Callback<List<Grupo>>() {
            @Override
            public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {
                List<Grupo> grupos = response.body();
                grupoDAO.removerTodosGrupos();
                grupoDAO.importarGrupo(grupos);
                importarSubGrupo();
            }

            @Override
            public void onFailure(Call<List<Grupo>> call, Throwable t) {
                erroConexao(t);
            }
        });
    }

    private void importarSubGrupo(){

        Call<List<SubGrupo>> callSubGrupo = new SincronizarDadosServidor().importarSubGrupo().importarSubGrupo();
        callSubGrupo.enqueue(new Callback<List<SubGrupo>>() {
            @Override
            public void onResponse(Call<List<SubGrupo>> call, Response<List<SubGrupo>> response) {
                List<SubGrupo> subgrupos = response.body();
                subGrupoDAO.removerTodosSubGrupos();
                subGrupoDAO.importarSubGrupo(subgrupos);
                importarMaterial();
            }

            @Override
            public void onFailure(Call<List<SubGrupo>> call, Throwable t) {
                erroConexao(t);
            }
        });
    }


    private void importarMaterial(){
        Call<List<Material>> callMaterial = new SincronizarDadosServidor().importarMaterial().importarMaterial();
        callMaterial.enqueue(new Callback<List<Material>>() {
            @Override
            public void onResponse(Call<List<Material>> call, Response<List<Material>> response) {
                List<Material> materials = response.body();
                materialDAO.removerTodosMaterias();
                materialDAO.importarMaterial(materials);
                importarSecretaria();
            }

            @Override
            public void onFailure(Call<List<Material>> call, Throwable t) {
                erroConexao(t);
            }
        });
    }


    private void importarSecretaria(){
        Call<List<Secretaria>> callSecretaria = new SincronizarDadosServidor().importarSecretaria().importarSecretaria();
        callSecretaria.enqueue(new Callback<List<Secretaria>>() {
            @Override
            public void onResponse(Call<List<Secretaria>> call, Response<List<Secretaria>> response) {
                List<Secretaria> secretarias = response.body();
                secretariaDAO.removerTodasSecretarias();
                secretariaDAO.importarSecretaria(secretarias);
                importarSetor();
            }

            @Override
            public void onFailure(Call<List<Secretaria>> call, Throwable t) {
                erroConexao(t);
            }
        });
    }


    private void importarSetor(){
        Call<List<Setor>> callSetor = new SincronizarDadosServidor().importarSetor().importarSetor();
        callSetor.enqueue(new Callback<List<Setor>>() {
            @Override
            public void onResponse(Call<List<Setor>> call, Response<List<Setor>> response) {
                List<Setor> setors = response.body();
                setorDAO.removerTodosSetores();
                setorDAO.importarSetor(setors);
                importarLocal();
            }

            @Override
            public void onFailure(Call<List<Setor>> call, Throwable t) {
                erroConexao(t);
            }
        });
    }


    private void importarLocal(){
        Call<List<Local>> callLocal = new SincronizarDadosServidor().importarLocal().importarLocal();
        callLocal.enqueue(new Callback<List<Local>>() {
            @Override
            public void onResponse(Call<List<Local>> call, Response<List<Local>> response) {
                List<Local> local = response.body();
                localDAO.removerTodosLocais();
                localDAO.importarLocal(local);
                importarMarca();
            }

            @Override
            public void onFailure(Call<List<Local>> call, Throwable t) {
                erroConexao(t);
            }
        });
    }

    private void importarMarca(){
        Call<List<Marca>> callLocal = new SincronizarDadosServidor().importarMarca().importarMarca();
        callLocal.enqueue(new Callback<List<Marca>>() {
            @Override
            public void onResponse(Call<List<Marca>> call, Response<List<Marca>> response) {
                List<Marca> marcas = response.body();
                marcaDAO.removerTodasMarcas();
                marcaDAO.importarMarca(marcas);
                importarModelo();
            }

            @Override
            public void onFailure(Call<List<Marca>> call, Throwable t) {
                erroConexao(t);
            }
        });
    }

    private void importarModelo(){
        Call<List<Modelo>> callModelo = new SincronizarDadosServidor().importarModelo().importarModelo();
        callModelo.enqueue(new Callback<List<Modelo>>() {
            @Override
            public void onResponse(Call<List<Modelo>> call, Response<List<Modelo>> response) {
                List<Modelo> modelos = response.body();
                modeloDAO.removerTodosModelos();
                modeloDAO.importarModelo(modelos);
                importarBaixa();
            }

            @Override
            public void onFailure(Call<List<Modelo>> call, Throwable t) {
                erroConexao(t);
            }
        });
    }

    private void importarBaixa(){
        Call<List<Baixa>> callBaixa = new SincronizarDadosServidor().importarBaixa().importarBaixa();
        callBaixa.enqueue(new Callback<List<Baixa>>() {
            @Override
            public void onResponse(Call<List<Baixa>> call, Response<List<Baixa>> response) {
                List<Baixa> baixas = response.body();
                baixaDAO.removerTodasBaixas();
                baixaDAO.importarBaixa(baixas);
                importarTransferencia();
            }

            @Override
            public void onFailure(Call<List<Baixa>> call, Throwable t) {
                erroConexao(t);
            }
        });
    }

    private void importarTransferencia(){
        Call<List<Transferencia>> callTransferencia = new SincronizarDadosServidor().importarTransferencia().importarTransferencia();
        callTransferencia.enqueue(new Callback<List<Transferencia>>() {
            @Override
            public void onResponse(Call<List<Transferencia>> call, Response<List<Transferencia>> response) {
                List<Transferencia> transferencias = response.body();
                transferenciaDAO.removerTodasTRansferencia();
                transferenciaDAO.importarTransferencia(transferencias);
                importarFornecedor();
            }

            @Override
            public void onFailure(Call<List<Transferencia>> call, Throwable t) {
                erroConexao(t);
            }
        });
    }

    private void importarFornecedor(){
        Call<List<Fornecedor>> callFornecedor = new SincronizarDadosServidor().importarFornecedor().importarFornecedor();
        callFornecedor.enqueue(new Callback<List<Fornecedor>>() {
            @Override
            public void onResponse(Call<List<Fornecedor>> call, Response<List<Fornecedor>> response) {
                List<Fornecedor> fornecedors = response.body();
                fornecedorDAO.removerTodosFornecedores();
                fornecedorDAO.importarFornecedor(fornecedors);
                dialog.dismiss();
                new SweetAlertDialog(SincronizarActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Patrimonio")
                        .setContentText("Dados importado com sucesso !")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                                voltarPrincipal();
                            }
                        })
                        .show();

            }

            @Override
            public void onFailure(Call<List<Fornecedor>> call, Throwable t) {
                erroConexao(t);
            }
        });
    }

    private void voltarPrincipal(){
        Intent intent = new Intent(SincronizarActivity.this, PrincipalActivity.class);
        startActivity(intent);
        finish();
    }


    public void erroConexao(Throwable t){
        new SweetAlertDialog(SincronizarActivity.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText(t.getMessage())
                .show();

        dialog.dismiss();
        voltarPrincipal();
    }

    private void executarDialog(){
        dialog = new ProgressDialog(SincronizarActivity.this);
        dialog.getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
        dialog.setContentView(R.layout.my_progress);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        patrimonioDAO.close();
        grupoDAO.close();
        subGrupoDAO.close();
        materialDAO.close();
        marcaDAO.close();
        modeloDAO.close();
        secretariaDAO.close();
        setorDAO.close();
        localDAO.close();
        baixaDAO.close();
        transferenciaDAO.close();
        fornecedorDAO.close();
    }
}
