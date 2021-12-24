package com.bhardwaj.plugins

import com.bhardwaj.routes.getAllHeroes
import com.bhardwaj.routes.root
import com.bhardwaj.routes.searchHeroes
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*

fun Application.configureRouting() {
    routing {
        static("/images") {
            resources("images")
        }
        root()
        getAllHeroes()
        searchHeroes()
    }
}
