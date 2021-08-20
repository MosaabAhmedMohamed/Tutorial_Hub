package com.example.core.toturials.data.source.local.model

import com.google.gson.annotations.SerializedName

data class Tutorial(@SerializedName("id") val id: Int,
                    @SerializedName("type") val type: String,
                    @SerializedName("url") val url: String,
                    @SerializedName("name") val name: String,)
