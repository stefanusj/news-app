News App
=================

Aplikasi yang dibuat setelah mempelajari 6 unit yang diberikan pada #JuaraAndroid

Instruksi penggunaan
---------------

### NewsApi API key

News App menggunakan [NewsApi API](https://newsapi.org/docs/get-started) untuk mengambil data berita,
untuk menggunakan API, anda harus mengambil developer API key pada website tersebut.
Lihat pada [NewsApi API Documentation](https://newsapi.org/docs) untuk dokumentasi API dan instruksi penggunaan.

Setelah mendapatkan developer key, masukan pada  [`gradle.properties`](https://github.com/stefanusj/news-app/blob/master/gradle.properties)

```
news_api_key=NEWS_API_KEY
```

Aplikasi ini menggunakan NewsApi sehingga wajib untuk mendapatkan API nya terlebih dahulu apabila ingin mencoba,
atau bisa langsung download pada [News App](https://github.com/stefanusj/news-app/tree/master/app/release)
