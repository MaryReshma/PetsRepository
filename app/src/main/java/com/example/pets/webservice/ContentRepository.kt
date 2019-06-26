package com.example.pets.webservice

import com.example.pets.model.Content

interface ContentRepository {
    fun getContentDataFromService(successHandler: (List<Content>) -> Unit, failureHandler: (Throwable?) -> Unit)
}