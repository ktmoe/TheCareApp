package com.ktmmoe.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Speciality

/**
 * Created by ktmmoe on 05, December, 2020
 **/
@Dao
interface PatientsDao {

    @Query("SELECT * FROM patients")
    fun getAllPatients(): List<Patient>

    @Query("DELETE FROM patients")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatients(patients: List<Patient>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatient(patient: Patient)

    @Transaction
    fun refreshPatients(patients: List<Patient>) {
        deleteAll()
        insertPatients(patients)
    }
}