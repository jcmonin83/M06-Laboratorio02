package pp.developer.myapplication.ui.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pp.developer.myapplication.R
import pp.developer.myapplication.RestauranteAdapter
import pp.developer.myapplication.databinding.FragmentHomeBinding
import pp.developer.myapplication.models.Restaurante
import pp.developer.myapplication.services.APIServices
import pp.developer.myapplication.services.Communicator
import pp.developer.myapplication.services.GetRetrofit
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.converter.gson.GsonConverterFactory



class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private  lateinit var adapter: RestauranteAdapter
    private val restaurantes = mutableListOf<Restaurante>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var retroApi: Retrofit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        retroApi = GetRetrofit().getRetrofit()
        val root: View = binding.root
        initRecyclerViewer(root.findViewById(R.id.rvRestaurantes))
        loadRestaurantes("getRestaurantes")
        return root
    }
    private fun loadRestaurantes(path:String){
        Toast.makeText(context,"Cargando datos...",Toast.LENGTH_SHORT).show()
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<Array<Restaurante>> = retroApi.create(APIServices::class.java).getRestaurantes(path)
            val rest:Array<Restaurante>? = call.body()
            activity?.runOnUiThread {
                if(call.isSuccessful){
                    restaurantes.clear()
                    for(i in 0 until rest?.size!!){
                        restaurantes.add(rest[i])
                    }
                    adapter.notifyDataSetChanged()
                }else{
                    Error("Error: Carga de informacion")
                }
            }
        }
    }

    private fun initRecyclerViewer(recyclerView: RecyclerView){
        adapter = RestauranteAdapter(restaurantes)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter=adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun Error( message:String){
        Toast.makeText(context,"Ha ocurrido un error. ${message}",Toast.LENGTH_SHORT).show()
    }
}