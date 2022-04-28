package pp.developer.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pp.developer.myapplication.R
import pp.developer.myapplication.RestauranteAdapter
import pp.developer.myapplication.models.Restaurante
import pp.developer.myapplication.services.APIServices
import pp.developer.myapplication.services.GetRetrofit
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Url

class ItemRestauranteDetailFragment: Fragment() {
    private lateinit var retroApi: Retrofit
    private lateinit var restaurante: Restaurante
    private  lateinit var adapter: RestauranteAdapter
    private val restaurantes = mutableListOf<Restaurante>()
    private var id:String="0"

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        retroApi = GetRetrofit().getRetrofit()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        id=arguments?.get("id").toString()
        return inflater.inflate(R.layout.item_restaurante_detail,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRestaurante(view,"getRestaurante/${id}")
        // Bind objects
    }
    private fun loadRestaurante(view:View,path:String){
        Toast.makeText(context,"Cargando datos...", Toast.LENGTH_SHORT).show()
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<Restaurante> = retroApi.create(APIServices::class.java).getRestaurante(path)
            val rest: Restaurante? = call.body()
            activity?.runOnUiThread {
                if(call.isSuccessful){
                    bindRestaurante(view, rest!!)
                }else{
                    Error("Error: Carga de informacion")
                }
            }
        }
    }
    private fun bindRestaurante(view:View,restaurante:Restaurante){
        var imgRestaurante= view.findViewById<ImageView>(R.id.imgVwRestaurante)
        var nombre= view.findViewById<TextView>(R.id.txtRestaurante)
        var domicilio= view.findViewById<TextView>(R.id.txtDireccionRestaurante)
        var costoPromedio= view.findViewById<TextView>(R.id.txtCostoPromedioRestaurante)
        var anioFundacion= view.findViewById<TextView>(R.id.txtAnio)
        var resenia = view.findViewById<TextView>(R.id.txtReseniaRestaurante)
        var valoracion = view.findViewById<RatingBar>(R.id.ratbCalificacion)
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