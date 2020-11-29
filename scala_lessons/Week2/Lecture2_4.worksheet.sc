/*
You saw that all elements of a stream except the first one are
computed only when they are needed to produce a result.
This opens up the possibility to define infinite streams!
For instance, here is the stream of all integers starting from a given
number:
def from(n: Int): Stream[Int] = n #:: from(n+1)
The stream of all natural numbers:


 */

def from(n: Int): Stream[Int] = n #:: from(n+1)

// stream of all natural numbers

val nats = from(0)
// stream of all natural numbers multiple of 4s
val m4s = nats map(_*4)

(m4s take 1000).toList

def sieve(s: Stream[Int]): Stream[Int] =
  s.head #:: sieve(s.tail filter (_ % s.head != 0))

// List of all prime numbers
val primes = sieve(from(2))
primes.take(100).toList

//list of sqrt

def sqrtStream(x: Double): Stream[Double] = {
  def improve(guess: Double) = (guess + x / guess) / 2
  lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
  guesses
}


def isGoodEnough(guess: Double, x: Double) =
  math.abs((guess * guess - x) / x) < 0.0001
sqrtStream(4) filter (isGoodEnough(_, 4))


