package com.example.pkt.model

data class Soup (val name: String, val price: Int) {
    override fun toString(): String {
        return "${this.name} - ${price}zł"
    }
}