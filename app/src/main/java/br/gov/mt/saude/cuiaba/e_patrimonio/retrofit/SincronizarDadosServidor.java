package br.gov.mt.saude.cuiaba.e_patrimonio.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import br.gov.mt.saude.cuiaba.e_patrimonio.constants.Constantes;
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
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SincronizarDadosServidor {

    public APIRetrofitService enviarPatrimonio(){

        int timeOut = 5 * 60;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }


    public APIRetrofitService enviarTransferencia(){

        int timeOut = 5 * 60;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

    public APIRetrofitService enviarBaixa(){

        int timeOut = 5 * 60;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

    public APIRetrofitService enviarModelo(){

        int timeOut = 5 * 60;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

    public APIRetrofitService enviarMarca(){

        int timeOut = 5 * 60;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

    public APIRetrofitService enviarMaterial(){

        int timeOut = 5 * 60;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }


    public APIRetrofitService enviarSubGrupo(){

        int timeOut = 5 * 60;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }


    public APIRetrofitService enviarGrupo(){

       // Gson g = new GsonBuilder().registerTypeAdapter(Grupo.class, new GrupoDeserializaer()).create();

        int timeOut = 5 * 60;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

  
    public APIRetrofitService validaUsuario(){

        Gson g = new GsonBuilder().registerTypeAdapter(Usuario.class, new UsuarioDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

    public APIRetrofitService importarPatrimonio(){

        Gson g = new GsonBuilder().registerTypeAdapter(Patrimonio.class, new PatrimonioDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }


    public APIRetrofitService importarGrupo(){

        Gson g = new GsonBuilder().registerTypeAdapter(Grupo.class, new GrupoDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

    public APIRetrofitService importarSubGrupo(){

        Gson g = new GsonBuilder().registerTypeAdapter(SubGrupo.class, new SubGrupoDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }


    public APIRetrofitService importarMaterial(){

        Gson g = new GsonBuilder().registerTypeAdapter(Material.class, new MaterialDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }


    public APIRetrofitService importarMarca(){

        Gson g = new GsonBuilder().registerTypeAdapter(Marca.class, new MarcaDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }


    public APIRetrofitService importarModelo(){

        Gson g = new GsonBuilder().registerTypeAdapter(Modelo.class, new ModeloDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

    public APIRetrofitService importarSecretaria(){

        Gson g = new GsonBuilder().registerTypeAdapter(Secretaria.class, new SecretariaDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

    public APIRetrofitService importarSetor(){

        Gson g = new GsonBuilder().registerTypeAdapter(Setor.class, new SetorDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

    public APIRetrofitService importarLocal(){

        Gson g = new GsonBuilder().registerTypeAdapter(Local.class, new LocalDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

    public APIRetrofitService importarBaixa(){

        Gson g = new GsonBuilder().registerTypeAdapter(Baixa.class, new BaixaDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

    public APIRetrofitService importarTransferencia(){

        Gson g = new GsonBuilder().registerTypeAdapter(Transferencia.class, new TransferenciaDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }

    public APIRetrofitService importarFornecedor(){

        Gson g = new GsonBuilder().registerTypeAdapter(Fornecedor.class, new FornecedorDeserializaer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Constantes().IPSERVIDORPATRIMONIO)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        return  service;
    }



}
