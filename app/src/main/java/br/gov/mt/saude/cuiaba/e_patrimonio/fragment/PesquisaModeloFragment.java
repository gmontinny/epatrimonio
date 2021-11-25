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

import com.inqbarna.tablefixheaders.TableFixHeaders;


import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.TableFixHeadersAdapterFactory;
import br.gov.mt.saude.cuiaba.e_patrimonio.component.DrawableClickListener;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Modelo;

public class PesquisaModeloFragment extends Fragment {
    private String texto;
    private int tipo;
    private AppCompatEditText edtPesquisaModelo;
    private TableFixHeaders tableFixHeaders;
    private TableFixHeadersAdapterFactory tableFixHeadersAdapterFactory;
    private FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;

    public PesquisaModeloFragment() {
        // Required empty public constructor
    }

    public static PesquisaModeloFragment newInstance() {
        PesquisaModeloFragment fragment = new PesquisaModeloFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        View view = inflater.inflate(R.layout.fragment_pesquisa_modelo, container, false);
        edtPesquisaModelo = (AppCompatEditText) view.findViewById(R.id.edtPesquisaModelo);
        tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.tableModelo);
        tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());

        edtPesquisaModelo.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtPesquisaModelo) {
            @Override
            public boolean onDrawableClick() {
                setTexto(edtPesquisaModelo.getText().toString());
                setTipo(1);
                tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());
                createTable(TableFixHeadersAdapterFactory.MODELO);
                return true;
            }
        });

        createTable(TableFixHeadersAdapterFactory.MODELO);

        return view;
    }

    public void retornarModelo(Modelo modelo){
        ModeloFragment fragment = ModeloFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable("modelo", modelo);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void refresh(){
        ModeloFragment fragment = ModeloFragment.newInstance();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    private void createTable(int type) {
        tableFixHeaders.setAdapter(tableFixHeadersAdapterFactory.getAdapter(type));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
