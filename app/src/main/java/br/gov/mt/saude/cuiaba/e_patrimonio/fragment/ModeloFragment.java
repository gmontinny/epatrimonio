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
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.SpinnerArrayAdapter;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MarcaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.ModeloDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.listener.OnItemSelectedListener;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Marca;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Modelo;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class ModeloFragment extends Fragment implements Validator.ValidationListener{
    @NotEmpty(message = "Marca campo Obrigatório !")
    private BetterSpinner bspMarca;
    private FloatingActionButton fabSalvar;
    private FloatingActionButton fabPesquisar;
    private FloatingActionMenu floatActionModelo;
    private SpinnerArrayAdapter<Marca> marcaSpinnerAdapter;
    @NotEmpty(message = "Descrição campo Obrigatório !")
    private AppCompatEditText edtModelo;
    private boolean validated = false;
    private Validator validator;

    @Inject
    MarcaDAOImpl marcaDAO;

    @Inject
    ModeloDAOImpl modeloDAO;

    private Modelo modelo;
    private Marca marca;


    public ModeloFragment() {
        // Required empty public constructor
    }

    public static ModeloFragment newInstance() {
        ModeloFragment fragment = new ModeloFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            modelo = (Modelo)  getArguments().getSerializable("modelo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        modeloDAO = new ModeloDAOImpl(getContext());
        marcaDAO = new MarcaDAOImpl(getContext());
        validator = new Validator(this);
        validator.setValidationListener(this);

        View view = inflater.inflate(R.layout.fragment_modelo, container, false);
        bspMarca = (BetterSpinner) view.findViewById(R.id.spnMarca);
        fabSalvar = (FloatingActionButton) view.findViewById(R.id.menuSalvarModelo);
        fabPesquisar = (FloatingActionButton) view.findViewById(R.id.menuPesquisarModelo);
        floatActionModelo = (FloatingActionMenu) view.findViewById(R.id.menuFloatModelo);
        edtModelo = (AppCompatEditText) view.findViewById(R.id.edtModelo);

        final List<Marca> marcas = marcaDAO.pesquisaTodasMarcas();

        marcaSpinnerAdapter = new SpinnerArrayAdapter<Marca>(getContext(),marcas){
            @Override
            public String itemToString(Marca item) {
                return item.descmarca; // make sure that is unique
            }
        };

        bspMarca.setAdapter(marcaSpinnerAdapter);

        bspMarca.addTextChangedListener(new OnItemSelectedListener() {
            @Override
            protected void onItemSelected(String string) {
                marca = marcaSpinnerAdapter.stringToItem(string);
            }
        });

        fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatActionModelo.close(true);
                validator.validate();
                if(validated){

                    if(modelo!= null && modelo.getIdmodelo() != 0){
                        modelo.flag = 1;
                        modelo.idmarca = (int) marca.getIdmarca();
                        modelo.descmodelo = edtModelo.getText().toString().toUpperCase();
                        modeloDAO.atualizarModelo(modelo);
                    }else{
                        Modelo m = new Modelo((int) marca.getIdmarca(),edtModelo.getText().toString().toUpperCase(),1);
                        modeloDAO.inserirModelo(m);
                    }

                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Dados salvo com Sucesso!")
                            .setContentText("Click no botão para fechar!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    edtModelo.setText("");
                                    bspMarca.setText("");
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
                floatActionModelo.close(true);
                PesquisaModeloFragment pesquisa = PesquisaModeloFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,pesquisa)
                        .commit();
            }
        });

        edtModelo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        if(modelo != null){
            edtModelo.setText(modelo.descmodelo);
            marca = marcaDAO.pesquisaMarcaCodigo((int)modelo.idmarca);
            bspMarca.setText(marca.descmarca);
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
        modeloDAO.close();
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

            if (view instanceof BetterSpinner) {
                ((BetterSpinner) view).setError(message);
                validated = false;
            }
        }
    }
}
