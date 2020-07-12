package com.fizhu.bikeappconcept.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by fizhu on 06,July,2020
 * https://github.com/Fizhu
 */

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Expose @SerializedName("id")
    val id: Int? = 0,
    @Expose @SerializedName("name")
    val name: String? = null,
    @Expose @SerializedName("username")
    val username: String? = null,
    @Expose @SerializedName("password")
    val password: String? = null,
    @Expose @SerializedName("photo")
    val photo: String? = null
)

@Entity(tableName = "bike_table")
@Parcelize
data class Bike(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @Expose @SerializedName("id")
    val id: Int? = 0,
    @Expose @SerializedName("name")
    val name: String? = null,
    @Expose @SerializedName("type")
    val type: Int? = null,
    @Expose @SerializedName("image")
    val image: String? = null,
    @Expose @SerializedName("desc")
    val desc: String? = null,
    @Expose @SerializedName("price")
    val price: String? = null
): Parcelable
