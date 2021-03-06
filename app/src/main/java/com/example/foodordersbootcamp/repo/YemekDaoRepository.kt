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
                "Do??al ve sa??l??kl?? iz-indays ayran benzersiz k??vam?? ve lezzeti ile 7???den 70???e herkesin tercihi olmay?? hedefliyoruz.\n" +
                        "iz-indays Ayran i??tik??e ferahlayacak ve kendinizi yenilenmi?? hissedeceksiniz.\n"
            "Baklava" -> tasarim.textViewYemekAciklama.text =
                "Bayramlar??n ve ??zel g??nlerin olmazsa olmaz??, s??radan zamanlar??n ise vazge??ilmezi olan baklava, iz-indays kalitesi ile sofralar??n??za uzan??yor ve sevdiklerinizle bir arada oldu??unuz t??m anlar?? ta??land??r??yor!"

            "Fanta" -> tasarim.textViewYemekAciklama.text =
                "Yemek ve at????t??rmalar??n yan??nda sofran??za konuk olan Fanta, ayn?? zamanda misafirlerinize de ikram edebilece??iniz bir gazozdur. Portakal sevenlerin ve portakal lezzetini her mevsim ya??amak isteyenlerin rahatl??kla tercih edebilecekleri ??r??n, pratik yap??l?? ??i??esi sayesinde, kullan??m s??resi boyunca tazeli??ini muhafaza edebilir."
            "Izgara Somon" -> tasarim.textViewYemekAciklama.text =
                "Somon bal??????; tatl?? sularda yeti??ip, denizlerde g??z eden bir bal??k ??e??ididir. ??zel ya??lar??m??z?? kullanarak somonu en lezzetli hale getiriyoruz!"
            "Izgara Tavuk" -> tasarim.textViewYemekAciklama.text =
                "Pi??irme ??ncesi ve sonras??ndan al??nacak baz?? ??nlemler ve dikkat edilecek p??f noktalar??yla, sulu sulu ve lezzetli ??zgara tavuk g????s?? yemenin keyfini iz-indays de ya??ayabilirsiniz."
            "Kaday??f" -> tasarim.textViewYemekAciklama.text =
                "Kaday??f ya da Tel kaday??f, un ve suyun kar????t??r??lmas??yla elde edilen hamurun ince teller haline getirilmesi sonucu elde edilen bir yiyecektir. Iz-indays ile d??nyan??n en tatl?? kaday??f??n?? yemeye haz??r m??s??n??z?"
            "Kahve" -> tasarim.textViewYemekAciklama.text =
                "D??nyan??n en ince ??????t??m??ne sahip, Cezve/??brik ile alt??ndaki bir ??s?? kayna????n??n ??s??t??lmas?? ile telvesi i??inde olacak ??ekilde pi??irilen, a????k-orta kavruma sahip ve telvesi ile beraber t??ketilen kahveye iz-indays de yap??lan T??rk Kahvesi denir."
            "K??fte" -> tasarim.textViewYemekAciklama.text =
                "K??????kl??????m??zden beri en ??ok sevdi??imiz yeme??i soracak olsalar elbette ki bir??o??umuzun yan??t?? k??fte olur. En az annelerimizinki kadar lezzetli k??fteler iz-indays de!"
            "Lazanya" -> tasarim.textViewYemekAciklama.text =
                "Lazanya, peynir, domates sosu veya rag?? ile yap??lan ??talya as??ll?? bir yemektir. Lazanya s??zc??????, ba??ta pi??irme kab??n??n ad?? olsa da, g??n??m??zde sadece yeme??in ad??na lazanya denilmektedir."
            "Makarna" -> tasarim.textViewYemekAciklama.text =
                "Kendinizi italyada hissetmek ister misiniz? Ki??iye ??zel soslar??m??zla makarnaya yeni bir tat katt??k."
            "Pizza" -> tasarim.textViewYemekAciklama.text =
                "Ki??iye ??zel yapt??????m??z, s??cak s??cak yiyebilece??iniz Pizzan??z iz-indays de! Kalabal??k gruplara ??zel indirimlerimizi ka????rmay??n."
            "Su" -> tasarim.textViewYemekAciklama.text =
                "Bolu'dan ??zel olarak getirtti??imiz suyumuzu T??rkiye???nin d??rt bir yan??na ula??t??r??yoruz."
            "S??tla??" -> tasarim.textViewYemekAciklama.text =
                "S??tla??, s??tl?? ve ya s??tl?? a?? ??e??itli mutfak k??lt??rlerinde bulunabilen bir tatl??. T??rk mutfa????nda yer alan bir??ok s??tl?? tatl?? i??inde en yayg??n yap??lanlar??ndan ve tan??nanlar??ndan biri de s??tla??t??r. Iz-indays'in favori tatl??s?? incirli s??tlac??m??z?? mutlaka denemelisiniz."
            "Tiramisu" -> tasarim.textViewYemekAciklama.text =
                "??talyan k??kenli bir ya?? pasta olan tiramisunun ??talyanca'daki anlam?? 'Tira mi su' ???Beni kendime getir, kald??r??? demektir. Sizi kendinize getiren bir tiramisu haz??rl??yoruz."
            else -> tasarim.textViewYemekAciklama.text = "A????klama en k??sa s??rede eklenecektir..."
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