# FoodOrdersApp

• Yemekleri Listeleme • Yemek Detayı Görme • Detayda Adet Seçebilme • Seçilen Adet ile Yemeği Sepete
Ekleme • Sepetteki Yemekleri Görüntüleme • Sepetten Yemek Silme

özelliklerinin kullanıldığı bir sipariş verme uygulamasıdır.

## API

[Tüm Yemekleri Alma](http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php)
[Sepetteki Yemekleri Alma](http://kasimadalan.pe.hu/yemekler/sepettekiYemekleriGetir.php)
[Sepete Yemek Ekleme](http://kasimadalan.pe.hu/yemekler/sepeteYemekEkle.php)
[Sepetten Yemek Silme](http://kasimadalan.pe.hu/yemekler/sepettenYemekSil.php)
[Yemek Resimlerini Alma](http://kasimadalan.pe.hu/yemekler/resimler/ayran.png)

## Mimari

MVVM Mimarisi kullanılarak geliştirilmiştir.

## Kütüphaneler

- [Retrofit](https://square.github.io/retrofit/)
- [Room](https://developer.android.com/training/data-storage/room)
- [Picasso](https://square.github.io/picasso/)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Navigation](https://developer.android.com/guide/navigation)
- [GSON](https://guides.codepath.com/android/leveraging-the-gson-library)

## Arayüz

<table>
  <tr>
    <td> <b> Splash Ekranı </b> </td>
  </tr>
  <tr>
    <td valign="top"><img src=https://user-images.githubusercontent.com/36673014/154821007-28c56669-758c-4acf-aa31-143d900140eb.png  height="480" width="270"></td>
  </tr>
 </table>

 <table>
  <tr>
    <td> <b> Anasayfa </b> </td>
    <td> <b> Yemek Detay </b> </td>
    <td> <b> Sepet </b> </td>
    <td> <b> Yemek Detay Sil </b> </td>


  </tr>
  <tr>
     <td valign="top"><img src=https://user-images.githubusercontent.com/36673014/154821308-f7a4f071-fb94-4c22-9abf-cee02e955890.png height="480" width="270"><br></td>
     <td valign="top"><img src=https://user-images.githubusercontent.com/36673014/154821111-17d032ca-5c45-4d2b-8a93-9545743c98ad.png height="480" width="270"><br></td>
     <td valign="top"><img src=https://user-images.githubusercontent.com/36673014/154821122-75bcf1c1-f454-4d82-88bc-a8f219901f64.png height="480" width="270"><br></td>
     <td valign="top"><img src=https://user-images.githubusercontent.com/36673014/154821117-cac62dc3-95b5-4f65-80c7-d664ef6e49f5.png height="480" width="270"></td>
  </tr>
 </table>

