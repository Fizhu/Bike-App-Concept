package com.fizhu.bikeappconcept.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fizhu.bikeappconcept.data.models.User
import io.reactivex.Observable

/**
 * Created by fizhu on 06,July,2020
 * https://github.com/Fizhu
 */

@Dao
interface UserDao {

    @get:Query("SELECT * FROM user_table")
    val all: Observable<List<User>>

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUserById(id: Int): Observable<List<User>>

    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password")
    fun getUserByUsernamePassword(username: String, password: String): Observable<List<User>>

    @Query("SELECT * FROM user_table WHERE username = :username")
    fun getUserByUsername(username: String): Observable<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("UPDATE user_table SET name = :name WHERE id = :id")
    fun update(id: Int, name: String)

    @Query("UPDATE user_table SET photo = :photo WHERE id = :id")
    fun updatePhoto(id: Int, photo: String)

    @Query("UPDATE user_table SET photo = :password WHERE id = :id")
    fun updatePassword(id: Int, password: String)

}