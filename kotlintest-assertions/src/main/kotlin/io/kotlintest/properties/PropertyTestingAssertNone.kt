package io.kotlintest.properties

import io.kotlintest.Failures
import outputClassifications

inline fun <reified A> assertNone(fn: PropertyContext.(a: A) -> Unit) = assertNone(1000, fn)
inline fun <reified A> assertNone(iterations: Int, fn: PropertyContext.(a: A) -> Unit) {
  assertNone(iterations, Gen.default(), fn)
}

inline fun <A> assertNone(gena: Gen<A>, fn: PropertyContext.(a: A) -> Unit) = assertNone(1000, gena, fn)
inline fun <A> Gen<A>.assertNone(iterations: Int = 1000, fn: PropertyContext.(a0: A) -> Unit) = assertNone(iterations, this, fn)
inline fun <A> Gen<A>.assertNone(iterations: Int = 1000, fn: PropertyContext.(a0: A, a1: A) -> Unit) = assertNone(iterations, this, this, fn)
inline fun <A> Gen<A>.assertNone(iterations: Int = 1000, fn: PropertyContext.(a0: A, a1: A, a2: A) -> Unit) = assertNone(iterations, this, this, this, fn)
inline fun <A> Gen<A>.assertNone(iterations: Int = 1000, fn: PropertyContext.(a0: A, a1: A, a2: A, a3: A) -> Unit) = assertNone(iterations, this, this, this, this, fn)
inline fun <A> Gen<A>.assertNone(iterations: Int = 1000, fn: PropertyContext.(a0: A, a1: A, a2: A, a3: A, a4: A) -> Unit) = assertNone(iterations, this, this, this, this, this, fn)
inline fun <A> Gen<A>.assertNone(iterations: Int = 1000, fn: PropertyContext.(a0: A, a1: A, a2: A, a3: A, a4: A, a5: A) -> Unit) = assertNone(iterations, this, this, this, this, this, this, fn)
inline fun <A> assertNone(iterations: Int, gena: Gen<A>, fn: PropertyContext.(a: A) -> Unit) {
  if (iterations <= 0) throw IllegalArgumentException("Iterations should be a positive number")
  val context = PropertyContext()
  for (a in gena.constants()) {
    context.inc()
    val passed = try {
      context.fn(a)
      true
    } catch (e: AssertionError) {
      false
    } catch (e: Exception) {
      throw e
    }
    if (passed)
      throw Failures.failure("Property passed for\n$a\nafter ${context.attempts()} attempts")
  }
  val avalues = gena.random().iterator()
  while (context.attempts() < iterations) {
    val a = avalues.next()
    context.inc()
    val passed = try {
      context.fn(a)
      true
    } catch (e: AssertionError) {
      false
    } catch (e: Exception) {
      throw e
    }
    if (passed)
      throw Failures.failure("Property passed for\n$a\nafter ${context.attempts()} attempts")
  }
  outputClassifications(context)
}

inline fun <reified A, reified B> assertNone(fn: PropertyContext.(a: A, b: B) -> Unit) {
  assertNone(Gen.default(), Gen.default(), fn)
}

inline fun <reified A, reified B> assertNone(iterations: Int, fn: PropertyContext.(a: A, b: B) -> Unit) {
  assertNone(iterations, Gen.default(), Gen.default(), fn)
}

inline fun <A, B> assertNone(gena: Gen<A>, genb: Gen<B>, fn: PropertyContext.(a: A, b: B) -> Unit) = assertNone(1000, gena, genb, fn)

inline fun <A, B> assertNone(iterations: Int, gena: Gen<A>, genb: Gen<B>, fn: PropertyContext.(a: A, b: B) -> Unit) {
  val context = PropertyContext()
  for (a in gena.constants()) {
    for (b in genb.constants()) {
      context.inc()
      val passed = try {
        context.fn(a, b)
        true
      } catch (e: AssertionError) {
        false
      } catch (e: Exception) {
        throw e
      }
      if (passed)
        throw Failures.failure("Property passed for\n$a\n$b\nafter ${context.attempts()} attempts")
    }
  }
  val avalues = gena.random().iterator()
  val bvalues = genb.random().iterator()
  while (context.attempts() < iterations) {
    val a = avalues.next()
    val b = bvalues.next()
    context.inc()
    val passed = try {
      context.fn(a, b)
      true
    } catch (e: AssertionError) {
      false
    } catch (e: Exception) {
      throw e
    }
    if (passed)
      throw Failures.failure("Property passed for\n$a\n$b\nafter ${context.attempts()} attempts")
  }
  outputClassifications(context)
}

inline fun <reified A, reified B, reified C> assertNone(fn: PropertyContext.(a: A, b: B, c: C) -> Unit) {
  assertNone(1000, fn)
}

inline fun <reified A, reified B, reified C> assertNone(iterations: Int, fn: PropertyContext.(a: A, b: B, c: C) -> Unit) {
  assertNone(iterations, Gen.default(), Gen.default(), Gen.default(), fn)
}

inline fun <A, B, C> assertNone(gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, fn: PropertyContext.(a: A, b: B, c: C) -> Unit) =
    assertNone(1000, gena, genb, genc, fn)

inline fun <A, B, C> assertNone(iterations: Int, gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, fn: PropertyContext.(a: A, b: B, c: C) -> Unit) {
  if (iterations <= 0) throw IllegalArgumentException("Iterations should be a positive number")
  val context = PropertyContext()
  for (a in gena.constants()) {
    for (b in genb.constants()) {
      for (c in genc.constants()) {
        context.inc()
        val passed = try {
          context.fn(a, b, c)
          true
        } catch (e: AssertionError) {
          false
        } catch (e: Exception) {
          throw e
        }
        if (passed)
          throw Failures.failure("Property passed for\n$a\n$b\n$c\nafter ${context.attempts()} attempts")
      }
    }
  }
  val avalues = gena.random().iterator()
  val bvalues = genb.random().iterator()
  val cvalues = genc.random().iterator()
  while (context.attempts() < iterations) {
    val a = avalues.next()
    val b = bvalues.next()
    val c = cvalues.next()
    context.inc()
    val passed = try {
      context.fn(a, b, c)
      true
    } catch (e: AssertionError) {
      false
    } catch (e: Exception) {
      throw e
    }
    if (passed)
      throw Failures.failure("Property passed for\n$a\n$b\n$c\nafter ${context.attempts()} attempts")
  }
  outputClassifications(context)
}

inline fun <reified A, reified B, reified C, reified D> assertNone(fn: PropertyContext.(a: A, b: B, c: C, D) -> Unit) {
  assertNone(1000, fn)
}

inline fun <reified A, reified B, reified C, reified D> assertNone(iterations: Int, fn: PropertyContext.(a: A, b: B, c: C, D) -> Unit) {
  assertNone(iterations, Gen.default(), Gen.default(), Gen.default(), Gen.default(), fn)
}

inline fun <A, B, C, D> assertNone(gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, gend: Gen<D>, fn: PropertyContext.(a: A, b: B, c: C, d: D) -> Unit) =
    assertNone(1000, gena, genb, genc, gend, fn)

inline fun <A, B, C, D> assertNone(iterations: Int, gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, gend: Gen<D>, fn: PropertyContext.(a: A, b: B, c: C, d: D) -> Unit) {
  val context = PropertyContext()
  for (a in gena.constants()) {
    for (b in genb.constants()) {
      for (c in genc.constants()) {
        for (d in gend.constants()) {
          context.inc()
          val passed = try {
            context.fn(a, b, c, d)
            true
          } catch (e: AssertionError) {
            false
          } catch (e: Exception) {
            throw e
          }
          if (passed)
            throw Failures.failure("Property passed for\n$a\n$b\n$c\n$d\nafter ${context.attempts()} attempts")
        }
      }
    }
  }
  val avalues = gena.random().iterator()
  val bvalues = genb.random().iterator()
  val cvalues = genc.random().iterator()
  val dvalues = gend.random().iterator()
  while (context.attempts() < iterations) {
    val a = avalues.next()
    val b = bvalues.next()
    val c = cvalues.next()
    val d = dvalues.next()
    context.inc()
    val passed = try {
      context.fn(a, b, c, d)
      true
    } catch (e: AssertionError) {
      false
    } catch (e: Exception) {
      throw e
    }
    if (passed)
      throw Failures.failure("Property passed for\n$a\n$b\n$c\n$d\nafter ${context.attempts()} attempts")
  }
  outputClassifications(context)
}

inline fun <reified A, reified B, reified C, reified D, reified E> assertNone(fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E) -> Unit) =
    assertNone(1000, fn)

inline fun <reified A, reified B, reified C, reified D, reified E> assertNone(iterations: Int, fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E) -> Unit) {
  assertNone(iterations, Gen.default(), Gen.default(), Gen.default(), Gen.default(), Gen.default(), fn)
}

inline fun <A, B, C, D, E> assertNone(gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, gend: Gen<D>, gene: Gen<E>, fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E) -> Unit) =
    assertNone(1000, gena, genb, genc, gend, gene, fn)

inline fun <A, B, C, D, E> assertNone(iterations: Int, gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, gend: Gen<D>, gene: Gen<E>, fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E) -> Unit) {
  if (iterations <= 0) throw IllegalArgumentException("Iterations should be a positive number")
  val context = PropertyContext()
  
  for (a in gena.constants()) {
    for (b in genb.constants()) {
      for (c in genc.constants()) {
        for (d in gend.constants()) {
          for (e in gene.constants()) {
            context.inc()
            val passed = try {
              context.fn(a, b, c, d, e)
              true
            } catch (e: AssertionError) {
              false
            } catch (e: Exception) {
              throw e
            }
            if (passed)
              throw Failures.failure("Property passed for\n$a\n$b\n$c\n$d\n$e\nafter ${context.attempts()} attempts")
          }
        }
      }
    }
  }
  val avalues = gena.random().iterator()
  val bvalues = genb.random().iterator()
  val cvalues = genc.random().iterator()
  val dvalues = gend.random().iterator()
  val evalues = gene.random().iterator()
  while (context.attempts() < iterations) {
    val a = avalues.next()
    val b = bvalues.next()
    val c = cvalues.next()
    val d = dvalues.next()
    val e = evalues.next()
  
    context.inc()
    val passed = try {
      context.fn(a, b, c, d, e)
      true
    } catch (e: AssertionError) {
      false
    } catch (e: Exception) {
      throw e
    }
    if (passed)
      throw Failures.failure("Property passed for\n$a\n$b\n$c\n$d\n$e\nafter ${context.attempts()} attempts")
  }
  outputClassifications(context)
}

inline fun <reified A, reified B, reified C, reified D, reified E, reified F> assertNone(fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E, f: F) -> Unit) {
  assertNone(1000, fn)
}

inline fun <reified A, reified B, reified C, reified D, reified E, reified F> assertNone(iterations: Int, fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E, f: F) -> Unit) {
  assertNone(iterations, Gen.default(), Gen.default(), Gen.default(), Gen.default(), Gen.default(), Gen.default(), fn)
}

inline fun <A, B, C, D, E, F> assertNone(gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, gend: Gen<D>, gene: Gen<E>, genf: Gen<F>, fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E, f: F) -> Unit) =
    assertNone(1000, gena, genb, genc, gend, gene, genf, fn)

inline fun <A, B, C, D, E, F> assertNone(iterations: Int, gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, gend: Gen<D>, gene: Gen<E>, genf: Gen<F>, fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E, f: F) -> Unit) {
  if (iterations <= 0) throw IllegalArgumentException("Iterations should be a positive number")
  val context = PropertyContext()

  for (a in gena.constants()) {
    for (b in genb.constants()) {
      for (c in genc.constants()) {
        for (d in gend.constants()) {
          for (e in gene.constants()) {
            for (f in genf.constants()) {
              context.inc()
              val passed = try {
                context.fn(a, b, c, d, e, f)
                true
              } catch (e: AssertionError) {
                false
              } catch (e: Exception) {
                throw e
              }
              if (passed)
                throw Failures.failure("Property passed for\n$a\n$b\n$c\n$d\n$e\n$f\nafter ${context.attempts()} attempts")
            }
          }
        }
      }
    }
  }
  val avalues = gena.random().iterator()
  val bvalues = genb.random().iterator()
  val cvalues = genc.random().iterator()
  val dvalues = gend.random().iterator()
  val evalues = gene.random().iterator()
  val fvalues = genf.random().iterator()
  while (context.attempts() < iterations) {
    val a = avalues.next()
    val b = bvalues.next()
    val c = cvalues.next()
    val d = dvalues.next()
    val e = evalues.next()
    val f = fvalues.next()
  
    context.inc()
    val passed = try {
      context.fn(a, b, c, d, e, f)
      true
    } catch (e: AssertionError) {
      false
    } catch (e: Exception) {
      throw e
    }
    if (passed)
      throw Failures.failure("Property passed for\n$a\n$b\n$c\n$d\n$e\n$f\nafter ${context.attempts()} attempts")
  }
  outputClassifications(context)
}
