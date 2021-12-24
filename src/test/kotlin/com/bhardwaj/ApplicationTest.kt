package com.bhardwaj

import com.bhardwaj.models.ApiResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `access root`() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(
                    expected = HttpStatusCode.OK,
                    actual = response.status()
                )
                assertEquals(
                    expected = "Welcome From Pokemon API",
                    actual = response.content
                )
            }
        }
    }

    @Test
    fun `access heroes endpoint with wrong page number`() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/heroes?page=6").apply {
                assertEquals(
                    expected = HttpStatusCode.NotFound,
                    actual = response.status()
                )

                val expected = ApiResponse(
                    success = false,
                    message = "Hero Not Found",
                    previousPage = null,
                    nextPage = null,
                    heroes = emptyList()
                )

                val actual = Json.decodeFromString<ApiResponse>(response.content.toString())

                assertEquals(
                    expected = expected,
                    actual = actual
                )
            }
        }
    }

    @Test
    fun `access heroes endpoint rather than number in page query`() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/heroes?page=aditya1").apply {
                assertEquals(
                    expected = HttpStatusCode.BadRequest,
                    actual = response.status()
                )

                val expected = ApiResponse(
                    success = false,
                    message = "Only Numbers Allowed.",
                )

                val actual = Json.decodeFromString<ApiResponse>(response.content.toString())

                assertEquals(
                    expected = expected,
                    actual = actual
                )
            }
        }
    }

    @Test
    fun `access search heroes`() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/searchHero").apply {
                assertEquals(
                    expected = HttpStatusCode.OK,
                    actual = response.status()
                )

                val expected = ApiResponse(
                    success = true,
                    message = "OK",
                    totalHeroes = 15,
                    returnedHeroes = 0,
                    previousPage = null,
                    nextPage = null,
                    heroes = emptyList()
                )

                val actual = Json.decodeFromString<ApiResponse>(response.content.toString())

                assertEquals(
                    expected = expected,
                    actual = actual
                )
            }
        }
    }

    @Test
    fun `access unknown endpoint`() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/unknown").apply {
                assertEquals(expected = HttpStatusCode.NotFound, actual = response.status())
                assertEquals(expected = "Page Not Found", actual = response.content)
            }
        }
    }
}