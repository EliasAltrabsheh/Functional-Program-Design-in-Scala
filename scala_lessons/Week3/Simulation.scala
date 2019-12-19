package Week3

// abstract class used as the follwing:
/*
1)Abstract classes can have constructor parameters as well as type parameters. Traits can have only type parameters. There was some discussion that in future even traits can have constructor parameters
2)Abstract classes are fully interoperable with Java. You can call them from Java code without any wrappers. Traits are fully interoperable only if they do not contain any implementation code
3)A class can inherit from multiple traits but only one abstract class
 */
abstract class Simulation {

  type Action = () => Unit
  case class Event(time: Int, action: Action)

  private var curtime = 0
  def currentTime:Int = curtime



  private type Agenda = List[Event]
  private var agenda: Agenda = List()

  private def insert(ag:List[Event],item:Event):List[Event] = ag match {
    case first::rest if first.time <= item.time => first ::insert(rest,item)
    case _ => item ::ag
  }

  def afterDelay(delay: Int)(block: => Unit):Unit = {
    val item = Event(currentTime + delay,() => block)
    agenda = insert(agenda,item)
  }

def run(): Unit ={
  afterDelay(0){
    println("*** Simulation Started , Time = " + currentTime + "***")
    }
  loop()
  }

  private def loop():Unit = agenda match {
    case first :: rest =>
      agenda = rest
      curtime = first.time
      first.action()
      loop()
  }



}
