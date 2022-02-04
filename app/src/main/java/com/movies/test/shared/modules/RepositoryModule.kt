package com.movies.test.shared.modules

import com.movies.test.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { MoviesRepository(get()) }
}
