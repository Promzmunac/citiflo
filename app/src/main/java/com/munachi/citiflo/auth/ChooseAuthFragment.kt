package com.munachi.citiflo.auth

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.munachi.citiflo.HomeActivity
import com.munachi.citiflo.OnboardingActivity
import com.munachi.citiflo.R
import com.munachi.citiflo.databinding.FragmentChooseAuthBinding


class ChooseAuthFragment : Fragment() {
    private lateinit var  binding: FragmentChooseAuthBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var mProgressDialog: ProgressDialog
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val Req_Code: Int = 123
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fireDb : FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseAuthBinding.inflate(inflater, container, false)
        mProgressDialog = ProgressDialog(requireContext())
        mProgressDialog.setTitle("Citiflo")
        mProgressDialog.setMessage("Authenticating with Google")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        fireDb = FirebaseFirestore.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        updateUserInterface()
    }

    private fun updateUserInterface(){
       binding.btnContinueGoogle.setOnClickListener {

            //Toast.makeText(requireContext(), "Google Authentication...", Toast.LENGTH_LONG).show()
            signInGoogle()
            //findNavController().navigate(R.id.action_chooseAuthFragment_to_authFragment)
        }

        binding.btnSigupEmail.setOnClickListener {
            findNavController().navigate(R.id.action_chooseAuthFragment_to_authFragment)
        }

        binding.backPress.setOnClickListener {
            startActivity(Intent(requireContext(), OnboardingActivity::class.java))
        }

        binding.btnAlreadyHaveAccout.setOnClickListener {
            findNavController().navigate(R.id.action_chooseAuthFragment_to_loginFragment)
        }

        val appendText = " Login"
        appendColoredText(appendText, Color.GREEN)
    }

    private fun appendColoredText(text: String, color: Int) {
        val builder = SpannableStringBuilder()
        builder.append(text)

        val span = ForegroundColorSpan(color)
        builder.setSpan(span,builder.length - text.length,builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.btnAlreadyHaveAccout.append(builder)
    }


    private fun signInGoogle() {

        mProgressDialog.show()

        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)

    }


    // we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }



    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        dismissProgressDialog()

        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {

                UpdateUI(account)
            }
        } catch (e: ApiException) {

            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
        }
    }



    // this is where we update the UI after Google signin takes place
    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider
            .getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                addUserToFirestoreDatabase( account ,firebaseAuth.currentUser?.uid!! )
            }
        }
        dismissProgressDialog()
    }



    private fun addUserToFirestoreDatabase(account: GoogleSignInAccount, uid: String ){
        val userData = HashMap<String, Any>()
        userData["uid"] = uid
        userData["account"] = account
        userData["selectedPhotoUri"] = ""

        val currentUserID = firebaseAuth.currentUser!!.uid
        fireDb.collection("User").document(currentUserID).update(userData)

    }



    private fun dismissProgressDialog() {
        if (!mProgressDialog.isShowing) {
            mProgressDialog.show()
        } else {
            mProgressDialog.dismiss()
        }
    }



    override fun onStart() {
        super.onStart()

        if (GoogleSignIn.getLastSignedInAccount(requireContext()) != null) {

            startActivity(Intent(requireContext(), HomeActivity::class.java))

            val user = FirebaseAuth.getInstance().currentUser


            if (user != null) {
                startActivity(Intent(requireContext(), HomeActivity::class.java))

            } else {

                //loading my custom made animations
                val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
                //starting the animation
                binding.chooseAuthFragment.startAnimation(animation)
                startActivity(Intent(requireContext(), OnboardingActivity::class.java))

            }
        }

    }
}