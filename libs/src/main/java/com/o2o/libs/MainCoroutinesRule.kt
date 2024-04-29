package com.o2o.libs

import com.o2o.domain.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainCoroutinesRule : TestWatcher() {

    private val testCoroutinesDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testCoroutinesDispatcher)

    val testDispatcherProvider =
        object : DispatcherProvider {
            override fun default(): CoroutineDispatcher = testCoroutinesDispatcher

            override fun io(): CoroutineDispatcher = testCoroutinesDispatcher

            override fun main(): CoroutineDispatcher = testCoroutinesDispatcher

            override fun unconfined(): CoroutineDispatcher = testCoroutinesDispatcher
        }

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testCoroutinesDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }

    fun runTest(block: suspend TestScope.() -> Unit) = testScope.runTest(2000, block)
}