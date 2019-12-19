import Week4.{BankAccount, Signal}

object accounts{
  def consolidated(accts: List[BankAccount]):Signal[Int] =
    Signal(accts.map(_.balance().sum))

  val a,b = new BankAccount()
  val c = consolidated(List(a,b))
  c()
  a deposit 20
  c()
  b deposit 20

}