package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "fornecedor", indices = {@Index(value = {"cnpjfornecedor"},
        unique = true)})
public class Fornecedor {
    @PrimaryKey(autoGenerate = true)
    public long idfornecedor;

    @NonNull
    public String razaosocial;
    @NonNull
    public String nomefantasia;
    @NonNull
    public String cnpjfornecedor;
    @NonNull
    public String enderecofornecedor;
    @NonNull
    public String bairrofornecedor;
    @NonNull
    public String emailfornecedor;
    public String telefonefornecedor;
    public String celularfornecedor;
    public String contatofornecedor;

    public int flag;

    public Fornecedor(@NonNull String razaosocial, @NonNull String nomefantasia, @NonNull String cnpjfornecedor, @NonNull String enderecofornecedor, @NonNull String bairrofornecedor, @NonNull String emailfornecedor, String telefonefornecedor, String celularfornecedor, String contatofornecedor, int flag) {
        this.razaosocial = razaosocial;
        this.nomefantasia = nomefantasia;
        this.cnpjfornecedor = cnpjfornecedor;
        this.enderecofornecedor = enderecofornecedor;
        this.bairrofornecedor = bairrofornecedor;
        this.emailfornecedor = emailfornecedor;
        this.telefonefornecedor = telefonefornecedor;
        this.celularfornecedor = celularfornecedor;
        this.contatofornecedor = contatofornecedor;
        this.flag = flag;
    }

    public long getIdfornecedor() {
        return idfornecedor;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public String getCnpjfornecedor() {
        return cnpjfornecedor;
    }

    public String getEnderecofornecedor() {
        return enderecofornecedor;
    }

    public String getBairrofornecedor() {
        return bairrofornecedor;
    }

    public String getEmailfornecedor() {
        return emailfornecedor;
    }

    public String getTelefonefornecedor() {
        return telefonefornecedor;
    }

    public String getCelularfornecedor() {
        return celularfornecedor;
    }

    public String getContatofornecedor() {
        return contatofornecedor;
    }

    public int getFlag() {
        return flag;
    }

}
