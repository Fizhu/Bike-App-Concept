package com.fizhu.bikeappconcept.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable

/**
 * Created by fizhu on 06,July,2020
 * https://github.com/Fizhu
 */

@Dao
interface UserDao {

//    @get:Query("SELECT * FROM menu_table")
//    val all: Observable<List<Menu>>
//
//    @get:Query("SELECT * FROM menu_table WHERE quantity > 0")
//    val getListCart: Observable<List<Menu>>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertAll(questionList : List<Menu>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(menu: Menu)
//
//    @Query("DELETE FROM menu_table")
//    fun deleteAll()
//
//    @Query("DELETE FROM menu_table WHERE id = :id")
//    fun delete(id: String)
//
//    @Query("UPDATE menu_table SET note = :note WHERE id = :id")
//    fun updateNotes(id: String, note: String)
//
//    @Query("UPDATE menu_table SET quantity = :quantity WHERE id = :id")
//    fun updateQuantity(id: String, quantity: Int)
}