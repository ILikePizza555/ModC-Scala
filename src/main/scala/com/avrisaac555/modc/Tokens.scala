package com.avrisaac555.modc

import java.util.regex.Pattern

sealed abstract class Token(val pattern: Pattern, val str: String)

case class OPEN_BRACE (override val str: String) extends Token(new Pattern("{"), str)
case class CLOSE_BRACE(override val str: String) extends Token(new Pattern("}"), str)
case class OPEN_PAREN (override val str: String) extends Token(new Pattern("\\("), str)
case class CLOSE_PAREN(override val str: String) extends Token(new Pattern("\\)"), str)
case class LINE_END   (override val str: String) extends Token(new Pattern(";"), str)
case class KEY_MODULE (override val str: String) extends Token(new Pattern("module"), str)
case class KEY_INT    (override val str: String) extends Token(new Pattern("int"), str)
case class KEY_RETURN (override val str: String) extends Token(new Pattern("return"), str)
case class IDENTIFIER (override val str: String) extends Token(new Pattern("[a-zA-Z]\\w*"), str)
case class LIT_INT    (override val str: String) extends Token(new Pattern("[0-9]+"), str)