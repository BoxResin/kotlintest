package com.sksamuel.kotlintest.specs.behavior

import io.kotlintest.specs.BehaviorSpec

class BehaviorSpecParenthesisTest : BehaviorSpec() {
  init {
    given("a sheet of string cells 4x4") {
      `when`("get existing cell by reference (like A1 or B2)") {
        then("should contain its value") {
          // noop
        }
        then("should set the datatype for the value") {
          // noop
        }
      }
      `when`("adding a new cell") {
        then("the sheet should enlarge") {
          // noop
        }
      }
    }
  }
}