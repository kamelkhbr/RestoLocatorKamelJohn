package org.mousehole.restolocatorkameljohn.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "location_table")
data class LocationPlace (
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "businessName") var name: String,
    @ColumnInfo(name = "operationStatus") var operationStatus: String,
    @ColumnInfo(name = "iconUrl") var imageIconUrl: String,
    @ColumnInfo(name = "locationLat") var lat: String,
    @ColumnInfo(name = "locationLong") var long: String,
    @ColumnInfo(name = "hourStatus") var hourStatus: String,
    @ColumnInfo(name = "rating") var rating: String
)
{
    constructor(): this(-1, "","","","","","","")
}







