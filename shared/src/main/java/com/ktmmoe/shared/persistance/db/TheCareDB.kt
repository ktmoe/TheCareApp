package com.ktmmoe.shared.persistance.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.data.vos.GeneralQuestion
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.persistance.daos.DoctorsDao
import com.ktmmoe.shared.persistance.daos.GeneralQuestionsDao
import com.ktmmoe.shared.persistance.daos.PatientsDao
import com.ktmmoe.shared.persistance.daos.SpecialitiesDao

/**
 * Created by ktmmoe on 05, December, 2020
 **/
@Database(entities = [Speciality::class, Doctor::class, GeneralQuestion::class, Patient::class], version = 1, exportSchema = false)
abstract class TheCareDB : RoomDatabase() {
    companion object {
        private const val dbName = "TheCare.DB"
        var dbInstance: TheCareDB? = null

        fun getDBInstance(context: Context): TheCareDB {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context, TheCareDB::class.java, dbName)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            val i = dbInstance
            return i!!
        }
    }

    abstract fun specialitiesDao(): SpecialitiesDao
    abstract fun doctorsDao(): DoctorsDao
    abstract fun patientDao(): PatientsDao
    abstract fun generalQuestionsDao(): GeneralQuestionsDao
}