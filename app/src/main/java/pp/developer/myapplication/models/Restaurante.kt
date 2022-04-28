package pp.developer.myapplication.models

import com.google.gson.annotations.SerializedName

data class Restaurante(
    @SerializedName("id")
    var id:Int,
    @SerializedName("nombre")
    var nombre: String,
    @SerializedName("direccion")
    var domicilio: String,
    @SerializedName("costopromedio")
    var costopromedio: Float,
    @SerializedName("fundacion")
    var fundacion: Int,
    @SerializedName("resenia")
    var resenia: String,
    @SerializedName("calificacion")
    var valoracion: Float
)
