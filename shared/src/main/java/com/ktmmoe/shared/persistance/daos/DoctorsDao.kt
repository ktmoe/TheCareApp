package com.ktmmoe.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.data.vos.Speciality

/**
 * Created by ktmmoe on 05, December, 2020
 **/
@Dao
interface DoctorsDao {

    @Query("SELECT * FROM doctors")
    fun getAllDoctors(): List<Doctor>

    @Query("DELETE FROM doctors")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctors(doctors: List<Doctor>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctor(doctor: Doctor)

    @Transaction
    fun refreshDoctors(doctors: List<Doctor>) {
        deleteAll()
        insertDoctors(doctors)
    }
}