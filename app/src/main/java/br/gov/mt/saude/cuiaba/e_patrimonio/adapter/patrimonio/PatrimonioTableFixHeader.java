package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.patrimonio;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.PesquisaGrupoFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.PesquisaPatrimonioFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.PesquisaPatrimonioFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;
import cn.pedant.SweetAlert.SweetAlertDialog;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;

/**
 * Created by miguel on 12/02/2016.
 */
public class PatrimonioTableFixHeader {
    private Context context;
    private String texto;
    private int tipo;

    @Inject
    PatrimonioDAOImpl patrimonioDAO;


    public PatrimonioTableFixHeader(Context context, String texto, int tipo) {
        this.context = context;
        this.texto = texto;
        this.tipo = tipo;
    }

    public BaseTableAdapter getInstance() {
        PatrimonioTableFixHeaderAdapter adapter = new PatrimonioTableFixHeaderAdapter(context);
        List<Patrimonio> body = getBody();

        patrimonioDAO = new PatrimonioDAOImpl(context);

        adapter.setFirstHeader("Codigo");
        adapter.setHeader(getHeader());
        adapter.setFirstBody(body);
        adapter.setBody(body);
        adapter.setSection(body);

        setListeners(adapter);

        return adapter;
    }

    private void setListeners(PatrimonioTableFixHeaderAdapter adapter) {
        TableFixHeaderAdapter.ClickListener<String, PatrimonioCellViewGroup> clickListenerHeader = new TableFixHeaderAdapter.ClickListener<String, PatrimonioCellViewGroup>() {
            @Override
            public void onClickItem(String s, PatrimonioCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + s + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Patrimonio, PatrimonioCellViewGroup> clickListenerBody = new TableFixHeaderAdapter.ClickListener<Patrimonio, PatrimonioCellViewGroup>() {
            @Override
            public void onClickItem(final Patrimonio item, PatrimonioCellViewGroup viewGroup, int row, int column) {
                if(column + 1 == 0) {
                    Fragment fragment = PesquisaPatrimonioFragment.newInstance();
                    ((PesquisaPatrimonioFragment) fragment).retornarPatrimonio(item);
                }else                
                 if(column + 1 == 3){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Patrimonio")
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
                                    patrimonioDAO.deletarPatrimonio(item);
                                    Fragment fragment = PesquisaPatrimonioFragment.newInstance();
                                    ((PesquisaPatrimonioFragment) fragment).refresh();
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }
                //Snackbar.make(viewGroup, "Click on  (" + item.idgrupo + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Patrimonio, PatrimonioCellViewGroup> clickListenerSection = new TableFixHeaderAdapter.ClickListener<Patrimonio, PatrimonioCellViewGroup>() {
            @Override
            public void onClickItem(Patrimonio item, PatrimonioCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + item.idpatrimonio + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
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
                "Patrimonio",
                "Serial",
                "-"
        };

        return Arrays.asList(headers);
    }

    private List<Patrimonio> getBody() {
        patrimonioDAO = new PatrimonioDAOImpl(context);
        List<Patrimonio> items;

        if(texto == null){
            items = patrimonioDAO.pesquisaTodosPatrimonios();
        }else{
            if(tipo == 1){
                items = patrimonioDAO.pesquisaPorNumeroPatrimonioESerial(texto,null);
            }else{
                items = patrimonioDAO.pesquisaPorNumeroPatrimonioESerial(null,texto);
            }

        }

        return items;
    }
}
