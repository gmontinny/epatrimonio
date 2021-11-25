package br.gov.mt.saude.cuiaba.e_patrimonio.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "secretaria")
public class Secretaria {
    @PrimaryKey(autoGenerate = true)
    public long idsecretaria;
    @NonNull
    public String nomsecretaria;

    public int flag;

    public Secretaria(@NonNull String nomsecretaria, int flag) {
        this.nomsecretaria = nomsecretaria;
        this.flag = flag;
    }

    public long getIdsecretaria() {
        return idsecretaria;
    }

    @NonNull
    public String getNomsecretaria() {
        return nomsecretaria;
    }
}
