package com.example.storeapp.models

import android.os.Parcel
import android.os.Parcelable


data class Product(
    val id: String?,
    val code: String,
    val colors: String,
    val size: String,
    val material: String,
    val details: String,
    val price: Float,
    var image: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(code)
        parcel.writeString(colors)
        parcel.writeString(size)
        parcel.writeString(material)
        parcel.writeString(details)
        parcel.writeFloat(price)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}