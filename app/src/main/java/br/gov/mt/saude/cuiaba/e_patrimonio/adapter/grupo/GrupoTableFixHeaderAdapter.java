package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.grupo;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 11/02/2016.
 */
public class GrupoTableFixHeaderAdapter extends TableFixHeaderAdapter<
        String, GrupoCellViewGroup,
        String, GrupoCellViewGroup,
        Grupo,
        GrupoCellViewGroup,
        GrupoCellViewGroup,
        GrupoCellViewGroup> {
    private Context context;

    public GrupoTableFixHeaderAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected GrupoCellViewGroup inflateFirstHeader() {
        return new GrupoCellViewGroup(context);
    }

    @Override
    protected GrupoCellViewGroup inflateHeader() {
        return new GrupoCellViewGroup(context);
    }

    @Override
    protected GrupoCellViewGroup inflateFirstBody() {
        return new GrupoCellViewGroup(context);
    }

    @Override
    protected GrupoCellViewGroup inflateBody() {
        return new GrupoCellViewGroup(context);
    }

    @Override
    protected GrupoCellViewGroup inflateSection() {
        return new GrupoCellViewGroup(context);
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
    protected boolean isSection(List<Grupo> items, int row) {
        return items.isEmpty();
    }
}
