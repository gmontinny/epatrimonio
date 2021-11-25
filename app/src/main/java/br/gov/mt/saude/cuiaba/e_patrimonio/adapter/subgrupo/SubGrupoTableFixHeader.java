package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.subgrupo;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.SubGrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.PesquisaSubGrupoFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.SubGrupo;
import cn.pedant.SweetAlert.SweetAlertDialog;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;

/**
 * Created by miguel on 12/02/2016.
 */
public class SubGrupoTableFixHeader {
    private Context context;
    private String texto;
    private int tipo;

    @Inject
    SubGrupoDAOImpl subgrupoDAO;

    public SubGrupoTableFixHeader(Context context, String texto, int tipo) {
        this.context = context;
        this.texto = texto;
        this.tipo = tipo;
    }

    public BaseTableAdapter getInstance() {
        SubGrupoTableFixHeaderAdapter adapter = new SubGrupoTableFixHeaderAdapter(context);
        List<SubGrupo> body = getBody();

        adapter.setFirstHeader("Codigo");
        adapter.setHeader(getHeader());
        adapter.setFirstBody(body);
        adapter.setBody(body);
        adapter.setSection(body);

        setListeners(adapter);

        return adapter;
    }

    private void setListeners(SubGrupoTableFixHeaderAdapter adapter) {
        TableFixHeaderAdapter.ClickListener<String, SubGrupoCellViewGroup> clickListenerHeader = new TableFixHeaderAdapter.ClickListener<String, SubGrupoCellViewGroup>() {
            @Override
            public void onClickItem(String s, SubGrupoCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + s + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<SubGrupo, SubGrupoCellViewGroup> clickListenerBody = new TableFixHeaderAdapter.ClickListener<SubGrupo, SubGrupoCellViewGroup>() {
            @Override
            public void onClickItem(final SubGrupo item, SubGrupoCellViewGroup viewGroup, int row, int column) {
                if(column + 1 == 0) {
                    Fragment fragment = PesquisaSubGrupoFragment.newInstance();
                    ((PesquisaSubGrupoFragment) fragment).retornarSubGrupo(item);
                }else if(column + 1 == 3){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("SubGrupo")
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
                                    subgrupoDAO.deletarSubGrupo(item);
                                    Fragment fragment = PesquisaSubGrupoFragment.newInstance();
                                    ((PesquisaSubGrupoFragment) fragment).refresh();
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }
               //Snackbar.make(viewGroup, "Click on  (" + item.idgrupo + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<SubGrupo, SubGrupoCellViewGroup> clickListenerSection = new TableFixHeaderAdapter.ClickListener<SubGrupo, SubGrupoCellViewGroup>() {
            @Override
            public void onClickItem(SubGrupo item, SubGrupoCellViewGroup viewGroup, int row, int column) {
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
                "Grupo",
                "SubGrupo",
                "-"
        };

        return Arrays.asList(headers);
    }

    private List<SubGrupo> getBody() {
        subgrupoDAO = new SubGrupoDAOImpl(context);
        List<SubGrupo> items;
        if(texto == null){
            items = subgrupoDAO.pesquisaTodosSubGrupos();
        }else{
            items = subgrupoDAO.pesquisaSubGruposPorDescricao(texto);
        }

        return items;
    }
}
