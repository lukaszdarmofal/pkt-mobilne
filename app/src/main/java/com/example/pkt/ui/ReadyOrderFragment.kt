package com.example.pkt.ui

import com.example.pkt.viewmodel.readyMealArray
import com.example.pkt.viewmodel.soupArray
import com.example.pkt.viewmodel.drinkArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pkt.R
import com.example.pkt.databinding.FragmentReadyOrderBinding
import com.example.pkt.model.Soup

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

        val soupList = soupArray
        val soupMap = mutableMapOf<String, Soup>()

        soupList.forEach {
            soupMap[it.name] = it
        }

        val soupSpinner: Spinner = binding.soupSpinner
        val soupAdapter: ArrayAdapter<*> = ArrayAdapter<Any?>(binding.root.context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, soupMap.keys.toList())

        soupSpinner.adapter = soupAdapter

        binding.cancelOrder.setOnClickListener {
            findNavController().navigate(R.id.action_readyOrderFragment_to_menuChoiceFragment)
        }

        binding.submitOrder.setOnClickListener {
            binding.displayDrink.text = "nie"
            binding.displaySoup.text = "nie"
            binding.displayMainMeal.text = "nie"
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