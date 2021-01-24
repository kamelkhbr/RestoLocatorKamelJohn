package org.mousehole.restolocatorkameljohn.util

class Constants {
    companion object{

        // Full path
        // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&key=AIzaSyDh8h-16Q-uDkppVJsfPFjqS-U2OxSyfi8

        const val BASE_URL = "https://maps.googleapis.com/"
        const val URL_PATH= "/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&key=AIzaSyDh8h-16Q-uDkppVJsfPFjqS-U2OxSyfi8"
        const val SEARCH_QUERY= "location"
        const val SEARCH_RADIUS= "radius"
        const val MY_API_KEY = "AIzaSyDh8h-16Q-uDkppVJsfPFjqS-U2OxSyfi8"

    }
}