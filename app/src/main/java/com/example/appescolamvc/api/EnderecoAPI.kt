package com.example.appescolamvc.api

import com.example.appescolamvc.model.Aluno
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EnderecoAPI {

    @GET("aluno/listar")
    fun getAlunos(): Call<List<Aluno>>

    @POST("aluno/inserir")
    fun inserirAluno(@Body aluno: Aluno): Call<Void>

    @DELETE("aluno/excluir/{id}")
    fun excluirAluno(@Path("id") id: Int): Call<Void>

    @PUT("aluno/alterar")
    fun alterarAluno(@Body aluno: Aluno): Call<Void>
}

