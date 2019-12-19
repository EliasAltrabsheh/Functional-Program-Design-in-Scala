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

object generators {
  // take some instance for this as integers

  val integers = new Generator[Int] {
    val rand = new java.util.Random

    def generate = rand.nextInt ()
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


}

