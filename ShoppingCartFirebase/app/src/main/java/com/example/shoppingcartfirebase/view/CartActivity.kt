package com.example.shoppingcartfirebase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingcartfirebase.R
import com.example.shoppingcartfirebase.databinding.ActivityCartBinding
import com.example.shoppingcartfirebase.model.CartModel
import com.example.shoppingcartfirebase.viewmodel.CartAdapter
import com.example.shoppingcartfirebase.viewmodel.CartLoadListener
import com.example.shoppingcartfirebase.viewmodel.UpdateCart
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CartActivity : AppCompatActivity(), CartLoadListener {

    private lateinit var binding: ActivityCartBinding

    var cartLoadListener: CartLoadListener? = null

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().hasSubscriberForEvent(UpdateCart::class.java))
            EventBus.getDefault().removeStickyEvent(UpdateCart::class.java)
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onUpdateCartEvent(event: UpdateCart) {
        loadCartFromFirebase()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        loadCartFromFirebase()
    }

    private fun loadCartFromFirebase() {
        var cartModels: MutableList<CartModel> = ArrayList()
        FirebaseDatabase.getInstance()
            .getReference("Cart")
            .child("CUSTOMER")
            .addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(cartSnapshot in snapshot.children) {
                        val cartModel = cartSnapshot.getValue(CartModel::class.java)
                        cartModel!!.key = cartSnapshot.key
                        cartModels.add(cartModel)
                    }
                    cartLoadListener!!.onLoadCartSuccess(cartModels)
                }

                override fun onCancelled(error: DatabaseError) {
                    cartLoadListener!!.onLoadCartFailed(error.message)
                }

            })
    }

    private fun init() {
        cartLoadListener = this
        val layoutManager = LinearLayoutManager(this)
        binding.cartRecycler!!.layoutManager = layoutManager
        binding.cartRecycler!!.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        binding.backbutton!!.setOnClickListener {
            finish()
        }
    }

    override fun onLoadCartSuccess(cartModelList: List<CartModel>) {
        var sum = 0.00
        for(cartModel in cartModelList!!) {
            sum += cartModel!!.totalPrice

            sum = String.format("%.2f", sum).toDouble()
        }
        binding.totalText.text = StringBuilder("$").append(sum)
        val adapter = CartAdapter(this, cartModelList)
        binding.cartRecycler!!.adapter = adapter
    }

    override fun onLoadCartFailed(message: String?) {
 //       Snackbar.make(binding.mainLayout, message!!, Snackbar.LENGTH_LONG).show()
    }
}