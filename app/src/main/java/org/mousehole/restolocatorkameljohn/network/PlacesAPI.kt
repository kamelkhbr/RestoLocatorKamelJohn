package org.mousehole.restolocatorkameljohn.network

import io.reactivex.Observable
import org.mousehole.restolocatorkameljohn.model.PlacesResponse
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.SEARCH_QUERY
import org.mousehole.restolocatorkameljohn.util.Constants.Companion.URL_PATH
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAPI {

    @GET(URL_PATH)
    fun searchNearBy (@Query(SEARCH_QUERY) searchQuery: String): Observable<PlacesResponse>

}