package com.avrisaac555.modc.tests

import org.scalatest._
import com.avrisaac555.modc._
import org.scalatest.prop.TableDrivenPropertyChecks

import scala.collection.immutable.ListMap

class TokenizerSpec extends FlatSpec with Matchers with TableDrivenPropertyChecks {
    val TokenTable = Table(
        ("Token", "Type"),
        ("{", OPEN_BRACE),
        ("}", CLOSE_BRACE),
        ("(", OPEN_PAREN),
        (")", CLOSE_PAREN),
        (";", LINE_END),
        ("module", KEY_MODULE),
        ("int", KEY_INT),
        ("return", KEY_RETURN),
        ("identifier", IDENTIFIER),
        ("555666777", LIT_INT)
    )

    forAll (TokenTable) { (token: String, expected: Option[String] => Token) =>
        "tokenize" should "return a single Token" in {

        }
    }
}
