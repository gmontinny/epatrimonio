package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "material")
public class Material implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public  long idmaterial;
    @NonNull
    public int idsubgrupo;

    @NonNull
    public  String descmaterial;

    public int flag;

    public Material(int idsubgrupo, @NonNull String descmaterial, int flag) {
        this.idsubgrupo = idsubgrupo;
        this.descmaterial = descmaterial;
        this.flag = flag;
    }

    public long getIdmaterial() {
        return idmaterial;
    }

    public int getIdsubgrupo() {
        return idsubgrupo;
    }

    @NonNull
    public String getDescmaterial() {
        return descmaterial;
    }

    public int getFlag() {
        return flag;
    }
}
