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
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.SpinnerArrayAdapter;
import br.gov.mt.saude.cuiaba.e_patrimonio.component.DrawableClickListener;
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
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.UsuarioDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.listener.OnItemSelectedListener;
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
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Usuario;
import br.gov.mt.saude.cuiaba.e_patrimonio.ui.active.PrincipalActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class TransferenciaFragment extends Fragment {

    private AppCompatEditText edtPatrimonioTransferencia;
    private AppCompatEditText edtSerialTransferencia;

    private FloatingActionButton fabPesquisar;
    private FloatingActionMenu floatActionTransferencia;

    private BetterSpinner bspSecretaria;
    private BetterSpinner bspSetor;
    private BetterSpinner bspLocal;

    private SpinnerArrayAdapter<Secretaria> secretariaSpinnerArrayAdapter;
    private SpinnerArrayAdapter<Setor> setorSpinnerArrayAdapter;
    private SpinnerArrayAdapter<Local> localSpinnerArrayAdapter;

    private LinearLayout lnResultadoTransferencia;

    private AppCompatTextView txtNumeroPatrimonioTransferencia;
    private AppCompatTextView txtNumeroSerialTransferencia;
    private AppCompatTextView txtSecretariaTransferencia;
    private AppCompatTextView txtSetorTransferencia;
    private AppCompatTextView txtLocalTransferencia;
    private AppCompatTextView txtGrupoTransferencia;
    private AppCompatTextView txtSubGrupoTransferencia;
    private AppCompatTextView txtMaterialTransferencia;
    private AppCompatTextView txtMarcaTransferencia;
    private AppCompatTextView txtModeloTransferencia;

    private AppCompatButton btnTransferir;


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
    PatrimonioDAOImpl patrimonioDAO;
    @Inject
    SecretariaDAOImpl secretariaDAO;
    @Inject
    SetorDAOImpl setorDAO;
    @Inject
    LocalDAOImpl localDAO;
    @Inject
    TransferenciaDAOImpl transferenciaDAO;
    @Inject
    UsuarioDAOImpl usuarioDAO;

    private Setor setorDestino;
    private Local localDestino;
    private Secretaria secretariaDestino;

    private Patrimonio patrimonio;

    private String numeropatrimonio;

    DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat dh = new SimpleDateFormat("HH:mm:ss");

    private static final String[] SETOR = new String[] {
            "SELECIONE SETOR"
    };

    private static final String[] LOCAL = new String[] {
            "SELECIONE LOCAL"
    };


    public TransferenciaFragment() {
        // Required empty public constructor
    }

    public static TransferenciaFragment newInstance() {
        TransferenciaFragment fragment = new TransferenciaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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


        secretariaDAO = new SecretariaDAOImpl(getContext());
        setorDAO = new SetorDAOImpl(getContext());
        localDAO = new LocalDAOImpl(getContext());
        patrimonioDAO = new PatrimonioDAOImpl(getContext());
        transferenciaDAO = new TransferenciaDAOImpl(getContext());
        grupoDAO = new GrupoDAOImpl(getContext());
        subGrupoDAO = new SubGrupoDAOImpl(getContext());
        materialDAO = new MaterialDAOImpl(getContext());
        marcaDAO = new MarcaDAOImpl(getContext());
        modeloDAO = new ModeloDAOImpl(getContext());
        usuarioDAO = new UsuarioDAOImpl(getContext());

        View view = inflater.inflate(R.layout.fragment_transferencia, container, false);

        lnResultadoTransferencia = (LinearLayout) view.findViewById(R.id.lnResultadoTransferencia);

        fabPesquisar = (FloatingActionButton) view.findViewById(R.id.menuPesquisaTransferencia);

        txtNumeroPatrimonioTransferencia = (AppCompatTextView) view.findViewById(R.id.txtNumeroPatrimonioTransferencia);
        txtNumeroSerialTransferencia = (AppCompatTextView) view.findViewById(R.id.txtNumeroSerialTransferencia);
        txtSecretariaTransferencia = (AppCompatTextView) view.findViewById(R.id.txtSecretariaTransferencia);
        txtSetorTransferencia = (AppCompatTextView) view.findViewById(R.id.txtSetorTransferencia);
        txtLocalTransferencia = (AppCompatTextView) view.findViewById(R.id.txtLocalTransferencia);
        txtGrupoTransferencia = (AppCompatTextView) view.findViewById(R.id.txtGrupoTransferencia);
        txtSubGrupoTransferencia = (AppCompatTextView) view.findViewById(R.id.txtSubGrupoTransferencia);
        txtMaterialTransferencia = (AppCompatTextView) view.findViewById(R.id.txtMaterialTransferencia);
        txtMarcaTransferencia = (AppCompatTextView) view.findViewById(R.id.txtMarcaTransferencia);
        txtModeloTransferencia = (AppCompatTextView) view.findViewById(R.id.txtModeloTransferencia);

        btnTransferir = (AppCompatButton) view.findViewById(R.id.btnTransferir);
        btnTransferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeSecretaria = bspSecretaria.getText().toString();
                String nomeSetor = bspSetor.getText().toString();
                String nomeLocal = bspLocal.getText().toString();
                boolean check = true;
                String textoError = "";

                if(nomeSecretaria == null){
                    textoError += "Secretaria campo Obrigatorio !";
                    check = false;
                }

                if(nomeSetor == null){
                    textoError += "Setor campo Obrigatorio !";
                    check = false;
                }

                if(nomeLocal == null){
                    textoError += "Local campo Obrigatorio !";
                    check = false;
                }

                if(check){
                    Usuario usuario = usuarioDAO.pesquisaUsuario();
                    Local localOrigem = localDAO.pesquisaLocalPorCodigo(patrimonio.idlocal);
                    Setor setorOrigem = setorDAO.pesquisaSetorPorCodigo(localOrigem.idsetor);

                    patrimonio.idlocal = (int)localDestino.idlocal;
                    patrimonio.flag = 1;
                    patrimonioDAO.atualizarPatrimonio(patrimonio);

                    Transferencia transferencia = new Transferencia((int)setorOrigem.idsetor,(int)setorDestino.idsetor,patrimonio.idpatrimonio,dt.format(new Date()),dh.format(new java.sql.Time(new Date().getTime())),(int)usuario.idusuario,1);
                    transferenciaDAO.inserirTransferencia(transferencia);

                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Transferência")
                            .setContentText("Dados transferido com sucesso !")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    lnResultadoTransferencia.setVisibility(View.GONE);
                                    edtPatrimonioTransferencia.setText("");
                                    edtSerialTransferencia.setText("");
                                    bspSecretaria.setText("");
                                    bspSetor.setText("");
                                    bspLocal.setText("");
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();

                }else{
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Transferência")
                            .setContentText(textoError)
                            .show();
                }
            }
        });

        floatActionTransferencia = (FloatingActionMenu) view.findViewById(R.id.menuFloatTransferencia);

        fabPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionTransferencia.close(true);
                PesquisaTransferenciaFragment pesquisa = PesquisaTransferenciaFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,pesquisa)
                        .commit();
            }
        });

        ArrayAdapter<String> adapterLocal = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, LOCAL);

        ArrayAdapter<String> adapterSetor = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, SETOR);

        bspLocal = (BetterSpinner) view.findViewById(R.id.spnLocalDestino);
        bspSetor = (BetterSpinner) view.findViewById(R.id.spnSetorDestino);
        bspSecretaria = (BetterSpinner) view.findViewById(R.id.spnSecretariaTransferencia);

        List<Secretaria> secretarias = secretariaDAO.pesquisaTodasSecretarias();

        secretariaSpinnerArrayAdapter = new SpinnerArrayAdapter<Secretaria>(getContext(),secretarias){
            @Override
            public String itemToString(Secretaria item) {
                carregaSetor((int) item.idsecretaria);
                return item.nomsecretaria;
            }
        };

        bspLocal.setAdapter(adapterLocal);

        bspSetor.setAdapter(adapterSetor);

        bspSecretaria.setAdapter(secretariaSpinnerArrayAdapter);


        edtPatrimonioTransferencia = (AppCompatEditText) view.findViewById(R.id.edtPatrimonioTransferencia);
        edtPatrimonioTransferencia.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtPatrimonioTransferencia) {
            @Override
            public boolean onDrawableClick()
            {
                ((PrincipalActivity)getActivity()).executarScanner();
                return true;
            }
        });

        edtSerialTransferencia = (AppCompatEditText) view.findViewById(R.id.edtSerialTransferencia);
        edtSerialTransferencia.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtSerialTransferencia) {
            @Override
            public boolean onDrawableClick() {

                patrimonio = patrimonioDAO.pesquisaPorPatrimonio(edtPatrimonioTransferencia.getText().toString(), edtSerialTransferencia.getText().toString());

                if(patrimonio == null){
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Transferência")
                            .setContentText("Patrimonio não encontrado !")
                            .show();
                }else if(validaTransferencia(edtPatrimonioTransferencia.getText().toString(), edtSerialTransferencia.getText().toString())){
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Transferência")
                            .setContentText("Transferência já realizada para esse Patrimônio !")
                            .show();
                }else{
                    exibirPesquisaTransferencia();
                }

                return false;
            }
        });


        if(numeropatrimonio != null){
            edtPatrimonioTransferencia.setText(numeropatrimonio);
            patrimonio = patrimonioDAO.pesquisaPorPatrimonio(numeropatrimonio);

            if(patrimonio == null){
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Transferência")
                        .setContentText("Patrimonio não encontrado !")
                        .show();
            }else if(validaTransferencia(edtPatrimonioTransferencia.getText().toString(), edtSerialTransferencia.getText().toString())){
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Transferência")
                        .setContentText("Transferência já realizada para esse Patrimônio !")
                        .show();

            }else {
                exibirPesquisaTransferencia();
            }
        }

        return view;
    }

    public void exibirPesquisaTransferencia(){
        Local local = localDAO.pesquisaLocalPorCodigo(patrimonio.idlocal);
        Setor setor = setorDAO.pesquisaSetorPorCodigo(local.idsetor);
        Secretaria secretaria = secretariaDAO.pesquisaSecretariaPorCodigo(setor.idsecretaria);
        Material material = materialDAO.pesquisaMaterialPorCodigo(patrimonio.idmaterial);
        SubGrupo subGrupo = subGrupoDAO.pesquisaSubGrupoPorCodigo(material.idsubgrupo);
        Grupo grupo = grupoDAO.pesquisaGrupoPorCodigo(subGrupo.idgrupo);
        Modelo modelo = modeloDAO.pesquisaModeloPorCodigo(patrimonio.idmodelo);
        Marca marca = marcaDAO.pesquisaMarcaCodigo(modelo.idmarca);

        txtSecretariaTransferencia.setText(secretaria.nomsecretaria);
        txtSetorTransferencia.setText(setor.nomsetor);
        txtLocalTransferencia.setText(local.desclocal);
        txtGrupoTransferencia.setText(grupo.descgrupo);
        txtSubGrupoTransferencia.setText(subGrupo.descsubgrupo);
        txtMaterialTransferencia.setText(material.descmaterial);
        txtMarcaTransferencia.setText(marca.descmarca);
        txtModeloTransferencia.setText(modelo.descmodelo);

        lnResultadoTransferencia.setVisibility(View.VISIBLE);
    }


    public boolean validaTransferencia(String numeropatrimonio, String numeroserial){
        boolean resultado = transferenciaDAO.pesquisaTransferenciaPorPatrimonio(numeropatrimonio) != null || transferenciaDAO.pesquisaTransferenciaPorSerial(numeroserial) != null;
        return resultado;
    }

    public void carregaSetor(int idSecretaria){
        List<Setor> setors = setorDAO.pesquisaSetorPorSecretaria(idSecretaria);
        setorSpinnerArrayAdapter = new SpinnerArrayAdapter<Setor>(getContext(),setors){
            @Override
            public String itemToString(Setor item) {
                carregaLocal((int) item.idsetor);
                return item.nomsetor;
            }
        };

        bspSetor.setAdapter(setorSpinnerArrayAdapter);

        bspSetor.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                setorDestino = setorSpinnerArrayAdapter.stringToItem(string);
            }
        });
    }

    public void carregaLocal(int idSetor){
        List<Local> locals = localDAO.pesquisaLocalPorSetor(idSetor);
        localSpinnerArrayAdapter = new SpinnerArrayAdapter<Local>(getContext(),locals){
            @Override
            public String itemToString(Local item) {
                return item.desclocal;
            }
        };

        bspLocal.setAdapter(localSpinnerArrayAdapter);

        bspLocal.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                localDestino = localSpinnerArrayAdapter.stringToItem(string);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        patrimonioDAO.close();
        secretariaDAO.close();
        setorDAO.close();
        localDAO.close();
        grupoDAO.close();
        subGrupoDAO.close();
        materialDAO.close();
        marcaDAO.close();
        modeloDAO.close();
     }

}
