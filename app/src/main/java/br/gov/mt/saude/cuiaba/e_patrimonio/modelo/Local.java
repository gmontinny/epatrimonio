package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "local")
public class Local {
    @PrimaryKey(autoGenerate = true)
    public long idlocal;

    @NonNull
    public String desclocal;
    public int idsetor;
    public int flag;

    public Local(@NonNull String desclocal, int idsetor, int flag) {
        this.desclocal = desclocal;
        this.idsetor = idsetor;
        this.flag = flag;
    }

    public long getIdlocal() {
        return idlocal;
    }

    @NonNull
    public String getDesclocal() {
        return desclocal;
    }

    public int getIdsetor() {
        return idsetor;
    }

    public int getFlag() {
        return flag;
    }
}
