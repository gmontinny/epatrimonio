package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.transferencia;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.TransferenciaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.PesquisaTransferenciaFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Transferencia;
import cn.pedant.SweetAlert.SweetAlertDialog;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;

/**
 * Created by miguel on 12/02/2016.
 */
public class TransferenciaTableFixHeader {
    private Context context;
    private String texto;
    private int tipo;

    @Inject
    TransferenciaDAOImpl transferenciaDAO;
    @Inject
    PatrimonioDAOImpl patrimonioDAO;

    public TransferenciaTableFixHeader(Context context, String texto, int tipo) {
        this.context = context;
        this.texto = texto;
        this.tipo = tipo;
    }

    public BaseTableAdapter getInstance() {
        TransferenciaTableFixHeaderAdapter adapter = new TransferenciaTableFixHeaderAdapter(context);
        List<Transferencia> body = getBody();

        patrimonioDAO = new PatrimonioDAOImpl(context);

        adapter.setFirstHeader("Codigo");
        adapter.setHeader(getHeader());
        adapter.setFirstBody(body);
        adapter.setBody(body);
        adapter.setSection(body);

        setListeners(adapter);

        return adapter;
    }

    private void setListeners(TransferenciaTableFixHeaderAdapter adapter) {
        TableFixHeaderAdapter.ClickListener<String, TransferenciaCellViewGroup> clickListenerHeader = new TableFixHeaderAdapter.ClickListener<String, TransferenciaCellViewGroup>() {
            @Override
            public void onClickItem(String s, TransferenciaCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + s + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Transferencia, TransferenciaCellViewGroup> clickListenerBody = new TableFixHeaderAdapter.ClickListener<Transferencia, TransferenciaCellViewGroup>() {
            @Override
            public void onClickItem(final Transferencia item, TransferenciaCellViewGroup viewGroup, int row, int column) {
                 if(column + 1 == 3){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Transferência")
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
                                    Patrimonio patrimonio = patrimonioDAO.pesquisaPorPatrimonio(item.idpatrimonio);
                                    patrimonio.flag = 0;
                                    patrimonioDAO.atualizarPatrimonio(patrimonio);
                                    transferenciaDAO.removerTransferenciaPorCodigo(item);
                                    Fragment fragment = PesquisaTransferenciaFragment.newInstance();
                                    ((PesquisaTransferenciaFragment) fragment).refresh();
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }
                //Snackbar.make(viewGroup, "Click on  (" + item.idgrupo + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Transferencia, TransferenciaCellViewGroup> clickListenerSection = new TableFixHeaderAdapter.ClickListener<Transferencia, TransferenciaCellViewGroup>() {
            @Override
            public void onClickItem(Transferencia item, TransferenciaCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + item.idtransferencia + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
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

    private List<Transferencia> getBody() {
        transferenciaDAO = new TransferenciaDAOImpl(context);
        List<Transferencia> items;

        if(texto == null){
            items = transferenciaDAO.pesquisaTodasTransferencia();
        }else{
            if(tipo == 1){
                items = transferenciaDAO.pesquisaTransferenciaPorSerialPatrimonio(texto,null);
            }else{
                items = transferenciaDAO.pesquisaTransferenciaPorSerialPatrimonio(null,texto);
            }

        }

        return items;
    }
}
