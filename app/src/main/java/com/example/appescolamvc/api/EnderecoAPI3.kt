package com.example.appescolamvc.api

import com.example.appescolamvc.model.Turma
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EnderecoAPI3 {

    @GET("turma/listar")
    fun getTurma(): Call<List<Turma>>

    @POST("turma/inserir")
    fun inserirTurma(@Body turma: Turma): Call<Void>

    @DELETE("turma/excluir/{id}")
    fun excluirTurma(@Path("id") id: Int): Call<Void>

    @PUT("turma/alterar")
    fun alterarTurma(@Body turma: Turma): Call<Void>

}