package com.example.pkt.viewmodel

import com.example.pkt.model.Drink
import com.example.pkt.model.Meal
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
