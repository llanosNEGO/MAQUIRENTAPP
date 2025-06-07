package com.example.maquirentapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.maquirentapp.BaseActivity;
import com.example.maquirentapp.MainActivity;
import com.example.maquirentapp.R;

public class Loader extends BaseActivity {
    @Override
    protected int getHeaderIcon() {
        return R.drawable.icon_home_blanco;
    }

    @Override
    protected String getHeaderTitle() {
        return "Inicio";
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.ly_inicio;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ly_loader);

        // Espera 3 segundos antes de lanzar la actividad principal
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(Loader.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, 3000);
        setupMainContent();
    }
    private void setupMainContent() {
        // Configuración específica de MainActivity
        // Puedes acceder a las vistas usando getContentContainer()
    }


}