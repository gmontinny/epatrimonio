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

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.GrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class GrupoFragment extends Fragment implements Validator.ValidationListener{

    private FloatingActionButton fabPesquisar;
    private FloatingActionButton fabSalvar;
    private FloatingActionMenu floatActionGrupo;
    @NotEmpty(message = "Descrição campo Obrigatório !")
    private AppCompatEditText edtGrupo;
    private boolean validated = false;
    private Validator validator;

    @Inject
    GrupoDAOImpl grupoDAO;

    private Grupo grupo;

    public GrupoFragment() {
        // Required empty public constructor
    }

    public static GrupoFragment newInstance() {
        GrupoFragment fragment = new GrupoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            grupo = (Grupo) getArguments().getSerializable("grupo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grupo, container, false);
        grupoDAO = new GrupoDAOImpl(getContext());
        validator = new Validator(this);
        validator.setValidationListener(this);
        edtGrupo = view.findViewById(R.id.edtGrupo);
        fabPesquisar = (FloatingActionButton) view.findViewById(R.id.menuPesquisarGrupo);
        fabSalvar = (FloatingActionButton) view.findViewById(R.id.menuSalvarGrupo);
        floatActionGrupo = (FloatingActionMenu) view.findViewById(R.id.menuFloatGrupo);

        fabPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionGrupo.close(true);
                PesquisaGrupoFragment pesquisa = PesquisaGrupoFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,pesquisa)
                        .commit();
            }
        });

        fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionGrupo.close(true);
                validator.validate();
                if(validated) {
                    if(grupo != null && grupo.idgrupo != 0){
                        grupo.flag = 1;
                        grupo.descgrupo = edtGrupo.getText().toString().toUpperCase();
                        grupoDAO.atualizaGrupo(grupo);
                    }else {
                        Grupo g = new Grupo(edtGrupo.getText().toString().toUpperCase(),1);
                        grupoDAO.inserirGrupo(g);
                    }
                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Dados salvo com Sucesso!")
                            .setContentText("Click no botão para fechar!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    edtGrupo.setText("");
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        });

        edtGrupo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        if(grupo != null){
            edtGrupo.setText(grupo.descgrupo);
        }

        return view;
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
        }
    }
}
