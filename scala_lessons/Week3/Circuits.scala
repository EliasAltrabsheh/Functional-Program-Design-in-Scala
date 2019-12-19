package Week3
// abstract class used as the follwing:
/*
1)Abstract classes can have constructor parameters as well as type parameters. Traits can have only type parameters. There was some discussion that in future even traits can have constructor parameters
2)Abstract classes are fully interoperable with Java. You can call them from Java code without any wrappers. Traits are fully interoperable only if they do not contain any implementation code
3)A class can inherit from multiple traits but only one abstract class
 */
abstract class Circuits extends Gates{
  def halfadder(a:Wire,b:Wire,s:Wire,c:Wire): Unit =
  {
    val d,e = new Wire
    orGate(a,b,d)
    andGate(a,b,c)
    inverter(c,e)
    andGate(d,e,s)

  }

  def fullAdder(a:Wire,b:Wire,cin:Wire,sum:Wire,cout:Wire): Unit =
  {
    val s,c1,c2 = new Wire
    halfadder(a,cin,s,c1)
    halfadder(b,s,sum,c2)
    orGate(c1,c2,cout)
  }


}
