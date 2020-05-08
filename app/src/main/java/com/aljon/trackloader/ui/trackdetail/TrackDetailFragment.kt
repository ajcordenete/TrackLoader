package com.aljon.trackloader.ui.trackdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.aljon.trackloader.R
import com.aljon.trackloader.databinding.TrackDetailFragmentBinding
import com.aljon.trackloader.utils.helper.autoCleared
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TrackDetailFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TrackDetailViewModel> { viewModelFactory }

    private val navArgs by navArgs<TrackDetailFragmentArgs>()

    private var binding by autoCleared<TrackDetailFragmentBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TrackDetailFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner

        binding.viewModel = viewModel

        initImageTransition()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadTrackDetail(navArgs.trackID)
    }

    private fun initImageTransition() {
        //sets the transitionName for the detail imageView to be the same with the imageView from the list
        binding.artworkImage.transitionName = navArgs.trackID.toString()

        //sets the transition that will be use for the shared elements
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.image_shared_element_transition)
    }
}