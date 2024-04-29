package com.o2o.data.datasource

import com.o2o.data.exception.BadRequestException
import com.o2o.data.exception.ServerErrorException
import com.o2o.data.exception.ServiceNotFoundException
import com.o2o.data.exception.TimeoutException
import com.o2o.data.exception.UnauthorizedException
import com.o2o.data.model.response.ForecastDTO
import com.o2o.data.model.response.WeatherDTO
import com.o2o.data.service.WeatherService
import com.o2o.libs.MainCoroutinesRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRemoteDataSourceTest {

    @get:Rule
    val coroutineTestRule = MainCoroutinesRule()
    private val service: WeatherService = mockk()

    private lateinit var dataSource: WeatherRemoteDataSource

    private val mockkWeatherDTO = WeatherDTO(
        "16ºC", "8km/h", "Clear", listOf(ForecastDTO("1", "16ºC", "10km/h"))
    )

    @Before
    fun setup() {
        dataSource = WeatherRemoteDataSource(service)
    }


    @Test
    fun `call getWeatherByCity returns success with weather data`() = coroutineTestRule.runTest {
        coEvery { service.getWeatherByCity(any()) } returns Response.success(mockkWeatherDTO)

        val response = dataSource.getWeatherByCity("madrid")

        assertEquals(mockkWeatherDTO, response)
        coVerify { service.getWeatherByCity(any()) }
        confirmVerified(service)
    }

    @Test
    fun `call getWeatherByCity returns HttpCode 400 Bad Request`() = coroutineTestRule.runTest {
        coEvery { service.getWeatherByCity(any()) } returns Response.error(400, "{error}".toResponseBody())

        try {
            dataSource.getWeatherByCity("madrid")
        } catch (ex: Exception) {
            Assert.assertTrue(ex is BadRequestException)
        }

        coVerify { service.getWeatherByCity(any()) }
        confirmVerified(service)
    }

    @Test
    fun `call getWeatherByCity returns HttpCode 401 Unauthorized`() = coroutineTestRule.runTest {
        coEvery { service.getWeatherByCity(any()) } returns Response.error(401, "{error}".toResponseBody())

        try {
            dataSource.getWeatherByCity("madrid")
        } catch (ex: Exception) {
            Assert.assertTrue(ex is UnauthorizedException)
        }

        coVerify { service.getWeatherByCity(any()) }
        confirmVerified(service)
    }

    @Test
    fun `call getWeatherByCity returns HttpCode 403 Forbidden`() = coroutineTestRule.runTest {
        coEvery { service.getWeatherByCity(any()) } returns Response.error(403, "{error}".toResponseBody())

        try {
            dataSource.getWeatherByCity("madrid")
        } catch (ex: Exception) {
            Assert.assertTrue(ex is UnauthorizedException)
        }

        coVerify { service.getWeatherByCity(any()) }
        confirmVerified(service)
    }

    @Test
    fun `call getWeatherByCity returns HttpCode 404 Not Found`() = coroutineTestRule.runTest {
        coEvery { service.getWeatherByCity(any()) } returns Response.error(404, "{error}".toResponseBody())

        try {
            dataSource.getWeatherByCity("madrid")
        } catch (ex: Exception) {
            Assert.assertTrue(ex is ServiceNotFoundException)
        }

        coVerify { service.getWeatherByCity(any()) }
        confirmVerified(service)
    }

    @Test
    fun `call getWeatherByCity returns HttpCode 500 Server Error`() = coroutineTestRule.runTest {
        coEvery { service.getWeatherByCity(any()) } returns Response.error(500, "{error}".toResponseBody())

        try {
            dataSource.getWeatherByCity("madrid")
        } catch (ex: Exception) {
            Assert.assertTrue(ex is ServerErrorException)
        }

        coVerify { service.getWeatherByCity(any()) }
        confirmVerified(service)
    }

    @Test
    fun `call getWeatherByCity returns HttpCode 503 Timeout`() = coroutineTestRule.runTest {
        coEvery { service.getWeatherByCity(any()) } returns Response.error(503, "{error}".toResponseBody())

        try {
            dataSource.getWeatherByCity("madrid")
        } catch (ex: Exception) {
            Assert.assertTrue(ex is TimeoutException)
        }

        coVerify { service.getWeatherByCity(any()) }
        confirmVerified(service)
    }
}