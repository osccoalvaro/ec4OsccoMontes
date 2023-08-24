package com.alvaro.anotafacil.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "nota4")
@Parcelize
data class Note(
    @PrimaryKey
    val _id: String,
    val Nombre: String,
    val Historia: String,
    val Imagen: String,
    val Genero: String,
    val Estado: String,
    val Ocupacion: String,
    var isFavorite: Boolean = false
) : Parcelable {

}