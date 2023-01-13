package com.example.chococrumble.models

class Ingredient(val name: String, val measure: String) {
    override fun toString(): String {
        return "Ingredient(name='$name', measure='$measure')"
    }
}
