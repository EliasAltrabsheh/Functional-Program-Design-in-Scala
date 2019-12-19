import Week2.Pouring

object test
{
  val problem = new Pouring(Vector(4,9))
}


test.problem
test.problem.moves
test.problem.pathSets.take(3).toList
test.problem.solution(20)