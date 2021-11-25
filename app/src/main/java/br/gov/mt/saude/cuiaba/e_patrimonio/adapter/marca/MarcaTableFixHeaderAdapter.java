package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.marca;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Marca;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 11/02/2016.
 */
public class MarcaTableFixHeaderAdapter extends TableFixHeaderAdapter<
        String, MarcaCellViewGroup,
        String, MarcaCellViewGroup,
        Marca,
        MarcaCellViewGroup,
        MarcaCellViewGroup,
        MarcaCellViewGroup> {
    private Context context;

    public MarcaTableFixHeaderAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected MarcaCellViewGroup inflateFirstHeader() {
        return new MarcaCellViewGroup(context);
    }

    @Override
    protected MarcaCellViewGroup inflateHeader() {
        return new MarcaCellViewGroup(context);
    }

    @Override
    protected MarcaCellViewGroup inflateFirstBody() {
        return new MarcaCellViewGroup(context);
    }

    @Override
    protected MarcaCellViewGroup inflateBody() {
        return new MarcaCellViewGroup(context);
    }

    @Override
    protected MarcaCellViewGroup inflateSection() {
        return new MarcaCellViewGroup(context);
    }

    @Override
    protected List<Integer> getHeaderWidths() {
        Integer[] witdhs = {
                (int) context.getResources().getDimension(R.dimen._20dp),
                (int) context.getResources().getDimension(R.dimen._150dp),
                (int) context.getResources().getDimension(R.dimen._8dp)
        };

        return Arrays.asList(witdhs);
    }

    @Override
    protected int getHeaderHeight() {
        return (int) context.getResources().getDimension(R.dimen._35dp);
    }

    @Override
    protected int getSectionHeight() {
        return (int) context.getResources().getDimension(R.dimen._25dp);
    }

    @Override
    protected int getBodyHeight() {
        return (int) context.getResources().getDimension(R.dimen._35dp);
    }

    @Override
    protected boolean isSection(List<Marca> items, int row) {
        return items.isEmpty();
    }
}
