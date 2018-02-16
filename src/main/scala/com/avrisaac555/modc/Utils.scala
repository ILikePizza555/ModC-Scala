package com.avrisaac555.modc

object Utils {
    def mapReduce[A, B](f: (List[A] => (B, List[A])), init: List[A]): List[B] = init match {
        case Nil => Nil
        case _ =>
            val (b: B, al: List[A]) = f(init)
            b :: mapReduce(f, al)
    }
}
