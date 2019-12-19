/*
The proposed implementation suffers from a serious potential
performance problem: If tail is called several times, the
corresponding stream will be recomputed each time.
This problem can be avoided by storing the result of the first
evaluation of tail and re-using the stored result instead of
recomputing tail.
This optimization is sound, since in a purely functional language an
expression produces the same result each time it is evaluated.
We call this scheme lazy evaluation (as opposed to by-name
evaluation in the case where everything is recomputed, and strict
evaluation for normal parameters and val definitions.)

Haskell is a functional programming language that uses lazy
evaluation by default.
Scala uses strict evaluation by default, but allows lazy evaluation of
value definitions with the lazy val form:
lazy val x = expr

 */

def expr = {
  val x = { print("x"); 1 }
  lazy val y = { print("y"); 2 }
  def z = { print("z"); 3 }
  z + y + x + z + y + x
}
expr


// example in action
def streamRange(lo: Int, hi: Int): Stream[Int] =
  if (lo >= hi) Stream.empty
  else Stream.cons(lo, streamRange(lo + 1, hi))

val xs = Stream.cons(1, Stream.cons(2, Stream.empty))




(streamRange(1000, 10000) filter isPrime) apply 1
 (if (1000 >= 10000) empty // by expanding streamRange
else cons(1000, streamRange(1000 + 1, 10000))
  .filter(isPrime).apply(1)
  --> cons(1000, streamRange(1000 + 1, 10000)) // by evaluating if
  .filter(isPrime).apply(1)
