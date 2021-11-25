package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "transferencia")
public class Transferencia {
    @PrimaryKey(autoGenerate = true)
    public long idtransferencia;

    @NonNull
    public int idsetororigem;
    @NonNull
    public int idsetordestino;
    @NonNull
    public String idpatrimonio;
    @NonNull
    public String datatransferencia;
    @NonNull
    public String horatransferencia;
    @NonNull
    public int idusuario;

    public int flag;

    public Transferencia(int idsetororigem, int idsetordestino, @NonNull String idpatrimonio, @NonNull String datatransferencia, @NonNull String horatransferencia, int idusuario, int flag) {
        this.idsetororigem = idsetororigem;
        this.idsetordestino = idsetordestino;
        this.idpatrimonio = idpatrimonio;
        this.datatransferencia = datatransferencia;
        this.horatransferencia = horatransferencia;
        this.idusuario = idusuario;
        this.flag = flag;
    }

    public long getIdtransferencia() {
        return idtransferencia;
    }

    public int getIdsetororigem() {
        return idsetororigem;
    }

    public int getIdsetordestino() {
        return idsetordestino;
    }

    @NonNull
    public String getIdpatrimonio() {
        return idpatrimonio;
    }

    @NonNull
    public String getDatatransferencia() {
        return datatransferencia;
    }

    @NonNull
    public String getHoratransferencia() {
        return horatransferencia;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public int getFlag() {
        return flag;
    }
}
