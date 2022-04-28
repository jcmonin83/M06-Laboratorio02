package pp.developer.myapplication.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetRetrofit {
    public fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://demo7824967.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            //.addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}