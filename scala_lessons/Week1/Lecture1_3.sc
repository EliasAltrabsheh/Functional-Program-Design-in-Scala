
//suppose we have a data base of books,
// which is represented as a list of books. Each book we model as a case class with a title, which is a string, and the authors,
// which would be a list of strings.


// ******  OUR mini DB *****///

case class Book(title: String,authors: List[String])

val books: List[Book] = List(
  Book(title = "Structure and Interpretation of Computer Programs", authors = List("Abelson, Harald", "Sussman, Gerald J.")),
  Book(title = "Introduction to Functional Programming",
  authors = List("Bird, Richard", "Wadler, Phil")),
  Book(title = "Effective Java",
  authors = List("Bloch, Joshua")),
  Book(title = "Java Puzzlers",
  authors = List("Bloch, Joshua", "Gafter, Neal")),
  Book(title = "Programming in Scala",
  authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill"))
)

// Query to find title of books with author name is bird
// b <- books = this means B over range of books
// a <- b.authors  = This means a over range of b others

// it works and returns correct title
for(b <- books;a <- b.authors if a startsWith "Bird") yield b.title

// to find all books with word programming

for(b <- books if (b.title indexOf "Program" )>=0) yield b.title

// find name of all authers who have wriiten atleast two books in database

for {
  b1 <- books
  b2 <- books
  if b1.title < b2.title
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1

