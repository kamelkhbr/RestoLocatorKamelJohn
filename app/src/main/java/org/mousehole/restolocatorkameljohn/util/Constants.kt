package org.mousehole.restolocatorkameljohn.util

class Constants {
    companion object{

        // Full path
        // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&key=AIzaSyDh8h-16Q-uDkppVJsfPFjqS-U2OxSyfi8

        const val BASE_URL = "https://maps.googleapis.com/"
        const val URL_PATH= "/maps/api/place/nearbysearch/json"
        const val LOCATION= "location"
        const val RADIUS= "radius"
        const val KEY = "key"
        const val MY_API_KEY = "AIzaSyDh8h-16Q-uDkppVJsfPFjqS-U2OxSyfi8"

        const val LOCATION_REQUEST_CODE = 707


        const val TAG = "TAG_X"


        // Example of searching for nearby places based on current location radius
        // Must have 3 Parameters
        //      1) Location -> Long, Lat
        //      2) Radius -> meters (Max is 50000 meters)
        //      3) API Key
        /*https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=33.9091,-84.4791&radius=1500&key=AIzaSyDh8h-16Q-uDkppVJsfPFjqS-U2OxSyfi8*/

        // Other parameters for querys -> https://developers.google.com/places/web-service/search


        // Geocode
        //https://maps.googleapis.com/maps/api/geocode/json?address=stonemountainatlanta&key=AIzaSyDh8h-16Q-uDkppVJsfPFjqS-U2OxSyfi8

    }
}