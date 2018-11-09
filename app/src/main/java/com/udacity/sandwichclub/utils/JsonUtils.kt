package com.udacity.sandwichclub.utils

import com.udacity.sandwichclub.DetailActivity.Companion.alsoKnownAs
import com.udacity.sandwichclub.DetailActivity.Companion.description
import com.udacity.sandwichclub.DetailActivity.Companion.image
import com.udacity.sandwichclub.DetailActivity.Companion.ingredients
import com.udacity.sandwichclub.DetailActivity.Companion.mainName
import com.udacity.sandwichclub.DetailActivity.Companion.placeOfOrigin
import com.udacity.sandwichclub.model.Sandwich
import org.json.JSONObject

object JsonUtils {

    fun parseSandwichJson(json: String): Sandwich? {
        val jsonObject = JSONObject(json)
        val nameObject = jsonObject.getJSONObject("name")
        val mainName = nameObject.getString(mainName)
        val alsoKnownAs = nameObject.getString(alsoKnownAs)

        val placeOfOrigin = jsonObject.getString(placeOfOrigin)
        val description = jsonObject.getString(description)
        val image = jsonObject.getString(image)
        val ingredients = jsonObject.getString(ingredients)

        return Sandwich(mainName, listOf(alsoKnownAs), placeOfOrigin, description, image, listOf(ingredients))
    }
}