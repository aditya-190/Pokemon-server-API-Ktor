package com.bhardwaj.repository

import com.bhardwaj.models.ApiResponse
import com.bhardwaj.models.Hero

interface HeroRepository {
    val heroes: List<Hero>

    suspend fun getAllHeroes(page: Int = 1, limit: Int = 3): ApiResponse
    suspend fun searchHero(name: String?, page: Int, limit: Int): ApiResponse
}