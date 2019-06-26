package com.example.pets.webservice

import com.example.pets.model.Content
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContentImpl() : ContentRepository {

    private var apiInterface: APIInterface = APIInterface.create()

    override fun getContentDataFromService(
        successHandler: (List<Content>) -> Unit,
        failureHandler: (Throwable?) -> Unit
    ) {
        apiInterface.getContents()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
            {
                successHandler(it)
            },{
                failureHandler(it)
            }
        )
    }

}