package com.example.bulletopengles;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import com.example.bulletopengles.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'bulletopengles' library on application startup.
    static {
        System.loadLibrary("bulletopenglesBak");
    }

//    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
//
//        // Example of a call to a native method
//        TextView tv = findViewById(R.id.sample_text);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stringFromJNI();
//            }
//        });
        setupViews();
    }

    private void setupViews() {
        GLSurfaceView mGlSurfaceView = new GLSurfaceView(this);
        setContentView(mGlSurfaceView);
        //设置版本
        mGlSurfaceView.setEGLContextClientVersion(2);
        GLSurfaceView.Renderer renderer = new TriangleRenderJNI(MainActivity.this);
        mGlSurfaceView.setRenderer(renderer);
        //只有在绘制数据改变时才会绘制View,可以防止GLSurfaceView帧重绘
        //该种模式下当需要重绘时需要我们手动调用glSurfaceView.requestRender();
        mGlSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    /**
     * A native method that is implemented by the 'bulletopengles' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}