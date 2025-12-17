package com.example.pkt.ui

import com.example.pkt.viewmodel.readyMealArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pkt.R
import com.example.pkt.databinding.FragmentCustomOrderBinding
import com.example.pkt.model.Drink
import com.example.pkt.model.MealContent
import com.example.pkt.model.Soup
import com.example.pkt.viewmodel.OrderViewModel
import com.example.pkt.viewmodel.drinkArray
import com.example.pkt.viewmodel.meatsArray
import com.example.pkt.viewmodel.saladsArray
import com.example.pkt.viewmodel.sidesArray
import com.example.pkt.viewmodel.soupArray

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomOrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentCustomOrderBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedMeat: MealContent? = meatsArray[0]
        var selectedSide: MealContent? = sidesArray[0]
        var selectedSalad: MealContent? = saladsArray[0]
        var selectedSoup: Soup? = soupArray[0]
        var selectedDrink: Drink? = drinkArray[0]

        /* ================== MEAT ================== */

        val meatMap = mutableMapOf<String, MealContent>()
        meatsArray.forEach { meatMap[it.item] = it }

        val meatSpinner: Spinner = binding.meatSpinner
        meatSpinner.adapter = ArrayAdapter(
            binding.root.context,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            meatMap.keys.toList()
        )

        meatSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val name = parent?.getItemAtPosition(position) as String
                selectedMeat = meatMap[name]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        /* ================== SIDE ================== */

        val sideMap = mutableMapOf<String, MealContent>()
        sidesArray.forEach { sideMap[it.item] = it }

        val sideSpinner: Spinner = binding.sideSpinner
        sideSpinner.adapter = ArrayAdapter(
            binding.root.context,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            sideMap.keys.toList()
        )

        sideSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val name = parent?.getItemAtPosition(position) as String
                selectedSide = sideMap[name]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        /* ================== SALAD ================== */

        val saladMap = mutableMapOf<String, MealContent>()
        saladsArray.forEach { saladMap[it.item] = it }

        val saladSpinner: Spinner = binding.saladSpinner
        saladSpinner.adapter = ArrayAdapter(
            binding.root.context,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            saladMap.keys.toList()
        )

        saladSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val name = parent?.getItemAtPosition(position) as String
                selectedSalad = saladMap[name]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        /* ================== SOUP ================== */

        val soupMap = mutableMapOf<String, Soup>()
        soupArray.forEach { soupMap[it.name] = it }

        val soupSpinner: Spinner = binding.customSoupSpinner
        soupSpinner.adapter = ArrayAdapter(
            binding.root.context,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            soupMap.keys.toList()
        )

        soupSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val name = parent?.getItemAtPosition(position) as String
                selectedSoup = soupMap[name]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        /* ================== DRINK ================== */

        val drinkMap = mutableMapOf<String, Drink>()
        drinkArray.forEach { drinkMap[it.name] = it }

        val drinkSpinner: Spinner = binding.customDrinkSpinner
        drinkSpinner.adapter = ArrayAdapter(
            binding.root.context,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            drinkMap.keys.toList()
        )

        drinkSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val name = parent?.getItemAtPosition(position) as String
                selectedDrink = drinkMap[name]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        /* ================== SUBMIT ================== */

        binding.submitCustomOrder.setOnClickListener {
            val fullPrice =
                selectedMeat!!.price +
                        selectedSide!!.price +
                        selectedSalad!!.price +
                        selectedSoup!!.price +
                        selectedDrink!!.price

            val fullMeal =
                "Danie niestandardowe:\n" +
                        "Mięso: ${selectedMeat!!.item} (${selectedMeat!!.price}zl)\n" +
                        "Dodatek: ${selectedSide!!.item} (${selectedSide!!.price}zl)\n" +
                        "Sałatka: ${selectedSalad!!.item} (${selectedSalad!!.price}zl)\n" +
                        "Zupa: ${selectedSoup!!.name} (${selectedSoup!!.price}zl)\n" +
                        "Napój: ${selectedDrink!!.name} (${selectedDrink!!.price}zl)\n"

            sharedViewModel.sendOrder(fullMeal, fullPrice)

            findNavController().navigate(
                R.id.action_customOrderFragment_to_summaryFragment
            )
        }

        binding.cancelOrder.setOnClickListener {
            findNavController().navigate(
                R.id.action_customOrderFragment_to_menuChoiceFragment
            )
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CustomOrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CustomOrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}