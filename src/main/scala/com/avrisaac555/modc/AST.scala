package com.avrisaac555.modc

sealed class AST

case class IntExpression(value: Int) extends AST()

case class Statement(s: String, exp: IntExpression) extends AST()

case class Function(id: String, statements: List[Statement]) extends AST()

case class Module(id: String, functions: List[Function]) extends AST()

object AST {

}