package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row) {
        //        println(s"Col is $col and row is $row ")
        print(pascal(col, row) + " ")
      }
      println()
    }

    println(balance("(:-)(())()()".toList))
    println(List(2,4,6,1,43,7,6).sorted(Ordering[Int].reverse))
    println(countChange(4, List(1, 2)))
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    //    println(s"c is $c and r is $r ")
    if (c == 0 || r == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceN(chars: List[Char], opens: Int): Boolean = {
      chars match {
        case Nil => opens == 0
        case _ => chars.head match {
          case '(' => balanceN(chars.tail, opens + 1)
          case ')' if opens > 0 => balanceN(chars.tail, opens - 1)
          case ')' => false
          case _ => balanceN(chars.tail, opens)
        }
      }
    }

    balanceN(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
      if (money == 0) 1;
      else if (money < 0 || coins.isEmpty) 0;
      else countChange(money - coins.head, coins) + countChange(money, coins.tail) 
  }
}
