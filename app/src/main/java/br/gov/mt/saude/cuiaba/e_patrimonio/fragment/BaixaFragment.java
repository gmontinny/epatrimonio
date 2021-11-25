package br.gov.mt.saude.cuiaba.e_patrimonio.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.component.DrawableClickListener;
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
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.UsuarioDAOImpl;
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
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Usuario;
import br.gov.mt.saude.cuiaba.e_patrimonio.ui.active.PrincipalActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class BaixaFragment extends Fragment {

    private AppCompatEditText edtPatrimonio;
    private AppCompatEditText edtSerial;
    private AppCompatButton btnBaixar;

    private AppCompatTextView txtSecretariaBaixa;
    private AppCompatTextView txtSetorBaixa;
    private AppCompatTextView txtLocalBaixa;
    private AppCompatTextView txtGrupoBaixa;
    private AppCompatTextView txtSubGrupoBaixa;
    private AppCompatTextView txtMaterialBaixa;
    private AppCompatTextView txtMarcaBaixa;
    private AppCompatTextView txtModeloBaixa;
    private AppCompatTextView txtFornecedorBaixa;

    private TableLayout tblBaixa;

    private FloatingActionButton fabPesquisar;
    private FloatingActionMenu floatActionBaixa;

    @Inject
    BaixaDAOImpl baixaDAO;
    @Inject
    PatrimonioDAOImpl patrimonioDAO;
    @Inject
    GrupoDAOImpl grupoDAO;
    @Inject
    SubGrupoDAOImpl subGrupoDAO;
    @Inject
    MaterialDAOImpl materialDAO;
    @Inject
    SecretariaDAOImpl secretariaDAO;
    @Inject
    SetorDAOImpl setorDAO;
    @Inject
    LocalDAOImpl localDAO;
    @Inject
    MarcaDAOImpl marcaDAO;
    @Inject
    ModeloDAOImpl modeloDAO;
    @Inject
    FornecedorDAOImpl fornecedorDAO;
    @Inject
    UsuarioDAOImpl usuarioDAO;

    private Baixa baixa;
    private Patrimonio patrimonio;
    private Secretaria secretaria;
    private Setor setor;
    private Local local;
    private Grupo grupo;
    private SubGrupo subGrupo;
    private Material material;
    private Marca marca;
    private Modelo modelo;
    private Fornecedor fornecedor;

    private String numeropatrimonio;

    DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat dh = new SimpleDateFormat("HH:mm:ss");

    public BaixaFragment() {
        // Required empty public constructor
    }

    public static BaixaFragment newInstance() {
        BaixaFragment fragment = new BaixaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            numeropatrimonio = getArguments().getString("numeropatrimonio");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baixa, container, false);

        baixaDAO = new BaixaDAOImpl(getContext());
        patrimonioDAO = new PatrimonioDAOImpl(getContext());
        secretariaDAO = new SecretariaDAOImpl(getContext());
        setorDAO = new SetorDAOImpl(getContext());
        localDAO = new LocalDAOImpl(getContext());
        grupoDAO = new GrupoDAOImpl(getContext());
        subGrupoDAO = new SubGrupoDAOImpl(getContext());
        materialDAO = new MaterialDAOImpl(getContext());
        marcaDAO = new MarcaDAOImpl(getContext());
        modeloDAO = new ModeloDAOImpl(getContext());
        fornecedorDAO = new FornecedorDAOImpl(getContext());
        usuarioDAO = new UsuarioDAOImpl(getContext());

        tblBaixa = (TableLayout) view.findViewById(R.id.tblBaixa);

        fabPesquisar = (FloatingActionButton) view.findViewById(R.id.menuPesquisarBaixa);

        floatActionBaixa = (FloatingActionMenu) view.findViewById(R.id.menuFloatBaixa);

        txtSecretariaBaixa = (AppCompatTextView) view.findViewById(R.id.txtSecretariaBaixa);
        txtSetorBaixa = (AppCompatTextView) view.findViewById(R.id.txtSetorBaixa);
        txtLocalBaixa = (AppCompatTextView) view.findViewById(R.id.txtLocalBaixa);
        txtMaterialBaixa = (AppCompatTextView) view.findViewById(R.id.txtMaterialBaixa);
        txtGrupoBaixa = (AppCompatTextView) view.findViewById(R.id.txtGrupoBaixa);
        txtSubGrupoBaixa = (AppCompatTextView) view.findViewById(R.id.txtSubGrupoBaixa);
        txtMarcaBaixa = (AppCompatTextView) view.findViewById(R.id.txtMarcaBaixa);
        txtModeloBaixa = (AppCompatTextView) view.findViewById(R.id.txtModeloBaixa);
        txtFornecedorBaixa = (AppCompatTextView) view.findViewById(R.id.txtFornecedorBaixa);

        btnBaixar = (AppCompatButton) view.findViewById(R.id.btnBaixar);
        btnBaixar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Patrimonio será baixado !")
                        .setContentText("Confirme essa baixa ?")
                        .setCancelText("Não!")
                        .setConfirmText("Sim!")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Usuario usuario = usuarioDAO.pesquisaUsuario();
                                Baixa b = new Baixa(dt.format(new Date()),dh.format(new java.sql.Time(new Date().getTime())),(int)usuario.idusuario,patrimonio.idpatrimonio,1);
                                baixaDAO.inserirBaixa(b);
                                tblBaixa.setVisibility(View.GONE);
                                edtPatrimonio.setText("");
                                edtSerial.setText("");
                                patrimonio.statusbaixa = 1;
                                patrimonio.flag = 1;
                                patrimonioDAO.atualizarPatrimonio(patrimonio);
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        })
                        .show();

            }
        });

        fabPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionBaixa.close(true);
                PesquisaBaixaFragment pesquisa = PesquisaBaixaFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,pesquisa)
                        .commit();
            }
        });


        edtPatrimonio = (AppCompatEditText) view.findViewById(R.id.edtPatrimonio);
        edtPatrimonio.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtPatrimonio) {
            @Override
            public boolean onDrawableClick()
            {
                ((PrincipalActivity)getActivity()).executarScanner();
                return true;
            }
        });

        edtSerial = (AppCompatEditText) view.findViewById(R.id.edtSerial);
        edtSerial.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtSerial) {
            @Override
            public boolean onDrawableClick() {

                patrimonio = patrimonioDAO.pesquisaPorPatrimonio(edtPatrimonio.getText().toString(), edtSerial.getText().toString());

                if(patrimonio == null){
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Baixa")
                            .setContentText("Patrimonio não encontrado !")
                            .show();
                }else if(validaBaixaPorPatrimonio(edtPatrimonio.getText().toString(), edtSerial.getText().toString())){
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Baixa")
                            .setContentText("Baixa já realizada para esse Patrimônio !")
                            .show();

                }else{
                    exibePesquisaPatrimonio();
                }
                return false;
            }
        });


        if(numeropatrimonio != null){
            edtPatrimonio.setText(numeropatrimonio);
            patrimonio = patrimonioDAO.pesquisaPorPatrimonio(numeropatrimonio);

            if(patrimonio == null){
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Baixa")
                        .setContentText("Patrimonio não encontrado !")
                        .show();
            }else if(validaBaixaPorPatrimonio(edtPatrimonio.getText().toString(), edtSerial.getText().toString())){
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Baixa")
                        .setContentText("Baixa já realizada para esse Patrimônio !")
                        .show();

            }else {
                exibePesquisaPatrimonio();
            }
        }


        return view;
    }

    public void exibePesquisaPatrimonio(){

        local = localDAO.pesquisaLocalPorCodigo(patrimonio.idlocal);
        setor = setorDAO.pesquisaSetorPorCodigo(local.idsetor);
        secretaria = secretariaDAO.pesquisaSecretariaPorCodigo(setor.idsecretaria);
        material = materialDAO.pesquisaMaterialPorCodigo(patrimonio.idmaterial);
        subGrupo = subGrupoDAO.pesquisaSubGrupoPorCodigo(material.idsubgrupo);
        grupo = grupoDAO.pesquisaGrupoPorCodigo(subGrupo.idgrupo);
        modelo = modeloDAO.pesquisaModeloPorCodigo(patrimonio.idmodelo);
        marca = marcaDAO.pesquisaMarcaCodigo(modelo.idmarca);
        fornecedor = fornecedorDAO.pesquisaFornecedorPorCodigo(patrimonio.idfornecedor);

        tblBaixa.setVisibility(View.VISIBLE);
        txtSecretariaBaixa.setText(secretaria.nomsecretaria);
        txtSetorBaixa.setText(setor.nomsetor);
        txtLocalBaixa.setText(local.desclocal);
        txtGrupoBaixa.setText(grupo.descgrupo);
        txtSubGrupoBaixa.setText(subGrupo.descsubgrupo);
        txtMaterialBaixa.setText(material.descmaterial);
        txtMarcaBaixa.setText(marca.descmarca);
        txtModeloBaixa.setText(modelo.descmodelo);
        txtFornecedorBaixa.setText(fornecedor.razaosocial);

    }
    

    public boolean validaBaixaPorPatrimonio(String numeroPatrimonio, String numeroSerial){
        boolean resultado = baixaDAO.pesquisaBaixaPorPatrimonio(numeroPatrimonio) != null || baixaDAO.pesquisaBaixaPorSerial(numeroSerial) != null;
        return resultado;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        baixaDAO.close();
        patrimonioDAO.close();
        secretariaDAO.close();
        setorDAO.close();
        localDAO.close();
        grupoDAO.close();
        subGrupoDAO.close();
        materialDAO.close();
        marcaDAO.close();
        modeloDAO.close();
        fornecedorDAO.close();
    }

}
