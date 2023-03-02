package com.example.retrofitforecaster

import android.os.Bundle
import androidx.fragment.app.Fragment

class RetainFragment : Fragment() {
    private var adapter: Adapter? = null
    public var savedAdapter: Adapter?
        get() {
            return adapter
        }
        set(value) {
            adapter = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}
