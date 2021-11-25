package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.modelo;

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
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MarcaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Marca;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Modelo;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 09/02/2016.
 */
public class ModeloCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.FirstHeaderBinder<String>,
        TableFixHeaderAdapter.HeaderBinder<String>,
        TableFixHeaderAdapter.FirstBodyBinder<Modelo>,
        TableFixHeaderAdapter.BodyBinder<Modelo>,
        TableFixHeaderAdapter.SectionBinder<Modelo> {

    private Context context;
    public TextView textView;
    public TextView textView2;
    public View vg_root;

    @Inject
    MarcaDAOImpl marcaDAO;

    public ModeloCellViewGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ModeloCellViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        marcaDAO = new MarcaDAOImpl(context);
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
    public void bindFirstBody(Modelo item, int row) {
        textView.setText(String.valueOf(item.idmodelo));
        textView.setTypeface(null, Typeface.NORMAL);
        vg_root.setBackgroundResource(row % 2 == 0 ? R.drawable.cell_lightgray_border_bottom_right_gray : R.drawable.cell_white_border_bottom_right_gray);
    }

    @Override
    public void bindBody(Modelo item, int row, int column) {
        if(column + 1 == 1 ) {
            Marca marca = marcaDAO.pesquisaMarcaCodigo(item.idmarca);
            textView.setVisibility(View.GONE);
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(marca.descmarca);
            textView2.setTypeface(null, Typeface.NORMAL);
            textView2.setGravity(Gravity.LEFT);
        }else if(column + 1 == 2 ) {
            textView.setText(item.descmodelo);
            textView.setTypeface(null, Typeface.NORMAL);
            textView.setGravity(Gravity.LEFT);
        }else if(column + 1 == 3 ) {
            textView.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.GONE);
            textView.setBackgroundResource(R.drawable.ic_delete_black_24dp);
            textView.setPadding(15,15,15,15);
            textView.setGravity(Gravity.CENTER);
        }
        vg_root.setBackgroundResource(row % 2 == 0 ? R.drawable.cell_lightgray_border_bottom_right_gray : R.drawable.cell_white_border_bottom_right_gray);
    }

    @Override
    public void bindSection(Modelo item, int row, int column) {
        textView.setText(column == 0 ? String.valueOf(item.idmarca): "");
        textView.setTypeface(null, Typeface.BOLD);
        vg_root.setBackgroundResource(R.drawable.cell_blue_border_bottom_right_gray);
    }
}
