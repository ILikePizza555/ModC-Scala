package com.avrisaac555.modc

sealed abstract class Token(val value: Option[String] = None)

case class OPEN_BRACE () extends Token()
case class CLOSE_BRACE() extends Token()
case class OPEN_PAREN () extends Token()
case class CLOSE_PAREN() extends Token()
case class LINE_END   () extends Token()
case class KEY_MODULE () extends Token()
case class KEY_INT    () extends Token()
case class KEY_RETURN () extends Token()
case class IDENTIFIER (override val value: String) extends Token(Some(value))
case class LIT_INT    (override val value: String) extends Token(Some(value))

object Token {
  /**
    * Maps a string containing a single token to a token object
    * @param string
    * @return
    */
  def TokenMap(string: String): Token = string match {
    
  }
}