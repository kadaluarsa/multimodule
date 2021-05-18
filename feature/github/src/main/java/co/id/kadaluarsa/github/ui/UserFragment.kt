package co.id.kadaluarsa.github.ui

import co.id.kadaluarsa.github.R
import co.id.kadaluarsa.mainui.base.BaseFragment
import co.id.kadaluarsa.github.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>() {
    override fun layoutRes(): Int = R.layout.fragment_user

    override fun initView() {

    }
}