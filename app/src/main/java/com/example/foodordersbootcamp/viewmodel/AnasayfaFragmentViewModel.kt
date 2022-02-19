package com.example.foodordersbootcamp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodordersbootcamp.databinding.FragmentAnasayfaBinding
import com.example.foodordersbootcamp.entity.Yemekler
import com.example.foodordersbootcamp.repo.YemeklerDaoRepository

//ViewModel gereken dataları alır UI'ya uygular yani ekrana sunar.
//ViewModel’ de MutableLiveData assign ederiz çünkü MutableLiveData sayesinde UI içindeki tüm degişiklikler bu degişkene direk atanır.
class AnasayfaFragmentViewModel : ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    private val ydaor = YemeklerDaoRepository()

    init {
        yemekleriYukle()
        yemeklerListesi = ydaor.yemekleriGetir()
    }

    fun yemekleriYukle() {
        ydaor.tumYemekleriAl()
    }

    fun homePageRvAnim(tasarim: FragmentAnasayfaBinding) {
        ydaor.homePageRvAnim(tasarim)
    }
}