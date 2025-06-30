package org.utl.dragonballapi

import org.utl.apiidgs901.Personaje
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import kotlin.jvm.java

interface ApiService {

    /*@POST("posts")
    fun addStudent(@Body misDatos: Student): Call<StudentResponse>

    @PUT("posts/{id}")
    fun updateStudent(@Path("id") itemId:Int, @Body misDatos: Student):
            Call<StudentResponse>

    @DELETE("posts/{id}")
    fun deleteStudent(@Path("id") itemId:Int): Call<Void>*/

    @GET("characters?page=2&limit=15")
    suspend fun getPersonajes(): RespuestaPersonaje

    companion object{
        private var apiService: ApiService? = null
        private var url: String = "https://dragonball-api.com/api/"

        //Instancia a Retrofit
        fun getInstance(): ApiService {
            if(apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    //Instancia a la interfaz
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }

}