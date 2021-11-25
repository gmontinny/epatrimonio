package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.transferencia;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Transferencia;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 11/02/2016.
 */
public class TransferenciaTableFixHeaderAdapter extends TableFixHeaderAdapter<
        String, TransferenciaCellViewGroup,
        String, TransferenciaCellViewGroup,
        Transferencia,
        TransferenciaCellViewGroup,
        TransferenciaCellViewGroup,
        TransferenciaCellViewGroup> {
    private Context context;

    public TransferenciaTableFixHeaderAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected TransferenciaCellViewGroup inflateFirstHeader() {
        return new TransferenciaCellViewGroup(context);
    }

    @Override
    protected TransferenciaCellViewGroup inflateHeader() {
        return new TransferenciaCellViewGroup(context);
    }

    @Override
    protected TransferenciaCellViewGroup inflateFirstBody() {
        return new TransferenciaCellViewGroup(context);
    }

    @Override
    protected TransferenciaCellViewGroup inflateBody() {
        return new TransferenciaCellViewGroup(context);
    }

    @Override
    protected TransferenciaCellViewGroup inflateSection() {
        return new TransferenciaCellViewGroup(context);
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
    protected boolean isSection(List<Transferencia> items, int row) {
        return items.isEmpty();
    }
}
