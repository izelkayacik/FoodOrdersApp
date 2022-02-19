package com.example.foodordersbootcamp.repo

import android.util.Log
import android.view.animation.AnimationUtils
import androidx.lifecycle.MutableLiveData
import com.example.foodordersbootcamp.R
import com.example.foodordersbootcamp.databinding.FragmentAnasayfaBinding
import com.example.foodordersbootcamp.databinding.FragmentYemekDetayBinding
import com.example.foodordersbootcamp.databinding.FragmentYemekSepetBinding
import com.example.foodordersbootcamp.entity.YemekSepet
import com.example.foodordersbootcamp.entity.YemekSepetCevap
import com.example.foodordersbootcamp.entity.Yemekler
import com.example.foodordersbootcamp.entity.YemeklerCevap
import com.example.foodordersbootcamp.retrofit.ApiUtils
import com.example.foodordersbootcamp.retrofit.YemeklerDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository {
    private val yemeklerListesi: MutableLiveData<List<Yemekler>>
    private val yemeklerDaoInterface: YemeklerDaoInterface
    private val sepetYemeklerListesi: MutableLiveData<List<YemekSepet>>

    init {
        yemeklerDaoInterface = ApiUtils.getYemeklerDaoInterface()
        yemeklerListesi = MutableLiveData()
        sepetYemeklerListesi = MutableLiveData()
    }

    fun yemekleriGetir(): MutableLiveData<List<Yemekler>> {
        return yemeklerListesi
    }

    fun tumYemekleriAl() {
        yemeklerDaoInterface.tumYemekler().enqueue(object : Callback<YemeklerCevap> {
            override fun onResponse(call: Call<YemeklerCevap>, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                yemeklerListesi.value = liste
            }

            override fun onFailure(call: Call<YemeklerCevap>, t: Throwable) {}
        })
    }

    fun sepetYemekleriGetir(): MutableLiveData<List<YemekSepet>> {
        return sepetYemeklerListesi
    }

    fun tumSepetYemekleriAl() {
        yemeklerDaoInterface.sepetYemekler("izel_kayacik")
            .enqueue(object : Callback<YemekSepetCevap> {
                override fun onResponse(
                    call: Call<YemekSepetCevap>, response: Response<YemekSepetCevap>
                ) {
                    val liste = response.body().sepet_yemekler
                    sepetYemeklerListesi.value = liste
                }

                override fun onFailure(call: Call<YemekSepetCevap>, t: Throwable) {}
            })
    }

    fun sepeteEkle(
        sepet_yemek_id: Int,
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        yemeklerDaoInterface.sepeteYemekEkle(
            sepet_yemek_id,
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi
        ).enqueue(object : Callback<YemekSepetCevap> {
            override fun onResponse(
                call: Call<YemekSepetCevap>,
                response: Response<YemekSepetCevap>
            ) {
                if (response.isSuccessful) {
                    if (response.body().success == 1) {
                        Log.e("Mesaj", "${response.body().success}")
                    }
                }
            }

            override fun onFailure(call: Call<YemekSepetCevap>, t: Throwable) {}
        })
    }

    fun sepettenYemekSilme(yemek_id: Int, kullanici_adi: String) {
        yemeklerDaoInterface.sepettenYemekSil(yemek_id, kullanici_adi)
            .enqueue(object : Callback<YemekSepetCevap> {
                override fun onResponse(
                    call: Call<YemekSepetCevap>,
                    response: Response<YemekSepetCevap>
                ) {
                    tumSepetYemekleriAl()
                }

                override fun onFailure(call: Call<YemekSepetCevap>, t: Throwable) {}
            })
    }

    fun yemekAciklama(yemek_adi: String, tasarim: FragmentYemekDetayBinding) {
        when (yemek_adi) {
            "Ayran" -> tasarim.textViewYemekAciklama.text =
                "Doğal ve sağlıklı iz-indays ayran benzersiz kıvamı ve lezzeti ile 7’den 70’e herkesin tercihi olmayı hedefliyoruz.\n" +
                        "iz-indays Ayran içtikçe ferahlayacak ve kendinizi yenilenmiş hissedeceksiniz.\n"
            "Baklava" -> tasarim.textViewYemekAciklama.text =
                "Bayramların ve özel günlerin olmazsa olmazı, sıradan zamanların ise vazgeçilmezi olan baklava, iz-indays kalitesi ile sofralarınıza uzanıyor ve sevdiklerinizle bir arada olduğunuz tüm anları taçlandırıyor!"

            "Fanta" -> tasarim.textViewYemekAciklama.text =
                "Yemek ve atıştırmaların yanında sofranıza konuk olan Fanta, aynı zamanda misafirlerinize de ikram edebileceğiniz bir gazozdur. Portakal sevenlerin ve portakal lezzetini her mevsim yaşamak isteyenlerin rahatlıkla tercih edebilecekleri ürün, pratik yapılı şişesi sayesinde, kullanım süresi boyunca tazeliğini muhafaza edebilir."
            "Izgara Somon" -> tasarim.textViewYemekAciklama.text =
                "Somon balığı; tatlı sularda yetişip, denizlerde göz eden bir balık çeşididir. Özel yağlarımızı kullanarak somonu en lezzetli hale getiriyoruz!"
            "Izgara Tavuk" -> tasarim.textViewYemekAciklama.text =
                "Pişirme öncesi ve sonrasından alınacak bazı önlemler ve dikkat edilecek püf noktalarıyla, sulu sulu ve lezzetli ızgara tavuk göğsü yemenin keyfini iz-indays de yaşayabilirsiniz."
            "Kadayıf" -> tasarim.textViewYemekAciklama.text =
                "Kadayıf ya da Tel kadayıf, un ve suyun karıştırılmasıyla elde edilen hamurun ince teller haline getirilmesi sonucu elde edilen bir yiyecektir. Iz-indays ile dünyanın en tatlı kadayıfını yemeye hazır mısınız?"
            "Kahve" -> tasarim.textViewYemekAciklama.text =
                "Dünyanın en ince öğütümüne sahip, Cezve/İbrik ile altındaki bir ısı kaynağının ısıtılması ile telvesi içinde olacak şekilde pişirilen, açık-orta kavruma sahip ve telvesi ile beraber tüketilen kahveye iz-indays de yapılan Türk Kahvesi denir."
            "Köfte" -> tasarim.textViewYemekAciklama.text =
                "Küçüklüğümüzden beri en çok sevdiğimiz yemeği soracak olsalar elbette ki birçoğumuzun yanıtı köfte olur. En az annelerimizinki kadar lezzetli köfteler iz-indays de!"
            "Lazanya" -> tasarim.textViewYemekAciklama.text =
                "Lazanya, peynir, domates sosu veya ragù ile yapılan İtalya asıllı bir yemektir. Lazanya sözcüğü, başta pişirme kabının adı olsa da, günümüzde sadece yemeğin adına lazanya denilmektedir."
            "Makarna" -> tasarim.textViewYemekAciklama.text =
                "Kendinizi italyada hissetmek ister misiniz? Kişiye özel soslarımızla makarnaya yeni bir tat kattık."
            "Pizza" -> tasarim.textViewYemekAciklama.text =
                "Kişiye özel yaptığımız, sıcak sıcak yiyebileceğiniz Pizzanız iz-indays de! Kalabalık gruplara özel indirimlerimizi kaçırmayın."
            "Su" -> tasarim.textViewYemekAciklama.text =
                "Bolu'dan özel olarak getirttiğimiz suyumuzu Türkiye’nin dört bir yanına ulaştırıyoruz."
            "Sütlaç" -> tasarim.textViewYemekAciklama.text =
                "Sütlaç, sütlü ve ya sütlü aş çeşitli mutfak kültürlerinde bulunabilen bir tatlı. Türk mutfağında yer alan birçok sütlü tatlı içinde en yaygın yapılanlarından ve tanınanlarından biri de sütlaçtır. Iz-indays'in favori tatlısı incirli sütlacımızı mutlaka denemelisiniz."
            "Tiramisu" -> tasarim.textViewYemekAciklama.text =
                "İtalyan kökenli bir yaş pasta olan tiramisunun İtalyanca'daki anlamı 'Tira mi su' “Beni kendime getir, kaldır” demektir. Sizi kendinize getiren bir tiramisu hazırlıyoruz."
            else -> tasarim.textViewYemekAciklama.text = "Açıklama en kısa sürede eklenecektir..."
        }
    }

    private fun display(number: Int, tasarim: FragmentYemekDetayBinding) {
        tasarim.textViewNumber.text = "$number"
    }

    fun increaseInteger(tasarim: FragmentYemekDetayBinding) {
        display(tasarim.textViewNumber.text.toString().toInt() + 1, tasarim)
    }

    fun decreaseInteger(tasarim: FragmentYemekDetayBinding) {
        display(tasarim.textViewNumber.text.toString().toInt() - 1, tasarim)
        if (tasarim.textViewNumber.text == "0") {
            tasarim.textViewNumber.text = "1"
        }
    }

    fun addRemoveButton(tasarim: FragmentYemekDetayBinding) {
        tasarim.imageButtonAdd.setOnClickListener { increaseInteger(tasarim) }
        tasarim.imageButtonRemove.setOnClickListener { decreaseInteger(tasarim) }
    }

    fun homePageRvAnim(tasarim: FragmentAnasayfaBinding) {
        val rvanim = AnimationUtils.loadAnimation(
            tasarim.yemekRecyclerView.context,
            R.anim.recyclerview_animation
        )
        tasarim.yemekRecyclerView.startAnimation(rvanim)
    }

    fun yemekSepetRvAnim(tasarim: FragmentYemekSepetBinding) {
        val rvanim = AnimationUtils.loadAnimation(
            tasarim.recyclerView.context,
            R.anim.recyclerview_animation
        )
        tasarim.recyclerView.startAnimation(rvanim)
    }

}