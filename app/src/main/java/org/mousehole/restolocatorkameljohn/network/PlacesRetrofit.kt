package org.mousehole.restolocatorkameljohn.network

import io.reactivex.Observable
import org.mousehole.restolocatorkameljohn.model.data.PlacesResponse
import org.mousehole.restolocatorkameljohn.model.data.PlacesResult
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.BASE_URL
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.MY_API_KEY
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


    fun getNearBy(location: String, radius: String): Observable<PlacesResponse> =
        placesAPI.searchNearBy(location, radius, MY_API_KEY)
}