/*
Case classes are Scala’s preferred way to define complex data

we start off by looking at a joson file (javascript object Notation

 */

abstract class JSON
case class JSeq (elems: List[JSON]) extends JSON
case class JObj (bindings: Map[String, JSON]) extends JSON
case class JNum (num: Double) extends JSON
case class JStr (str: String) extends JSON
case class JBool(b: Boolean) extends JSON
case object JNull extends JSON

def show(json: JSON): String = json match { // Para matching condition
  case JSeq(elems) =>
    "[" + (elems map show mkString ",") + "]" // if elements map and concatenate with sting
  case JObj(bindings) =>
    val assocs = bindings map { // map binding for pair using key => value
      case (key, value) => "\"" + key + "\" : " + show(value)
      // Function above hasn't got a type so we need to create one for it
    }
    "{" + (assocs mkString "," ) + "}"
  case JNum(num) => num.toString  // if you have number turn into sting (scala to sting function)
  case JStr(str) => "\"" + str + "\""
  case JBool(b) => b.toString
  case JNull => "null"
}

// Traits are used to share interfaces and fields between classes. They are
// similar to Java 8’s interfaces. Classes and objects
// can extend traits but traits cannot be instantiated and therefore have no parameters.

// function of two type parameters
// - and + parameter is variance of type parameter
//it tells you that type from subtyping
trait Function1[-A,+R] {
  def apply(x:A):R // takes an argument of type A and gives you type R
}

//trait Map[key,value] extends(key => value)

// seqences in scala are functions (unlike array in c++)
// we can use subclassing to define trairts such as
// trait seq[elem] extends (Int => Elem)
// this makes Us call index of sequences as
//Elems(i)  -- Magic see


//***** Partial matching ****///////
// lets try match a string to string using pattern matching

val f :String => String = {
  case "ping" => "Pong"
  case "yeah" => "budy"
}
f("ping")
f("yeah")
//f("no")

/// we can also use partial function to test this out that can healp us solve the error above




val f2: PartialFunction[String,String] = {
  case "ping" => "Pong"
  case "yeah" => "budy"
}


f2.isDefinedAt("ping")




// exercise using partial functions
val f3: PartialFunction[List[Int],String] = {
  case Nil => "one"
  case x::y::rest=> "two"
}

f3.isDefinedAt(List(1,2,3))


// exercise using partial functions will give you match error
val f4: PartialFunction[List[Int],String] = {
  case Nil => "one"
  case x::rest =>
    rest match {
      case Nil => "two"
    }
}

f4.isDefinedAt(List(1,2,3))








