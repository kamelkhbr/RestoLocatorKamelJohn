package org.mousehole.restolocatorkameljohn.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "location_table")
data class LocationPlace (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "business name") var name: String?,
    @ColumnInfo(name = "operationStatus") var operationStatus: String?,
    @ColumnInfo(name = "iconUrl") var imageIconUrl: String?,
    @ColumnInfo(name = "locationLat") var lat: String?,
    @ColumnInfo(name = "locationLong") var long: String?,
    @ColumnInfo(name = "hourStatus") var hourStatus: String?,
    @ColumnInfo(name = "rating") var rating: String?
){
    constructor(): this(0,"","","","","","","")
}







