package com.udacity.sandwichclub.model

data class Sandwich(val mainName: String, val alsoKnownAs: List<String>, val placeOfOrigin: String, val description: String, val image: String, val ingredients: List<String>) {}
