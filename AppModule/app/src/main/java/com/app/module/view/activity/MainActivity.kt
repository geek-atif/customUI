package com.app.module.view.activity

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.module.R
import com.app.module.constants.ConstantsAppModule
import com.app.module.databinding.ActivityMainBinding
import com.app.module.factory.EzetapModelFactory
import com.app.module.factory.UIEventManager
import com.app.module.viewmodel.EzetapViewModel
import com.bumptech.glide.Glide
import com.network.module.model.UIModel


/**
 * Created by Atif Qamar
 */

class MainActivity : AppCompatActivity(), UIEventManager, View.OnClickListener {
    private lateinit var ezetapViewModel: EzetapViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayout: LinearLayout
    private lateinit var progressBar: ProgressBar
    private var name : String = ""
    private var phoneNumber : String = ""
    private var city : String = ""
    private lateinit var editText : EditText
    private lateinit var uiModels: UIModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModelFactory = EzetapModelFactory(this)
        binding.lifecycleOwner = this
        ezetapViewModel = ViewModelProvider(this, viewModelFactory).get(EzetapViewModel::class.java)
        linearLayout = findViewById<LinearLayout>(R.id.ll_layout)
        progressBar = findViewById(R.id.progressBar)
        loadUI()
    }

    private fun loadUI() {
        ezetapViewModel.fetchCustomUI()
        ezetapViewModel.uIModelData.observe(this, Observer {
            uiModels = it
            buildUI()
        })

    }

    private fun buildUI() {

        if(uiModels ==null)
             return

        createImage(uiModels.logo_url)
        createTextView(uiModels.heading_text)
        for (uIModel in uiModels.uidata) {
            if (uIModel.uitype.equals(ConstantsAppModule.label))
                createLabel(uIModel.value)
            else if (uIModel.uitype.equals(ConstantsAppModule.edittext))
                createEditText(uIModel.key)
            else if ((uIModel.uitype.equals(ConstantsAppModule.button)))
                createButton(uIModel.value)
        }
    }

    private fun createTextView(hint: String) {
        // Create EditText
        var textView = TextView(this)
        textView.setHint(hint)
        textView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textView.setGravity(Gravity.CENTER)
        textView.textSize = 20F
        textView.setPadding(20, 30, 20, 20)
        linearLayout?.addView(textView)
    }

    private fun createLabel(hint: String) {
        // Create EditText
        val textView = TextView(this)
        textView.setHint(hint)
        textView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        linearLayout?.addView(textView)
    }

    private fun createEditText(idkey: String) {
        // Create EditText
         editText = EditText(this)
        editText.setTag(idkey)
        editText.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        editText.setPadding(20, 20, 20, 20)
        linearLayout?.addView(editText)
    }

    private fun createButton(displayName: String) {
        //set the properties for button
        val btnTag = Button(this)
        btnTag.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        btnTag.setText(displayName)
        btnTag.setOnClickListener(this)
        linearLayout?.addView(btnTag)
    }

    private fun createImage(imageURL: String) {
        val imageView = ImageView(this)
        Glide.with(applicationContext)
            .load(imageURL)
            //.asGif()
            .placeholder(R.drawable.ic_launcher_background)
            // .crossFade()
            .into(imageView)
        linearLayout?.addView(imageView)
    }


    override fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar() {

    }

    override fun hideProgressBar() {
        progressBar.visibility = ViewGroup.GONE
    }

    override fun onClick(view: View?) {
       for (uIModel in uiModels.uidata) {
           if (uIModel.uitype.equals(ConstantsAppModule.label)){
                // editText.getTag(uIModel.key)
           }
       }
    }
}