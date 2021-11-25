package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "modelo")
public class Modelo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long idmodelo;

    @NonNull
    public int idmarca;

    @NonNull
    public String descmodelo;

    public int flag;

    public Modelo(int idmarca, @NonNull String descmodelo, int flag) {
        this.idmarca = idmarca;
        this.descmodelo = descmodelo;
        this.flag = flag;
    }

    public int getIdmarca() {
        return idmarca;
    }

    @NonNull
    public String getDescmodelo() {
        return descmodelo;
    }

    public long getIdmodelo() {
        return idmodelo;
    }
}
