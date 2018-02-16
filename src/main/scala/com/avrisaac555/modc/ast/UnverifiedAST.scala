package com.avrisaac555.modc.ast

import com.avrisaac555.modc._

/**
  * An "AST" that's not really an AST and garuntees no type saftey.
  * Consider this to be the first stage of parsing.
  */
sealed class UnverifiedAST {

}

/**
  * An abstract representation of an expression
  * @param v A list of the tokens (if any) that make up the expression
  */
case class Expression(v: Option[Token] = None) extends UnverifiedAST

/**
  * An abstract representation of a statement
  * @param v A list of tokens (if any) that describe the statement
  * @param c The children of the Statement AST node.
  */
case class Statement(v: List[Token], c: List[UnverifiedAST]) extends UnverifiedAST

/**
  * An abstract representation of a function.
  * @param id The identifier token of the function
  * @param c The children of the function AST node
  */
case class Function(id: IDENTIFIER, c: List[UnverifiedAST]) extends UnverifiedAST

/**
  * An abstract representation of a module
  * @param id The identifier token of the module
  */
case class Module(id: IDENTIFIER) extends UnverifiedAST

/**
  * The root node of the AST
  * @param c The rest of the AST
  */
case class Program(c: List[UnverifiedAST]) extends UnverifiedAST

object UnverifiedAST {
    def parseInner(tokens: List[Token]): (UnverifiedAST, List[Token]) = tokens match {
        case (x:KEY_RETURN)::(a:LIT_INT)::xs =>
            (Statement.curried apply x::Nil apply List(Expression(Some(a))), xs)
        case LIT_INT(_)::(id: IDENTIFIER)::OPEN_PAREN(_)::CLOSE_PAREN(_)::OPEN_BRACE(_)::xs =>
            // TODO: This lambda will eventually be a bug
            val (ct, et) = xs.span(t => !t.isInstanceOf[CLOSE_BRACE])
            val children = Utils.mapReduce(parseInner, ct)

            (Function(id, children), et)
        case KEY_MODULE(_)::(id: IDENTIFIER)::xs =>
            (Module(id), xs)
        case x::xs => (Expression(Some(x)), xs)
    }

    def parse(tokens: List[Token]) = Program apply Utils.mapReduce(parseInner, tokens)
}