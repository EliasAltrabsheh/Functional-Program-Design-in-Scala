//The syntax of for is closely related to the higher-order functions
//map, flatMap and filter.
//In reality, the Scala compiler expresses for-expressions in terms of
//map, flatMap and a lazy variant of filter.
/*

Here is the translation scheme used by the compiler (we limit
ourselves here to simple variables in generators)
1. A simple for-expression
for (x <- e1) yield e2
is translated to
e1.map(x => e2)

 */

/*
second exertions is rewritten as
for (x <- e1 if f; s) yield e2
where f is a filter and s is a (potentially empty) sequence of
generators and filters, is translated to
for (x <- e1.withFilter(x => f); s) yield e2


 */
def isPrime(i: Int): Boolean = {
  if (i <= 1)
    false
  else if (i == 2)
    true
  else
    !(2 to (i - 1)).exists(x => i % x == 0)
}

for {
  i <- 1 until (10)
  j <- 1 until (10)
  if isPrime(i + j)
} yield (i, j)
