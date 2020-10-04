package com.cr.o.cdc.sandboxAndroid.preguntadoshelper.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.SandBoxDB
import com.cr.o.cdc.sandboxAndroid.preguntadoshelper.db.model.PreguntadosQuestion
import javax.inject.Inject

class PreguntadosHelperRepository @Inject constructor(
    val db: SandBoxDB
) {

    fun loadAllPreguntadosQuestions(): LiveData<List<PreguntadosQuestion>> =
        db.preguntadosDao().loadAll()
}