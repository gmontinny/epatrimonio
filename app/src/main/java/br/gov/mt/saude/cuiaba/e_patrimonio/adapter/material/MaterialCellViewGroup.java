package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.material;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.SubGrupoDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.GrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.SubGrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Material;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Material;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.SubGrupo;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 09/02/2016.
 */
public class MaterialCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.FirstHeaderBinder<String>,
        TableFixHeaderAdapter.HeaderBinder<String>,
        TableFixHeaderAdapter.FirstBodyBinder<Material>,
        TableFixHeaderAdapter.BodyBinder<Material>,
        TableFixHeaderAdapter.SectionBinder<Material> {

    private Context context;
    public TextView textView;
    public TextView textView2;
    public View vg_root;

    @Inject
    SubGrupoDAO subGrupoDAO;

    public MaterialCellViewGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MaterialCellViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        subGrupoDAO = new SubGrupoDAOImpl(context);
        LayoutInflater.from(context).inflate(R.layout.text_view_group, this, true);
        textView = (TextView) findViewById(R.id.tv_text);
        textView2 = (TextView) findViewById(R.id.tv_text2);
        vg_root = findViewById(R.id.vg_root);
    }

    @Override
    public void bindFirstHeader(String headerName) {
        textView.setText(headerName.toUpperCase());
        textView.setTypeface(null, Typeface.BOLD);
        textView.setGravity(Gravity.CENTER);
        vg_root.setBackgroundResource(R.drawable.cell_header_border_bottom_right_gray);
    }

    @Override
    public void bindHeader(String headerName, int column) {
        textView.setText(headerName.toUpperCase());
        textView.setTypeface(null, Typeface.BOLD);
        textView.setGravity(Gravity.CENTER);
        vg_root.setBackgroundResource(R.drawable.cell_header_border_bottom_right_gray);
    }

    @Override
    public void bindFirstBody(Material item, int row) {
        textView.setText(String.valueOf(item.idmaterial));
        textView.setTypeface(null, Typeface.NORMAL);
        vg_root.setBackgroundResource(row % 2 == 0 ? R.drawable.cell_lightgray_border_bottom_right_gray : R.drawable.cell_white_border_bottom_right_gray);
    }

    @Override
    public void bindBody(Material item, int row, int column) {
        SubGrupo subgrupo = subGrupoDAO.pesquisaSubGrupoPorCodigo(item.idsubgrupo);
        if(column + 1 == 1 ) {
            textView.setVisibility(View.GONE);
            textView2.setText(subgrupo.descsubgrupo);
            textView2.setTypeface(null, Typeface.NORMAL);
            textView2.setGravity(Gravity.LEFT);
        }else if(column + 1 == 2 ) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(item.descmaterial);
            textView.setTypeface(null, Typeface.NORMAL);
            textView.setGravity(Gravity.LEFT);
        }else if(column + 1 == 3 ) {
            textView.setVisibility(View.VISIBLE);
            textView.setBackgroundResource(R.drawable.ic_delete_black_24dp);
            textView.setPadding(15,15,15,15);
            textView.setGravity(Gravity.CENTER);
        }
        vg_root.setBackgroundResource(row % 2 == 0 ? R.drawable.cell_lightgray_border_bottom_right_gray : R.drawable.cell_white_border_bottom_right_gray);
    }

    @Override
    public void bindSection(Material item, int row, int column) {
        textView.setText(column == 0 ? String.valueOf(item.idmaterial): "");
        textView.setTypeface(null, Typeface.BOLD);
        vg_root.setBackgroundResource(R.drawable.cell_blue_border_bottom_right_gray);
    }
}
