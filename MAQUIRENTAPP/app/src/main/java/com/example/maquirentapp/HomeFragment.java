package com.example.maquirentapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView cardNuevoAlquiler = view.findViewById(R.id.cardNuevoAlquiler);
        CardView cardCotizaciones = view.findViewById(R.id.cardCotizaciones);

        NavController navController = Navigation.findNavController(view);

        cardNuevoAlquiler.setOnClickListener(v ->
                navController.navigate(R.id.action_homeFragment_to_nuevoAlquilerFragment));

//        cardCotizaciones.setOnClickListener(v ->
//                navController.navigate(R.id.action_homeFragment_to_cotizacionesFragment));

    }

}