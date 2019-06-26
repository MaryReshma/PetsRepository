package com.example.pets.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pets.constants.Constants
import com.example.pets.model.Content
import com.example.pets.model.Pets
import com.example.pets.webservice.ContentImpl

class ContentViewModel : ViewModel() {
    var contentRepo = ContentImpl()
    var petsListMaleOwner = MutableLiveData<List<Pets>>()
    var petsListFemaleOwner = MutableLiveData<List<Pets>>()
    var errorMessage = MutableLiveData<String>()

    fun getContents() {
        contentRepo.getContentDataFromService({
            sortGender(it)
        }, {
            errorMessage.value = it?.message
        })
    }

    /**
     * method to sort contentList by gender
     */
    private fun sortGender(it: List<Content>) {
        val maleList = it.filter { content -> content.gender == Constants.MALE }
        val femaleList = it.filter { content -> content.gender == Constants.FEMALE }
        petsListMaleOwner.value = sortCatsByName(maleList)
        petsListFemaleOwner.value = sortCatsByName(femaleList)
    }

    /**
     * method to sort petsList by type
     */
    private fun sortCatsByName(list: List<Content>) : List<Pets> {
        val petsList : MutableList<Pets> = ArrayList()
        list.forEach {
            var sortedPetsList: List<Pets> = ArrayList()
            if (it.pets != null) {
                sortedPetsList = it.pets.filter { pets -> pets.type == Constants.CAT }
            }
            petsList.addAll(sortedPetsList)
        }
        return petsList.sortedBy { it.name }
    }
}

