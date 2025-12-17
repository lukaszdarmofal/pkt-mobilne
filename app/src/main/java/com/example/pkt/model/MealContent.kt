package com.example.pkt.model

data class MealContent(val item: String, val price: Int){
    override fun toString(): String {
        return "${this.item} - ${price}zł"
    }
}
