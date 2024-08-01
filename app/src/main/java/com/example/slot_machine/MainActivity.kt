package com.example.slot_machine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.util.Random
import android.widget.Button
import android.graphics.Color


class MainActivity : AppCompatActivity() {

    //Define slot number range
    private val randomNumberRange = 9

    private var balance = 10

    private var betAmount = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Balance = findViewById<TextView>(R.id.balance)

        Balance.text = balance.toString()

        val BetAmount = findViewById<TextView>(R.id.betAmount)

        BetAmount.text = betAmount.toString()

        val message: String? //Declare a nullable String variable 'message'

        message = "Good Luck!"
        val ResetMessage = findViewById<TextView>(R.id.message)
        ResetMessage.setTextColor(Color.parseColor("#000000"))

        ResetMessage.text = message
        val increasedBetButton = findViewById<Button>(R.id.button4)
        val decreasedBetButton = findViewById<Button>(R.id.button5)

        val num1TextView = findViewById<TextView>(R.id.random_number1)
        val num2TextView = findViewById<TextView>(R.id.random_number2)
        val num3TextView = findViewById<TextView>(R.id.random_number3)

        num1TextView.text = "0"
        num2TextView.text = "0"
        num3TextView.text = "0"

        val num1 = num1TextView.text.toString().toInt()
        val num2 = num2TextView.text.toString().toInt()
        val num3 = num3TextView.text.toString().toInt()

    }

    fun increaseBetButton(view: View) {
        if ((betAmount < balance) && (balance !=0)) {
            betAmount++
        }

        val BetAmount = findViewById<TextView>(R.id.betAmount)

        BetAmount.text = betAmount.toString()
    }

    fun decreaseBetButton(view: View){
        if ((betAmount > 0) && (balance != 0)){
            betAmount--
        }
        val BetAmount = findViewById<TextView>(R.id.betAmount)

        BetAmount.text = betAmount.toString()
    }

    fun reset(view: View){
        balance = 10
        betAmount = 1

        val message: String? //Declare a nullable String variable 'message'

        message = "Good Luck!"
        val ResetMessage = findViewById<TextView>(R.id.message)
        ResetMessage.setTextColor(Color.parseColor("#000000"))

        ResetMessage.text = message


        val Balance = findViewById<TextView>(R.id.balance)

        Balance.text = balance.toString()

        val BetAmount = findViewById<TextView>(R.id.betAmount)

        BetAmount.text = betAmount.toString()

        val increasedBetButton = findViewById<Button>(R.id.button4)
        increasedBetButton.isEnabled = true

        val decreasedBetButton = findViewById<Button>(R.id.button5)
        decreasedBetButton.isEnabled = true

        val num1TextView = findViewById<TextView>(R.id.random_number1)
        val num2TextView = findViewById<TextView>(R.id.random_number2)
        val num3TextView = findViewById<TextView>(R.id.random_number3)

// Set the values to 0
        num1TextView.text = "0"
        num2TextView.text = "0"
        num3TextView.text = "0"

// Now, convert the text to integers
        val num1 = num1TextView.text.toString().toInt()
        val num2 = num2TextView.text.toString().toInt()
        val num3 = num3TextView.text.toString().toInt()


    }


    fun play(view: View){

        val message: String? //Declare a nullable String variable 'message'

        if ((balance > 0 && betAmount > 0) && (balance >= betAmount)) {

            // helper function to pick 2 random numbers and display them
            pickRandomNumber()


            // Retrieve and convert each of the text from 'random_number1' and random_number2 TextViews to an integer
            val num1 = findViewById<TextView>(R.id.random_number1).text.toString().toInt()
            val num2 = findViewById<TextView>(R.id.random_number2).text.toString().toInt()
            val num3 = findViewById<TextView>(R.id.random_number3).text.toString().toInt()

            if ((num1 == num2) && (num2 == num3)) {
                balance += 5 * betAmount


                message = "Hooray! You've won!"
                val winMessage = findViewById<TextView>(R.id.message)

                winMessage.text = message
                winMessage.setTextColor(Color.parseColor("#00FF00"))

            }
            else if((betAmount >  (balance - betAmount)) && (balance - betAmount) > 0){
                balance -= betAmount
                message = "Insufficient funds for $$betAmount bet."

                val loseMessage = findViewById<TextView>(R.id.message)

                loseMessage.text = message
                loseMessage.setTextColor(Color.parseColor("#FF0000"))

            }
            else if(balance == betAmount){
                balance -= betAmount
                message = "You lost, game is over!"



                val loseMessage = findViewById<TextView>(R.id.message)

                loseMessage.text = message
                loseMessage.setTextColor(Color.parseColor("#FF0000"))

                val increasedBetButton = findViewById<Button>(R.id.button4)
                increasedBetButton.isEnabled = false

                val decreasedBetButton = findViewById<Button>(R.id.button5)
                decreasedBetButton.isEnabled = false
            }

            else {
                balance -= betAmount
                message = "You lost! Try again!"

                val loseMessage = findViewById<TextView>(R.id.message)

                loseMessage.text = message
                loseMessage.setTextColor(Color.parseColor("#FF0000"))
            }

        }

        else if (balance == 0) {
            message = "You lost, game is over!"

            val loseMessage = findViewById<TextView>(R.id.message)

            loseMessage.text = message
            loseMessage.setTextColor(Color.parseColor("#FF0000"))

            val increasedBetButton = findViewById<Button>(R.id.button4)
            increasedBetButton.isEnabled = false

            val decreasedBetButton = findViewById<Button>(R.id.button5)
            decreasedBetButton.isEnabled = false

        }

            if (betAmount >= balance){

                betAmount = balance
            }

            else{
                val increasedBetButton = findViewById<Button>(R.id.button4)

                increasedBetButton.isEnabled = true


            }




        val Balance = findViewById<TextView>(R.id.balance)

        Balance.text = balance.toString()

        val BetAmount = findViewById<TextView>(R.id.betAmount)

        BetAmount.text = betAmount.toString()


    }
    private fun pickRandomNumber() {
        // Generate random numbers, and
        // Set the random numbers to the text views
        findViewById<TextView>(R.id.random_number1).text = "${generateRandomNumber(randomNumberRange)}"
        findViewById<TextView>(R.id.random_number2).text = "${generateRandomNumber(randomNumberRange)}"
        findViewById<TextView>(R.id.random_number3).text = "${generateRandomNumber(randomNumberRange)}"

    }

    private fun generateRandomNumber(range: Int): Int {
        return Random().nextInt(range)
    }
}

