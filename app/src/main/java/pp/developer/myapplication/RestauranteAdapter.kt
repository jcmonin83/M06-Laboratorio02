package pp.developer.myapplication

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import pp.developer.myapplication.models.Restaurante
import pp.developer.myapplication.ui.home.HomeFragment
import pp.developer.myapplication.ui.home.ItemRestauranteDetailFragment

class RestauranteAdapter(private val restaurantes: List<Restaurante>):
    RecyclerView.Adapter<RestauranteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  RestauranteViewHolder(layoutInflater.inflate(R.layout.item_restaurante,parent,false))
    }

    override fun onBindViewHolder(holder: RestauranteViewHolder, position: Int) {
        val items:Restaurante = restaurantes[position]
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("id" to items.id)
            Navigation.findNavController(it).navigate(R.id.action_nav_home_to_itemRestauranteDetailFragment,bundle)
        }
        holder.itemView.findViewById<ImageView>(R.id.imgVwRestaurante).setImageURI(Uri.parse("android:resource://${holder.itemView.context.packageName}/imgh-${items.id}"))
        holder.bind(items)
    }

    override fun getItemCount(): Int {
        return  restaurantes.size
    }
}