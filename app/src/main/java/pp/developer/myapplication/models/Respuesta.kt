package pp.developer.myapplication.models

import com.google.gson.annotations.SerializedName

data class Respuesta(
    @SerializedName("status")
    var status:Int,
    @SerializedName("msg")
    var msg:String
)
