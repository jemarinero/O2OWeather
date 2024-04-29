package com.o2o.data.repository

import com.o2o.data.datasource.WeatherRemoteDataSource
import com.o2o.data.exception.BadRequestException
import com.o2o.data.exception.ConnectivityException
import com.o2o.data.exception.GenericException
import com.o2o.data.exception.ServerErrorException
import com.o2o.data.exception.ServiceNotFoundException
import com.o2o.data.exception.TimeoutException
import com.o2o.data.exception.UnauthorizedException
import com.o2o.data.model.mapper.toDomain
import com.o2o.data.model.response.ForecastDTO
import com.o2o.data.model.response.WeatherDTO
import com.o2o.domain.common.utilities.error.Failure
import com.o2o.domain.common.utilities.result.Result
import com.o2o.domain.common.utilities.result.onFailure
import com.o2o.domain.repository.WeatherRepository
import com.o2o.libs.MainCoroutinesRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryImplTest {

    @get:Rule
    val coroutineTestRule = MainCoroutinesRule()

    private val dataSource: WeatherRemoteDataSource = mockk()

    private lateinit var repository: WeatherRepository

    private val mockkWeatherDTO = WeatherDTO(
        "16ºC", "8km/h", "Clear", listOf(ForecastDTO("1", "16ºC", "10km/h"))
    )

    @Before
    fun setUp() {
        repository = WeatherRepositoryImpl(dataSource)
    }

    @Test
    fun `call getWeatherByCity returns success with weather data`() = coroutineTestRule.runTest {
        coEvery { dataSource.getWeatherByCity(any()) } returns mockkWeatherDTO
        val result = repository.getWeatherByCity("madrid")
        assertNotNull(result)
        assertTrue(result is Result.Success)
        coVerify { dataSource.getWeatherByCity(any()) }
        confirmVerified(dataSource)
    }

    @Test
    fun `call getWeatherByCity returns error with not data`() = coroutineTestRule.runTest {
        coEvery { dataSource.getWeatherByCity(any()) } returns null
        val result = repository.getWeatherByCity("madrid")
        assertNotNull(result)
        assertTrue(result is Result.Error)
        result.onFailure {
            assertEquals(Failure.NoDataError, it)
        }
        coVerify { dataSource.getWeatherByCity(any()) }
        confirmVerified(dataSource)
    }

    @Test
    fun `call getWeatherByCity datasource throws BadRequestException and repository returns ServiceError`() = coroutineTestRule.runTest {
        coEvery { dataSource.getWeatherByCity(any()) } throws BadRequestException("error")
        val result = repository.getWeatherByCity("madrid")
        assertNotNull(result)
        assertTrue(result is Result.Error)
        result.onFailure {
            assertTrue(it is Failure.ServiceError)
        }
        coVerify { dataSource.getWeatherByCity(any()) }
        confirmVerified(dataSource)
    }

    @Test
    fun `call getWeatherByCity datasource throws ConnectivityException and repository returns ConnectivityError`() = coroutineTestRule.runTest {
        coEvery { dataSource.getWeatherByCity(any()) } throws ConnectivityException("error")
        val result = repository.getWeatherByCity("madrid")
        assertNotNull(result)
        assertTrue(result is Result.Error)
        result.onFailure {
            assertTrue(it is Failure.ConnectivityError)
        }
        coVerify { dataSource.getWeatherByCity(any()) }
        confirmVerified(dataSource)
    }

    @Test
    fun `call getWeatherByCity datasource throws ServerErrorException and repository returns ServerError`() = coroutineTestRule.runTest {
        coEvery { dataSource.getWeatherByCity(any()) } throws ServerErrorException("error")
        val result = repository.getWeatherByCity("madrid")
        assertNotNull(result)
        assertTrue(result is Result.Error)
        result.onFailure {
            assertTrue(it is Failure.ServerError)
        }
        coVerify { dataSource.getWeatherByCity(any()) }
        confirmVerified(dataSource)
    }

    @Test
    fun `call getWeatherByCity datasource throws ServiceNotFoundException and repository returns ServerError`() = coroutineTestRule.runTest {
        coEvery { dataSource.getWeatherByCity(any()) } throws ServiceNotFoundException("error")
        val result = repository.getWeatherByCity("madrid")
        assertNotNull(result)
        assertTrue(result is Result.Error)
        result.onFailure {
            assertTrue(it is Failure.ServerError)
        }
        coVerify { dataSource.getWeatherByCity(any()) }
        confirmVerified(dataSource)
    }

    @Test
    fun `call getWeatherByCity datasource throws TimeoutException and repository returns TimeoutError`() = coroutineTestRule.runTest {
        coEvery { dataSource.getWeatherByCity(any()) } throws TimeoutException("error")
        val result = repository.getWeatherByCity("madrid")
        assertNotNull(result)
        assertTrue(result is Result.Error)
        result.onFailure {
            assertTrue(it is Failure.TimeoutError)
        }
        coVerify { dataSource.getWeatherByCity(any()) }
        confirmVerified(dataSource)
    }

    @Test
    fun `call getWeatherByCity datasource throws UnauthorizedException and repository returns UnauthorizedError`() = coroutineTestRule.runTest {
        coEvery { dataSource.getWeatherByCity(any()) } throws UnauthorizedException("error")
        val result = repository.getWeatherByCity("madrid")
        assertNotNull(result)
        assertTrue(result is Result.Error)
        result.onFailure {
            assertTrue(it is Failure.UnauthorizedError)
        }
        coVerify { dataSource.getWeatherByCity(any()) }
        confirmVerified(dataSource)
    }

    @Test
    fun `call getWeatherByCity datasource throws Exception and repository returns GenericError`() = coroutineTestRule.runTest {
        coEvery { dataSource.getWeatherByCity(any()) } throws Exception("error")
        val result = repository.getWeatherByCity("madrid")
        assertNotNull(result)
        assertTrue(result is Result.Error)
        result.onFailure {
            assertTrue(it is Failure.GenericError)
        }
        coVerify { dataSource.getWeatherByCity(any()) }
        confirmVerified(dataSource)
    }

    @Test
    fun `call getWeatherByCity datasource throws GenericException and repository returns GenericError`() = coroutineTestRule.runTest {
        coEvery { dataSource.getWeatherByCity(any()) } throws GenericException("error")
        val result = repository.getWeatherByCity("madrid")
        assertNotNull(result)
        assertTrue(result is Result.Error)
        result.onFailure {
            assertTrue(it is Failure.GenericError)
        }
        coVerify { dataSource.getWeatherByCity(any()) }
        confirmVerified(dataSource)
    }
}