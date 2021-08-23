package com.example.core.toturials.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Tutorial")
data class Tutorial(
    @Expose
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = 0,

    @Expose
    @SerializedName("type")
    @ColumnInfo(name = "type")
    val type: String?,

    @Expose
    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String?,

    @Expose
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "localPath")
    var localPath: String?,

    @ColumnInfo(name = "isDownloaded")
    var isDownloaded: Boolean = false,
)
