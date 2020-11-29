/*

Structural induction is not limited to lists; it applies to any tree
structure.
The general induction principle is the following:
To prove a property P(t) for all trees t of a certain type,
▶ show that P(l) holds for all leaves l of a tree,
▶ for each type of internal node t with subtrees s1, ..., sn, show
that
P(s1) ∧ ... ∧ P(sn) implies P(t).


 */

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
}
object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = NonEmpty(x, Empty, Empty)
}

// Elements of tree has an invaraiants that:
// Left elements in subtree are smaller
// Right elements in subtree are larger

case class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  def incl(x: Int): IntSet =
    if (x < elem) NonEmpty(elem, left incl x, right)
    else if (x > elem) NonEmpty(elem, left, right incl x)
    else this
}

/*
What does it mean to prove the correctness of this implementation?
One way to define and show the correctness of an implementation
consists of proving the laws that it respects.
In the case of IntSet, we have the following three laws:
For any set s, and elements x and y:
Empty contains x = false  // empty contants X then  we get false
(s incl x) contains x = true // we have an
(s incl x) contains y = s contains y if x != y
(In fact, we can show that these laws completely characterize the
desired data type)
*/


// Proof of (s incl x) contains x = true ?

// define basecase = Empty
// (Empty incl x) contains x = NonEmpty(x,empty,empty) cointains x  = True//

// Soluatiion 2 = Indection step
