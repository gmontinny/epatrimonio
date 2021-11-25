package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "patrimonio", indices = {@Index(value = {"idpatrimonio"},
        unique = true)})
public class Patrimonio implements Serializable {

    @PrimaryKey
    @NonNull
    public String idpatrimonio;

    @NonNull
    public int idmaterial;
    @NonNull
    public int idmodelo;
    @NonNull
    public int idlocal;
    @NonNull
    public int idfornecedor;
    @NonNull
    public int numeroarquivo;
    @NonNull
    public int situacaopatrimonio;
    public String serialpatrimonio;
    public int statusbaixa;
    public String obspatrimonio;

    public int flag;

    public Patrimonio(String idpatrimonio, int idmaterial, int idmodelo, int idlocal, int idfornecedor, int numeroarquivo, int situacaopatrimonio, String serialpatrimonio, int statusbaixa, String obspatrimonio, int flag) {
        this.idpatrimonio = idpatrimonio;
        this.idmaterial = idmaterial;
        this.idmodelo = idmodelo;
        this.idlocal = idlocal;
        this.idfornecedor = idfornecedor;
        this.numeroarquivo = numeroarquivo;
        this.situacaopatrimonio = situacaopatrimonio;
        this.serialpatrimonio = serialpatrimonio;
        this.statusbaixa = statusbaixa;
        this.obspatrimonio = obspatrimonio;
        this.flag = flag;
    }

    public String getIdpatrimonio() {
        return idpatrimonio;
    }

    public int getIdmaterial() {
        return idmaterial;
    }

    public int getIdmodelo() {
        return idmodelo;
    }

    public int getIdlocal() {
        return idlocal;
    }

    public int getIdfornecedor() {
        return idfornecedor;
    }

    public int getNumeroarquivo() {
        return numeroarquivo;
    }

    public int getSituacaopatrimonio() {
        return situacaopatrimonio;
    }

    public String getSerialpatrimonio() {
        return serialpatrimonio;
    }

    public int getStatusbaixa() {
        return statusbaixa;
    }

    public String getObspatrimonio() {
        return obspatrimonio;
    }

    public int getFlag() {
        return flag;
    }
}
