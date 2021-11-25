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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.inqbarna.tablefixheaders.TableFixHeaders;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.TableFixHeadersAdapterFactory;
import br.gov.mt.saude.cuiaba.e_patrimonio.component.DrawableClickListener;


public class PesquisaTransferenciaFragment extends Fragment {

    private String texto;
    private int tipo;
    private AppCompatEditText edtPesquisaTransferencia;
    private TableFixHeaders tableFixHeaders;
    private TableFixHeadersAdapterFactory tableFixHeadersAdapterFactory;
    private FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;
    private static int tipoPesquisa = 0;

    private RadioGroup rdGrupoTransferencia;

    public PesquisaTransferenciaFragment() {
        // Required empty public constructor
    }

    public static PesquisaTransferenciaFragment newInstance() {
        PesquisaTransferenciaFragment fragment = new PesquisaTransferenciaFragment();
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

        View view = inflater.inflate(R.layout.fragment_pesquisa_transferencia, container, false);


        edtPesquisaTransferencia = (AppCompatEditText) view.findViewById(R.id.edtPesquisaTransferencia);
        tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.tableTransferencia);
        tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());

        rdGrupoTransferencia = (RadioGroup) view.findViewById(R.id.rdGrupoTransferencia);

        edtPesquisaTransferencia.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtPesquisaTransferencia) {
            @Override
            public boolean onDrawableClick() {
                setTexto(edtPesquisaTransferencia.getText().toString());
                setTipo(tipoPesquisa);
                tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(getContext(), getTexto(), getTipo());
                createTable(TableFixHeadersAdapterFactory.TRANSFERENCIA);

                return false;
            }
        });

        rdGrupoTransferencia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rdbPatrimonioTransferencia:
                        tipoPesquisa = 1;
                        break;
                    case R.id.rdbSerialTransferencia:
                        tipoPesquisa = 2;
                        break;
                }
            }
        });

        createTable(TableFixHeadersAdapterFactory.TRANSFERENCIA);


        return view;
    }

    public void refresh(){
        TransferenciaFragment fragment = TransferenciaFragment.newInstance();
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
