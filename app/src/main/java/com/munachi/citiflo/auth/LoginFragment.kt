package com.munachi.citiflo.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.munachi.citiflo.HomeActivity
import com.munachi.citiflo.R
import com.munachi.citiflo.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var  binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.registerBtn.setOnClickListener {
        //  EmailLogin()
          startActivity(Intent(requireContext(), HomeActivity::class.java))
       }

       binding.dontHaveAccount.setOnClickListener {

           findNavController().navigate(R.id.action_loginFragment_to_authFragment)
       }

       binding.backPress.setOnClickListener {

           findNavController().navigate(R.id.action_loginFragment_to_chooseAuthFragment)
       }
   }

   private fun EmailLogin() {
        val username: String? = arguments?.getString("USERNAME")
        val email = arguments?.getString("EMAIL")
        val password = binding.passwordSignsUp.text.toString()

       binding.EmailTxt.setText(email)

       if (username!!.isEmpty() || password.isEmpty()) {
            binding.passwordSignsUp.error = "Password is Empty"

            return
       }

       FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
       .addOnCompleteListener {
            if (it.isSuccessful) {
                 val intent = Intent(requireContext(), HomeActivity::class.java)

                 intent.putExtra("NAME", username)
                 startActivity(intent)
                 Toast.makeText(requireContext(), "Login successfully", Toast.LENGTH_SHORT).show()

            } else {

                Toast.makeText(requireContext(), it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                return@addOnCompleteListener
            }

       }
   }
}

