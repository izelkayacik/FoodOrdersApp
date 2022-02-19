package com.example.foodordersbootcamp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodordersbootcamp.databinding.FragmentYemekDetayBinding
import com.example.foodordersbootcamp.repo.YemeklerDaoRepository

class YemekDetayFragmentViewModel : ViewModel() {
    private var ydaor: YemeklerDaoRepository

    init {
        ydaor = YemeklerDaoRepository()
    }

    fun sepetEkle(
        yemek_id: Int,
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        ydaor.sepeteEkle(
            yemek_id,
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi
        )
    }

    fun yemekAciklamalariAl(yemek_adi: String, tasarim: FragmentYemekDetayBinding) {
        ydaor.yemekAciklama(yemek_adi, tasarim)
    }

    fun imageButtonAddRemove(tasarim: FragmentYemekDetayBinding) {
        ydaor.addRemoveButton(tasarim)
    }

}