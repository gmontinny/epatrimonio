package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.patrimonio;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 11/02/2016.
 */
public class PatrimonioTableFixHeaderAdapter extends TableFixHeaderAdapter<
        String, PatrimonioCellViewGroup,
        String, PatrimonioCellViewGroup,
        Patrimonio,
        PatrimonioCellViewGroup,
        PatrimonioCellViewGroup,
        PatrimonioCellViewGroup> {
    private Context context;

    public PatrimonioTableFixHeaderAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected PatrimonioCellViewGroup inflateFirstHeader() {
        return new PatrimonioCellViewGroup(context);
    }

    @Override
    protected PatrimonioCellViewGroup inflateHeader() {
        return new PatrimonioCellViewGroup(context);
    }

    @Override
    protected PatrimonioCellViewGroup inflateFirstBody() {
        return new PatrimonioCellViewGroup(context);
    }

    @Override
    protected PatrimonioCellViewGroup inflateBody() {
        return new PatrimonioCellViewGroup(context);
    }

    @Override
    protected PatrimonioCellViewGroup inflateSection() {
        return new PatrimonioCellViewGroup(context);
    }

    @Override
    protected List<Integer> getHeaderWidths() {
        Integer[] witdhs = {
                (int) context.getResources().getDimension(R.dimen._30dp),
                (int) context.getResources().getDimension(R.dimen._150dp),
                (int) context.getResources().getDimension(R.dimen._50dp),
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
    protected boolean isSection(List<Patrimonio> items, int row) {
        return items.isEmpty();
    }
}
