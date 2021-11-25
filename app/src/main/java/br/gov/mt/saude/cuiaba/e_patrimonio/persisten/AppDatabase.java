package br.gov.mt.saude.cuiaba.e_patrimonio.persisten;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import br.gov.mt.saude.cuiaba.e_patrimonio.convert.Converters;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.BaixaDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.FornecedorDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.GrupoDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.LocalDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.MarcaDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.MaterialDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.ModeloDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.PatrimonioDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.SecretariaDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.SetorDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.SubGrupoDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.TransferenciaDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.UsuarioDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Baixa;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Fornecedor;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Grupo;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Local;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Marca;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Material;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Modelo;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Patrimonio;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Secretaria;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Setor;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.SubGrupo;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Transferencia;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Usuario;


@Database(entities = {Usuario.class, Baixa.class, Fornecedor.class, Grupo.class, Local.class, Marca.class, Material.class, Modelo.class,
        Patrimonio.class, Secretaria.class, Setor.class, SubGrupo.class, Transferencia.class}, version = 3)

@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract UsuarioDAO usuarioDAO();
    public abstract BaixaDAO baixaDAO();
    public abstract FornecedorDAO fornecedorDAO();
    public abstract GrupoDAO grupoDAO();
    public abstract LocalDAO localDAO();
    public abstract MarcaDAO marcaDAO();
    public abstract ModeloDAO modeloDAO();
    public abstract MaterialDAO materialDAO();
    public abstract PatrimonioDAO patrimonioDAO();
    public abstract SecretariaDAO secretariaDAO();
    public abstract SetorDAO setorDAO();
    public abstract SubGrupoDAO subGrupoDAO();
    public abstract TransferenciaDAO transferenciaDAO();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, "evisacard")
//Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the exercise, allow queries on the main thread.
                            // Don't do this on a real app!
                            .allowMainThreadQueries()
                            // recreate the database if necessary
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
