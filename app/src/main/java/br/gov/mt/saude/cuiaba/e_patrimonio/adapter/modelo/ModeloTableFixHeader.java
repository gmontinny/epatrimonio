package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.modelo;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.ModeloDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.PesquisaModeloFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Modelo;
import cn.pedant.SweetAlert.SweetAlertDialog;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;

/**
 * Created by miguel on 12/02/2016.
 */
public class ModeloTableFixHeader {
    private Context context;
    private String texto;
    private int tipo;

    @Inject
    ModeloDAOImpl modeloDAO;

    public ModeloTableFixHeader(Context context, String texto, int tipo) {
        this.context = context;
        this.texto = texto;
        this.tipo = tipo;
    }

    public BaseTableAdapter getInstance() {
        ModeloTableFixHeaderAdapter adapter = new ModeloTableFixHeaderAdapter(context);
        List<Modelo> body = getBody();

        adapter.setFirstHeader("Codigo");
        adapter.setHeader(getHeader());
        adapter.setFirstBody(body);
        adapter.setBody(body);
        adapter.setSection(body);

        setListeners(adapter);

        return adapter;
    }

    private void setListeners(ModeloTableFixHeaderAdapter adapter) {
        TableFixHeaderAdapter.ClickListener<String, ModeloCellViewGroup> clickListenerHeader = new TableFixHeaderAdapter.ClickListener<String, ModeloCellViewGroup>() {
            @Override
            public void onClickItem(String s, ModeloCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + s + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Modelo, ModeloCellViewGroup> clickListenerBody = new TableFixHeaderAdapter.ClickListener<Modelo, ModeloCellViewGroup>() {
            @Override
            public void onClickItem(final Modelo item, ModeloCellViewGroup viewGroup, int row, int column) {
                if(column + 1 == 0) {
                    Fragment fragment = PesquisaModeloFragment.newInstance();
                    ((PesquisaModeloFragment) fragment).retornarModelo(item);
                }else if(column + 1 == 3){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Modelo")
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
                                    modeloDAO.deletarModelo(item);
                                    Fragment fragment = PesquisaModeloFragment.newInstance();
                                    ((PesquisaModeloFragment) fragment).refresh();
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }
               //Snackbar.make(viewGroup, "Click on  (" + item.idgrupo + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Modelo, ModeloCellViewGroup> clickListenerSection = new TableFixHeaderAdapter.ClickListener<Modelo, ModeloCellViewGroup>() {
            @Override
            public void onClickItem(Modelo item, ModeloCellViewGroup viewGroup, int row, int column) {
                //Snackbar.make(viewGroup, "Click on " + item.idgrupo + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
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
                "Marca",
                "Modelo",
                "-"
        };

        return Arrays.asList(headers);
    }

    private List<Modelo> getBody() {
        modeloDAO = new ModeloDAOImpl(context);
        List<Modelo> items;
        if(texto == null){
            items = modeloDAO.pesquisaTodosModelos();
        }else{
            items = modeloDAO.pesquisaDescricaoModelo(texto);
        }

        return items;
    }
}
