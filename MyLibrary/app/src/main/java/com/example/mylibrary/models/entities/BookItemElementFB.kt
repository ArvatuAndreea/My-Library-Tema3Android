package com.example.mylibrary.models.entities

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class BookItemElementFB (val title: String? = null, val author: String? = null, val description: String? = null) {
    fun convert() = BookItem(title, author, description)
}