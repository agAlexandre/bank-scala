package bank

object Main {
  def main(args: Array[String]): Unit = {
    var account = new accountBank
    account.openAccount("Alexandre",123,"ca")
    account.monthlyPayment()
    account.monthlyPayment()
    account.monthlyPayment()
    account.closeAccount(true)
    account.cashDeposit(10)
    account.closeAccount(true)
  }
}
