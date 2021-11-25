package br.gov.mt.saude.cuiaba.e_patrimonio.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.SpinnerArrayAdapter;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.GrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MaterialDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.SubGrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.listener.OnItemSelectedListener;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Material;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.SubGrupo;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class MaterialFragment extends Fragment implements Validator.ValidationListener{

    @NotEmpty(message = "Grupo campo Obrigatório !")
    private BetterSpinner bspGrupo;

    @NotEmpty(message = "SubGrupo campo Obrigatório !")
    private BetterSpinner bspSubGrupo;

    private FloatingActionButton fabSalvar;
    private FloatingActionButton fabPesquisar;
    private FloatingActionMenu floatActionMaterial;

    private SpinnerArrayAdapter<Grupo> grupoSpinnerAdapter;
    private SpinnerArrayAdapter<SubGrupo> subGrupoSpinnerAdapter;

    @NotEmpty(message = "Material campo Obrigatório !")
    private AppCompatEditText edtMaterial;

    private boolean validated = false;
    private Validator validator;

    @Inject
    GrupoDAOImpl grupoDAO;

    @Inject
    SubGrupoDAOImpl subGrupoDAO;

    @Inject
    MaterialDAOImpl materialDAO;

    private SubGrupo subGrupo;
    private Grupo grupo;
    private Material material;

    private static final String[] SUBGRUPO = new String[] {
            "SELECIONE SUBGRUPO"
    };

    public MaterialFragment(){

    }

    public static MaterialFragment newInstance() {
        MaterialFragment fragment = new MaterialFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            material = (Material) getArguments().getSerializable("material");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_material, container, false);

        grupoDAO = new GrupoDAOImpl(getContext());
        subGrupoDAO = new SubGrupoDAOImpl(getContext());
        materialDAO = new MaterialDAOImpl(getContext());

        validator = new Validator(this);
        validator.setValidationListener(this);

        ArrayAdapter<String> adapterSubGrupo = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, SUBGRUPO);

        bspGrupo = (BetterSpinner) view.findViewById(R.id.spnGrupo);
        bspSubGrupo = (BetterSpinner) view.findViewById(R.id.spnSubGrupo);
        edtMaterial = (AppCompatEditText) view.findViewById(R.id.edtMaterial);

        fabSalvar = (FloatingActionButton) view.findViewById(R.id.menuSalvarMaterial);
        fabPesquisar = (FloatingActionButton) view.findViewById(R.id.menuPesquisarMaterial);
        floatActionMaterial = (FloatingActionMenu) view.findViewById(R.id.menuFloatMaterial);

        List<Grupo> grupos = grupoDAO.pesquisaTodosGrupos();

        grupoSpinnerAdapter = new SpinnerArrayAdapter<Grupo>(getContext(), grupos){
            @Override
            public String itemToString(Grupo item) {
                bspSubGrupo.setText("");
                carregarSubGrupo((int)item.idgrupo);
                return item.descgrupo; // make sure that is unique
            }
        };

        bspGrupo.setAdapter(grupoSpinnerAdapter);

        bspGrupo.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                grupo = grupoSpinnerAdapter.stringToItem(string);
            }
        });

        bspSubGrupo.setEnabled(false);
        bspSubGrupo.setClickable(false);
        bspSubGrupo.setAdapter(adapterSubGrupo);

        fabPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionMaterial.close(true);
                PesquisaMaterialFragment pesquisa = PesquisaMaterialFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,pesquisa)
                        .commit();
            }
        });

        fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionMaterial.close(true);
                validator.validate();
                if(validated) {

                    if(material != null && material.idmaterial != 0){
                        material.flag = 1;
                        material.idsubgrupo = (int) subGrupo.idsubgrupo;
                        material.descmaterial = edtMaterial.getText().toString().toUpperCase();
                        materialDAO.atualizarMaterial(material);
                    }else{
                        Material m = new Material((int) subGrupo.idsubgrupo, edtMaterial.getText().toString().toUpperCase(), 1 );
                        materialDAO.inserirMaterial(m);
                    }

                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Dados salvo com Sucesso!")
                            .setContentText("Click no botão para fechar!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    edtMaterial.setText("");
                                    bspGrupo.setText("");
                                    bspSubGrupo.setText("");
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        });

        edtMaterial.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        if(material != null){
            edtMaterial.setText(material.descmaterial);
            subGrupo = subGrupoDAO.pesquisaSubGrupoPorCodigo((int)material.idsubgrupo);

            String descricaoSubgrupo = subGrupo.descsubgrupo;

            grupo = grupoDAO.pesquisaGrupoPorCodigo((int) subGrupo.idgrupo);

            String descricaoGrupo = grupo.descgrupo;

            bspGrupo.setText(descricaoGrupo);
            bspSubGrupo.setText(descricaoSubgrupo);
        }

        return view;
    }

    private void carregarSubGrupo(int idGrupo){

        List<SubGrupo> subGrupos = subGrupoDAO.pesquisaSubGrupoPorGrupo(idGrupo);

        subGrupoSpinnerAdapter = new SpinnerArrayAdapter<SubGrupo>(getContext(), subGrupos){
            @Override
            public String itemToString(SubGrupo item) {
                return item.descsubgrupo; // make sure that is unique
            }
        };

        bspSubGrupo.setAdapter(subGrupoSpinnerAdapter);

        bspSubGrupo.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                subGrupo = subGrupoSpinnerAdapter.stringToItem(string);
            }
        });

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        grupoDAO.close();
        subGrupoDAO.close();
        materialDAO.close();
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

            if (view instanceof AppCompatEditText) {
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
