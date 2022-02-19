package com.example.foodordersbootcamp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//Veritabanından bir varlığını temsil eden tüm sınıflara @Entity olarak belirtilmesi gerekir.
data class Yemekler(
    @SerializedName("yemek_id")
    @Expose
    var yemek_id: Int,
    @SerializedName("yemek_adi")
    @Expose
    var yemek_adi: String,
    @SerializedName("yemek_resim_adi")
    @Expose
    var yemek_resim_adi: String,
    @SerializedName("yemek_fiyat")
    @Expose
    var yemek_fiyat: Int
) : Serializable {
}