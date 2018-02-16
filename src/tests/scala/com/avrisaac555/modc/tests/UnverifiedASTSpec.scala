package com.avrisaac555.modc.tests

import com.avrisaac555.modc.ast.{Expression, Statement, UnverifiedAST}
import com.avrisaac555.modc._
import org.scalatest.{FlatSpec, Matchers}

class UnverifiedASTSpec extends FlatSpec with Matchers {
    "parserInner" should "return a statement and Nil" in {
        val (aTree, aExcess) = UnverifiedAST.parseInner(
            List(KEY_RETURN(None), LIT_INT(Some("11223344")))
        )

        aTree should be(Statement(List(KEY_RETURN(None)), List(Expression(Some(LIT_INT(Some("11223344")))))))
        aExcess should be(Nil)
    }
}
