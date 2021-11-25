package br.gov.mt.saude.cuiaba.e_patrimonio.ui.active;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.king.zxing.CaptureActivity;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.util.StatusBarUtils;

public class CustomCaptureActivity extends CaptureActivity {
    private boolean isContinuousScan;
    @Override
    public int getLayoutId() {
        return R.layout.custom_capture_activity;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        StatusBarUtils.immersiveStatusBar(this,toolbar,0.2f);
        TextView tvTitle = findViewById(R.id.tvTitle);
       // tvTitle.setText(getIntent().getStringExtra(MainActivity.KEY_TITLE));

      //  isContinuousScan = getIntent().getBooleanExtra(MainActivity.KEY_IS_CONTINUOUS,false);

        getBeepManager().setPlayBeep(true);
        getBeepManager().setVibrate(true);
    }

    private void offFlash(){
        Camera camera = getCameraManager().getOpenCamera().getCamera();
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
    }

    public void openFlash(){
        Camera camera = getCameraManager().getOpenCamera().getCamera();
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameters);
    }

    @Override
    public void onResult(Result result) {
        super.onResult(result);
        if(isContinuousScan){
            Toast.makeText(this,result.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean isContinuousScan() {
        return isContinuousScan;
    }

    @Override
    public boolean isAutoRestartPreviewAndDecode() {
        return super.isAutoRestartPreviewAndDecode();
    }

    private void clickFlash(View v){
        if(v.isSelected()){
            offFlash();
            v.setSelected(false);
        }else{
            openFlash();
            v.setSelected(true);
        }

    }

    public void OnClick(View v){
        switch (v.getId()){
            case R.id.ivLeft:
                onBackPressed();
                break;
            case R.id.ivFlash:
                clickFlash(v);
                break;
        }
    }

}
