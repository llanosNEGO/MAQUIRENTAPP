package com.example.maquirentapp.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maquirentapp.View.NuevoAlquilerDiaFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VerAlquilerAdapter extends RecyclerView.Adapter<NuevoAlquilerDiaFragment.NuevoAlquilerViewHolder> {
    @NonNull
    @Override
    public NuevoAlquilerDiaFragment.NuevoAlquilerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NuevoAlquilerDiaFragment.NuevoAlquilerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}


