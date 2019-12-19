// ********* Introduction *************//
// Scala has a rich hierarchy of clooection classes
// iterable is broken down intio seq,set and Map
// seq is broken down into index seq , Linear seq
// index seq is broken down into Vector, -- Array , -- strings
// Linear sequence is broken down into list

// All collections types above share comment set of methods such as :
//map,flatmap,filter,foldleft and foldright
// we are going to study in this lecture how the "collections" is implemented for List type

val x = List(1,2,3,4,5)

var temp = Map("A" -> 1, "B" -> 2,"C" -> 3, "D" -> 4)
temp("D")
/*
abstract class List[+T]
{
  def map[U](f: T => U): List[U] = this match {
    case x :: xs => f(x):: xs.map(f)
    case Nil => Nil
  }
}
*/
val name = Seq("Elias", "Altrabsheh")

name.flatMap(_.toUpperCase)

/*abstract class List[+T]
{
  def flatmap[U](f: T => U): List[U] = this match {
    case x :: xs => f(x):: xs.flatmap(f)
    case Nil => Nil
  }
}*/


name.filter(_.startsWith("A"))


/*abstract class List[+T]
{
  def filter[U](f: T => Boolean): List[U] = this match {
    case x :: xs =>
    if (f(x)) x::xs.filter(f) else sx.filter(f)
    case Nil => Nil
  }
}*/

// we need a taylor recursive implementation of this as the above is not







// lets have a look at the implementation











//lets assume we wanted to do the following WITHOUT a FOR Loop
// (1 untill n ) flatMap( i =>
//    (1 untill i) filter (j => isPrime(i+j))map
//      (j => (i,j))

// one can write
// for{
//  i < - 1  untill n
//  j <-  1 untill i
//   if( isPrime(i+j)
//   } yeild(i,j)

// example (1) on this is
//for(x <- e1) yield e2  is translated to e1.map(x=>e2)
