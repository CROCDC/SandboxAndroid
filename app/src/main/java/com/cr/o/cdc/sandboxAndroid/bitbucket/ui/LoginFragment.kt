package com.cr.o.cdc.sandboxAndroid.bitbucket.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.networking.StatusResult
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.bitbucket.vm.LoginViewModel
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentLoginBinding
import com.cr.o.cdc.sandboxAndroid.di.Injectable
import com.cr.o.cdc.sandboxAndroid.getString
import javax.inject.Inject

@Injectable
class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        // TODO CHECKS
        binding.btnLogin.setOnClickListener { view ->
            viewModel.login(
                binding.inputKey.getString(),
                binding.inputSecret.getString(),
                binding.inputUsername.getString(),
                binding.inputPassword.getString()
            ).observe(viewLifecycleOwner, Observer {
                if (it.status == StatusResult.SUCCESS) {
                    findNavController().navigate(R.id.action_loginFragment_to_workspacesFragment)
                } else if (it.status == StatusResult.FAILURE) {
                    Toast.makeText(view.context, R.string.error, Toast.LENGTH_LONG).show()
                }
            })
        }

        return binding.root
    }

}
