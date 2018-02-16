package com.avrisaac555.modc.ast

import com.avrisaac555.modc.{IDENTIFIER, Token}

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
case class Expression(v: Some[List[Token]] = None()) extends UnverifiedAST

/**
  * An abstract representation of a statement
  * @param v A list of tokens (if any) that describe the statement
  * @param c The children of the Statement AST node.
  */
case class Statement(v: Some[List[Token]] = None(), c: List[UnverifiedAST])

/**
  * An abstract representation of a function.
  * @param id The identifier token of the function
  * @param c The children of the function AST node
  */
case class Function(id: IDENTIFIER, c: List[UnverifiedAST])

/**
  * An abstract representation of a module
  * @param id The identifier token of the module
  * @param c The children of the module AST node
  */
case class Module(id: IDENTIFIER, c: List[UnverifiedAST])

/**
  * The root node of the AST
  * @param c The rest of the AST
  */
case class Program(c: List[UnverifiedAST])

object UnverifiedAST {

}