package br.gov.mt.saude.cuiaba.e_patrimonio.ui.active;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.king.zxing.Intents;
import com.king.zxing.util.CodeUtils;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.BaixaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.GrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MarcaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.MaterialDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.ModeloDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.SubGrupoDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.TransferenciaDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl.UsuarioDAOImpl;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.BaixaFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.GrupoFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.HomeFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.MarcaFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.MaterialFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.ModeloFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.PatrimonioFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.ReadBarCodeFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.SubGrupoFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.fragment.TransferenciaFragment;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Marca;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Usuario;
import br.gov.mt.saude.cuiaba.e_patrimonio.util.UriUtils;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, EasyPermissions.PermissionCallbacks {

    public static final String KEY_TITLE = "key_title";
    public static final String KEY_IS_QR_CODE = "key_code";
    public static final String KEY_IS_CONTINUOUS = "key_continuous_scan";

    public static final int REQUEST_CODE_SCAN = 0X01;
    public static final int REQUEST_CODE_PHOTO = 0X02;

    public static final int RC_CAMERA = 0X01;

    public static final int RC_READ_PHOTO = 0X02;

    private FloatingActionButton fabPatrimonio;
    private FloatingActionButton fabBarCode;
    private FloatingActionMenu fabActionPrincipal;

    private CircleImageView imgAvatar;
    private TextView txtNomeAvatar;
    private TextView txtEmailAvatar;

    private static int opcao = 0;

    @Inject
    UsuarioDAOImpl usuarioDAO;
    @Inject
    GrupoDAOImpl grupoDAO;
    @Inject
    SubGrupoDAOImpl subGrupoDAO;
    @Inject
    MaterialDAOImpl materialDAO;
    @Inject
    MarcaDAOImpl marcaDAO;
    @Inject
    ModeloDAOImpl modeloDAO;
    @Inject
    BaixaDAOImpl baixaDAO;
    @Inject
    TransferenciaDAOImpl transferenciaDAO;
    @Inject
    PatrimonioDAOImpl patrimonioDAO;

    private Class<?> cls;
    private String title;
    private boolean isContinuousScan;
    private String patrimonio;

    private  NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        grupoDAO = new GrupoDAOImpl(getContext());
        subGrupoDAO = new SubGrupoDAOImpl(getContext());
        materialDAO = new MaterialDAOImpl(getContext());
        marcaDAO = new MarcaDAOImpl(getContext());
        modeloDAO = new ModeloDAOImpl(getContext());
        baixaDAO = new BaixaDAOImpl(getContext());
        transferenciaDAO = new TransferenciaDAOImpl(getContext());
        patrimonioDAO = new PatrimonioDAOImpl(getContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_principal);
        setSupportActionBar(toolbar);

        fabActionPrincipal = (FloatingActionMenu) findViewById(R.id.menuFloat) ;

        fabBarCode = (FloatingActionButton) findViewById(R.id.readBarCode);
        fabBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabActionPrincipal.hideMenu(true);
                executarScanner();
                //Fragment readBarCode = ReadBarCodeFragment.newInstance();
                //openFragment(readBarCode);
               //Snackbar.make(v, "Abrir Leitura de Barra de Codigo", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        fabPatrimonio = (FloatingActionButton) findViewById(R.id.addPatrimonio);
        fabPatrimonio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                fabActionPrincipal.hideMenu(true);
                Fragment patrimonioFragment = PatrimonioFragment.newInstance();
                openFragment(patrimonioFragment);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));

        View hView =  navigationView.getHeaderView(0);

        imgAvatar = (CircleImageView) hView.findViewById(R.id.imgAvatar);
        txtNomeAvatar = (TextView) hView.findViewById(R.id.txtNomeAvatar);
        txtEmailAvatar = (TextView) hView.findViewById(R.id.txtEmailAvatar);

        Context context = getApplicationContext();
        usuarioDAO = new UsuarioDAOImpl(context);

        Usuario usuario = usuarioDAO.pesquisaUsuario();

        if(usuario.imagemusuario != null) {
            String encodedDataString = usuario.imagemusuario;
            encodedDataString = encodedDataString.replace("data:image/jpeg;base64,", "");
            byte[] imageAsBytes = Base64.decode(encodedDataString.getBytes(), 0);

            imgAvatar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        }

        txtNomeAvatar.setText(usuario.nomusuario);
        txtEmailAvatar.setText(usuario.emailusuario);

    }

    public void executarScanner(){
        cls = CustomCaptureActivity.class;
        title = "Leitor Code de Barra";
        isContinuousScan = true;
        checkCameraPermissions();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mm_transmitir) {

            int totalGrupo = grupoDAO.pesquisaGrupoPorFlag().size();
            int totalSubGrupo = subGrupoDAO.pesquisaSubGrupoPorFlag().size();
            int totalMaterial = materialDAO.pesquisaMaterialPorFlag().size();
            int totalMarca = marcaDAO.pesquisaMarcaFlag().size();
            int totalModelo = modeloDAO.pesquisaModeloFlag().size();
            int totalBaixa = baixaDAO.pesquisaBaixaFlag().size();
            int totalTransferencia = transferenciaDAO.pesquisaTransferenciaFlag().size();
            int totalPatrimonio = patrimonioDAO.pesquisaPatrimonioFlag().size();

            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Trasmitir !")
                    .setContentText("Total de Itens para Transmissão: \n" +
                            "Grupo :" + totalGrupo + "\n " +
                            "SubGrupo :" + totalSubGrupo + "\n" +
                            "Material :" + totalMaterial + "\n" +
                            "Marca :" + totalMarca + "\n" +
                            "Modelo :" + totalModelo + "\n" +
                            "Baixa :" + totalBaixa + "\n" +
                            "Tranferência :" + totalTransferencia + "\n" +
                            "Patrimônio :" + totalPatrimonio)
                    .setCancelText("Não!")
                    .setConfirmText("Sim!")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            Intent intent = new Intent(PrincipalActivity.this, EnviarActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .show();

            return true;
        }else if(id == R.id.mm_sincronizar){
            Intent intent = new Intent(PrincipalActivity.this, SincronizarActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_patrimonio:
                fabActionPrincipal.hideMenu(true);
                Fragment patrimonioFragment = PatrimonioFragment.newInstance();
                openFragment(patrimonioFragment);
                opcao = 3;
                break;
            case R.id.nav_home:
                fabActionPrincipal.showMenu(true);
                Fragment homeFragment = HomeFragment.newInstance();
                openFragment(homeFragment);
                opcao = 3;
                break;
            case R.id.nav_grupo:
                fabActionPrincipal.hideMenu(true);
                Fragment grupoFragment = GrupoFragment.newInstance();
                openFragment(grupoFragment);
                break;
            case R.id.nav_subgrupo:
                fabActionPrincipal.hideMenu(true);
                Fragment subGrupoFragment = SubGrupoFragment.newInstance();
                openFragment(subGrupoFragment);
                break;
            case R.id.nav_material:
                fabActionPrincipal.hideMenu(true);
                Fragment materialFragment = MaterialFragment.newInstance();
                openFragment(materialFragment);
                break;
            case R.id.nav_marca:
                fabActionPrincipal.hideMenu(true);
                Fragment marcaFragment = MarcaFragment.newInstance();
                openFragment(marcaFragment);
                break;
            case R.id.nav_modelo:
                fabActionPrincipal.hideMenu(true);
                Fragment modeloFragment = ModeloFragment.newInstance();
                openFragment(modeloFragment);
                break;
            case R.id.nav_baixa:
                fabActionPrincipal.hideMenu(true);
                Fragment baixaFragment = BaixaFragment.newInstance();
                openFragment(baixaFragment);
                opcao = 1;
                break;
            case R.id.nav_transferencia:
                fabActionPrincipal.hideMenu(true);
                Fragment transferenciaFragment = TransferenciaFragment.newInstance();
                openFragment(transferenciaFragment);
                opcao = 2;
                break;
            case R.id.nav_sair:
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Patrimonio")
                        .setContentText("Você quer realmente fazer logout ?")
                        .setCancelText("Não!")
                        .setConfirmText("Sim!")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                usuarioDAO.removerTodosUsuarios();
                                Intent intent = new Intent(PrincipalActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void parsePhoto(Intent data){
        final String path = UriUtils.INSTANCE.getImagePath(this,data);

        if(TextUtils.isEmpty(path)){
            return;
        }

        asyncThread(new Runnable() {
            @Override
            public void run() {
                final String result = CodeUtils.parseCode(path);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),result,Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    private void asyncThread(Runnable runnable){
        new Thread(runnable).start();
    }

    private Context getContext(){
        return this;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null){
            switch (requestCode){
                case REQUEST_CODE_SCAN:
                    patrimonio = data.getStringExtra(Intents.Scan.RESULT);
                    setPatrimonio(patrimonio);
                    retornarPatrimonio();
                    //Toast.makeText(this,patrimonio,Toast.LENGTH_SHORT).show();
                    break;
                case REQUEST_CODE_PHOTO:
                    parsePhoto(data);
                    break;
            }

        }
    }

    public void retornarPatrimonio(){
       switch (opcao){
           case 1 :
               Fragment baixaFragment = BaixaFragment.newInstance();
               Bundle argsBaixa = new Bundle();
               argsBaixa.putString("numeropatrimonio", getPatrimonio());
               baixaFragment.setArguments(argsBaixa);
               openFragment(baixaFragment);
               break;
           case 2 :
               Fragment transferenciaFragment = TransferenciaFragment.newInstance();
               Bundle argsTransferencia = new Bundle();
               argsTransferencia.putString("numeropatrimonio", getPatrimonio());
               transferenciaFragment.setArguments(argsTransferencia);
               openFragment(transferenciaFragment);
               break;
           case 3 :
               navigationView.getMenu().getItem(1).setChecked(true);
               Fragment patrimonioFragment = PatrimonioFragment.newInstance();
               Bundle argsPatrimonio = new Bundle();
               argsPatrimonio.putString("numeropatrimonio", getPatrimonio());
               patrimonioFragment.setArguments(argsPatrimonio);
               openFragment(patrimonioFragment);
               break;
       }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usuarioDAO.close();
        grupoDAO.close();
        subGrupoDAO.close();
        materialDAO.close();
        marcaDAO.close();
        modeloDAO.close();
        baixaDAO.close();
        transferenciaDAO.close();
        patrimonioDAO.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermissions(){
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            startScan(cls,title);
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera),
                    RC_CAMERA, perms);
        }
    }

    private void startScan(Class<?> cls,String title){
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeCustomAnimation(this,R.anim.in,R.anim.out);
        Intent intent = new Intent(this, cls);
        intent.putExtra(KEY_TITLE,title);
        intent.putExtra(KEY_IS_CONTINUOUS,isContinuousScan);
        ActivityCompat.startActivityForResult(this,intent,REQUEST_CODE_SCAN,optionsCompat.toBundle());
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }
}
