package org.mousehole.restolocatorkameljohn.model

data class PlacesResponse(
    val html_attributions: List<Any>,
    val next_page_token: String,
    val results: List<PlacesResult>,
    val status: String
)