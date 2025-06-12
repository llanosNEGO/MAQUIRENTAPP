package com.example.maquirentapp;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private LinearLayout navHome, navRent, navPerfil;
    private TextView navHomeText, navRentText, navPerfilText;
    private TextView headerTitle;
    private ImageView headerIcon;

    // Para el indicador animado
    private View currentSelectedIndicator;
    private int currentSelectedIndex = 0; // 0: home, 1: rent, 2: perfil

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configurar window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas
        initViews();

        // Configurar Navigation Component
        setupNavigation();

        // Configurar estado inicial
        setupInitialState();
    }

    private void initViews() {
        headerTitle = findViewById(R.id.header_title);
        headerIcon = findViewById(R.id.header_icon);

        navHome = findViewById(R.id.nav_home);
        navRent = findViewById(R.id.nav_rent);
        navPerfil = findViewById(R.id.nav_perfil);

        navHomeText = findViewById(R.id.nav_home_text);
        navRentText = findViewById(R.id.nav_rent_text);
        navPerfilText = findViewById(R.id.nav_perfil_text);
    }

    private void setupInitialState() {
        // Configurar estado inicial - Home seleccionado
        currentSelectedIndicator = navHome;
        updateNavigationUI(0);
    }

    private void setupNavigation() {
        // Obtener NavHostFragment y NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();

            // Listener para cambios de destino
            navController.addOnDestinationChangedListener((ctrl, dest, args) -> {
                if (dest.getId() == R.id.homeFragment) {
                    setHeaderTitle("Inicio");
                    setHeaderIcon(R.drawable.icon_home_blanco);
                    updateNavigationUI(0);
                } else if (dest.getId() == R.id.cgeFragment) {
                    setHeaderTitle("CGE");
                    setHeaderIcon(R.drawable.icon_generador);
                    updateNavigationUI(1);
                } else if (dest.getId() == R.id.perfilFragment) {
                    setHeaderTitle("Perfil");
                    setHeaderIcon(R.drawable.icon_perfil_blanco);
                    updateNavigationUI(2);
                }
            });

            // Configurar clicks de navegación
            navHome.setOnClickListener(v -> {
                navController.navigate(R.id.homeFragment);
            });

            navRent.setOnClickListener(v -> {
                navController.navigate(R.id.cgeFragment);
            });

            navPerfil.setOnClickListener(v -> {
                navController.navigate(R.id.perfilFragment);
            });

            // Configurar botón de retroceso
            FloatingActionButton btnBack = findViewById(R.id.btn_back);
            btnBack.setOnClickListener(v -> {
                if (!navController.popBackStack()) {
                    finish();
                }
            });
        }
    }

    private void updateNavigationUI(int selectedIndex) {
        if (selectedIndex == currentSelectedIndex) {
            return; // No hacer nada si ya está seleccionado
        }

        // Animar la transición del indicador
        animateIndicatorTransition(selectedIndex);

        // Actualizar textos de navegación
        updateNavigationText(selectedIndex);

        currentSelectedIndex = selectedIndex;
    }

    private void animateIndicatorTransition(int newIndex) {
        View newSelectedView = getNavigationViewByIndex(newIndex);

        if (currentSelectedIndicator != null && newSelectedView != null) {
            // Remover el background del elemento anterior
            currentSelectedIndicator.setBackground(null);

            // Crear y ejecutar la animación
            ObjectAnimator fadeOut = ObjectAnimator.ofFloat(currentSelectedIndicator, "alpha", 1f, 0.7f);
            fadeOut.setDuration(150);

            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(newSelectedView, "alpha", 0.7f, 1f);
            fadeIn.setDuration(150);
            fadeIn.setStartDelay(150);

            fadeOut.start();
            fadeIn.start();

            // Aplicar el background al nuevo elemento
            newSelectedView.setBackgroundResource(R.drawable.selection_indicator_background);

            // Actualizar referencia
            currentSelectedIndicator = newSelectedView;
        }
    }

    private void updateNavigationText(int selectedIndex) {
        // Ocultar todos los textos primero
        navHomeText.setVisibility(View.GONE);
        navRentText.setVisibility(View.GONE);
        navPerfilText.setVisibility(View.GONE);

        // Mostrar el texto del elemento seleccionado con animación
        TextView selectedText = getNavigationTextByIndex(selectedIndex);
        if (selectedText != null) {
            selectedText.setVisibility(View.VISIBLE);
            selectedText.setAlpha(0f);
            selectedText.animate()
                    .alpha(1f)
                    .setDuration(200)
                    .setStartDelay(100)
                    .start();
        }
    }

    private View getNavigationViewByIndex(int index) {
        switch (index) {
            case 0: return navHome;
            case 1: return navRent;
            case 2: return navPerfil;
            default: return navHome;
        }
    }

    private TextView getNavigationTextByIndex(int index) {
        switch (index) {
            case 0: return navHomeText;
            case 1: return navRentText;
            case 2: return navPerfilText;
            default: return navHomeText;
        }
    }

    private void setHeaderIcon(int iconResId) {
        if (headerIcon != null) {
            headerIcon.setImageResource(iconResId);
        }
    }

    private void setHeaderTitle(String title) {
        if (headerTitle != null) {
            headerTitle.setText(title);
        }
    }
}