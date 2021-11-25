package br.gov.mt.saude.cuiaba.e_patrimonio.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inqbarna.tablefixheaders.TableFixHeaders;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.TableFixHeadersAdapterFactory;
import br.gov.mt.saude.cuiaba.e_patrimonio.component.DrawableClickListener;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MaterialDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Material;

public class PesquisaMaterialFragment extends Fragment {
    private String texto;
    private int tipo;
    private AppCompatEditText edtPesquisaMaterial;
    private TableFixHeaders tableFixHeaders;
    private TableFixHeadersAdapterFactory tableFixHeadersAdapterFactory;
    private FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;

    @Inject
    MaterialDAOImpl materialDAO;

    public PesquisaMaterialFragment() {
        // Required empty public constructor
    }

    public static PesquisaMaterialFragment newInstance() {
        PesquisaMaterialFragment fragment = new PesquisaMaterialFragment();
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

        materialDAO = new MaterialDAOImpl(getContext());

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        View view = inflater.inflate(R.layout.fragment_pesquisa_material, container, false);

        edtPesquisaMaterial = (AppCompatEditText) view.findViewById(R.id.edtPesquisaMaterial);
        tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.tableMaterial);
        tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());

        edtPesquisaMaterial.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtPesquisaMaterial) {
            @Override
            public boolean onDrawableClick() {
                setTexto(edtPesquisaMaterial.getText().toString());
                setTipo(1);
                tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());
                createTable(TableFixHeadersAdapterFactory.MATERIAL);
                return true;
            }
        });

        createTable(TableFixHeadersAdapterFactory.MATERIAL);

        return view;
    }


    public void retornarMaterial(Material material){
        MaterialFragment fragment = MaterialFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable("material", material);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void refresh(){
        SubGrupoFragment fragment = SubGrupoFragment.newInstance();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
        materialDAO.close();
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
}
