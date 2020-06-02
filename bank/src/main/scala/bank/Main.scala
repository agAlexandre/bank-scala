package bank

object Main {
  def main(args: Array[String]): Unit = {
    var account = new accountBank
    account.openAccount("Alexandre",123,"ca")
    account.accountStatement()
  }
}
