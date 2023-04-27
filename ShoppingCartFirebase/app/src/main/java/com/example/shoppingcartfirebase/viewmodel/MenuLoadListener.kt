package com.example.shoppingcartfirebase.viewmodel

import com.example.shoppingcartfirebase.model.MenuModel

interface MenuLoadListener {
    fun onMenuLoadSuccess(menuLoadListener: List<MenuModel>?)
    fun onMenuLoadFailed(message: String?)
}