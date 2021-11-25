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
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.SubGrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.SubGrupo;

public class PesquisaSubGrupoFragment extends Fragment {

    private String texto;
    private int tipo;
    private AppCompatEditText edtPesquisaSubGrupo;
    private TableFixHeaders tableFixHeaders;
    private TableFixHeadersAdapterFactory tableFixHeadersAdapterFactory;
    private FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;

    public PesquisaSubGrupoFragment() {
        // Required empty public constructor
    }

    public static PesquisaSubGrupoFragment newInstance() {
        PesquisaSubGrupoFragment fragment = new PesquisaSubGrupoFragment();
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

        View view = inflater.inflate(R.layout.fragment_pesquisa_sub_grupo, container, false);
        edtPesquisaSubGrupo = (AppCompatEditText) view.findViewById(R.id.edtPesquisaSubGrupo);
        tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.tableSubGrupo);
        tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());

        edtPesquisaSubGrupo.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtPesquisaSubGrupo) {
            @Override
            public boolean onDrawableClick() {
                setTexto(edtPesquisaSubGrupo.getText().toString());
                setTipo(1);
                tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());
                createTable(TableFixHeadersAdapterFactory.SUBGRUPO);
                return true;
            }
        });

        createTable(TableFixHeadersAdapterFactory.SUBGRUPO);

        return view;
    }

    public void retornarSubGrupo(SubGrupo subgrupo){
        SubGrupoFragment fragment = SubGrupoFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable("subgrupo", subgrupo);
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


    public String getTexto() {
        return texto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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
