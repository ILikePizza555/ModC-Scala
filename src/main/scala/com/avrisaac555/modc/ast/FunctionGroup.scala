package com.avrisaac555.modc.ast

import com.avrisaac555.modc._

/**
  * A FunctionGroup is a single AST node that contains Tokens as children.
  * @param name The name of the function
  * @param tokens
  */
sealed class FunctionGroup(name: String, tokens: List[Token])

object FunctionGroup {
    def fromTokens(list: List[Token]): (FunctionGroup, List[Token]) = list match {
        case KEY_INT(_) :: IDENTIFIER(Some(s)) :: OPEN_PAREN(_) :: CLOSE_PAREN(_) :: OPEN_BRACE(_) :: xs =>
            def switch(l: List[Token]): (List[Token], List[Token]) = {
                val (a, b) = l span (t => !t.isInstanceOf[OPEN_PAREN] & !t.isInstanceOf[CLOSE_PAREN])

                b match {
                    case CLOSE_PAREN(_)::bs => (a, bs)
                    case OPEN_PAREN(_)::bs =>
                        val (c, d) = switch(bs)
                        (a ++ c, d)
                }
            }

            val (group, ex) = switch(xs)
            (new FunctionGroup(s, group), ex)
    }
}