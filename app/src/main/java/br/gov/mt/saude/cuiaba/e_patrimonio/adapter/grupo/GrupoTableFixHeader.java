package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.grupo;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.TableFixHeadersAdapterFactory;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.GrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.PesquisaGrupoFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;
import cn.pedant.SweetAlert.SweetAlertDialog;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;

/**
 * Created by miguel on 12/02/2016.
 */
public class GrupoTableFixHeader {
    private Context context;
    private String texto;
    private int tipo;

    @Inject
    GrupoDAOImpl grupoDAO;

    public GrupoTableFixHeader(Context context, String texto, int tipo) {
        this.context = context;
        this.texto = texto;
        this.tipo = tipo;
    }

    public BaseTableAdapter getInstance() {
        GrupoTableFixHeaderAdapter adapter = new GrupoTableFixHeaderAdapter(context);
        List<Grupo> body = getBody();

        adapter.setFirstHeader("Codigo");
        adapter.setHeader(getHeader());
        adapter.setFirstBody(body);
        adapter.setBody(body);
        adapter.setSection(body);

        setListeners(adapter);

        return adapter;
    }

    private void setListeners(GrupoTableFixHeaderAdapter adapter) {
        TableFixHeaderAdapter.ClickListener<String, GrupoCellViewGroup> clickListenerHeader = new TableFixHeaderAdapter.ClickListener<String, GrupoCellViewGroup>() {
            @Override
            public void onClickItem(String s, GrupoCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + s + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Grupo, GrupoCellViewGroup> clickListenerBody = new TableFixHeaderAdapter.ClickListener<Grupo, GrupoCellViewGroup>() {
            @Override
            public void onClickItem(final Grupo item, GrupoCellViewGroup viewGroup, int row, int column) {
                if(column + 1 == 0) {
                    Fragment fragment = PesquisaGrupoFragment.newInstance();
                    ((PesquisaGrupoFragment) fragment).retornarGrupo(item);
                }else if(column + 1 == 2){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Grupo")
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
                                    grupoDAO.deletarGrupo(item);
                                    Fragment fragment = PesquisaGrupoFragment.newInstance();
                                    ((PesquisaGrupoFragment) fragment).refresh();
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }
                //Snackbar.make(viewGroup, "Click on  (" + item.idgrupo + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Grupo, GrupoCellViewGroup> clickListenerSection = new TableFixHeaderAdapter.ClickListener<Grupo, GrupoCellViewGroup>() {
            @Override
            public void onClickItem(Grupo item, GrupoCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + item.idgrupo + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
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

    private List<Grupo> getBody() {
        grupoDAO = new GrupoDAOImpl(context);
        List<Grupo> items;
        if(texto == null){
            items = grupoDAO.pesquisaTodosGrupos();
        }else{
            items = grupoDAO.pesquisaGrupoPorDescricao(texto);
        }

        return items;
    }
}
