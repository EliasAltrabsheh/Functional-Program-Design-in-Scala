/*Operations of sets, or databases, or options.
Question: Are for-expressions tied to collections?
Answer: No! All that is required is some interpretation of map, flatMap
and withFilter.


There are many domains outside collections that afford such an
  interpretation.
    Example: random value generators.
 */

// we start off by importing the random integer from java
import java.util.Random

val rand = new Random
rand.nextInt()

// we want a frame work to build this to genertare other types as ( boolean,pair,tuples,sets .trees)

// lets define generator trait general function

trait Generator[+T] {
  def generate: T
}

// take some instance for this as integers

val integers = new Generator[Int] {
val rand = new java.util.Random

  def generate = rand.nextInt()
}

// not so good way of doing bool

/*
val booleans = new Generator[Boolean] {
  def generate = integers.generate > 0
}
 */

// a better way to do this with out calling new each time is to stream line

val booleans = new Generator[Boolean] {
  def generate = integers.generate > 0
}

val pairs = new Generator[(Int, Int)] {
  def generate = (integers.generate, integers.generate)
}

//val boolean_1 = for(x <- generators.integers) yield x > 0

// def pairs_1[T,U](t:Generator[T],u:Generator[U]) =
//   for{
//  x <- t
//  y <- u
// } yield (x,y)

/*val booleans_2 = integers map(x => x > 0)
def pairs_2[T, U](t: Generator[T], u: Generator[U]) =
  t flatMap(x => u map (y => (x, y)))
 */

trait Generator2[+T] {
  self => // an alias for ”this”.
  def generate: T

  def map[S](f: T => S): Generator[S] =
    new Generator[S] {
      def generate = f(self.generate)
    }

  def flatMap[S](f: T => Generator[S]): Generator[S] =
    new Generator[S] {
      def generate = f(self.generate).generate
    }

}

val booleans2 = new Generator2[Boolean] {
  def generate = integers.generate > 0
}


// tree representation 

