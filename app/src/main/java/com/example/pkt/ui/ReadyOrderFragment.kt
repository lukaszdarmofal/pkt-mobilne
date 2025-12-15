package com.example.pkt.ui

import com.example.pkt.viewmodel.readyMealArray
import com.example.pkt.viewmodel.soupArray
import com.example.pkt.viewmodel.drinkArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pkt.R
import com.example.pkt.databinding.FragmentReadyOrderBinding
import com.example.pkt.model.Drink
import com.example.pkt.model.Meal
import com.example.pkt.model.Soup
import com.example.pkt.viewmodel.OrderViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReadyOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReadyOrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()
    private var _binding: FragmentReadyOrderBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentReadyOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedReadyMeal: Meal? = readyMealArray[0]
        var selectedSoup: Soup? = soupArray[0]
        var selectedDrink: Drink? = drinkArray[0]

        val soupList = soupArray
        val soupMap = mutableMapOf<String, Soup>()

        soupList.forEach {
            soupMap[it.name] = it
        }

        val soupSpinner: Spinner = binding.soupSpinner
        val soupAdapter: ArrayAdapter<*> = ArrayAdapter<Any?>(binding.root.context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, soupMap.keys.toList())

        soupSpinner.adapter = soupAdapter

        soupSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedSoupName = parent?.getItemAtPosition(position) as String
                selectedSoup = soupMap[selectedSoupName]

                binding.displaySoup.text = "Wybrana zupa: ${selectedSoup?.name} || ${selectedSoup?.price}zl"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }


        val drinkList = drinkArray
        val drinkMap = mutableMapOf<String, Drink>()

        drinkList.forEach {
            drinkMap[it.name] = it
        }

        val drinkSpinner: Spinner = binding.drinkSpinner
        val drinkAdapter: ArrayAdapter<*> =
            ArrayAdapter(
                binding.root.context,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                drinkMap.keys.toList()
            )

        drinkSpinner.adapter = drinkAdapter

        drinkSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedDrinkName = parent?.getItemAtPosition(position) as String
                selectedDrink = drinkMap[selectedDrinkName]

                binding.displayDrink.text =
                    "Wybrany napój: ${selectedDrink?.name} || ${selectedDrink?.price}zl"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.cancelOrder.setOnClickListener {
            findNavController().navigate(R.id.action_readyOrderFragment_to_menuChoiceFragment)
        }

        val readyMealList = readyMealArray
        val readyMealMap = mutableMapOf<String, Meal>()

        readyMealList.forEach {
            readyMealMap[it.name] = it
        }

        val readyMealSpinner: Spinner = binding.mainMealSpinner
        val readyMealAdapter: ArrayAdapter<*> =
            ArrayAdapter(
                binding.root.context,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                readyMealMap.keys.toList()
            )

        readyMealSpinner.adapter = readyMealAdapter

        readyMealSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedReadyMealName = parent?.getItemAtPosition(position) as String
                selectedReadyMeal = readyMealMap[selectedReadyMealName]

                binding.displayMainMeal.text =
                    "Wybrane danie gotowe: ${selectedReadyMeal?.name} || ${selectedReadyMeal?.price}zl"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.submitOrder.setOnClickListener {
            val fullPrice: Int = selectedReadyMeal!!.price + selectedDrink!!.price + selectedSoup!!.price

            val fullMeal = "${selectedReadyMeal!!.name} (${selectedReadyMeal!!.price}):\n" +
                    "${selectedReadyMeal!!.contents.contentToString()}\n\n" +
                    "Zupa: ${selectedSoup!!.name} (${selectedSoup!!.price})\n" +
                    "Napój: ${selectedDrink!!.name} (${selectedDrink!!.price})\n"
            println(fullMeal)
            println(fullPrice)

            sharedViewModel.sendOrder(fullMeal, fullPrice)

            findNavController().navigate(R.id.action_readyOrderFragment_to_summaryFragment)
        }

        binding.cancelOrder.setOnClickListener {
            findNavController().navigate(R.id.action_readyOrderFragment_to_menuChoiceFragment)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReadyOrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReadyOrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}