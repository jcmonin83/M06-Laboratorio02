package pp.developer.myapplication

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pp.developer.myapplication.models.Restaurante

class RestauranteViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val v=view
    var imgRestaurante= view.findViewById<ImageView>(R.id.imgVwRestaurante)
    var nombre= view.findViewById<TextView>(R.id.txtRestaurante)
    var domicilio= view.findViewById<TextView>(R.id.txtDireccionRestaurante)
    var costoPromedio= view.findViewById<TextView>(R.id.txtCostoPromedioRestaurante)
    var anioFundacion= view.findViewById<TextView>(R.id.txtAnio)
    var resenia = view.findViewById<TextView>(R.id.txtReseniaRestaurante)
    var valoracion = view.findViewById<RatingBar>(R.id.ratbCalificacion)
    fun  bind(restaurante: Restaurante){
        var idImage:Int =0
        idImage =v.context.resources.getIdentifier("@drawable/imgh-${restaurante.id}","drawable",v.context.packageName)
        when( restaurante.id ){
            1 -> imgRestaurante.setImageResource(R.drawable.imgh_1)
            2 -> imgRestaurante.setImageResource(R.drawable.imgh_2)
            3 -> imgRestaurante.setImageResource(R.drawable.imgh_3)
            4 -> imgRestaurante.setImageResource(R.drawable.imgh_4)
            5 -> imgRestaurante.setImageResource(R.drawable.imgh_5)
            6 -> imgRestaurante.setImageResource(R.drawable.imgh_6)
            7 -> imgRestaurante.setImageResource(R.drawable.imgh_7)
            8 -> imgRestaurante.setImageResource(R.drawable.imgh_8)
            9 -> imgRestaurante.setImageResource(R.drawable.imgh_9)
            10 -> imgRestaurante.setImageResource(R.drawable.imgh_10)
        }
        //imgRestaurante.setImageResource(v.context.resources.getIdentifier("imgh-${restaurante.id.toString()}","drawable",v.context.packageName))
        //imgRestaurante.setImageURI(Uri.parse("android:resource://${v.context.packageName}/imgh-${restaurante.id}"))
        anioFundacion.text = "Desde ${restaurante.fundacion}"
        nombre.text= restaurante.nombre
        domicilio.text ="Ubicacion: ${restaurante.domicilio}"
        costoPromedio.text ="Costo aprox.: ${restaurante.costopromedio}"
        resenia.text = restaurante.resenia
        valoracion.rating= restaurante.valoracion
    }
}