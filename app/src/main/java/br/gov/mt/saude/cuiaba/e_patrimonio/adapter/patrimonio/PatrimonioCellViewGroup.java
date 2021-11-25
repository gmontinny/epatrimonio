package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.patrimonio;

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
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.LocalDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.SetorDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Local;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Setor;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 09/02/2016.
 */
public class PatrimonioCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.FirstHeaderBinder<String>,
        TableFixHeaderAdapter.HeaderBinder<String>,
        TableFixHeaderAdapter.FirstBodyBinder<Patrimonio>,
        TableFixHeaderAdapter.BodyBinder<Patrimonio>,
        TableFixHeaderAdapter.SectionBinder<Patrimonio> {

    private Context context;
    public TextView textView;
    public TextView textView2;
    public View vg_root;

    @Inject
    PatrimonioDAOImpl patrimonioDAO;
    @Inject
    LocalDAOImpl localDAO;
    @Inject
    SetorDAOImpl setorDAO;

    public PatrimonioCellViewGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PatrimonioCellViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.text_view_group, this, true);
        textView = (TextView) findViewById(R.id.tv_text);
        textView2 = (TextView) findViewById(R.id.tv_text2);
        patrimonioDAO = new PatrimonioDAOImpl(context);
        localDAO = new LocalDAOImpl(context);
        setorDAO = new SetorDAOImpl(context);
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
    public void bindFirstBody(Patrimonio item, int row) {
        textView.setText(String.valueOf(item.idpatrimonio));
        textView.setTypeface(null, Typeface.NORMAL);
        vg_root.setBackgroundResource(row % 2 == 0 ? R.drawable.cell_lightgray_border_bottom_right_gray : R.drawable.cell_white_border_bottom_right_gray);
    }

    @Override
    public void bindBody(Patrimonio item, int row, int column) {
        Patrimonio patrimonio = patrimonioDAO.pesquisaPorPatrimonio(item.idpatrimonio);
        if(column + 1 == 1) {
            Local local = localDAO.pesquisaLocalPorCodigo(patrimonio.idlocal);
            Setor setor = setorDAO.pesquisaSetorPorCodigo(local.idsetor);
            textView.setVisibility(View.GONE);
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(setor.nomsetor + " - "+local.desclocal);
            textView2.setTypeface(null, Typeface.NORMAL);
            textView2.setGravity(Gravity.LEFT);
        }else if(column + 1 == 2){
            textView.setText(patrimonio.serialpatrimonio);
            textView.setTypeface(null, Typeface.NORMAL);
            textView.setGravity(Gravity.LEFT);
        }else if(column + 1 == 3){
            textView.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.GONE);
            textView.setBackgroundResource(R.drawable.ic_delete_black_24dp);
            textView.setPadding(15,15,15,15);
            textView.setGravity(Gravity.CENTER);
        }
        vg_root.setBackgroundResource(row % 2 == 0 ? R.drawable.cell_lightgray_border_bottom_right_gray : R.drawable.cell_white_border_bottom_right_gray);
    }

    @Override
    public void bindSection(Patrimonio item, int row, int column) {
        textView.setText(column == 0 ? String.valueOf(item.idpatrimonio): "");
        textView.setTypeface(null, Typeface.BOLD);
        vg_root.setBackgroundResource(R.drawable.cell_blue_border_bottom_right_gray);
    }
}
