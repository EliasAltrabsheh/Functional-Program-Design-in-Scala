/*

Data structures with map and flatMap seem to be quite common.
In fact there’s a name that describes this class of a data structures
together with some algebraic laws that they should have.
They are called monads.

A monad M is a parametric type M[T] with two operations, flatMap and
unit, that have to satisfy some laws.

trait M[T] {
def flatMap[U](f: T => M[U]): M[U]
}
def unit[T](x: T): M[T]
In the literature, flatMap is more commonly called bind.

▶ List is a monad with unit(x) = List(x)
▶ Set is monad with unit(x) = Set(x)
▶ Option is a monad with unit(x) = Some(x)
▶ Generator is a monad with unit(x) = single(x)
flatMap is an operation on each of these types, whereas unit in Scala is
different for each monad.



map can be defined for every monad as a combination of flatMap and unit:
m map f == m flatMap (x => unit(f(x)))
== m flatMap (f andThen unit)



To qualify as a monad, a type has to satisfy three laws:
1) Associativity:
m flatMap f flatMap g == m flatMap (x => f(x) flatMap g)
2) Left unit
unit(x) flatMap f == f(x)
3) Right unit
m flatMap unit == m

 */

