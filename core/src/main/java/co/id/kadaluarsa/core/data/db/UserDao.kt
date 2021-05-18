/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.id.kadaluarsa.core.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.id.kadaluarsa.core.domain.model.User

/**
 * Interface for database access for User related operations.
 */
@Dao
abstract class UserDao {
    //save single user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(user: User)

    //save list of user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUsers(users: List<User>)

    //get list of user
    @Query("SELECT * FROM User")
    abstract fun getUsers() : LiveData<List<User>>

    @Query("SELECT * FROM User WHERE login = :username")
    abstract fun findByUsername(username: String): LiveData<User>

    @Query("select *from User where name in(:ids)")
    abstract fun getAll(ids: Array<String?>) : List<User>
}
