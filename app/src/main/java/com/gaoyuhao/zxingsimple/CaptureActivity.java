package com.gaoyuhao.zxingsimple;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;

import com.gaoyuhao.zxingsimple.camera.CameraManager;
import com.google.zxing.ResultMetadataType;

import java.util.Collection;
import java.util.EnumSet;

public class CaptureActivity extends Activity implements SurfaceHolder.Callback{
    private static final String TAG = CaptureActivity.class.getSimpleName();
    private static final Collection<ResultMetadataType> DISPLAYABLE_METADATA_TYPES =
            EnumSet.of(ResultMetadataType.ISSUE_NUMBER, ResultMetadataType.SUGGESTED_PRICE,
                    ResultMetadataType.ERROR_CORRECTION_LEVEL, ResultMetadataType.POSSIBLE_COUNTRY);

    private CameraManager cameraManager;
    private boolean hasSurface = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        hasSurface = false;


    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
