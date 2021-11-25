package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.subgrupo;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.SubGrupo;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 11/02/2016.
 */
public class SubGrupoTableFixHeaderAdapter extends TableFixHeaderAdapter<
        String, SubGrupoCellViewGroup,
        String, SubGrupoCellViewGroup,
        SubGrupo,
        SubGrupoCellViewGroup,
        SubGrupoCellViewGroup,
        SubGrupoCellViewGroup> {
    private Context context;

    public SubGrupoTableFixHeaderAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected SubGrupoCellViewGroup inflateFirstHeader() {
        return new SubGrupoCellViewGroup(context);
    }

    @Override
    protected SubGrupoCellViewGroup inflateHeader() {
        return new SubGrupoCellViewGroup(context);
    }

    @Override
    protected SubGrupoCellViewGroup inflateFirstBody() {
        return new SubGrupoCellViewGroup(context);
    }

    @Override
    protected SubGrupoCellViewGroup inflateBody() {
        return new SubGrupoCellViewGroup(context);
    }

    @Override
    protected SubGrupoCellViewGroup inflateSection() {
        return new SubGrupoCellViewGroup(context);
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
    protected boolean isSection(List<SubGrupo> items, int row) {
        return items.isEmpty();
    }
}
