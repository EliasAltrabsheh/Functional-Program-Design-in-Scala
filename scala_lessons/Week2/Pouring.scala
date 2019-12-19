package Week2


  // Pouring takes one entry per glass
  class Pouring(capacity: Vector[Int])
  {

    //States
    type State = Vector[Int]
    // initial state vector of all empty glasses
    // so we use a map that a function that takes a glass and retun zero
    val initialState = capacity map(x => 0)

    // Moves defined as a trait with case classes
    trait Move
    {
      def change(state: State):State
    }
    case class Empty(glass:Int) extends Move
    {
      def change(state: State) = state updated(glass,0)
    }
    case class Fill(glass: Int) extends Move
    {
      def change(state:State) = state updated(glass,capacity(glass))
    }
    case class Pour(from: Int,to:Int) extends Move
    {
      def change(state:State) =
      {
        // amount is calculating gives me the amount smaller from the state of glass to free space left
        // this was it calculates the amount being poured
        val amount = state(from) min (capacity(to) - state(to))
        state updated(from,state(from) - amount) updated(to,state(to)+amount)
      }
    }



    val  glasses = 0 until capacity.length

    val moves =
      (for(g <- glasses) yield Empty(g))++
      (for(g <- glasses) yield Fill(g))++
      (for(from <- glasses; to <-glasses if from != to) yield Pour(from,to))

// Path
// constructing a class to track the path of moving
//List[Move] is defined as history is taking in reverse which makes it easier to extend Moves **
  class Path(history:List[Move])
{
  def endState:State = (history foldRight initialState)(_ change _)
  def extend(move:Move) = new Path(move ::history)

  override def toString = (history.reverse mkString " ")  + "--> " + endState

 }

    val initialpath = new Path(Nil)
    // new definition for from but new we defined it as from path(which is a set)
    // the stream is defines as a set of set of paths
    def from(paths: Set[Path],explored: Set[State]): Stream[Set[Path]] =
      if (paths.isEmpty) Stream.empty
      else
    {
      val more = for {
        path <- paths
        next <- moves map path.extend
        if !(explored contains next.endState)
      } yield next
      paths #:: from(more,explored ++(more map (_.endState)))
    }
    val pathSets = from(Set(initialpath),Set(initialState))

    def solution(target :Int):Stream[Path] =
      for{
        pathSet <- pathSets
        path <- pathSet
        if path.endState contains target
      }yield path
}




/* solution for right fold breakdown
** we identified that  we could use a right fold making it much elegant solution to have
def endState:State = trackState(history)
 private trackState(xs:List[Move]):State = xs match {
    case Nil => initialState
    case move :: xs1 => move change trackState(xs1)

  } // track state is an auxiliry function that we define a paramatch in it
 */