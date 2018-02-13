package com.avrisaac555.modc

object Utils {
    def pAnd[A](p1: A => Boolean, p2: A => Boolean)(a: A): Boolean = p1(a) & p2(a)

    /**
      * Takes elements so long as p1 and p2 are true.
      *
      * If an element causes p1 to not be true, the loop will recurse.
      * If an element causes p2 to not be true, the loop will terminate.
      *
      * @param l
      * @param p1
      * @param p2
      * @tparam A
      * @return
      */
    implicit def switchTake[A](l: List[A], p1: A => Boolean, p2: A => Boolean): (List[A], List[A]) = {
        val (a, ax) = l span pAnd(p1, p2)

        ax match {
            case b::bx if !p2(b) => (a, bx)
            case b::bx if !p1(b) =>
                val (c, cx) = switchTake(bx, p1, p2)
                (a ++ c, cx)
        }

    }
}
