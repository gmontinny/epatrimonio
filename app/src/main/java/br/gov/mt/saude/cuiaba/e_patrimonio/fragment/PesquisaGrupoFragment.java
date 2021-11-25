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

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.TableFixHeadersAdapterFactory;
import br.gov.mt.saude.cuiaba.e_patrimonio.component.DrawableClickListener;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.GrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;

public class PesquisaGrupoFragment extends Fragment {

    private String texto;
    private int tipo;
    private AppCompatEditText edtPesquisaGrupo;
    private TableFixHeaders tableFixHeaders;
    private TableFixHeadersAdapterFactory tableFixHeadersAdapterFactory;
    private FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;

    @Inject
    GrupoDAOImpl grupoDAO;

    public PesquisaGrupoFragment() {
    }

    public static PesquisaGrupoFragment newInstance() {
        PesquisaGrupoFragment fragment = new PesquisaGrupoFragment();
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

        grupoDAO = new GrupoDAOImpl(getContext());

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        View view = inflater.inflate(R.layout.fragment_pesquisa_grupo, container, false);
        edtPesquisaGrupo = (AppCompatEditText) view.findViewById(R.id.edtPesquisaGrupo);
        tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.tableGrupo);
        tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());


        edtPesquisaGrupo.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtPesquisaGrupo) {
            @Override
            public boolean onDrawableClick() {
                //Toast.makeText(getContext(),"Pesquisar", Toast.LENGTH_LONG).show();
                setTexto(edtPesquisaGrupo.getText().toString());
                setTipo(0);
                tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());
                createTable(TableFixHeadersAdapterFactory.GRUPO);
                return true;
            }
        });

        createTable(TableFixHeadersAdapterFactory.GRUPO);
        return view;
    }


    public void retornarGrupo(Grupo grupo){
        GrupoFragment fragment = GrupoFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable("grupo", grupo);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void refresh(){
        GrupoFragment fragment = GrupoFragment.newInstance();
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
        grupoDAO.close();
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
