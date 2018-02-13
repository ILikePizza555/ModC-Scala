package com.avrisaac555.modc.tests

import org.scalatest._
import com.avrisaac555.modc._
import org.scalatest.prop.TableDrivenPropertyChecks

import scala.collection.immutable.ListMap

class TokenizerSpec extends FlatSpec with Matchers with TableDrivenPropertyChecks {
    val TokenTable = Table(
        ("Token", "Type"),
        ("{", OPEN_BRACE()),
        ("}", CLOSE_BRACE()),
        ("(", OPEN_PAREN()),
        (")", CLOSE_PAREN()),
        (";", LINE_END()),
        ("module", KEY_MODULE()),
        ("int", KEY_INT()),
        ("return", KEY_RETURN()),
        ("identifier", IDENTIFIER(Some("identifier"))),
        ("555666777", LIT_INT(Some("555666777")))
    )

    "tokenizer" should "return valid tokens" in {
        forAll(TokenTable) { (token: String, expected: Token) =>
            val actual: List[Token] = Token.tokenize(token)

            actual should have length 1
            actual should contain(expected)
        }
    }
}
