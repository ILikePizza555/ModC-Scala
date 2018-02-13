package com.avrisaac555.modc.ast

import com.avrisaac555.modc.Utils.switchTake
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
            val (group, ex) = switchTake(xs, t => !t.isInstanceOf[OPEN_PAREN], t => !t.isInstanceOf[CLOSE_PAREN])
            (new FunctionGroup(s, group), ex)
    }
}