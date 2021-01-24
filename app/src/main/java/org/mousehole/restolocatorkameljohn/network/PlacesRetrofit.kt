package org.mousehole.restolocatorkameljohn.network

import android.app.DownloadManager
import io.reactivex.Observable
import org.mousehole.restolocatorkameljohn.model.PlacesResponse
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PlacesRetrofit {

    private var placesAPI: PlacesAPI

    init {
        placesAPI = createPlacesAPI ( createRetrofitInstance())
    }


    private fun createRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createPlacesAPI(retorfit: Retrofit): PlacesAPI =
        retorfit.create(PlacesAPI::class.java)


    fun getNearBy(): Observable<PlacesResponse> = placesAPI.getNearBy()
}