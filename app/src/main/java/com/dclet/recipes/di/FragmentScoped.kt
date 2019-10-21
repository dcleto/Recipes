package com.dclet.recipes.di

import java.lang.annotation.ElementType
import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION,AnnotationTarget.CLASS)
annotation class FragmentScoped