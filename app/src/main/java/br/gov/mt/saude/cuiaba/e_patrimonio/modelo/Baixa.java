package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "baixa")
public class Baixa {
    @PrimaryKey(autoGenerate = true)
    public long idbaixa;

    public String databaixa;
    public String horabaixa;
    public int idusario;
    public String idpatrimonio;
    public int flag;

    public Baixa(String databaixa, String horabaixa, int idusario, String idpatrimonio, int flag) {
        this.databaixa = databaixa;
        this.horabaixa = horabaixa;
        this.idusario = idusario;
        this.idpatrimonio = idpatrimonio;
        this.flag = flag;
    }

    public long getIdbaixa() {
        return idbaixa;
    }

    public String getDatabaixa() {
        return databaixa;
    }

    public String getHorabaixa() {
        return horabaixa;
    }

    public int getIdusario() {
        return idusario;
    }

    public String getIdpatrimonio() {
        return idpatrimonio;
    }

    public int getFlag() {
        return flag;
    }
}
