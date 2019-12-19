import Week3.BankAccount

object account{
  println("Welcome to my Scala Bank")
  val x = new BankAccount
  val y = x
  //acct deposit(10)
}

account.x.deposit(100)
account.x.withdraw(20)
account.y.deposit(10)
account.y.withdraw(3)
//account.x.withdraw(90)
