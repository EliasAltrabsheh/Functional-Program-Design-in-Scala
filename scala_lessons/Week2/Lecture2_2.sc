
/*
We’ve seen a number of immutable collections that provide powerful
operations, in particular for combinatorial search.
For instance, to find the second prime number between 1000 and
  10000:
  ((1000 to 10000) filter isPrime)(1)
This is much shorter than the recursive alternative:
def secondPrime(from: Int, to: Int) = nthPrime(from, to, 2)
def nthPrime(from: Int, to: Int, n: Int): Int =
  if (from >= to) throw new Error("no prime")
else if (isPrime(from))
  if (n == 1) from else nthPrime(from + 1, to, n - 1)
else nthPrime(from + 1, to, n)
 */

/*
But from a standpoint of performance,
((1000 to 10000) filter isPrime)(1)
is pretty bad; it constructs all prime numbers between 1000 and
10000 in a list, but only ever looks at the first two elements of that
list.
Reducing the upper bound would speed things up, but risks that we
miss the second prime number all together.

 */

// to resolve above we can use streams !!!

//method 1  of streams

trait Stream[+A] extends Seq[A]
{
  def isEmpty: Boolean
  def head: A
  def tail: Stream[A]
}

//Concrete implementations of streams are defined in the Stream
//companion object. Here’s a first draft:
/*object Stream {
  def cons[T](hd: T, tl: => Stream[T]) = new Stream[T] {
    def isEmpty = false
    def head = hd
    def tail = tl
  }
  val empty = new Stream[Nothing] {
    def isEmpty = true
    def head = throw new NoSuchElementException("empty.head")
    def tail = throw new NoSuchElementException("empty.tail")
  }
}*/

val xs = Stream.cons(1, Stream.cons(2, Stream.empty))

// ? is there as the tail of collection hasnt been defined yet

//method 2 of streams
(1 to 1000).toStream

// stream range function is Broken out into this

def streamRange(lo: Int, hi: Int): Stream[Int] =
  print(lo + "")
  if (lo >= hi) Stream.empty
  else Stream.cons(lo, streamRange(lo + 1, hi))

// stream range function is applied to list

def listRange(lo: Int, hi: Int): List[Int] =
  if (lo >= hi) Nil
  else lo :: listRange(lo + 1, hi)



/// breakdown of implementation of strings

