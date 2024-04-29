package com.o2o.domain.usecase

import app.cash.turbine.test
import com.o2o.domain.common.utilities.error.Failure
import com.o2o.domain.common.utilities.result.resultError
import com.o2o.domain.common.utilities.result.resultSuccess
import com.o2o.domain.model.Forecast
import com.o2o.domain.model.Weather
import com.o2o.domain.repository.WeatherRepository
import com.o2o.libs.MainCoroutinesRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

@OptIn(ExperimentalCoroutinesApi::class)
class GetWeatherByCityUseCaseTest {

    @get:Rule
    val coroutineTestRule = MainCoroutinesRule()

    private val repository: WeatherRepository = mockk()

    private lateinit var useCase: GetWeatherByCityUseCase

    private val mockkWeather = Weather(
        "16ºC", "8km/h", "Clear", listOf(Forecast(Date(), "16ºC", "10km/h"))
    )

    @Before
    fun setUp() {
        useCase = GetWeatherByCityUseCase(repository, coroutineTestRule.testDispatcherProvider)
    }

    @Test
    fun `execute UseCase returns Result Success with weather data`() = coroutineTestRule.runTest {
        val expected = resultSuccess(mockkWeather)
        coEvery { repository.getWeatherByCity(any()) } returns expected
        useCase.execute("madrid").test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
        coVerify { repository.getWeatherByCity(any()) }
        confirmVerified(repository)
    }

    @Test
    fun `execute UseCase returns Result Error with ServerError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.ServerError)
        coEvery { repository.getWeatherByCity(any()) } returns expected
        useCase.execute("madrid").test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
        coVerify { repository.getWeatherByCity(any()) }
        confirmVerified(repository)
    }

    @Test
    fun `execute UseCase returns Result Error with TimeoutError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.TimeoutError)
        coEvery { repository.getWeatherByCity(any()) } returns expected
        useCase.execute("madrid").test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
        coVerify { repository.getWeatherByCity(any()) }
        confirmVerified(repository)
    }

    @Test
    fun `execute UseCase returns Result Error with ConnectivityError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.ConnectivityError)
        coEvery { repository.getWeatherByCity(any()) } returns expected
        useCase.execute("madrid").test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
        coVerify { repository.getWeatherByCity(any()) }
        confirmVerified(repository)
    }

    @Test
    fun `execute UseCase returns Result Error with UnauthorizedError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.UnauthorizedError)
        coEvery { repository.getWeatherByCity(any()) } returns expected
        useCase.execute("madrid").test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
        coVerify { repository.getWeatherByCity(any()) }
        confirmVerified(repository)
    }

    @Test
    fun `execute UseCase returns Result Error with NoDataError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.NoDataError)
        coEvery { repository.getWeatherByCity(any()) } returns expected
        useCase.execute("madrid").test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
        coVerify { repository.getWeatherByCity(any()) }
        confirmVerified(repository)
    }

    @Test
    fun `execute UseCase returns Result Error with ServiceError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.ServiceError("error"))
        coEvery { repository.getWeatherByCity(any()) } returns expected
        useCase.execute("madrid").test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
        coVerify { repository.getWeatherByCity(any()) }
        confirmVerified(repository)
    }

    @Test
    fun `execute UseCase returns Result Error with GenericError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.GenericError("error"))
        coEvery { repository.getWeatherByCity(any()) } returns expected
        useCase.execute("madrid").test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
        coVerify { repository.getWeatherByCity(any()) }
        confirmVerified(repository)
    }
}