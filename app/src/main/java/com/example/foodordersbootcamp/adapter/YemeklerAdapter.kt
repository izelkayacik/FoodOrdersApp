package com.example.foodordersbootcamp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodordersbootcamp.databinding.YemekCardTasarimBinding
import com.example.foodordersbootcamp.entity.Yemekler
import com.example.foodordersbootcamp.fragment.AnasayfaFragmentDirections
import com.squareup.picasso.Picasso

class YemeklerAdapter(var mContext: Context, var yemeklerListe: List<Yemekler>) :
    RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(yemeklerCardTasarimBinding: YemekCardTasarimBinding) :
        RecyclerView.ViewHolder(yemeklerCardTasarimBinding.root) {
        var yemeklerCardTasarimBinding: YemekCardTasarimBinding

        init {
            this.yemeklerCardTasarimBinding = yemeklerCardTasarimBinding
        }
    }

    //RecyclerView yeni bir satıra ihtiyaç duyduğunda yeni bir ViewHolder nesnesi oluşturur.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = YemekCardTasarimBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    //Görüntülemek istediğimiz listenin boyutunu bize döndürür.
    override fun getItemCount(): Int {
        return yemeklerListe.size
    }

    //ViewHolder nesnesini alır ve içerisindeki görünümleri belirli satır için uygun liste verilerine ayarlar.
    // Yani verilerimizi ViewHolder’a iletiriz.
    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListe.get(position)
        val t = holder.yemeklerCardTasarimBinding
        t.yemekNesnesi = yemek

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Picasso.get().load(url).into(t.imageViewYemekResim)

        t.yemekCard.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek)
            Navigation.findNavController(it).navigate(gecis)
        }
    }
}