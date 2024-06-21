import com.example.appescolamvc.model.Professor
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EnderecoAPI2{
    //professor
    @GET("professor/listar")
    fun getProfessor(): Call<List<Professor>>

    @POST("professor/inserir")
    fun inserirProfessor(@Body professor: Professor): Call<Void>

    @DELETE("professor/excluir/{id}")
    fun excluirProfessor(@Path("id") id: Int): Call<Void>

    @PUT("professor/alterar")
    fun alterarProfessor(@Body professor: Professor): Call<Void>

}