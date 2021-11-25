package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.material;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MaterialDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.PesquisaMaterialFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Material;
import cn.pedant.SweetAlert.SweetAlertDialog;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;

/**
 * Created by miguel on 12/02/2016.
 */
public class MaterialTableFixHeader {
    private Context context;
    private String texto;
    private int tipo;

    @Inject
    MaterialDAOImpl materialDAO;

    public MaterialTableFixHeader(Context context, String texto, int tipo) {
        this.context = context;
        this.texto = texto;
        this.tipo = tipo;
    }

    public BaseTableAdapter getInstance() {
        MaterialTableFixHeaderAdapter adapter = new MaterialTableFixHeaderAdapter(context);
        List<Material> body = getBody();

        adapter.setFirstHeader("Codigo");
        adapter.setHeader(getHeader());
        adapter.setFirstBody(body);
        adapter.setBody(body);
        adapter.setSection(body);

        setListeners(adapter);

        return adapter;
    }

    private void setListeners(MaterialTableFixHeaderAdapter adapter) {
        TableFixHeaderAdapter.ClickListener<String, MaterialCellViewGroup> clickListenerHeader = new TableFixHeaderAdapter.ClickListener<String, MaterialCellViewGroup>() {
            @Override
            public void onClickItem(String s, MaterialCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + s + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Material, MaterialCellViewGroup> clickListenerBody = new TableFixHeaderAdapter.ClickListener<Material, MaterialCellViewGroup>() {
            @Override
            public void onClickItem(final Material item, MaterialCellViewGroup viewGroup, int row, int column) {
                if(column + 1 == 0) {
                    Fragment fragment = PesquisaMaterialFragment.newInstance();
                    ((PesquisaMaterialFragment) fragment).retornarMaterial(item);
                }else if(column + 1 == 3){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Material")
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
                                    materialDAO.deletarMaterial(item);
                                    Fragment fragment = PesquisaMaterialFragment.newInstance();
                                    ((PesquisaMaterialFragment) fragment).refresh();
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }
               //Snackbar.make(viewGroup, "Click on  (" + item.idgrupo + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<Material, MaterialCellViewGroup> clickListenerSection = new TableFixHeaderAdapter.ClickListener<Material, MaterialCellViewGroup>() {
            @Override
            public void onClickItem(Material item, MaterialCellViewGroup viewGroup, int row, int column) {
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
                "SubGrupo",
                "Material",
                "-"
        };

        return Arrays.asList(headers);
    }

    private List<Material> getBody() {
        materialDAO = new MaterialDAOImpl(context);
        List<Material> items;
        if(texto == null){
            items = materialDAO.pesquisaTodosMaterials();
        }else{
            items = materialDAO.pesquisaMaterialPorDescricao(texto);
        }

        return items;
    }
}
