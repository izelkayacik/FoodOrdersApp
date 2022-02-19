package com.example.foodordersbootcamp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodordersbootcamp.databinding.FragmentYemekSepetBinding
import com.example.foodordersbootcamp.entity.YemekSepet
import com.example.foodordersbootcamp.repo.YemeklerDaoRepository

class YemekSepetFragmentViewModel : ViewModel() {
    var sepetYemeklerListesi: MutableLiveData<List<YemekSepet>>
    private val ydaor = YemeklerDaoRepository()

    init {
        sepetYemekleriYukle()
        sepetYemeklerListesi = ydaor.sepetYemekleriGetir()
    }

    fun sepetYemekleriYukle() {
        ydaor.tumSepetYemekleriAl()
    }

    fun yemekSil(sepet_yemek_id: Int, kullanici_adi: String) {
        ydaor.sepettenYemekSilme(sepet_yemek_id, kullanici_adi)
    }

    fun sepetYemekRvAnim(tasarim: FragmentYemekSepetBinding) {
        ydaor.yemekSepetRvAnim(tasarim)
    }
}