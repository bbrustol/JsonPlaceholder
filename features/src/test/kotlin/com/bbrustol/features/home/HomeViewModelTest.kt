package com.bbrustol.features.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.bbrustol.core.data.infrastructure.ApiError
import com.bbrustol.core.data.infrastructure.ApiException
import com.bbrustol.core.data.infrastructure.ApiSuccess
import com.bbrustol.core.data.infrastructure.ResourceUtils
import com.bbrustol.core.data.remote.newsapi.model.response.users.UsersResponse
import com.bbrustol.core.data.repository.JSONPlaceholderRepository
import com.bbrustol.features.home.HomeViewModel.*
import com.squareup.moshi.Moshi
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val JSONPlaceholderRepository = mockk<JSONPlaceholderRepository>()

    private lateinit var viewModel: HomeViewModel

    private lateinit var moshi: Moshi

    @Before
    fun setUp() {
        moshi = Moshi.Builder().build()
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    private fun getHeadlinesMock(): UsersResponse {
        val response = ResourceUtils().openFile("headlines_200.json")
        val adapter = moshi.adapter(UsersResponse::class.java)
        return adapter.fromJson(response)!!
    }

    @Test
    fun `WHEN fetch getHeadline is requested and receive a throwable before emit, THEN State going to be an Catch`() = runTest {

        coEvery { JSONPlaceholderRepository.getUsers() } returns flow { throw IllegalStateException() }

        viewModel = HomeViewModel(JSONPlaceholderRepository)

        viewModel.uiState.test {
            assertTrue(awaitItem() is UiState.Catch)
            coVerify(exactly = 1) { JSONPlaceholderRepository.getUsers() }
            confirmVerified(JSONPlaceholderRepository)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `WHEN fetch getHeadline is requested unsuccessfully, THEN State going to be an Catch`() = runTest {

        coEvery { JSONPlaceholderRepository.getUsers() } returns flow { emit(ApiException(Throwable(""))) }

        viewModel = HomeViewModel(JSONPlaceholderRepository)

        viewModel.uiState.test {
            assertTrue(awaitItem() is UiState.Catch)
            coVerify(exactly = 1) { JSONPlaceholderRepository.getUsers() }
            confirmVerified(JSONPlaceholderRepository)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `WHEN fetch getHeadline is requested unsuccessfully, THEN State going to be an Failure`() = runTest {

        coEvery { JSONPlaceholderRepository.getUsers() } returns flow { emit(ApiError(0, "")) }

        viewModel = HomeViewModel(JSONPlaceholderRepository)

        viewModel.uiState.test {
            assertTrue(awaitItem() is UiState.Failure)
            coVerify(exactly = 1) { JSONPlaceholderRepository.getUsers() }
            confirmVerified(JSONPlaceholderRepository)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `WHEN fetch getHeadline is requested successfully, THEN State going to be a Success`() = runTest {

        coEvery { JSONPlaceholderRepository.getUsers() } returns flow { emit(ApiSuccess(getHeadlinesMock())) }

        viewModel = HomeViewModel(JSONPlaceholderRepository)

        viewModel.uiState.test {
            assertTrue(awaitItem() is UiState.Success)
            coVerify(atMost = 1) { JSONPlaceholderRepository.getUsers() }
            confirmVerified(JSONPlaceholderRepository)
            cancelAndConsumeRemainingEvents()
        }
    }
}