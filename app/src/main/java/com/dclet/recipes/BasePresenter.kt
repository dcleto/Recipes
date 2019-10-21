package com.dclet.recipes

interface BasePresenter<T>{
    fun attach(view : T)
    fun dettach()
}