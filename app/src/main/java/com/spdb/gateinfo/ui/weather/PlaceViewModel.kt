package com.spdb.gateinfo.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.spdb.gateinfo.GateInfoApplication
import com.spdb.gateinfo.Repository

class PlaceViewModel: ViewModel() {

    private val definePlace = MutableLiveData<String>()

    init {
        definePlace.value = GateInfoApplication.LOCATION
    }

    val placeLiveData = Transformations.switchMap(definePlace){
        Repository.searchPlace()
    }
}