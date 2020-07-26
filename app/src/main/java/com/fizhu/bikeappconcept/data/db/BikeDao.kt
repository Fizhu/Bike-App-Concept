package com.fizhu.bikeappconcept.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fizhu.bikeappconcept.data.models.Bike
import io.reactivex.Observable

/**
 * Created by fizhu on 06,July,2020
 * https://github.com/Fizhu
 */

@Dao
interface BikeDao {

    @get:Query("SELECT * FROM bike_table")
    val all: Observable<List<Bike>>

    @get:Query("SELECT COUNT(*) FROM bike_table")
    val count: Observable<Int>

    @Query("SELECT * FROM bike_table WHERE id = :id")
    fun getById(id: Int): Observable<List<Bike>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bike: Bike)

    @Query("DELETE FROM bike_table WHERE id = :id")
    fun deleteById(id: Int)

}