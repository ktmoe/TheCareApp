package com.ktmmoe.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.data.vos.GeneralQuestion

/**
 * Created by ktmmoe on 05, December, 2020
 **/
@Dao
interface GeneralQuestionsDao {

    @Query("SELECT * FROM generalQuestions")
    fun getAllGeneralQuestions(): LiveData<List<GeneralQuestion>>

    @Query("DELETE FROM generalQuestions")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGeneralQuestions(generalQuestions: List<GeneralQuestion>)

    @Transaction
    fun refreshGeneralQuestions(generalQuestions: List<GeneralQuestion>) {
        deleteAll()
        insertGeneralQuestions(generalQuestions)
    }
}