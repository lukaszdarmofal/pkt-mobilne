package com.example.pkt.model

data class Meal(val name: String, val contents: Array<String>, val price: Number) {
    override fun toString(): String {
        return "${this.name} - ${price}zł"
    }
}

