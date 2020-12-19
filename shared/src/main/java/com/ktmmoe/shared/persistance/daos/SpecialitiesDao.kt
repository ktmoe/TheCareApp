package com.ktmmoe.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ktmmoe.shared.data.vos.Speciality

/**
 * Created by ktmmoe on 05, December, 2020
 **/
@Dao
interface SpecialitiesDao {

    @Query("SELECT * FROM specialities")
    fun getAllSpecialities(): LiveData<List<Speciality>>

    @Query("DELETE FROM specialities")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialities(specialities: List<Speciality>)

    @Transaction
    fun refreshSpecialities(specialities: List<Speciality>) {
        deleteAll()
        insertSpecialities(specialities)
    }
}