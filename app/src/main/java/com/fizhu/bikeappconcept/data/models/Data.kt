package com.fizhu.bikeappconcept.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by fizhu on 06,July,2020
 * https://github.com/Fizhu
 */

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = false)
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