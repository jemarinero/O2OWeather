package com.o2o.ui.features.weather

import app.cash.turbine.test
import com.o2o.domain.common.utilities.error.Failure
import com.o2o.domain.common.utilities.result.resultError
import com.o2o.domain.common.utilities.result.resultSuccess
import com.o2o.domain.model.Forecast
import com.o2o.domain.model.Weather
import com.o2o.domain.usecase.GetWeatherByCityUseCase
import com.o2o.libs.MainCoroutinesRule
import com.o2o.ui.features.weather.WeatherViewModel.*
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    @get:Rule
    val coroutineTestRule = MainCoroutinesRule()

    private val useCase: GetWeatherByCityUseCase = mockk()

    private val mockkWeather = Weather(
        "16ºC", "8km/h", "Clear", listOf(Forecast(Date(), "16ºC", "10km/h"))
    )

    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setUp() {
        viewModel = WeatherViewModel(useCase)
    }

    @Test
    fun `call getWeatherByCity with success`() = coroutineTestRule.runTest {
        val expected = resultSuccess(mockkWeather)
        every { useCase.execute(any()) } returns flow { emit(expected) }
        viewModel.state.test {
            viewModel.getWeatherByCity("madrid")
            assertEquals(State.OnLoading(false), awaitItem())
            assertEquals(State.OnLoading(true), awaitItem())
            assertEquals(State.OnSuccess(expected.data), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `call getWeatherByCity with ServerError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.ServerError)
        every { useCase.execute(any()) } returns flow { emit(expected) }
        viewModel.state.test {
            viewModel.getWeatherByCity("madrid")
            assertEquals(State.OnLoading(false), awaitItem())
            assertEquals(State.OnLoading(true), awaitItem())
            assertEquals(State.OnError(expected.error), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `call getWeatherByCity with TimeoutError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.TimeoutError)
        every { useCase.execute(any()) } returns flow { emit(expected) }
        viewModel.state.test {
            viewModel.getWeatherByCity("madrid")
            assertEquals(State.OnLoading(false), awaitItem())
            assertEquals(State.OnLoading(true), awaitItem())
            assertEquals(State.OnError(expected.error), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `call getWeatherByCity with ConnectivityError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.ConnectivityError)
        every { useCase.execute(any()) } returns flow { emit(expected) }
        viewModel.state.test {
            viewModel.getWeatherByCity("madrid")
            assertEquals(State.OnLoading(false), awaitItem())
            assertEquals(State.OnLoading(true), awaitItem())
            assertEquals(State.OnError(expected.error), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `call getWeatherByCity with UnauthorizedError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.UnauthorizedError)
        every { useCase.execute(any()) } returns flow { emit(expected) }
        viewModel.state.test {
            viewModel.getWeatherByCity("madrid")
            assertEquals(State.OnLoading(false), awaitItem())
            assertEquals(State.OnLoading(true), awaitItem())
            assertEquals(State.OnError(expected.error), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `call getWeatherByCity with NoDataError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.NoDataError)
        every { useCase.execute(any()) } returns flow { emit(expected) }
        viewModel.state.test {
            viewModel.getWeatherByCity("madrid")
            assertEquals(State.OnLoading(false), awaitItem())
            assertEquals(State.OnLoading(true), awaitItem())
            assertEquals(State.OnError(expected.error), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `call getWeatherByCity with ServiceError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.ServiceError(""))
        every { useCase.execute(any()) } returns flow { emit(expected) }
        viewModel.state.test {
            viewModel.getWeatherByCity("madrid")
            assertEquals(State.OnLoading(false), awaitItem())
            assertEquals(State.OnLoading(true), awaitItem())
            assertEquals(State.OnError(expected.error), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `call getWeatherByCity with GenericError`() = coroutineTestRule.runTest {
        val expected = resultError(Failure.GenericError(""))
        every { useCase.execute(any()) } returns flow { emit(expected) }
        viewModel.state.test {
            viewModel.getWeatherByCity("madrid")
            assertEquals(State.OnLoading(false), awaitItem())
            assertEquals(State.OnLoading(true), awaitItem())
            assertEquals(State.OnError(expected.error), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }
}