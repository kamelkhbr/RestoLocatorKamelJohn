package org.mousehole.restolocatorkameljohn.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.mousehole.restolocatorkameljohn.model.PlacesResult
import org.mousehole.restolocatorkameljohn.network.PlacesRetrofit

class PlacesViewModel: ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val placesLiveData: MutableLiveData<List<PlacesResult>> = MutableLiveData()

    private val placesRetofit : PlacesRetrofit = PlacesRetrofit()


    fun getNearBy (){
        compositeDisposable.add(
            placesRetofit.getNearBy().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map {
                    it.results
                }
                .subscribe ({
                    if (it.isNotEmpty())
                        placesLiveData.postValue(it)
                    compositeDisposable.clear()
                },{
                    Log.d("TAG_X", "${it.localizedMessage}")
                })

        )

    }
}