package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.baixa;

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
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Baixa;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 09/02/2016.
 */
public class BaixaCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.FirstHeaderBinder<String>,
        TableFixHeaderAdapter.HeaderBinder<String>,
        TableFixHeaderAdapter.FirstBodyBinder<Baixa>,
        TableFixHeaderAdapter.BodyBinder<Baixa>,
        TableFixHeaderAdapter.SectionBinder<Baixa> {

    private Context context;
    public TextView textView;
    public View vg_root;

    @Inject
    PatrimonioDAOImpl patrimonioDAO;

    public BaixaCellViewGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public BaixaCellViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.text_view_group, this, true);
        textView = (TextView) findViewById(R.id.tv_text);
        patrimonioDAO = new PatrimonioDAOImpl(context);
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
    public void bindFirstBody(Baixa item, int row) {
        textView.setText(String.valueOf(item.idbaixa));
        textView.setTypeface(null, Typeface.NORMAL);
        vg_root.setBackgroundResource(row % 2 == 0 ? R.drawable.cell_lightgray_border_bottom_right_gray : R.drawable.cell_white_border_bottom_right_gray);
    }

    @Override
    public void bindBody(Baixa item, int row, int column) {
        Patrimonio patrimonio = patrimonioDAO.pesquisaPorPatrimonio(item.idpatrimonio);
        if(column + 1 == 1) {
            textView.setText(item.idpatrimonio);
            textView.setTypeface(null, Typeface.NORMAL);
            textView.setGravity(Gravity.LEFT);
        }else if(column + 1 == 2){
            textView.setText(patrimonio.serialpatrimonio);
            textView.setTypeface(null, Typeface.NORMAL);
            textView.setGravity(Gravity.LEFT);
        }else if(column + 1 == 3){
            textView.setBackgroundResource(R.drawable.ic_delete_black_24dp);
            textView.setPadding(15,15,15,15);
            textView.setGravity(Gravity.CENTER);
        }
        vg_root.setBackgroundResource(row % 2 == 0 ? R.drawable.cell_lightgray_border_bottom_right_gray : R.drawable.cell_white_border_bottom_right_gray);
    }

    @Override
    public void bindSection(Baixa item, int row, int column) {
        textView.setText(column == 0 ? String.valueOf(item.idbaixa): "");
        textView.setTypeface(null, Typeface.BOLD);
        vg_root.setBackgroundResource(R.drawable.cell_blue_border_bottom_right_gray);
    }
}
