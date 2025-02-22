package com.example.oishii.menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oishii.R
import com.example.oishii.database.MenuObject

class MenuFragment : Fragment() {


    private lateinit var viewModel: MenuViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var goToCheckOut: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.menu_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        recyclerView = view.findViewById(R.id.menu_recyclerView)
        goToCheckOut = view.findViewById(R.id.goTOCheckOut)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()
        initRecyclerView()

    }

    private fun setClickListeners() {

        goToCheckOut.setOnClickListener {

            findNavController().navigate(R.id.action_menuFragment2_to_goToCartFragment)
        }
    }


    private fun initRecyclerView() {

        menuAdapter = MenuAdapter(
            listOf(
                MenuCardObject(
                    "RAMEN", listOf(
                        MenuObject(
                            0,
                            "vegan ramen",
                            "Fresh ramen noodles, tofu,\nscallions, shitake mushrooms,\nleeks, bamboo shoots, pak choi\nin spicy dobanjiang soup.",
                            "allergens: hvete, soya, sesam, nøtter",
                            70,
                            "add to cart"
                        ),
                        MenuObject(
                            0,
                            "spicy vegan ramen",
                            "Fresh ramen noodles, tofu,\nscallions, shitake mushrooms,\nleeks, bamboo shoots, pak choi\nin spicy dobanjiang soup.",
                            "allergens: hvete, soya, sesam, nøtter",
                            70,
                            "add to cart"
                        )
                    )
                ), MenuCardObject(
                    "WOK", listOf(
                        MenuObject(
                            0,
                            "vegan pad thai prik",
                            "Rice noodle sticks with tofu,\nbean sprouts and chinese \nchieves in chilli sauce.\nTopped with cashew and lime.",
                            "allergens: nøtter, soya",
                            70,
                            "add to cart"
                        ),
                        MenuObject(
                            0,
                            "vegan teriyaki noodles",
                            "Wheat noodles woked with tofu,\npak choi, bell peppers, red\nonions and broccoli in teriyaki",
                            "allergens: hvete, soya",
                            70,
                            "add to cart"
                        )
                    )
                ), MenuCardObject(
                    "DRINKS", listOf(
                        MenuObject(
                            0,
                            "sakura ramune",
                            null,
                            null,
                            30,
                            "add to cart"
                        ), MenuObject(
                            0,
                            "coffee",
                            null,
                            null,
                            20,
                            "add to cart"
                        ), MenuObject(
                            0,
                            "soda",
                            "Coca-cola\nCoca-cola zero\nsparkling water",
                            null,
                            35,
                            "add to cart"
                        )
                    )
                )
            ),
            requireContext()
        ) {
            //clicklistener from MenuAdapter / saving MenuObject to DB
            val itemToCart = it
            viewModel.addItem(itemToCart)
            Toast.makeText(
                requireContext(),
                getString(R.string.toast_dish_added_text),
                Toast.LENGTH_SHORT
            ).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = menuAdapter
    }


}