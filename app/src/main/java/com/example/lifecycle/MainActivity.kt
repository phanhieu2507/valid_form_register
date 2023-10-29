package com.example.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import java.util.Calendar
import android.app.DatePickerDialog
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var inputFirstName: TextInputEditText
    private lateinit var inputLastName: TextInputEditText
    private lateinit var inputBirthday: TextInputEditText
    private lateinit var inputAddress: TextInputEditText
    private lateinit var inputEmail: TextInputEditText
    private lateinit var agreeToTermsCheckBox: CheckBox
    private lateinit var selectMale: RadioButton
    private lateinit var selectFemale: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputFirstName = findViewById(R.id.inputFirstName)
        inputLastName = findViewById(R.id.inputLastName)
        inputBirthday = findViewById(R.id.inputBirthday)
        inputAddress = findViewById(R.id.inputAddress)
        inputEmail = findViewById(R.id.inputEmail)
        agreeToTermsCheckBox = findViewById(R.id.agreeToTermsCheckBox)
        selectMale = findViewById(R.id.selectMale)
        selectFemale = findViewById(R.id.selectFemale)
    }

    fun selectBirthdayOnClick(view: View) {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            inputBirthday.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun validateInput(firstName: String, lastName: String, birthday: String, address: String, email: String): Boolean {
        if (firstName.isEmpty() || lastName.isEmpty() || birthday.isEmpty() || address.isEmpty() || email.isEmpty()) {
            return false
        }
        return true
    }

    fun registerOnClick(view: View) {
        val firstName = inputFirstName.text.toString()
        val lastName = inputLastName.text.toString()
        val birthday = inputBirthday.text.toString()
        val address = inputAddress.text.toString()
        val email = inputEmail.text.toString()

        val isMaleSelected = selectMale.isChecked
        val isFemaleSelected = selectFemale.isChecked

        val agreeToTerms = agreeToTermsCheckBox.isChecked

        if (validateInput(firstName, lastName, birthday, address, email) && (isMaleSelected || isFemaleSelected) && agreeToTerms) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please fill out all required fields and agree to Terms of Use.", Toast.LENGTH_SHORT).show()
        }
    }
}