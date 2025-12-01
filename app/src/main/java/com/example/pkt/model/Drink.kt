package com.example.pkt.model

data class Drink (val name: String, val price: Number) {
    override fun toString(): String {
        return "${this.name} - ${price}zł"
    }
}