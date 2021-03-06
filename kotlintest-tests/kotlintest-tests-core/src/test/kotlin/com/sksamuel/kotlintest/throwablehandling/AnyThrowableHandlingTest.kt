package com.sksamuel.kotlintest.throwablehandling

import io.kotlintest.matchers.boolean.shouldBeTrue
import io.kotlintest.matchers.types.shouldBeInstanceOf
import io.kotlintest.matchers.types.shouldBeSameInstanceAs
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotThrowAny
import io.kotlintest.shouldNotThrowAnyUnit
import io.kotlintest.shouldThrowAny
import io.kotlintest.shouldThrowAnyUnit
import io.kotlintest.specs.FreeSpec

class AnyThrowableHandlingTest : FreeSpec() {

  init {
    onShouldThrowAnyMatcher { shouldThrowAnyMatcher ->
      "Should throw any ($shouldThrowAnyMatcher)" - {
        "Should throw an exception" - {
          "When no exception is thrown in the code" {
            verifyThrowsNoExceptionError {
              shouldThrowAnyMatcher { /* No exception being thrown */ }
            }
          }
        }

        "Should return the thrown instance" - {
          "When an exception is thrown in the code" {
            val instanceToThrow = FooRuntimeException()

            verifyReturnsExactly(instanceToThrow) {
              shouldThrowAnyMatcher { throw instanceToThrow }
            }
          }
        }
      }
    }

    onShouldNotThrowAnyMatcher { shouldNotThrowAnyMatcher ->
      "Should not throw any($shouldNotThrowAnyMatcher)" - {
        "Should throw an exception" - {
          "When any exception is thrown in the code" {
            val exception = FooRuntimeException()

            verifyThrowsAssertionWrapping(exception) {
              shouldNotThrowAnyMatcher { throw exception }
            }
          }
        }

        "Should not throw an exception" - {
          "When no exception is thrown in the code" {
            verifyNoErrorIsThrown {
              shouldNotThrowAnyMatcher { /* Nothing thrown */ }
            }
          }
        }
      }
    }
  }

  private inline fun onShouldThrowAnyMatcher(func: (ShouldThrowAnyMatcher) -> Unit) {
    func(::shouldThrowAny)
    func(::shouldThrowAnyUnit)
  }

  private fun verifyThrowsNoExceptionError(block: () -> Unit) {
    val thrown = catchThrowable(block)

    thrown.shouldBeInstanceOf<AssertionError>()
    thrown!!.message shouldBe "Expected a throwable, but nothing was thrown."
  }

  private fun verifyReturnsExactly(thrownInstance: Throwable, block: () -> Any?) {
    val actualReturn = block()

    (actualReturn === thrownInstance).shouldBeTrue()
  }

  private inline fun onShouldNotThrowAnyMatcher(func: (ShouldNotThrowAnyMatcher) -> Unit) {
    func(::shouldNotThrowAny)
    func(::shouldNotThrowAnyUnit)
  }

  private fun verifyThrowsAssertionWrapping(throwable: Throwable, block: () -> Any?) {
    val thrownException = catchThrowable(block)

    thrownException.shouldBeInstanceOf<AssertionError>()
    thrownException!!.message shouldBe "No exception expected, but a ${throwable::class.simpleName} was thrown."
    thrownException.cause shouldBeSameInstanceAs throwable
  }

  private fun verifyNoErrorIsThrown(block: () -> Unit) {
    catchThrowable(block) shouldBe null
  }
}

private typealias ShouldThrowAnyMatcher = (() -> Unit) -> Throwable
private typealias ShouldNotThrowAnyMatcher = (() -> Unit) -> Unit
