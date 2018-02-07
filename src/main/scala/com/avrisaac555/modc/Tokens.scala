package com.avrisaac555.modc

import scala.collection.immutable.ListMap
import scala.util.matching.Regex

sealed abstract class Token(val value: Option[String])

case class OPEN_BRACE (override val value: Option[String] = None) extends Token(value)
case class CLOSE_BRACE(override val value: Option[String] = None) extends Token(value)
case class OPEN_PAREN (override val value: Option[String] = None) extends Token(value)
case class CLOSE_PAREN(override val value: Option[String] = None) extends Token(value)
case class LINE_END   (override val value: Option[String] = None) extends Token(value)
case class KEY_MODULE (override val value: Option[String] = None) extends Token(value)
case class KEY_INT    (override val value: Option[String] = None) extends Token(value)
case class KEY_RETURN (override val value: Option[String] = None) extends Token(value)
case class IDENTIFIER (override val value: Option[String]) extends Token(value)
case class LIT_INT    (override val value: Option[String]) extends Token(value)

object Token {
  val TokenMap: Map[Regex, Option[String] => Token] = ListMap(
    "\\{".r               -> OPEN_BRACE,
    "\\}".r               -> CLOSE_BRACE,
    "\\(".r               -> OPEN_PAREN,
    "\\)".r               -> CLOSE_PAREN,
    ";".r                 -> LINE_END,
    "module".r            -> KEY_MODULE,
    "int".r               -> KEY_INT,
    "return".r            -> KEY_RETURN,
    "([a-zA-Z]\\w*)".r    -> IDENTIFIER,
    "([0-9]+)".r          -> LIT_INT
  )

  def tokenizePartial(s: String): PartialFunction[(Regex, Option[String] => Token), (Token, String)] = {
    case (k, v) if k.findPrefixOf(s).nonEmpty => {
      val m = k.findPrefixMatchOf(s).get

      if (m.groupCount == 0) (v(None), s.substring(m.end).trim)
      else (v(Some(m group 1)), s.substring(m.end).trim)
    }
  }

  def tokenize(string: String): List[Token] = string match {
    case "" => Nil
    case s =>
      val (a, b) = TokenMap.collectFirst(tokenizePartial(s)).get
      a :: tokenize(b)
  }

}