package com.cr.o.cdc.sandboxAndroid.vm

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.repos.PokemonRepository
import javax.inject.Inject

/**
 * Created by Camilo on 31/12/19.
 */

class PokemonViewModel @Inject constructor(repository: PokemonRepository) : ViewModel() {

}