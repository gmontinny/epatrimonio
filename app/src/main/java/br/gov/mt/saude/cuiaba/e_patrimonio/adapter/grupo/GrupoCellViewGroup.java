package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.grupo;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 09/02/2016.
 */
public class GrupoCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.FirstHeaderBinder<String>,
        TableFixHeaderAdapter.HeaderBinder<String>,
        TableFixHeaderAdapter.FirstBodyBinder<Grupo>,
        TableFixHeaderAdapter.BodyBinder<Grupo>,
        TableFixHeaderAdapter.SectionBinder<Grupo> {

    private Context context;
    public TextView textView;
    public View vg_root;

    public GrupoCellViewGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public GrupoCellViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.text_view_group, this, true);
        textView = (TextView) findViewById(R.id.tv_text);
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
    public void bindFirstBody(Grupo item, int row) {
        textView.setText(String.valueOf(item.idgrupo));
        textView.setTypeface(null, Typeface.NORMAL);
        vg_root.setBackgroundResource(row % 2 == 0 ? R.drawable.cell_lightgray_border_bottom_right_gray : R.drawable.cell_white_border_bottom_right_gray);
    }

    @Override
    public void bindBody(Grupo item, int row, int column) {
        if(column + 1 != 2 ) {
            textView.setText(item.descgrupo);
            textView.setTypeface(null, Typeface.NORMAL);
            textView.setGravity(Gravity.LEFT);
        }else{
            textView.setBackgroundResource(R.drawable.ic_delete_black_24dp);
            textView.setPadding(15,15,15,15);
            textView.setGravity(Gravity.CENTER);
        }
        vg_root.setBackgroundResource(row % 2 == 0 ? R.drawable.cell_lightgray_border_bottom_right_gray : R.drawable.cell_white_border_bottom_right_gray);
    }

    @Override
    public void bindSection(Grupo item, int row, int column) {
        textView.setText(column == 0 ? String.valueOf(item.idgrupo): "");
        textView.setTypeface(null, Typeface.BOLD);
        vg_root.setBackgroundResource(R.drawable.cell_blue_border_bottom_right_gray);
    }
}
