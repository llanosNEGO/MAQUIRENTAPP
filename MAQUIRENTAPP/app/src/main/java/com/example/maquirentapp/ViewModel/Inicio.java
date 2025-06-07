package com.example.maquirentapp.ViewModel;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.maquirentapp.BaseActivity;
import com.example.maquirentapp.R;

public class Inicio extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ly_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CGE), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected int getHeaderIcon() {
        return R.drawable.icon_aceite_blanco;
    }

    @Override
    protected String getHeaderTitle() {
        return "Prueba de si funciona";
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.ly_inicio;
    }
}