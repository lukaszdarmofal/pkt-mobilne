package com.example.pkt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pkt.model.Drink
import com.example.pkt.model.Meal
import com.example.pkt.model.MealContent
import com.example.pkt.model.Soup

public val readyMealArray =
    listOf(
        Meal("Polski Klasyk", arrayOf("Schabowy", "Ziemniaki", "Mizeria"), 25),
        Meal("Kluski Śląskie", arrayOf("Gulasz wieprzowy", "Kluski", "Czerwona kapusta"), 19),
        Meal("Mielone", arrayOf("Mielony", "Puree ziemniaczane", "Buraczki"), 20),
        Meal("Pieczona Karkówka", arrayOf("Karkówka", "Ziemniaki opiekane", "Coleslaw"), 24),
        Meal("Devolaye", arrayOf("Devolay", "Frytki", "Coleslaw"), 26),
        Meal("Filet z Kurczaka", arrayOf("Panierowany kurczak", "Ryż", "Surówka z marchwi"), 21)
    )

public val drinkArray =
    listOf(
        Drink("Woda mineralna", 4),
        Drink("Sok jabłkowy", 6),
        Drink("Sok pomarańczowy", 6),
        Drink("Pepsi", 7),
        Drink("Herbata", 5),
        Drink("Kawa czarna", 7)
    )

public val soupArray =
    listOf(
        Soup("Pomidorowa", 8),
        Soup("Rosół z makaronem", 9),
        Soup("Żurek", 10),
        Soup("Barszcz czerwony", 7),
        Soup("Krupnik", 8),
        Soup("Zupa ogórkowa", 9)
    )

val meatsArray = listOf(
    MealContent("Schabowy", 12),
    MealContent("Gulasz wieprzowy", 11),
    MealContent("Mielony", 10),
    MealContent("Karkówka", 13),
    MealContent("Devolay", 14),
    MealContent("Panierowany kurczak", 11)
)

val sidesArray = listOf(
    MealContent("Ziemniaki", 5),
    MealContent("Ziemniaki opiekane", 6),
    MealContent("Puree ziemniaczane", 5),
    MealContent("Frytki", 6),
    MealContent("Kluski", 5),
    MealContent("Ryż", 4)
)

val saladsArray = listOf(
    MealContent("Mizeria", 4),
    MealContent("Czerwona kapusta", 4),
    MealContent("Buraczki", 4),
    MealContent("Coleslaw", 4),
    MealContent("Surówka z marchwi", 4)
)


class OrderViewModel: ViewModel() {
    private val _fullOrder = MutableLiveData<String>()
    val fullOrder get() = _fullOrder
    private val _orderPrice = MutableLiveData<Int>()
    val orderPrice get() = _orderPrice

    private val _totalValue = MutableLiveData<Int>(0)
    val totalValue get() = _totalValue

    fun sendOrder(content: String, price: Int) {
        _fullOrder.value = content
        _orderPrice.value = price
    }

    fun updateTotal(price: Int) {
        _totalValue.value += price
    }


}