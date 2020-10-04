package com.cr.o.cdc.sandboxAndroid.preguntadoshelper.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.preguntadoshelper.db.model.PreguntadosQuestion
import com.cr.o.cdc.sandboxAndroid.preguntadoshelper.repos.PreguntadosHelperRepository

class PreguntadosQuestionListViewModel @ViewModelInject constructor(repo: PreguntadosHelperRepository) :
    ViewModel() {

    val preguntadosQuestions: LiveData<List<PreguntadosQuestion>> =
        repo.loadAllPreguntadosQuestions()
}
