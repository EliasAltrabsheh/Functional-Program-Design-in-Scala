package quickcheck

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    k <- arbitrary[Int]
    m <- oneOf(const(empty), genHeap)
  } yield insert(k, m)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("findMin") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("gen") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("findMin2") = forAll { (a: Int, b: Int) =>
    val h = insert(a, empty) // insert first element into empty heap
    val k = insert(b, h) // insert second element into heap
    findMin(k) == scala.math.min(a, b)
  }

  property("deleteMin") = forAll { (a: Int) =>
    val h = insert(a, empty)
    isEmpty(
      deleteMin(h)
    ) // check if delete min is empty for a head with one element
  }

  property("sorted") = forAll { (a: H) =>
    def isSorted(min: Int, h: H): Boolean =
      if (isEmpty(h))  // return true when true
        true
      else if (min > findMin(h)) 
        false
      else
        isSorted(findMin(h), deleteMin(h)) // recursive helper function 

    isSorted(findMin(a), deleteMin(a))

  }


  property("melding") = forAll { (a: H, b:H) =>
   val h = findMin(a)
   val k = findMin(b)
   findMin(meld(a,b)) == scala.math.min(h, k)
  }




}
