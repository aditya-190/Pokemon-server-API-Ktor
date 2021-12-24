package com.bhardwaj.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.root() {
    get("/") {
        call.respond(message = "Welcome From Pokemon API", status = HttpStatusCode.OK)
    }
}