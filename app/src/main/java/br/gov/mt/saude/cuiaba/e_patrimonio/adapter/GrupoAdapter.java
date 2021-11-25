package br.gov.mt.saude.cuiaba.e_patrimonio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;

public class GrupoAdapter extends ArrayAdapter<Grupo> {

    public GrupoAdapter(Context context, ArrayList<Grupo> grupos) {
        super(context, 0, grupos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Grupo grupo = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.spinner_custon,parent,false);
        }

        TextView textView1 = (TextView) convertView.findViewById(R.id.textView1);
        TextView textView2 = (TextView) convertView.findViewById(R.id.textView2);

        textView1.setText(grupo.descgrupo);
        textView2.setText(String.valueOf(grupo.idgrupo));

        return convertView;
    }



}
