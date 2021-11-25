package br.gov.mt.saude.cuiaba.e_patrimonio.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.SpinnerArrayAdapter;
import br.gov.mt.saude.cuiaba.e_patrimonio.component.DrawableClickListener;
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
import br.gov.mt.saude.cuiaba.e_patrimonio.listener.OnItemSelectedListener;
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
import br.gov.mt.saude.cuiaba.e_patrimonio.ui.active.PrincipalActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class PatrimonioFragment extends Fragment implements Validator.ValidationListener {

    private FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;

    @NotEmpty(message = "Patrimonio campo Obrigatório !")
    private AppCompatEditText edtPatrimonio;
    @NotEmpty(message = "Serial campo Obrigatório !")
    private AppCompatEditText edtSerialPatrimonio;
    @NotEmpty(message = "Secretaria campo Obrigatório !")
    private BetterSpinner bspSecretaria;
    @NotEmpty(message = "Setor campo Obrigatório !")
    private BetterSpinner bspSetor;
    @NotEmpty(message = "Local campo Obrigatório !")
    private BetterSpinner bspLocal;
    @NotEmpty(message = "Grupo campo Obrigatório !")
    private BetterSpinner bspGrupo;
    @NotEmpty(message = "SubGrupo campo Obrigatório !")
    private BetterSpinner bspSubGrupo;
    @NotEmpty(message = "Material campo Obrigatório !")
    private BetterSpinner bspMaterial;
    @NotEmpty(message = "Marca campo Obrigatório !")
    private BetterSpinner bspMarca;
    @NotEmpty(message = "Modelo campo Obrigatório !")
    private BetterSpinner bspModelo;
    @NotEmpty(message = "Forncedor campo Obrigatório !")
    private BetterSpinner bspFornecedor;
    @NotEmpty(message = "Situação do Patrimonio campo Obrigatório !")
    private BetterSpinner bspSituacaoPatrimonio;

    private AppCompatEditText edtDescricao;

    private SpinnerArrayAdapter<Secretaria> secretariaSpinnerArrayAdapter;
    private SpinnerArrayAdapter<Setor> setorSpinnerArrayAdapter;
    private SpinnerArrayAdapter<Local> localSpinnerArrayAdapter;

    private SpinnerArrayAdapter<Grupo> grupoSpinnerArrayAdapter;
    private SpinnerArrayAdapter<SubGrupo> subGrupoSpinnerArrayAdapter;
    private SpinnerArrayAdapter<Material> materialSpinnerArrayAdapter;

    private SpinnerArrayAdapter<Marca> marcaSpinnerArrayAdapter;
    private SpinnerArrayAdapter<Modelo> modeloSpinnerArrayAdapter;
    private SpinnerArrayAdapter<Fornecedor> fornecedorSpinnerArrayAdapter;
    private SpinnerArrayAdapter<Patrimonio> patrimonioSpinnerArrayAdapter;

    private String numeropatrimonio;

    private static final String[] SETOR = new String[] {
            "SELECIONE UM SETOR"
    };

    private static final String[] LOCAL = new String[] {
            "SELECIONE UM LOCAL"
    };

    private static final String[] MATERIAL = new String[] {
            "SELECIONE UM MATERIAL"
    };

    private static final String[] GRUPO = new String[] {
            "SELECIONE UM GRUPO"
    };

    private static final String[] SUBGRUPO = new String[] {
            "SELECIONE UM SUBGRUPO"
    };

    private static final String[] MARCA = new String[] {
            "SELECIONE UM MARCA"
    };

    private static final String[] MODELO = new String[] {
            "SELECIONE UM MODELO"
    };

    private static final String[] FORNECEDOR = new String[] {
            "SELECIONE UM FORNECEDOR"
    };


    private static final String[] SITUACAO = new String[] {
            "DOADO","NOVO","USADO","QUEBRADO"
    };


    @Inject
    SecretariaDAOImpl secretariaDAO;
    @Inject
    SetorDAOImpl setorDAO;
    @Inject
    LocalDAOImpl localDAO;
    @Inject
    GrupoDAOImpl grupoDAO;
    @Inject
    SubGrupoDAOImpl subGrupoDAO;
    @Inject
    MaterialDAOImpl materialDAO;
    @Inject
    FornecedorDAOImpl fornecedorDAO;
    @Inject
    MarcaDAOImpl marcaDAO;
    @Inject
    ModeloDAOImpl modeloDAO;
    @Inject
    PatrimonioDAOImpl patrimonioDAO;

    private FloatingActionButton fabSalvar;
    private FloatingActionButton fabPesquisar;
    private FloatingActionMenu floatActionPatrimonio;

    private boolean validated = false;
    private Validator validator;

    private Patrimonio patrimonio;
    private Local local;
    private Fornecedor fornecedor;
    private Material material;
    private Modelo modelo;
    private Secretaria secretaria;
    private Setor setor;
    private Grupo grupo;
    private SubGrupo subGrupo;
    private Marca marca;


    public PatrimonioFragment() {
    }

    public static PatrimonioFragment newInstance() {
        PatrimonioFragment fragment = new PatrimonioFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            numeropatrimonio = getArguments().getString("numeropatrimonio");
            patrimonio = (Patrimonio) getArguments().getSerializable("patrimonio");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        secretariaDAO = new SecretariaDAOImpl(getContext());
        setorDAO = new SetorDAOImpl(getContext());
        localDAO = new LocalDAOImpl(getContext());
        grupoDAO = new GrupoDAOImpl(getContext());
        subGrupoDAO = new SubGrupoDAOImpl(getContext());
        materialDAO = new MaterialDAOImpl(getContext());
        fornecedorDAO = new FornecedorDAOImpl(getContext());
        marcaDAO = new MarcaDAOImpl(getContext());
        modeloDAO = new ModeloDAOImpl(getContext());
        patrimonioDAO = new PatrimonioDAOImpl(getContext());

        View view = inflater.inflate(R.layout.fragment_patrimonio, container, false);

        validator = new Validator(this);
        validator.setValidationListener(this);

        fabSalvar = (FloatingActionButton) view.findViewById(R.id.menuSalvarPatrimonio);
        floatActionPatrimonio = (FloatingActionMenu) view.findViewById(R.id.menuFloatPatrimonio);

        fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionPatrimonio.close(true);
                validator.validate();
                if(validated) {
                    if(patrimonio != null && patrimonio.idpatrimonio != null){
                        int situacao = 0;

                        if(bspSituacaoPatrimonio.getText().toString().equals("DOADO")){
                            situacao = 1;
                        }else if(bspSituacaoPatrimonio.getText().toString().equals("NOVO")){
                            situacao = 2;
                        }else if(bspSituacaoPatrimonio.getText().toString().equals("USADO")){
                            situacao = 3;
                        }else if(bspSituacaoPatrimonio.getText().toString().equals("QUEBRADO")){
                            situacao = 4;
                        }


                        patrimonio.flag = 1;
                        patrimonio.idlocal = (int)local.idlocal;
                        patrimonio.idfornecedor = (int) fornecedor.idfornecedor;
                        patrimonio.serialpatrimonio = edtSerialPatrimonio.getText().toString().toUpperCase();
                        patrimonio.situacaopatrimonio = situacao;
                        patrimonio.idmaterial = (int) material.idmaterial;
                        patrimonio.idmodelo = (int) modelo.idmodelo;
                        patrimonio.obspatrimonio = edtDescricao.getText().toString();
                        patrimonioDAO.atualizarPatrimonio(patrimonio);

                    }else{
                        int situacao = 0;

                        if(bspSituacaoPatrimonio.getText().toString().equals("DOADO")){
                            situacao = 1;
                        }else if(bspSituacaoPatrimonio.getText().toString().equals("NOVO")){
                            situacao = 2;
                        }else if(bspSituacaoPatrimonio.getText().toString().equals("USADO")){
                            situacao = 3;
                        }else if(bspSituacaoPatrimonio.getText().toString().equals("QUEBRADO")){
                            situacao = 4;
                        }

                        Patrimonio p = new Patrimonio(edtPatrimonio.getText().toString(),(int)material.idmaterial,(int)modelo.idmodelo,(int) local.idlocal,(int) fornecedor.idfornecedor,0,
                                situacao,edtSerialPatrimonio.getText().toString(),0,edtDescricao.getText().toString(),1);

                        patrimonioDAO.inserirPatrimonio(p);
                    }
                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Dados salvo com Sucesso!")
                            .setContentText("Click no botão para fechar!")
                            .show();

                    limparPatrimonio();

                }

            }
        });

        fabPesquisar = (FloatingActionButton) view.findViewById(R.id.menuPesquisarPatrimonio);
        fabPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionPatrimonio.close(true);
                PesquisaPatrimonioFragment pesquisa = PesquisaPatrimonioFragment.newInstance();
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

        edtSerialPatrimonio = (AppCompatEditText) view.findViewById(R.id.edtSerialPatrimonio);

        edtDescricao = (AppCompatEditText) view.findViewById(R.id.edtDescricao);

        bspSecretaria = (BetterSpinner) view.findViewById(R.id.spnSecretariaPatrimonio);
        bspSetor = (BetterSpinner) view.findViewById(R.id.spnSetorPatrimonio);
        bspLocal = (BetterSpinner) view.findViewById(R.id.spnLocalPatrimonio);
        bspGrupo = (BetterSpinner) view.findViewById(R.id.spnGrupoPatrimonio);
        bspSubGrupo = (BetterSpinner) view.findViewById(R.id.spnSubGrupoPatrimonio);
        bspMaterial = (BetterSpinner) view.findViewById(R.id.spnMaterialPatrimonio);
        bspMarca = (BetterSpinner) view.findViewById(R.id.spnMarcaPatrimonio);
        bspModelo = (BetterSpinner) view.findViewById(R.id.spnModeloPatrimonio);
        bspFornecedor = (BetterSpinner) view.findViewById(R.id.spnFornecedorPatrimonio);
        bspSituacaoPatrimonio = (BetterSpinner) view.findViewById(R.id.spnSituacaoPatrimonio);

        List<Secretaria> secretarias = secretariaDAO.pesquisaTodasSecretarias();
        List<String> addSecretaria = new ArrayList<>();

        for(Secretaria secretaria : secretarias){
            addSecretaria.add(secretaria.nomsecretaria);
        }

        ArrayAdapter<String> adapterSecretaria = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, addSecretaria.toArray(new String[addSecretaria.size()]));

        secretariaSpinnerArrayAdapter = new SpinnerArrayAdapter<Secretaria>(getContext(),secretarias){
            @Override
            public String itemToString(Secretaria item) {
                return item.nomsecretaria;
            }
        };

        bspSecretaria.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                Secretaria secretaria = secretariaSpinnerArrayAdapter.stringToItem(string);
                carregaSetor((int) secretaria.idsecretaria);
            }
        });

        List<Grupo> grupos = grupoDAO.pesquisaTodosGrupos();
        List<String> addGrupos = new ArrayList<>();

        for(Grupo grupo : grupos){
            addGrupos.add(grupo.descgrupo);
        }

        ArrayAdapter<String> adapterGrupo = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, addGrupos.toArray(new String[addGrupos.size()]));

        grupoSpinnerArrayAdapter = new SpinnerArrayAdapter<Grupo>(getContext(),grupos){
            @Override
            public String itemToString(Grupo item) {
                return item.descgrupo;
            }
        };

        bspGrupo.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                Grupo grupo = grupoSpinnerArrayAdapter.stringToItem(string);
                carregaSubGrupo((int) grupo.idgrupo);
            }
        });

        List<Marca> marcas = marcaDAO.pesquisaTodasMarcas();
        List<String> addMarcas = new ArrayList<>();

        for (Marca marca : marcas){
            addMarcas.add(marca.descmarca);
        }

        marcaSpinnerArrayAdapter = new SpinnerArrayAdapter<Marca>(getContext(),marcas){
            @Override
            public String itemToString(Marca item) {
                return item.descmarca;
            }
        };

        bspMarca.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                Marca marca = marcaSpinnerArrayAdapter.stringToItem(string);
                carregaModelo((int) marca.idmarca);
            }
        });

        List<Fornecedor> fornecedors = fornecedorDAO.pesquisaTodosFornecedores();
        List<String> addFonecedores = new ArrayList<>();

        for (Fornecedor fornecedor : fornecedors){
            addFonecedores.add(fornecedor.razaosocial);
        }

        fornecedorSpinnerArrayAdapter = new SpinnerArrayAdapter<Fornecedor>(getContext(),fornecedors){
            @Override
            public String itemToString(Fornecedor item) {
                return item.razaosocial;
            }
        };

        bspFornecedor.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                fornecedor = fornecedorSpinnerArrayAdapter.stringToItem(string);
            }
        });

        ArrayAdapter<String> adapterFornecedor = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, addFonecedores.toArray(new String[addFonecedores.size()]));

        ArrayAdapter<String> adapterSituacao = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, SITUACAO);

        ArrayAdapter<String> adapterSetor = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, SETOR);

        ArrayAdapter<String> adapterLocal = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, LOCAL);

        ArrayAdapter<String> adapterSubGrupo = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, SUBGRUPO);

        ArrayAdapter<String> adapterMaterial = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, MATERIAL);

        ArrayAdapter<String> adapterMarca = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, addMarcas.toArray(new String[addMarcas.size()]));

        ArrayAdapter<String> adapterModelo = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, MODELO);

        bspSecretaria.setAdapter(adapterSecretaria);

        bspSetor.setAdapter(adapterSetor);
        bspLocal.setAdapter(adapterLocal);
        bspGrupo.setAdapter(adapterGrupo);
        bspSubGrupo.setAdapter(adapterSubGrupo);
        bspMaterial.setAdapter(adapterMaterial);
        bspMarca.setAdapter(adapterMarca);
        bspModelo.setAdapter(adapterModelo);
        bspFornecedor.setAdapter(adapterFornecedor);
        bspSituacaoPatrimonio.setAdapter(adapterSituacao);

        if(numeropatrimonio != null){
            edtPatrimonio.setText(numeropatrimonio);
            pesquisaPatrimonio(numeropatrimonio);
        }

        if(patrimonio != null){
            atualizaPatrimonio(patrimonio);
        }

        return view;
    }

    public void pesquisaPatrimonio(String numeropatrimonio){
        patrimonio = patrimonioDAO.pesquisaPorPatrimonio(numeropatrimonio);
        if(patrimonio != null) {
            atualizaPatrimonio(patrimonio);
        }
    }

    public void limparPatrimonio(){
        PatrimonioFragment fragment = PatrimonioFragment.newInstance();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void atualizaPatrimonio(Patrimonio patrimonio){
        local = localDAO.pesquisaLocalPorCodigo(patrimonio.idlocal);
        setor = setorDAO.pesquisaSetorPorCodigo(local.idsetor);
        secretaria = secretariaDAO.pesquisaSecretariaPorCodigo(setor.idsecretaria);
        material = materialDAO.pesquisaMaterialPorCodigo(patrimonio.idmaterial);
        subGrupo = subGrupoDAO.pesquisaSubGrupoPorCodigo(material.idsubgrupo);
        grupo = grupoDAO.pesquisaGrupoPorCodigo(subGrupo.idgrupo);
        modelo = modeloDAO.pesquisaModeloPorCodigo(patrimonio.idmodelo);
        marca = marcaDAO.pesquisaMarcaCodigo(modelo.idmarca);
        fornecedor = fornecedorDAO.pesquisaFornecedorPorCodigo(patrimonio.idfornecedor);

        String situacao = "";

        switch (patrimonio.situacaopatrimonio){
            case 1:
                situacao = "DOADO";
                break;
            case 2:
                situacao = "NOVO";
                break;
            case 3:
                situacao = "USADO";
                break;
            case 4:
                situacao = "QUEBRADO";
                break;

        }

        edtPatrimonio.setText(patrimonio.idpatrimonio);
        edtSerialPatrimonio.setText(patrimonio.serialpatrimonio);
        bspSituacaoPatrimonio.setText(situacao);
        bspSecretaria.setText(secretaria.nomsecretaria);
        bspSetor.setText(setor.nomsetor);
        bspLocal.setText(local.desclocal);
        bspGrupo.setText(grupo.descgrupo);
        bspSubGrupo.setText(subGrupo.descsubgrupo);
        bspMaterial.setText(material.descmaterial);
        bspMarca.setText(marca.descmarca);
        bspModelo.setText(modelo.descmodelo);
        bspFornecedor.setText(fornecedor.razaosocial);
        edtDescricao.setText(patrimonio.obspatrimonio);
    }

    public void carregaSetor(int idSecretaria){
        List<Setor> setors = setorDAO.pesquisaSetorPorSecretaria(idSecretaria);
        List<String> addSetores = new ArrayList<>();

        for(Setor setor :setors){
            addSetores.add(setor.nomsetor);
        }

        ArrayAdapter<String> adapterSetor = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, addSetores.toArray(new String[addSetores.size()]));

        setorSpinnerArrayAdapter = new SpinnerArrayAdapter<Setor>(getContext(),setors){
            @Override
            public String itemToString(Setor item) {
                return item.nomsetor;
            }
        };

        bspSetor.setAdapter(adapterSetor);

        bspSetor.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                Setor setor = setorSpinnerArrayAdapter.stringToItem(string);
                carregaLocal((int) setor.idsetor);
            }
        });
    }

    public void carregaLocal(int idSetor){
        final List<Local> locals = localDAO.pesquisaLocalPorSetor(idSetor);
        List<String> addLocais = new ArrayList<>();

        for(Local local : locals){
            addLocais.add(local.desclocal);
        }

        ArrayAdapter<String> adapterLocal = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, addLocais.toArray(new String[addLocais.size()]));

        localSpinnerArrayAdapter = new SpinnerArrayAdapter<Local>(getContext(),locals){
            @Override
            public String itemToString(Local item) {
                return item.desclocal;
            }
        };

        bspLocal.setAdapter(adapterLocal);

        bspLocal.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                local = localSpinnerArrayAdapter.stringToItem(string);
            }
        });
    }

    public void carregaSubGrupo(int idGrupo){
        List<SubGrupo> subGrupos = subGrupoDAO.pesquisaSubGrupoPorGrupo(idGrupo);
        List<String> addSubgrupos = new ArrayList<>();

        for (SubGrupo subGrupo : subGrupos){
            addSubgrupos.add(subGrupo.descsubgrupo);
        }

        ArrayAdapter<String> adapterSubGrupo = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item,addSubgrupos.toArray(new String[addSubgrupos.size()]));

        subGrupoSpinnerArrayAdapter = new SpinnerArrayAdapter<SubGrupo>(getContext(), subGrupos){
            @Override
            public String itemToString(SubGrupo item) {
                return item.descsubgrupo;
            }
        };

        bspSubGrupo.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                SubGrupo subGrupo = subGrupoSpinnerArrayAdapter.stringToItem(string);
                carregaMaterial((int)subGrupo.idsubgrupo);
            }
        });

        bspSubGrupo.setAdapter(adapterSubGrupo);
    }

    public void carregaMaterial(int idSubGrupo){
        List<Material> materials = materialDAO.pesquisaMaterialPOrSubGrupo(idSubGrupo);
        List<String> addMaterias = new ArrayList<>();

        for(Material material : materials){
            addMaterias.add(material.descmaterial);
        }

        ArrayAdapter<String> adapterMaterias = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item,addMaterias.toArray(new String[addMaterias.size()]));

        materialSpinnerArrayAdapter = new SpinnerArrayAdapter<Material>(getContext(), materials){
            @Override
            public String itemToString(Material item) {
                return item.descmaterial;
            }
        };

        bspMaterial.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                material = materialSpinnerArrayAdapter.stringToItem(string);
            }
        });

        bspMaterial.setAdapter(adapterMaterias);

    }

    public void carregaModelo(int idMarca){
        List<Modelo> modelos = modeloDAO.pesquisaModeloPorMarca(idMarca);
        List<String> addModelos = new ArrayList<>();

        for (Modelo modelo : modelos){
            addModelos.add(modelo.descmodelo);
        }

        ArrayAdapter<String> adapterModelos = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item,addModelos.toArray(new String[addModelos.size()]));

        modeloSpinnerArrayAdapter = new SpinnerArrayAdapter<Modelo>(getContext(),modelos){
            @Override
            public String itemToString(Modelo item) {
                return item.descmodelo;
            }
        };

        bspModelo.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                modelo = modeloSpinnerArrayAdapter.stringToItem(string);
            }
        });

        bspModelo.setAdapter(adapterModelos);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        patrimonioDAO.close();
        grupoDAO.close();
        subGrupoDAO.close();
        materialDAO.close();
        marcaDAO.close();
        modeloDAO.close();
        fornecedorDAO.close();
        secretariaDAO.close();
        setorDAO.close();
        localDAO.close();
    }

    @Override
    public void onValidationSucceeded() {
        validated = true;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {

            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());
            validated = true;

            if(view instanceof  AppCompatEditText){
                ((AppCompatEditText) view).setError(message);
                validated = false;
            }

            if (view instanceof BetterSpinner) {
                ((BetterSpinner) view).setError(message);
                validated = false;
            }
        }
    }
}
