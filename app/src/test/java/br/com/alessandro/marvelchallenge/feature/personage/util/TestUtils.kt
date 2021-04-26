package br.com.alessandro.marvelchallenge.feature.personage.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.RuleChain

val instantLiveDataAndCoroutineRules: RuleChain
    get() = RuleChain
        .outerRule(InstantCoroutineDispatcherRule())
        .around(InstantTaskExecutorRule())