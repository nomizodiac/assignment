package com.systems.assignment.common.base

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected open fun getBaseActivity(): BaseActivity? {
        return activity as BaseActivity?
    }
}