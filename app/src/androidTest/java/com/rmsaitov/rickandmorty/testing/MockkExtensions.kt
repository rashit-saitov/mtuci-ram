package com.rmsaitov.rickandmorty.testing

import io.mockk.mockk

inline fun <reified T : Any> relaxedMockk(): T = mockk(relaxed = true)
