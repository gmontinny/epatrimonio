package br.gov.mt.saude.cuiaba.e_patrimonio.adapter;

import android.content.Context;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.baixa.BaixaTableFixHeader;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.grupo.GrupoTableFixHeader;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.marca.MarcaTableFixHeader;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.material.MaterialTableFixHeader;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.modelo.ModeloTableFixHeader;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.patrimonio.PatrimonioTableFixHeader;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.subgrupo.SubGrupoTableFixHeader;
import br.gov.mt.saude.cuiaba.e_patrimonio.adapter.transferencia.TransferenciaTableFixHeader;


/**
 * Created by miguel on 12/02/2016.
 */
public class TableFixHeadersAdapterFactory {
    public static final int GRUPO = 0, SUBGRUPO = 1, MATERIAL = 2, MARCA = 3, MODELO = 4, BAIXA = 5, TRANSFERENCIA = 6, PATRIMONIO = 7;
    private Context context;
    private String texto;
    private int tipo;

    public TableFixHeadersAdapterFactory(Context context, String texto, int tipo) {
        this.context = context;
        this.texto = texto;
        this.tipo = tipo;
    }

    public BaseTableAdapter getAdapter(int type) {
        switch (type) {
            case GRUPO: return new GrupoTableFixHeader(context, texto, tipo).getInstance();
            case SUBGRUPO: return new SubGrupoTableFixHeader(context, texto, tipo).getInstance();
            case MATERIAL: return new MaterialTableFixHeader(context,texto,tipo).getInstance();
            case MARCA: return new MarcaTableFixHeader(context,texto,tipo).getInstance();
            case MODELO: return new ModeloTableFixHeader(context,texto,tipo).getInstance();
            case BAIXA: return new BaixaTableFixHeader(context,texto,tipo).getInstance();
            case TRANSFERENCIA: return new TransferenciaTableFixHeader(context,texto,tipo).getInstance();
            case PATRIMONIO: return  new PatrimonioTableFixHeader(context,texto,tipo).getInstance();
            default:return null;
        }
    }
}
