package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.baixa;

import android.arch.persistence.room.Insert;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.BaixaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.PesquisaBaixaFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Baixa;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;
import cn.pedant.SweetAlert.SweetAlertDialog;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;

/**
 * Created by miguel on 12/02/2016.
 */
public class BaixaTableFixHeader {
    private Context context;
    private String texto;
    private int tipo;

    @Inject
    BaixaDAOImpl baixaDAO;
    @Inject PatrimonioDAOImpl patrimonioDAO;

    public BaixaTableFixHeader(Context context, String texto, int tipo) {
        this.context = context;
        this.texto = texto;
        this.tipo = tipo;
    }

    public BaseTableAdapter getInstance() {
        BaixaTableFixHeaderAdapter adapter = new BaixaTableFixHeaderAdapter(context);
        List<Baixa> body = getBody();
        patrimonioDAO = new PatrimonioDAOImpl(context);

        adapter.setFirstHeader("Codigo");
        adapter.setHeader(getHeader());
        adapter.setFirstBody(body);
        adapter.setBody(body);
        adapter.setSection(body);

        setListeners(adapter);

        return adapter;
    }

    private void setListeners(BaixaTableFixHeaderAdapter adapter) {
        TableFixHeaderAdapter.ClickListener<String, BaixaCellViewGroup> clickListenerHeader = new TableFixHeaderAdapter.ClickListener<String, BaixaCellViewGroup>() {
            @Override
            public void onClickItem(String s, BaixaCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + s + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Baixa, BaixaCellViewGroup> clickListenerBody = new TableFixHeaderAdapter.ClickListener<Baixa, BaixaCellViewGroup>() {
            @Override
            public void onClickItem(final Baixa item, BaixaCellViewGroup viewGroup, int row, int column) {
                 if(column + 1 == 3){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Baixa")
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
                                    baixaDAO.removerBaixaPorCodigo(item);
                                    Patrimonio patrimonio = patrimonioDAO.pesquisaPorPatrimonio(item.idpatrimonio);
                                    patrimonio.statusbaixa = 0;
                                    patrimonioDAO.atualizarPatrimonio(patrimonio);
                                    Fragment fragment = PesquisaBaixaFragment.newInstance();
                                    ((PesquisaBaixaFragment) fragment).refresh();
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }
                //Snackbar.make(viewGroup, "Click on  (" + item.idgrupo + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Baixa, BaixaCellViewGroup> clickListenerSection = new TableFixHeaderAdapter.ClickListener<Baixa, BaixaCellViewGroup>() {
            @Override
            public void onClickItem(Baixa item, BaixaCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + item.idbaixa + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
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

    private List<Baixa> getBody() {
        baixaDAO = new BaixaDAOImpl(context);
        List<Baixa> items;

        if(texto == null){
            items = baixaDAO.pesquisaBaixaGeral();
        }else{
            if(tipo == 1){
                items = baixaDAO.pesquisaBaixaPorSerialPatrimonio(texto,null);
            }else{
                items = baixaDAO.pesquisaBaixaPorSerialPatrimonio(null,texto);
            }

        }

        return items;
    }
}
