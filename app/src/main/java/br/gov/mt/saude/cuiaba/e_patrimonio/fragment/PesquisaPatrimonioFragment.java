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
import android.widget.RadioGroup;

import com.inqbarna.tablefixheaders.TableFixHeaders;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.TableFixHeadersAdapterFactory;
import br.gov.mt.saude.cuiaba.e_patrimonio.component.DrawableClickListener;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;

public class PesquisaPatrimonioFragment extends Fragment {


    private String texto;
    private int tipo;
    private AppCompatEditText edtPesquisaPatrimonio;
    private TableFixHeaders tableFixHeaders;
    private TableFixHeadersAdapterFactory tableFixHeadersAdapterFactory;
    private FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;
    private static int tipoPesquisa = 0;

    private RadioGroup rdPatrimonio;


    public PesquisaPatrimonioFragment() {
    }

    public static PesquisaPatrimonioFragment newInstance() {
        PesquisaPatrimonioFragment fragment = new PesquisaPatrimonioFragment();
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

        View view = inflater.inflate(R.layout.fragment_pesquisa_patrimonio, container, false);

        edtPesquisaPatrimonio = (AppCompatEditText) view.findViewById(R.id.edtPesquisaPatrimonio);
        tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.tablePatrimonio);
        tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());

        rdPatrimonio = (RadioGroup) view.findViewById(R.id.rdPesquisaPatrimonio);

        edtPesquisaPatrimonio.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtPesquisaPatrimonio) {
            @Override
            public boolean onDrawableClick() {
                setTexto(edtPesquisaPatrimonio.getText().toString());
                setTipo(tipoPesquisa);
                tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());
                createTable(TableFixHeadersAdapterFactory.PATRIMONIO);
                return false;
            }
        });

        rdPatrimonio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rdbNumeroPatrimonio:
                        tipoPesquisa = 1;
                        break;
                    case R.id.rdbSerialPatrimonio:
                        tipoPesquisa = 2;
                        break;
                }
            }
        });

        createTable(TableFixHeadersAdapterFactory.PATRIMONIO);
        // Inflate the layout for this fragment
        return view;
    }

    public void retornarPatrimonio(Patrimonio patrimonio){
        PatrimonioFragment fragment = PatrimonioFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable("patrimonio", patrimonio);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void refresh(){
        PatrimonioFragment fragment = PatrimonioFragment.newInstance();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void createTable(int type) {
        tableFixHeaders.setAdapter(tableFixHeadersAdapterFactory.getAdapter(type));
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
