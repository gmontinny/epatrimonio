package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "setor")
public class Setor {
    @PrimaryKey(autoGenerate = true)
    public long idsetor;
    @NonNull
    public int idsecretaria;
    @NonNull
    public String nomsetor;

    public int flag;

    public Setor(int idsecretaria, @NonNull String nomsetor, int flag) {
        this.idsecretaria = idsecretaria;
        this.nomsetor = nomsetor;
        this.flag = flag;
    }

    public long getIdsetor() {
        return idsetor;
    }

    public int getIdsecretaria() {
        return idsecretaria;
    }

    @NonNull
    public String getNomsetor() {
        return nomsetor;
    }
}
