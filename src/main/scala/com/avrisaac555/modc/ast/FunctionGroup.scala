package com.avrisaac555.modc.ast

import com.avrisaac555.modc._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * A FunctionGroup is a single AST node that contains Tokens as children.
  * @param name The name of the function
  * @param tokens
  */
sealed class FunctionGroup(val name: String, val tokens: List[Token])

object FunctionGroup {
    def loop(l: List[Token]): (List[Token], List[Token]) = {
        val stack = mutable.Stack[Token]()
        val rv = ArrayBuffer[Token]()

        for (i <- l.indices) {
            val n = l(i)

            n match {
                case _: OPEN_BRACE =>
                    rv.append(n)
                    stack.push(n)
                case _: CLOSE_BRACE if stack.isEmpty => return (rv.toList, l.drop(i + 1))
                case _: CLOSE_BRACE if stack.nonEmpty =>
                    rv.append(n)
                    stack.pop()
                case _ => rv.append(n)
            }
        }

        (rv.toList, Nil)
    }

    def fromTokens(list: List[Token]): (FunctionGroup, List[Token]) = list match {
        case KEY_INT(_) :: IDENTIFIER(Some(s)) :: OPEN_PAREN(_) :: CLOSE_PAREN(_) :: OPEN_BRACE(_) :: xs =>
            val (tokens, excess) = loop(xs)
            (new FunctionGroup(s, tokens), excess)
    }
}