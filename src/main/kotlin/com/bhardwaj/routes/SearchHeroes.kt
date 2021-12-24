package com.bhardwaj.routes

import com.bhardwaj.repository.HeroRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.searchHeroes() {
    val heroRepository: HeroRepository by inject()

    get("/searchHero") {
        val name = call.request.queryParameters["name"]
        val page = call.request.queryParameters["page"]?.toInt() ?: 1
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 3

        val apiResponse = heroRepository.searchHero(name = name, page = page, limit = limit)
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}