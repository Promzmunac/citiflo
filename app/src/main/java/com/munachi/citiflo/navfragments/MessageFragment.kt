package com.munachi.citiflo.navfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.munachi.citiflo.databinding.FragmentMessageBinding


class MessageFragment : Fragment() {
    private lateinit var binding: FragmentMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()



    }

   /* private fun moveToRazorPay() {
        val intent = Intent(requireContext(), RazorPayActivity::class.java)
        startActivity(intent)
    }
    private fun moveToPayStack(){

    }*/
}












/*
val TAG:String = PaymentActivity::class.toString()


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_payment)

    *//*
    * To ensure faster loading of the Checkout form,
    * call this method as early as possible in your checkout flow
    * *//*
    Checkout.preload(applicationContext)

    var button: Button = findViewById(R.id.startpayment)
    button.setOnClickListener {
        startPayment()
    }
}

private fun startPayment() {
    *//*
    *  You need to pass current activity in order to let Razorpay create CheckoutActivity
    * *//*
    val activity: Activity = this
    val co = Checkout()

    try {
        val options = JSONObject()
        options.put("name","Razorpay Corp")
        options.put("description","Demoing Charges")
        //You can omit the image option to fetch the image from dashboard
        options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
        options.put("currency","INR")
        options.put("amount","100")
        options.put("send_sms_hash",true);

        val retryObj = JSONObject();
        retryObj.put("enabled", true);
        retryObj.put("max_count", 4);
        options.put("retry", retryObj);

        val prefill = JSONObject()
        prefill.put("email","orgafood@razorpay.com")
        prefill.put("contact","9156220330")


        options.put("prefill",prefill)
        co.open(activity,options)
    }catch (e: Exception){
        Toast.makeText(activity,"Error in payment: "+ e.message, Toast.LENGTH_LONG).show()
        e.printStackTrace()
    }
}

override fun onPaymentError(errorCode: Int, response: String?) {
    try{
        Toast.makeText(this,"Payment failed $errorCode \n $response", Toast.LENGTH_LONG).show()
    }catch (e: Exception){
        Log.e(TAG,"Exception in onPaymentSuccess", e)
    }
}

override fun onPaymentSuccess(razorpayPaymentId: String?) {
    Log.e(TAG, "Payment Successfull$razorpayPaymentId")
    Toast.makeText(this, "Payment Successfully Done!$razorpayPaymentId", Toast.LENGTH_SHORT).show()

}


}*/
