package br.gov.mt.saude.cuiaba.e_patrimonio.ui.active;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.king.zxing.CaptureActivity;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;
import br.gov.mt.saude.cuiaba.e_patrimonio.util.StatusBarUtils;

public class EasyCaptureActivity extends CaptureActivity {
    @Override
    public int getLayoutId() {
        return R.layout.easy_capture_activity;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        StatusBarUtils.immersiveStatusBar(this,toolbar,0.2f);
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(getIntent().getStringExtra(PrincipalActivity.KEY_TITLE));

    }

    public void OnClick(View v){
        switch (v.getId()){
            case R.id.ivLeft:
                onBackPressed();
                break;
        }
    }
}
