package br.gov.mt.saude.cuiaba.e_patrimonio.retrofit;

import java.util.ArrayList;
import java.util.List;

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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIRetrofitService {

    /* Valida Usuario */
    @GET("usuario/mobile/validaUsuario/{cpf}/{senha}")
    Call<Usuario> validaUsuario(@Path("cpf") String cpf, @Path("senha") String senha);

    /* Importar Patrimonio */
    @GET("patrimonio/mobile/importarPatrimonio")
    Call<List<Patrimonio>> importarPatrimonio();

    /* Importar Grupo */
    @GET("grupo/mobile/importarGrupo")
    Call<List<Grupo>> importarGrupo();

    /* Importar SubGrupo */
    @GET("subgrupo/mobile/importarSubGrupo")
    Call<List<SubGrupo>> importarSubGrupo();

    /* Importar Material */
    @GET("material/mobile/importarMaterial")
    Call<List<Material>> importarMaterial();

    /* Importar Marca */
    @GET("marca/mobile/importarMarca")
    Call<List<Marca>> importarMarca();

    /* Importar Modelo */
    @GET("modelo/mobile/importarModelo")
    Call<List<Modelo>> importarModelo();

    /* Importar Secretaria */
    @GET("secretaria/mobile/importarSecretaria")
    Call<List<Secretaria>> importarSecretaria();

    /* Importar Setor */
    @GET("setor/mobile/importarSetor")
    Call<List<Setor>> importarSetor();

    /* Importar Baixa */
    @GET("baixa/mobile/importarBaixa")
    Call<List<Baixa>> importarBaixa();

    /* Importar Transferência */
    @GET("transferencia/mobile/importarTransferencia")
    Call<List<Transferencia>> importarTransferencia();

    /* Importar Local */
    @GET("local/mobile/importarLocal")
    Call<List<Local>> importarLocal();

    /* Importar Fornecedor */
    @GET("fornecedore/mobile/importarFornecedor")
    Call<List<Fornecedor>> importarFornecedor();

    /* Envio Grupo */
    @POST("grupo/receberDadosTablet")
    Call<Void> envioGrupo(
        @Body List<Grupo> grupos
    );

    /* Envio SubGrupo */
    @POST("subgrupo/receberDadosTablet")
    Call<Void> envioSubGrupo(
        @Body List<SubGrupo> subgrupos
    );

    /* Envio Material */
    @POST("material/receberDadosTablet")
    Call<Void> envioMaterial(
        @Body List<Material> materials
    );

    /* Envio Marca */
    @POST("marca/receberDadosTablet")
    Call<Void> envioMarca(
        @Body List<Marca> marcas
    );

    /* Envio Modelo */
    @POST("modelo/receberDadosTablet")
    Call<Void> envioModelo(
        @Body List<Modelo> modelos
    );

    /* Envio Baixa */
    @POST("baixa/receberDadosTablet")
    Call<Void> envioBaixa(
        @Body List<Baixa> baixas
    );

    /* Envio Transferência */
    @POST("transferencia/receberDadosTablet")
    Call<Void> envioTransferencia(
        @Body List<Transferencia> transferencias
    );

    /* Envio Patrimonio */
    @POST("patrimonio/receberDadosTablet")
    Call<Void> envioPatrimonio(
        @Body List<Patrimonio> patrimonios
    );


}
