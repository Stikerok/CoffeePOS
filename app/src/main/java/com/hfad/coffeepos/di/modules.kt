package com.hfad.coffeepos.di

import com.hfad.coffeepos.data.DishDatabase
import com.hfad.coffeepos.data.IngredientDatabase
import com.hfad.coffeepos.domain.usecase.*
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.factoryBy

val appModule = module {
    single {
        DishDatabase(androidContext())
    }
    single {
        IngredientDatabase(androidContext())
    }
    single {
        DishesUseCaseImp(get(), get())
    }
    single {
        IngredientsUseCaseImp(get())
    }
    factoryBy<DishRepository, DishDatabase>()
    factoryBy<DishUseCase, DishesUseCaseImp>()
    factoryBy<IngredientRepository, IngredientDatabase>()
    factoryBy<IngredientUseCase, IngredientsUseCaseImp>()
}

val viewModelModel = module {
    viewModel {
        MainViewModel(get(), get())
    }
}