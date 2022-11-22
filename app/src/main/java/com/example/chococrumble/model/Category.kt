package com.example.chococrumble.model

import com.google.gson.annotations.SerializedName

class Category {
    @SerializedName("idCategory")
    var id: Int? = null
    @SerializedName("strCategory")
    var name: String? = null
    @SerializedName("strCategoryThumb")
    var thumb: String? = null
    @SerializedName("strCategoryDescription")
    var description: String? = null
}
