package com.avrisaac555.modc.tests

import com.avrisaac555.modc._
import com.avrisaac555.modc.ast.FunctionGroup
import org.scalatest.{FlatSpec, Matchers}

class FunctionGroupSpec extends FlatSpec with Matchers {
    "fromTokens" should "return (list, Nil)" in {
        val t = KEY_INT(None)::
                IDENTIFIER(Some("test"))::
                OPEN_PAREN(None)::
                CLOSE_PAREN(None)::
                OPEN_BRACE(None)::
                KEY_RETURN(None)::
                LIT_INT(Some("23457"))::
                IDENTIFIER(Some("TestID"))::
                CLOSE_BRACE(None)::
                Nil

        val expectedList = KEY_RETURN(None)::LIT_INT(Some("23457"))::IDENTIFIER(Some("TestID"))::Nil

        val actual = FunctionGroup.fromTokens(t)

        actual should be (expectedList, Nil)
    }

    it should "return (list, list)" in {
        val t = KEY_INT(None)::
                IDENTIFIER(Some("test"))::
                OPEN_PAREN(None)::
                CLOSE_PAREN(None)::
                OPEN_BRACE(None)::
                KEY_RETURN(None)::
                LIT_INT(Some("23457"))::
                IDENTIFIER(Some("TestID"))::
                CLOSE_BRACE(None)::
                IDENTIFIER(Some("This should be excess"))::
                Nil

        val expectedList = KEY_RETURN(None)::LIT_INT(Some("23457"))::IDENTIFIER(Some("TestID"))::Nil
        val excessList = IDENTIFIER(Some("This should be excess"))::Nil

        val actual = FunctionGroup.fromTokens(t)
        actual should be (expectedList, excessList)
    }
}
