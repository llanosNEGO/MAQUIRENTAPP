package com.example.maquirentapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends BaseActivity {
    private LinearLayout navHome, navRent, navPerfil;
    private TextView navHomeText, navRentText, navPerfilText;
    private int currentFragmentIndex = 0;
    private TextView headerTitle;
    private ImageView headerIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headerTitle = findViewById(R.id.header_title);
        headerIcon = findViewById(R.id.header_icon);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initNavigation();
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment(), 0);
        }

        navHome.setBackgroundResource(R.drawable.selection_indicator_background);
        navHomeText.setVisibility(View.VISIBLE);

        navRent.setBackground(null);
        navRentText.setVisibility(View.GONE);

        navPerfil.setBackground(null);
        navPerfilText.setVisibility(View.GONE);


        FloatingActionButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                finish();
            }
        });
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (currentFragment instanceof HomeFragment) {
                updateNavigation(0);
                currentFragmentIndex = 0;
            } else if (currentFragment instanceof CGEFragment) {
                updateNavigation(1);
                currentFragmentIndex = 1;
            } else if (currentFragment instanceof PerfilFragment) {
                updateNavigation(2);
                currentFragmentIndex = 2;
            }
        });

    }
    private void initNavigation() {
        navHome = findViewById(R.id.nav_home);
        navRent = findViewById(R.id.nav_rent);
        navPerfil = findViewById(R.id.nav_perfil);
        navHomeText = findViewById(R.id.nav_home_text);
        navRentText = findViewById(R.id.nav_rent_text);
        navPerfilText = findViewById(R.id.nav_perfil_text);

        // Configurar clicks una sola vez
        navHome.setOnClickListener(v -> switchToFragment(0));
        navRent.setOnClickListener(v -> switchToFragment(1));
        navPerfil.setOnClickListener(v -> switchToFragment(2));

        updateNavigation(0); // Estado inicial
    }
    private void switchToFragment(int index) {
        if (currentFragmentIndex != index) {
            Fragment fragment;

            switch (index) {
                case 0: fragment = new HomeFragment(); break;
                case 1: fragment = new CGEFragment(); break;
                case 2: fragment = new PerfilFragment(); break;
                default: return;
            }

            loadFragment(fragment, index);
        }
    }

    private void loadFragment(Fragment fragment, int index) {
        // Animación según la dirección
        boolean isForward = index > currentFragmentIndex;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (isForward) {
            transaction.setCustomAnimations(
                    R.anim.slide_in_right, R.anim.slide_out_left,
                    R.anim.slide_in_left, R.anim.slide_out_right
            );
        } else {
            transaction.setCustomAnimations(
                    R.anim.slide_in_left, R.anim.slide_out_right,
                    R.anim.slide_in_right, R.anim.slide_out_left
            );
        }

        transaction.replace(R.id.fragment_container, fragment);

        if (currentFragmentIndex != index) {
            transaction.addToBackStack(null);
        }

        transaction.commit();

        currentFragmentIndex = index;
        updateNavigation(index);
    }

    private void updateNavigation(int selectedIndex) {
        // Limpiar todos
        clearAllNavigation();

        // Activar el seleccionado
        switch (selectedIndex) {
            case 0:
                activateNavItem(navHome, navHomeText);
                setHeaderTitle("Inicio");
                setHeaderIcon(R.drawable.icon_home_blanco);
                break;
            case 1:
                activateNavItem(navRent, navRentText);
                setHeaderTitle("Control de grupos electrógenos");
                setHeaderIcon(R.drawable.icon_generador);
                break;
            case 2:
                activateNavItem(navPerfil, navPerfilText);
                setHeaderTitle("Perfil");
                setHeaderIcon(R.drawable.icon_perfil_blanco);
                break;
        }
    }

    private void clearAllNavigation() {
        navHome.setBackground(null);
        navRent.setBackground(null);
        navPerfil.setBackground(null);

        navHomeText.setVisibility(View.GONE);
        navRentText.setVisibility(View.GONE);
        navPerfilText.setVisibility(View.GONE);
    }

    private void activateNavItem(LinearLayout nav, TextView text) {
        nav.setBackgroundResource(R.drawable.selection_indicator_background);
        text.setVisibility(View.VISIBLE);

        // Animación suave
        text.setAlpha(0f);
        text.animate().alpha(1f).setDuration(300).start();

        nav.setAlpha(0f);
        nav.animate().alpha(1f).setDuration(300).start();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }
}