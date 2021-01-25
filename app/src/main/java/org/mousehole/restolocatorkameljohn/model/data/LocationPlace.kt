package org.mousehole.restolocatorkameljohn.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "location_table")
class LocationPlace {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id = 0
        private set
    @ColumnInfo(name = "businessName") var name: String?
    @ColumnInfo(name = "operationStatus") var operationStatus: String?
    @ColumnInfo(name = "iconUrl") var imageIconUrl: String?
    @ColumnInfo(name = "locationLat") var lat: String?
    @ColumnInfo(name = "locationLong") var lon: String?
    @ColumnInfo(name = "hourStatus") var hourStatus: String?
    @ColumnInfo(name = "rating") var rating: String?

    @Ignore
    constructor(name: String?, operationStatus: String?, imageIconUrl: String?, lat: String?, lon: String?, hourStatus: String?, rating: String?) {
        this.name = name
        this.operationStatus = operationStatus
        this.imageIconUrl = imageIconUrl
        this.lat = lat
        this.lon = lon
        this.hourStatus = hourStatus
        this.rating = rating
    }

    constructor(id: Int, name: String?, operationStatus: String?, imageIconUrl: String?, lat: String?, lon: String?, hourStatus: String?, rating: String?) {
        this.id = id
        this.name = name
        this.operationStatus = operationStatus
        this.imageIconUrl = imageIconUrl
        this.lat = lat
        this.lon = lon
        this.hourStatus = hourStatus
        this.rating = rating
    }


}









