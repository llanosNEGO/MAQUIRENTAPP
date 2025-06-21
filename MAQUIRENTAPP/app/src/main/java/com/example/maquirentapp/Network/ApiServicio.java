package com.example.maquirentapp.Network;

import com.example.maquirentapp.Model.GrupoElectrogeno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServicio {
    @GET("GruposElectrogenos")
    Call<List<GrupoElectrogeno>>GetGruposElectrogenos();
    @POST("GruposElectrogenos")
    Call<GrupoElectrogeno>PostGrupoElectrogeno(@Body GrupoElectrogeno grupoElectrogeno);
}
