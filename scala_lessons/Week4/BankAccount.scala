package Week4

class BankAccount {
    private var balance = Var(0)
    def currentBalance:Int = balance
    def deposit(amount: Int): Unit = {
      if (amount > 0) {
        val b  = balance()
        balance() = b + amount
       // publish()
      }
    }
    def withdraw(amount: Int): Int =
      if (0 < amount && amount <= balance()) {
        val b  = balance()
        balance() = b - amount
       // publish()
       // balance
      } else throw new Error("insufficient funds")
  }



