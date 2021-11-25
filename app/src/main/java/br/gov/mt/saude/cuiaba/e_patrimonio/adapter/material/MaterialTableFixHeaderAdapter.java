package br.gov.mt.saude.cuiaba.e_patrimonio.adapter.material;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Material;
import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;


/**
 * Created by miguel on 11/02/2016.
 */
public class MaterialTableFixHeaderAdapter extends TableFixHeaderAdapter<
        String, MaterialCellViewGroup,
        String, MaterialCellViewGroup,
        Material,
        MaterialCellViewGroup,
        MaterialCellViewGroup,
        MaterialCellViewGroup> {
    private Context context;

    public MaterialTableFixHeaderAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected MaterialCellViewGroup inflateFirstHeader() {
        return new MaterialCellViewGroup(context);
    }

    @Override
    protected MaterialCellViewGroup inflateHeader() {
        return new MaterialCellViewGroup(context);
    }

    @Override
    protected MaterialCellViewGroup inflateFirstBody() {
        return new MaterialCellViewGroup(context);
    }

    @Override
    protected MaterialCellViewGroup inflateBody() {
        return new MaterialCellViewGroup(context);
    }

    @Override
    protected MaterialCellViewGroup inflateSection() {
        return new MaterialCellViewGroup(context);
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
    protected boolean isSection(List<Material> items, int row) {
        return items.isEmpty();
    }
}
