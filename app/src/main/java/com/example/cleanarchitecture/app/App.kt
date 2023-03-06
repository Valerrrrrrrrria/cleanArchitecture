package com.example.cleanarchitecture.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // generate base class for App
class App: Application()