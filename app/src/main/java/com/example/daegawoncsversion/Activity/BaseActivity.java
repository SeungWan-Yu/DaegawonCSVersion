package com.example.daegawoncsversion.Activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.sanbot.opensdk.base.BindBaseActivity;
import com.sanbot.opensdk.beans.FuncConstant;
import com.sanbot.opensdk.function.beans.handmotion.NoAngleHandMotion;
import com.sanbot.opensdk.function.unit.HandMotionManager;
import com.sanbot.opensdk.function.unit.HardWareManager;
import com.sanbot.opensdk.function.unit.HeadMotionManager;
import com.sanbot.opensdk.function.unit.SpeechManager;
import com.sanbot.opensdk.function.unit.SystemManager;

public class BaseActivity extends BindBaseActivity {

    public SystemManager systemManager;
    public SpeechManager speechManager;
    public HardWareManager hardWareManager;
    public HandMotionManager handMotionManager;
    public NoAngleHandMotion noAngleHandMotion;
    HeadMotionManager headMotionManager;
    public String ipurl, keycode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        register(PopupActivity.class);
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        systemManager = (SystemManager)getUnitManager(FuncConstant. SYSTEM_MANAGER);
        speechManager = (SpeechManager)getUnitManager(FuncConstant.SPEECH_MANAGER );
        hardWareManager = (HardWareManager)getUnitManager(FuncConstant.HARDWARE_MANAGER);
        handMotionManager = (HandMotionManager)getUnitManager(FuncConstant.HANDMOTION_MANAGER);
        headMotionManager= (HeadMotionManager)getUnitManager(FuncConstant.HEADMOTION_MANAGER);

//        ipurl = "http://106.10.39.137:48701"; // 지행아이티 테스트 서버
//        ipurl = "http://211.254.79.203:9999";
        ipurl = "http://daegawon.cafe24app.com";
        keycode = "0783cc9efa974aa598dc2ec2c9";
    }

    public void AToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onMainServiceConnected() { }
}