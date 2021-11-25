package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.marca;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MarcaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MarcaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.PesquisaMarcaFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Marca;
import cn.pedant.SweetAlert.SweetAlertDialog;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;

/**
 * Created by miguel on 12/02/2016.
 */
public class MarcaTableFixHeader {
    private Context context;
    private String texto;
    private int tipo;

    @Inject
    MarcaDAOImpl marcaDAO;

    public MarcaTableFixHeader(Context context, String texto, int tipo) {
        this.context = context;
        this.texto = texto;
        this.tipo = tipo;
    }

    public BaseTableAdapter getInstance() {
        MarcaTableFixHeaderAdapter adapter = new MarcaTableFixHeaderAdapter(context);
        List<Marca> body = getBody();

        adapter.setFirstHeader("Codigo");
        adapter.setHeader(getHeader());
        adapter.setFirstBody(body);
        adapter.setBody(body);
        adapter.setSection(body);

        setListeners(adapter);

        return adapter;
    }

    private void setListeners(MarcaTableFixHeaderAdapter adapter) {
        TableFixHeaderAdapter.ClickListener<String, MarcaCellViewGroup> clickListenerHeader = new TableFixHeaderAdapter.ClickListener<String, MarcaCellViewGroup>() {
            @Override
            public void onClickItem(String s, MarcaCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + s + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Marca, MarcaCellViewGroup> clickListenerBody = new TableFixHeaderAdapter.ClickListener<Marca, MarcaCellViewGroup>() {
            @Override
            public void onClickItem(final Marca item, MarcaCellViewGroup viewGroup, int row, int column) {
                if(column + 1 == 0) {
                    Fragment fragment = PesquisaMarcaFragment.newInstance();
                    ((PesquisaMarcaFragment) fragment).retornarMarca(item);
                }else if(column + 1 == 2){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Marca")
                            .setContentText("Deseja realmente deletar ?")
                            .setCancelText("Não!")
                            .setConfirmText("Sim!")
                            .showCancelButton(true)
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.cancel();
                                }
                            })
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    marcaDAO.deletarMarca(item);
                                    Fragment fragment = PesquisaMarcaFragment.newInstance();
                                    ((PesquisaMarcaFragment) fragment).refresh();
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }
                //Snackbar.make(viewGroup, "Click on  (" + item.idmarca + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Marca, MarcaCellViewGroup> clickListenerSection = new TableFixHeaderAdapter.ClickListener<Marca, MarcaCellViewGroup>() {
            @Override
            public void onClickItem(Marca item, MarcaCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + item.idmarca + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        adapter.setClickListenerFirstHeader(clickListenerHeader);
        adapter.setClickListenerHeader(clickListenerHeader);
        adapter.setClickListenerFirstBody(clickListenerBody);
        adapter.setClickListenerBody(clickListenerBody);
        adapter.setClickListenerSection(clickListenerSection);
    }

    private List<String> getHeader() {
        final String headers[] = {
                "Descrição",
                "-"
        };

        return Arrays.asList(headers);
    }

    private List<Marca> getBody() {
        marcaDAO = new MarcaDAOImpl(context);
        List<Marca> items;
        if(texto == null){
            items = marcaDAO.pesquisaTodasMarcas();
        }else{
            items = marcaDAO.pesquisaDescricaoMarca(texto);
        }

        return items;
    }
}
