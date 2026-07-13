package com.stepan_vin.coursesapp

import android.app.Application
import com.stepan_vin.coursesapp.di.initKoin

class CourseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
