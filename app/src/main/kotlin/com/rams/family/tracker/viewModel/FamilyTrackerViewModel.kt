package com.rams.family.tracker.viewModel

import androidx.lifecycle.ViewModel
import com.rams.family.tracker.model.LocationRepository
import com.rams.family.tracker.model.Supporter
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class FamilyTrackerViewModel(
    supporter: Supporter,
    locationRepository: LocationRepository
) : ViewModel() {



}