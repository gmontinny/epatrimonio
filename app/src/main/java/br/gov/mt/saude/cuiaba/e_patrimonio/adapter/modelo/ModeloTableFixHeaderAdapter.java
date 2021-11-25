package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.modelo;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Modelo;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 11/02/2016.
 */
public class ModeloTableFixHeaderAdapter extends TableFixHeaderAdapter<
        String, ModeloCellViewGroup,
        String, ModeloCellViewGroup,
        Modelo,
        ModeloCellViewGroup,
        ModeloCellViewGroup,
        ModeloCellViewGroup> {
    private Context context;

    public ModeloTableFixHeaderAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected ModeloCellViewGroup inflateFirstHeader() {
        return new ModeloCellViewGroup(context);
    }

    @Override
    protected ModeloCellViewGroup inflateHeader() {
        return new ModeloCellViewGroup(context);
    }

    @Override
    protected ModeloCellViewGroup inflateFirstBody() {
        return new ModeloCellViewGroup(context);
    }

    @Override
    protected ModeloCellViewGroup inflateBody() {
        return new ModeloCellViewGroup(context);
    }

    @Override
    protected ModeloCellViewGroup inflateSection() {
        return new ModeloCellViewGroup(context);
    }

    @Override
    protected List<Integer> getHeaderWidths() {
        Integer[] witdhs = {
                (int) context.getResources().getDimension(R.dimen._20dp),
                (int) context.getResources().getDimension(R.dimen._80dp),
                (int) context.getResources().getDimension(R.dimen._80dp),
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
    protected boolean isSection(List<Modelo> items, int row) {
        return items.isEmpty();
    }
}
