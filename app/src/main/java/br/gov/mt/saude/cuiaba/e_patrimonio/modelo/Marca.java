package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "marca")
public class Marca implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long idmarca;

    @NonNull
    public String descmarca;

    public int flag;

    public Marca(@NonNull String descmarca, int flag) {
        this.descmarca = descmarca;
        this.flag = flag;
    }

    public long getIdmarca() {
        return idmarca;
    }

    @NonNull
    public String getDescmarca() {
        return descmarca;
    }

    public int getFlag() {
        return flag;
    }
}
