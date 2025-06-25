package com.example.admineventoscatolica.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notice(
    val noticeid: String? = null,
    val title: String? = null,
    val description: String? = null,
    val date: String? = null,
    val image: String? = null,  // URL de la imagen
    val isUpcoming: Boolean = false
) : Parcelable
