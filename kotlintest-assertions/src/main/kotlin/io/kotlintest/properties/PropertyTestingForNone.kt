package io.kotlintest.properties

import io.kotlintest.Failures
import outputClassifications

inline fun <reified A> forNone(fn: PropertyContext.(a: A) -> Boolean) = forNone(1000, fn)
inline fun <reified A> forNone(iterations: Int, fn: PropertyContext.(a: A) -> Boolean) {
  forNone(iterations, Gen.default(), fn)
}

inline fun <A> forNone(gena: Gen<A>, fn: PropertyContext.(a: A) -> Boolean) = forNone(1000, gena, fn)
inline fun <A> Gen<A>.forNone(iterations: Int = 1000, fn: PropertyContext.(a0: A) -> Boolean) = forNone(iterations, this, fn)
inline fun <A> Gen<A>.forNone(iterations: Int = 1000, fn: PropertyContext.(a0: A, a1: A) -> Boolean) = forNone(iterations, this, this, fn)
inline fun <A> Gen<A>.forNone(iterations: Int = 1000, fn: PropertyContext.(a0: A, a1: A, a2: A) -> Boolean) = forNone(iterations, this, this, this, fn)
inline fun <A> Gen<A>.forNone(iterations: Int = 1000, fn: PropertyContext.(a0: A, a1: A, a2: A, a3: A) -> Boolean) = forNone(iterations, this, this, this, this, fn)
inline fun <A> Gen<A>.forNone(iterations: Int = 1000, fn: PropertyContext.(a0: A, a1: A, a2: A, a3: A, a4: A) -> Boolean) = forNone(iterations, this, this, this, this, this, fn)
inline fun <A> Gen<A>.forNone(iterations: Int = 1000, fn: PropertyContext.(a0: A, a1: A, a2: A, a3: A, a4: A, a5: A) -> Boolean) = forNone(iterations, this, this, this, this, this, this, fn)
inline fun <A> forNone(iterations: Int, gena: Gen<A>, fn: PropertyContext.(a: A) -> Boolean) {
  if (iterations <= 0) throw IllegalArgumentException("Iterations should be a positive number")
  val context = PropertyContext()
  for (a in gena.constants()) {
    context.inc()
    val passed = context.fn(a)
    if (passed) {
      throw Failures.failure("Property passed for\n$a\nafter ${context.attempts()} attempts")
    }
  }
  val avalues = gena.random().iterator()
  while (context.attempts() < iterations) {
    val a = avalues.next()
    context.inc()
    val passed = context.fn(a)
    if (passed) {
      throw Failures.failure("Property passed for\n$a\nafter ${context.attempts()} attempts")
    }
  }
  outputClassifications(context)
}

inline fun <reified A, reified B> forNone(fn: PropertyContext.(a: A, b: B) -> Boolean) {
  forNone(Gen.default(), Gen.default(), fn)
}

inline fun <reified A, reified B> forNone(iterations: Int, fn: PropertyContext.(a: A, b: B) -> Boolean) {
  forNone(iterations, Gen.default(), Gen.default(), fn)
}

inline fun <A, B> forNone(gena: Gen<A>, genb: Gen<B>, fn: PropertyContext.(a: A, b: B) -> Boolean) = forNone(1000, gena, genb, fn)

inline fun <A, B> forNone(iterations: Int, gena: Gen<A>, genb: Gen<B>, fn: PropertyContext.(a: A, b: B) -> Boolean) {
  val context = PropertyContext()
  for (a in gena.constants()) {
    for (b in genb.constants()) {
      context.inc()
      val passed = context.fn(a, b)
      if (passed) {
        throw Failures.failure("Property passed for\n$a\n$b\nafter ${context.attempts()} attempts")
      }
    }
  }
  val avalues = gena.random().iterator()
  val bvalues = genb.random().iterator()
  while (context.attempts() < iterations) {
    val a = avalues.next()
    val b = bvalues.next()
    context.inc()
    val passed = context.fn(a, b)
    if (passed) {
      throw Failures.failure("Property passed for\n$a\n$b\nafter ${context.attempts()} attempts")
    }
  }
  outputClassifications(context)
}

inline fun <reified A, reified B, reified C> forNone(fn: PropertyContext.(a: A, b: B, c: C) -> Boolean) {
  forNone(1000, fn)
}

inline fun <reified A, reified B, reified C> forNone(iterations: Int, fn: PropertyContext.(a: A, b: B, c: C) -> Boolean) {
  forNone(iterations, Gen.default(), Gen.default(), Gen.default(), fn)
}

inline fun <A, B, C> forNone(gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, fn: PropertyContext.(a: A, b: B, c: C) -> Boolean) =
  forNone(1000, gena, genb, genc, fn)

inline fun <A, B, C> forNone(iterations: Int, gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, fn: PropertyContext.(a: A, b: B, c: C) -> Boolean) {
  if (iterations <= 0) throw IllegalArgumentException("Iterations should be a positive number")
  val context = PropertyContext()
  for (a in gena.constants()) {
    for (b in genb.constants()) {
      for (c in genc.constants()) {
        context.inc()
        val passed = context.fn(a, b, c)
        if (passed) {
          throw Failures.failure("Property passed for\n$a\n$b\n$c\nafter ${context.attempts()} attempts")
        }
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
    val passed = context.fn(a, b, c)
    if (passed) {
      throw Failures.failure("Property passed for\n$a\n$b\n$c\nafter ${context.attempts()} attempts")
    }
  }
  outputClassifications(context)
}

inline fun <reified A, reified B, reified C, reified D> forNone(fn: PropertyContext.(a: A, b: B, c: C, D) -> Boolean) {
  forNone(1000, fn)
}

inline fun <reified A, reified B, reified C, reified D> forNone(iterations: Int, fn: PropertyContext.(a: A, b: B, c: C, D) -> Boolean) {
  forNone(iterations, Gen.default(), Gen.default(), Gen.default(), Gen.default(), fn)
}

inline fun <A, B, C, D> forNone(gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, gend: Gen<D>, fn: PropertyContext.(a: A, b: B, c: C, d: D) -> Boolean) =
  forNone(1000, gena, genb, genc, gend, fn)

inline fun <A, B, C, D> forNone(iterations: Int, gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, gend: Gen<D>, fn: PropertyContext.(a: A, b: B, c: C, d: D) -> Boolean) {
  val context = PropertyContext()
  for (a in gena.constants()) {
    for (b in genb.constants()) {
      for (c in genc.constants()) {
        for (d in gend.constants()) {
          context.inc()
          val passed = context.fn(a, b, c, d)
          if (passed) {
            throw Failures.failure("Property passed for\n$a\n$b\n$c\n$d\nafter ${context.attempts()} attempts")
          }
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
    val passed = context.fn(a, b, c, d)
    if (passed) {
      throw Failures.failure("Property passed for\n$a\n$b\n$c\n$d\nafter ${context.attempts()} attempts")
    }
  }
  outputClassifications(context)
}

inline fun <reified A, reified B, reified C, reified D, reified E> forNone(fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E) -> Boolean) =
  forNone(1000, fn)

inline fun <reified A, reified B, reified C, reified D, reified E> forNone(iterations: Int, fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E) -> Boolean) {
  forNone(iterations, Gen.default(), Gen.default(), Gen.default(), Gen.default(), Gen.default(), fn)
}

inline fun <A, B, C, D, E> forNone(gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, gend: Gen<D>, gene: Gen<E>, fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E) -> Boolean) =
  forNone(1000, gena, genb, genc, gend, gene, fn)

inline fun <A, B, C, D, E> forNone(iterations: Int, gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, gend: Gen<D>, gene: Gen<E>, fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E) -> Boolean) {
  if (iterations <= 0) throw IllegalArgumentException("Iterations should be a positive number")
  val context = PropertyContext()
  
  for (a in gena.constants()) {
    for (b in genb.constants()) {
      for (c in genc.constants()) {
        for (d in gend.constants()) {
          for (e in gene.constants()) {
            context.inc()
            val passed = context.fn(a, b, c, d, e)
            if (passed) {
              throw Failures.failure("Property passed for\n$a\n$b\n$c\n$d\n$e\nafter ${context.attempts()} attempts")
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
  while (context.attempts() < iterations) {
    val a = avalues.next()
    val b = bvalues.next()
    val c = cvalues.next()
    val d = dvalues.next()
    val e = evalues.next()
    context.inc()
    val passed = context.fn(a, b, c, d, e)
    if (passed) {
      throw Failures.failure("Property passed for\n$a\n$b\n$c\n$d\n$e\nafter ${context.attempts()} attempts")
    }
  }
  outputClassifications(context)
}

inline fun <reified A, reified B, reified C, reified D, reified E, reified F> forNone(fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E, f: F) -> Boolean) {
  forNone(1000, fn)
}

inline fun <reified A, reified B, reified C, reified D, reified E, reified F> forNone(iterations: Int, fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E, f: F) -> Boolean) {
  forNone(iterations, Gen.default(), Gen.default(), Gen.default(), Gen.default(), Gen.default(), Gen.default(), fn)
}

inline fun <A, B, C, D, E, F> forNone(gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, gend: Gen<D>, gene: Gen<E>, genf: Gen<F>, fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E, f: F) -> Boolean) =
  forNone(1000, gena, genb, genc, gend, gene, genf, fn)

inline fun <A, B, C, D, E, F> forNone(iterations: Int, gena: Gen<A>, genb: Gen<B>, genc: Gen<C>, gend: Gen<D>, gene: Gen<E>, genf: Gen<F>, fn: PropertyContext.(a: A, b: B, c: C, d: D, e: E, f: F) -> Boolean) {
  if (iterations <= 0) throw IllegalArgumentException("Iterations should be a positive number")

  val context = PropertyContext()
  
  for (a in gena.constants()) {
    for (b in genb.constants()) {
      for (c in genc.constants()) {
        for (d in gend.constants()) {
          for (e in gene.constants()) {
            for (f in genf.constants()) {
              context.inc()
              val passed = context.fn(a, b, c, d, e, f)
              if (passed) {
                throw Failures.failure("Property passed for\n$a\n$b\n$c\n$d\n$e\n$f\nafter ${context.attempts()} attempts")
              }
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
    val passed = context.fn(a, b, c, d, e, f)
    if (passed) {
      throw Failures.failure("Property passed for\n$a\n$b\n$c\n$d\n$e\n$f\nafter ${context.attempts()} attempts")
    }
  }
  outputClassifications(context)
}
