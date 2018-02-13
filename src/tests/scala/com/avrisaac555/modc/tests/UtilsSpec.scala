package com.avrisaac555.modc.tests

import com.avrisaac555.modc.Utils.switchTake
import org.scalatest.{FlatSpec, Matchers}

class UtilsSpec extends FlatSpec with Matchers {
    def p1(s: String): Boolean = s != "{"
    def p2(s: String): Boolean = s != "}"

    "switchTake" should "return (list, Nil)" in {
        val t = "{ 0 0 0 0 0 }".split(" ").toList

        val actual = switchTake(t, p1, p2)
        actual should be (List("0", "0", "0", "0", "0"), Nil)
    }

    it should "return (List, Nil)" in {
        val t = "{ 1 { 2 } 3 { 4 } 5 }".split(" ").toList

        val actual = switchTake(t, p1, p2)
        actual should be (List("1", "{", "2", "}", "3", "{", "4", "}", "5"), Nil)
    }

    it should "return (List, List)" in {
        val t = "{1 { 2 3 } } 4 5".split(" ").toList

        val actual = switchTake(t, p1, p2)
        actual should be (List("1", "{", "2", "3", "}"), List("4", "5"))
    }
}
