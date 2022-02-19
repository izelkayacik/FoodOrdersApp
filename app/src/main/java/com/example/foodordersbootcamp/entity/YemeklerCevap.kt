package com.example.foodordersbootcamp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//İstek ve yanıt için json anahtarları Android'in kodlama kuralını takip etmiyor, bu nedenle anahtarlar için SerializedName ek açıklaması eklememiz gerekiyor, böylece Retrofit'i kullandığımızda bu anahtarlarla uygun json üretiyor. SerializedName kullanarak özelliğimizi jason key ile eşleyebiliriz. Özellikleri isteğe bağlı yapmanın daha iyi olduğunu unutmayın, böylece sunucu tüm anahtarlarla yanıt vermiyorsa bile, zengin nesnemizi istemci tarafında oluşturabiliriz.
data class YemeklerCevap(
    @SerializedName("yemekler")
    @Expose
    var yemekler: List<Yemekler>,
    @SerializedName("success")
    @Expose
    var success: Int
) {
}