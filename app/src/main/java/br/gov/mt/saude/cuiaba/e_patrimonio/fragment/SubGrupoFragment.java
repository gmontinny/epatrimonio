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
import android.widget.Toast;

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
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.SubGrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.listener.OnItemSelectedListener;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.SubGrupo;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SubGrupoFragment extends Fragment implements Validator.ValidationListener{

    @NotEmpty(message = "Grupo campo Obrigatório !")
    private BetterSpinner bspGrupo;
    private FloatingActionButton fabSalvar;
    private FloatingActionButton fabPesquisar;
    private FloatingActionMenu floatActionSubGrupo;
    private SpinnerArrayAdapter<Grupo> grupoSpinnerAdapter;
    @NotEmpty(message = "Descrição campo Obrigatório !")
    private AppCompatEditText edtSubGrupo;
    private boolean validated = false;
    private Validator validator;


    @Inject
    GrupoDAOImpl grupoDAO;

    @Inject
    SubGrupoDAOImpl subgrupoDAO;


    private SubGrupo subGrupo;
    private Grupo grupo;

    public SubGrupoFragment() {
        // Required empty public constructor
    }

    public static SubGrupoFragment newInstance() {
        SubGrupoFragment fragment = new SubGrupoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            subGrupo = (SubGrupo)  getArguments().getSerializable("subgrupo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        grupoDAO = new GrupoDAOImpl(getContext());
        subgrupoDAO = new SubGrupoDAOImpl(getContext());

        validator = new Validator(this);
        validator.setValidationListener(this);

        View view = inflater.inflate(R.layout.fragment_sub_grupo, container, false);
        bspGrupo = (BetterSpinner) view.findViewById(R.id.spnGrupo);
        fabSalvar = (FloatingActionButton) view.findViewById(R.id.menuSalvarSubGrupo);
        fabPesquisar = (FloatingActionButton) view.findViewById(R.id.menuPesquisarSubGrupo);
        floatActionSubGrupo = (FloatingActionMenu) view.findViewById(R.id.menuFloatSubGrupo);
        edtSubGrupo = (AppCompatEditText) view.findViewById(R.id.edtSubGrupo);

        List<Grupo> grupos = grupoDAO.pesquisaTodosGrupos();

        grupoSpinnerAdapter = new SpinnerArrayAdapter<Grupo>(getContext(), grupos){
            @Override
            public String itemToString(Grupo item) {
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

        fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionSubGrupo.close(true);
                validator.validate();
                if(validated) {
                    if(subGrupo != null && subGrupo.idsubgrupo != 0){
                        subGrupo.flag = 1;
                        subGrupo.descsubgrupo = edtSubGrupo.getText().toString().toUpperCase();
                        subGrupo.idgrupo = (int) grupo.idgrupo;
                        subgrupoDAO.atualizarSubGrupo(subGrupo);
                    }else{
                        SubGrupo s = new SubGrupo((int)grupo.idgrupo,edtSubGrupo.getText().toString().toUpperCase(),1);
                        subgrupoDAO.inserirSubGrupo(s);
                    }
                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Dados salvo com Sucesso!")
                            .setContentText("Click no botão para fechar!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    edtSubGrupo.setText("");
                                    bspGrupo.setText("");
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        });

        fabPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionSubGrupo.close(true);
                PesquisaSubGrupoFragment pesquisa = PesquisaSubGrupoFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,pesquisa)
                        .commit();

            }
        });


        edtSubGrupo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        if(subGrupo != null){
            edtSubGrupo.setText(subGrupo.descsubgrupo);
            grupo = grupoDAO.pesquisaGrupoPorCodigo(subGrupo.idgrupo);
            bspGrupo.setText(grupo.descgrupo);
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        grupoDAO.close();
        subgrupoDAO.close();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
