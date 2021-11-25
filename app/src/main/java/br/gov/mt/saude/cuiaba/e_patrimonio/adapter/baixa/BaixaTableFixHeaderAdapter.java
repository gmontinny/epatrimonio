package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.baixa;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Baixa;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 11/02/2016.
 */
public class BaixaTableFixHeaderAdapter extends TableFixHeaderAdapter<
        String, BaixaCellViewGroup,
        String, BaixaCellViewGroup,
        Baixa,
        BaixaCellViewGroup,
        BaixaCellViewGroup,
        BaixaCellViewGroup> {
    private Context context;

    public BaixaTableFixHeaderAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected BaixaCellViewGroup inflateFirstHeader() {
        return new BaixaCellViewGroup(context);
    }

    @Override
    protected BaixaCellViewGroup inflateHeader() {
        return new BaixaCellViewGroup(context);
    }

    @Override
    protected BaixaCellViewGroup inflateFirstBody() {
        return new BaixaCellViewGroup(context);
    }

    @Override
    protected BaixaCellViewGroup inflateBody() {
        return new BaixaCellViewGroup(context);
    }

    @Override
    protected BaixaCellViewGroup inflateSection() {
        return new BaixaCellViewGroup(context);
    }

    @Override
    protected List<Integer> getHeaderWidths() {
        Integer[] witdhs = {
                (int) context.getResources().getDimension(R.dimen._30dp),
                (int) context.getResources().getDimension(R.dimen._80dp),
                (int) context.getResources().getDimension(R.dimen._100dp),
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
    protected boolean isSection(List<Baixa> items, int row) {
        return items.isEmpty();
    }
}
