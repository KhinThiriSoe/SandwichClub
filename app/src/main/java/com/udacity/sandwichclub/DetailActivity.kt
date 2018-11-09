package com.udacity.sandwichclub

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.udacity.sandwichclub.model.Sandwich
import com.udacity.sandwichclub.utils.JsonUtils

class DetailActivity : AppCompatActivity() {

    private lateinit var ingredientsIv: ImageView
    private lateinit var originTv: TextView
    private lateinit var descriptionTv: TextView
    private lateinit var ingredientsTv: TextView
    private lateinit var alsoKnownTv: TextView

    private var sandwich: Sandwich? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ingredientsIv = findViewById(R.id.image_iv)
        originTv = findViewById(R.id.origin_tv)
        descriptionTv = findViewById(R.id.description_tv)
        ingredientsTv = findViewById(R.id.ingredients_tv)
        alsoKnownTv = findViewById(R.id.also_known_tv)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val intent = intent
        if (intent == null) {
            closeOnError()
        }

        var position = 0
        if (intent != null) {
            position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION)
        }
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError()
            return
        }

        val sandwiches = resources.getStringArray(R.array.sandwich_details)
        val json = sandwiches[position]
        sandwich = JsonUtils.parseSandwichJson(json)
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError()
            return
        }

        populateUI()
        Picasso.with(this)
                .load(sandwich!!.image)
                .into(ingredientsIv)

        title = sandwich!!.mainName
    }

    private fun closeOnError() {
        finish()
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show()
    }

    private fun populateUI() {
        if (sandwich != null) {
            originTv.text = sandwich?.placeOfOrigin
            descriptionTv.text = sandwich?.description

            val ingredientString = sandwich!!.ingredients.toString()
                    .replace("\"", " ")
                    .replace("[", "")
                    .replace("]", "")
                    .trim()
            ingredientsTv.text = ingredientString

            val alsoKnownString = sandwich!!.alsoKnownAs.toString()
                    .replace("\"", " ")
                    .replace("[", "")
                    .replace("]", "")
                    .trim()
            alsoKnownTv.text = alsoKnownString
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        val EXTRA_POSITION = "extra_position"
        private val DEFAULT_POSITION = -1
        val mainName = "mainName"
        val alsoKnownAs = "alsoKnownAs"
        val placeOfOrigin = "placeOfOrigin"
        val description = "description"
        val image = "image"
        val ingredients = "ingredients"

    }
}
