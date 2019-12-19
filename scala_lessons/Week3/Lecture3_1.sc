import Week3.BankAccount

object account{
  println("Welcome to my Scala Bank")
  val acct = new BankAccount
  //acct deposit(10)
}

account.acct.deposit(100)
account.acct.withdraw(20)
account.acct.withdraw(90)
