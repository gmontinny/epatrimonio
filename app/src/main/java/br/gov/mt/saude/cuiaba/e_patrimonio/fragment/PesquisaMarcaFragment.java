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
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MarcaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Marca;

public class PesquisaMarcaFragment extends Fragment {

    private String texto;
    private int tipo;
    private AppCompatEditText edtPesquisaMarca;
    private TableFixHeaders tableFixHeaders;
    private TableFixHeadersAdapterFactory tableFixHeadersAdapterFactory;
    private FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;

    @Inject
    MarcaDAOImpl marcaDAO;

    public PesquisaMarcaFragment() {
        // Required empty public constructor
    }

    public static PesquisaMarcaFragment newInstance() {
        PesquisaMarcaFragment fragment = new PesquisaMarcaFragment();
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
        marcaDAO = new MarcaDAOImpl(getContext());
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        View view = inflater.inflate(R.layout.fragment_pesquisa_marca, container, false);
        edtPesquisaMarca = (AppCompatEditText) view.findViewById(R.id.edtPesquisaMarca);
        tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.tableMarca);
        tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());

        edtPesquisaMarca.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtPesquisaMarca) {
            @Override
            public boolean onDrawableClick() {
                //Toast.makeText(getContext(),"Pesquisar", Toast.LENGTH_LONG).show();
                setTexto(edtPesquisaMarca.getText().toString());
                setTipo(0);
                tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());
                createTable(TableFixHeadersAdapterFactory.MARCA);
                return true;
            }
        });

        createTable(TableFixHeadersAdapterFactory.MARCA);

        return view;
    }

    public void retornarMarca(Marca marca){
        MarcaFragment fragment = MarcaFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable("marca", marca);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public void refresh(){
        MarcaFragment fragment = MarcaFragment.newInstance();
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
        marcaDAO.close();
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
