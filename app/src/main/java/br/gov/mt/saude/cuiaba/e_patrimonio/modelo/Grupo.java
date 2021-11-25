package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "grupo")
public class Grupo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long idgrupo;

    public String descgrupo;
    public int flag;

    public Grupo(String descgrupo, int flag) {
        this.descgrupo = descgrupo; this.flag = flag;
    }

    public long getIdgrupo() {
        return idgrupo;
    }

    public String getDescgrupo() {
        return descgrupo;
    }

    public int getFlag() {
        return flag;
    }


}
