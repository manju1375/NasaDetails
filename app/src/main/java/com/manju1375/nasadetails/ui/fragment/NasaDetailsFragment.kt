package com.manju1375.nasadetails.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manju1375.nasadetails.databinding.LayoutDetailsBinding
import com.manju1375.nasadetails.ui.adapter.NasaDetailsPagerAdapter
import com.manju1375.nasadetails.ui.viewmodel.NasaDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class NasaDetailsFragment : Fragment() {

    private var _binding: LayoutDetailsBinding? = null

    val nasaDetailsViewModel: NasaDetailsViewModel by viewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    @Inject lateinit var nasaDetailsPagerAdapter:NasaDetailsPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LayoutDetailsBinding.inflate(inflater, container, false)
        val viewPager = binding.viewpager
        viewPager.adapter = nasaDetailsPagerAdapter
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}