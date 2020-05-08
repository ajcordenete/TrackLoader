package com.aljon.trackloader.ui.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aljon.trackloader.databinding.TracksFragmentBinding
import com.aljon.trackloader.utils.*
import com.aljon.trackloader.utils.helper.autoCleared
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TracksFragment: DaggerFragment() {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TracksViewModel> { viewmodelFactory }

    private var binding by autoCleared<TracksFragmentBinding>()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = TracksFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initTracks()
        observeSession()
        viewModel.saveSession()
    }

    private fun initTracks() {
        val trackAdapter = TrackAdapter(TrackAdapter.OnItemSelectListener { track, viewTransition ->
            navigateToTrackDetailWithTransition(track.trackId, viewTransition)
        })

        binding.trackList.adapter = trackAdapter

        //add dividers
        binding.trackList.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

        viewModel.tracks.observe(viewLifecycleOwner, Observer { resource ->
            binding.loadingProgress.visibility = View.GONE
            trackAdapter.submitList(resource.data)

            if(resource.status == Status.LOADING) {
                binding.loadingProgress.visibility = View.VISIBLE
            }
        })

        //wait for the list to be preDrawn before starting fragment enter transition
        //this will enable smooth view transition when coming back from trackDetailFragment
        waitForTransition(binding.trackList)
    }

    private fun observeSession() {
        viewModel.sessionTrackDetailResume.observe(viewLifecycleOwner, EventObserver{ trackID ->
            //Last page from user's session is trackDetail, resume this page...
            navigateToTrackDetail(trackID, null)
        })

        viewModel.lastVisited.observe(viewLifecycleOwner, Observer { lastVisited ->
            if(lastVisited.isNotEmpty()) {
                //only shows when last visit is not empty e.g first time opening the app...
                binding.lastVisitedText.visibility = View.VISIBLE
            }
        })
    }

    private fun navigateToTrackDetailWithTransition(trackID: Long, viewTransition: View) {
        //assign sharedElements that will be used in fragment transition
        val extras = FragmentNavigatorExtras(
            viewTransition to viewTransition.transitionName
        )
        navigateToTrackDetail(trackID, extras)
    }

    private fun navigateToTrackDetail(trackID: Long, extras: FragmentNavigator.Extras?) {
        this.findNavController().safeNavigate(TracksFragmentDirections.actionTracksFragmentToTrackDetailFragment(trackID), extras)
    }
}