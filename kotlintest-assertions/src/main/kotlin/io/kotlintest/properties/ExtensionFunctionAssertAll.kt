package io.kotlintest.properties

import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2

inline fun <reified A, R> KFunction1<A, R>.assertAll(test: PropertyContext.(A, R) -> Unit) {
  val gena = Gen.default<A>()
  _assertAll(1000, gena.constants().asSequence() + gena.random(), gena.shrinker()) { a ->
    val r = this@assertAll.invoke(a)
    test(a, r)
  }
}

inline fun <reified A, reified B, R> KFunction2<A, B, R>.assertAll(test: PropertyContext.(A, B, R) -> Unit) {

  val gena = Gen.default<A>()
  val genb = Gen.default<B>()
  
  val values = gena.constants().flatMap { a ->
    genb.constants().map { b ->
      Pair(a, b)
    }
  }.asSequence() + gena.random().zip(genb.random())

  _assertAll(1000, values, gena.shrinker(), genb.shrinker()) { a, b ->
    val r = this@assertAll.invoke(a, b)
    test(a, b, r)
  }
}