package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "subgrupo")
public class SubGrupo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long idsubgrupo;

    @NonNull
    public int idgrupo;
    @NonNull
    public String descsubgrupo;

    public int flag;

    public SubGrupo(int idgrupo, @NonNull String descsubgrupo, int flag) {
        this.idgrupo = idgrupo;
        this.descsubgrupo = descsubgrupo;
        this.flag = flag;
    }

    public long getIdsubgrupo() {
        return idsubgrupo;
    }

    public int getIdgrupo() {
        return idgrupo;
    }

    @NonNull
    public String getDescsubgrupo() {
        return descsubgrupo;
    }

    public int getFlag() {
        return flag;
    }
}
