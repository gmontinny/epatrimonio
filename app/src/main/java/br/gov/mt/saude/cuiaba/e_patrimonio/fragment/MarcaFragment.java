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
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MarcaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Marca;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class MarcaFragment extends Fragment implements Validator.ValidationListener{

    private FloatingActionButton fabPesquisar;
    private FloatingActionButton fabSalvar;
    private FloatingActionMenu floatActionMarca;
    @NotEmpty(message = "Descrição campo Obrigatório !")
    private AppCompatEditText edtMarca;
    private boolean validated = false;
    private Validator validator;

    @Inject
    MarcaDAOImpl marcaDAO;

    private Marca marca;



    public MarcaFragment() {
        // Required empty public constructor
    }

    public static MarcaFragment newInstance() {
        MarcaFragment fragment = new MarcaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            marca = (Marca) getArguments().getSerializable("marca");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_marca, container, false);
        marcaDAO = new MarcaDAOImpl(getContext());
        validator = new Validator(this);
        validator.setValidationListener(this);
        fabSalvar = (FloatingActionButton) view.findViewById(R.id.menuSalvarMarca);
        fabPesquisar = (FloatingActionButton) view.findViewById(R.id.menuPesquisarMarca);
        floatActionMarca = (FloatingActionMenu) view.findViewById(R.id.menuFloatMarca);

        edtMarca = (AppCompatEditText) view.findViewById(R.id.edtMarca);

        fabPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionMarca.close(true);
                PesquisaMarcaFragment pesquisa = PesquisaMarcaFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,pesquisa)
                        .commit();
            }
        });

        fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionMarca.close(true);
                validator.validate();

                if(validated) {
                    if(marca != null && marca.getIdmarca() != 0){
                        marca.flag = 1;
                        marca.descmarca = edtMarca.getText().toString().toUpperCase();
                        marcaDAO.atualizarMarca(marca);
                    }else{
                        Marca m = new Marca(edtMarca.getText().toString().toUpperCase(), 1);
                        marcaDAO.inserirMarca(m);
                    }

                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Dados salvo com Sucesso!")
                            .setContentText("Click no botão para fechar!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    edtMarca.setText("");
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();

                }

            }
        });

        edtMarca.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        if(marca != null){
            edtMarca.setText(marca.descmarca);
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
        marcaDAO.close();
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
