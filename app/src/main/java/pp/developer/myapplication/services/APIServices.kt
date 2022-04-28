package pp.developer.myapplication.services

import pp.developer.myapplication.models.Respuesta
import pp.developer.myapplication.models.Restaurante
import pp.developer.myapplication.models.Usuario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIServices {
    @GET
    suspend fun validateUser(@Url url:String):Response<Usuario>
    @GET
    suspend fun recordUsuario(@Url url:String):Response<Respuesta>
//                              nombre: String,
//                              usuario: String,
//                              correo: String,
//                              password: String,
//                              sexo: String,
//                              habilitado: Boolean
    @GET
    suspend fun getRestaurantes(@Url url:String):Response<Array<Restaurante>>
    @GET
    suspend fun getRestaurante(@Url url:String):Response<Restaurante>

}