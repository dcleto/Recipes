package com.dclet.recipes.di

import java.lang.annotation.Documented
import javax.inject.Scope

@Documented
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScoped