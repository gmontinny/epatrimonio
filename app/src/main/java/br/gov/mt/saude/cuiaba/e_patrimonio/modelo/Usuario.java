package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "usuario")
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    public long idusuario;

    @NonNull
    public String nomusuario;
    @NonNull
    public String senusuario;
    @NonNull
    public String cpfusuario;
    @NonNull
    public String emailusuario;
    public String imagemusuario;
    @NonNull
    public int statususuario;
    @NonNull
    public int tipousuario;
    @NonNull
    public String datausuario;

    public Usuario(@NonNull String nomusuario, @NonNull String senusuario, @NonNull String cpfusuario, @NonNull String emailusuario, String imagemusuario, int statususuario, int tipousuario, @NonNull String datausuario) {
        this.nomusuario = nomusuario;
        this.senusuario = senusuario;
        this.cpfusuario = cpfusuario;
        this.emailusuario = emailusuario;
        this.imagemusuario = imagemusuario;
        this.statususuario = statususuario;
        this.tipousuario = tipousuario;
        this.datausuario = datausuario;
    }

    public long getIdusuario() {
        return idusuario;
    }

    @NonNull
    public String getNomusuario() {
        return nomusuario;
    }

    @NonNull
    public String getSenusuario() {
        return senusuario;
    }

    @NonNull
    public String getCpfusuario() {
        return cpfusuario;
    }

    @NonNull
    public String getEmailusuario() {
        return emailusuario;
    }

    public String getImagemusuario() {
        return imagemusuario;
    }

    public int getStatususuario() {
        return statususuario;
    }

    public int getTipousuario() {
        return tipousuario;
    }

    @NonNull
    public String getDatausuario() {
        return datausuario;
    }
}
