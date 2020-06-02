package bank

class accountBank{
  var accountNumber:Int=0
  protected var accountType : String =""
  private var accountOwner: String = ""
  private var accountBalance: Float = 0f
  private var accountStatus: Boolean = false

  def accountBank(): Unit ={
    this.setAccountStatus(false)
    this.setAccountBalance(0f)
  }

  final case class InvalidOperation(private val message: String = "Invalid Operation, try again", private val cause: Throwable = None.orNull) extends Exception(message, cause)

  def accountStatement(): Unit ={
    if (this.getAccountStatus() == true){
      println("Account: "+this.getAccountNumber())
      println("Owner: "+this.getAccountOwner())
      println("Account Balance: \n $"+this.getAccountBalance())
      if (this.getAccountStatus()==true){
        println("Account status: Enabled")
        }
      else{
        println("Account status: Disabled")
       }
    }else{
      println("Account statement unavailable, account disabled")
    }
  }

  private def setAccountNumber(accountNumber:Int): Unit ={
    this.accountNumber = accountNumber
  }

  def getAccountNumber():Int={
    return this.accountNumber
  }

  private def setAccountOwner(accountOwner: String): Unit ={
    this.accountOwner = accountOwner
  }

  def getAccountOwner():String={
    return this.accountOwner
  }

  private def setAccountBalance(accountBalance:Float): Unit ={
    this.accountBalance = accountBalance
  }

  def getAccountBalance():Float={
    return this.accountBalance
  }

  private def setAccountStatus(accountStatus:Boolean): Unit ={
    this.accountStatus = accountStatus
  }

  def getAccountStatus():Boolean={
    return this.accountStatus
  }

  private def setCurrentAccount(): Unit ={
    this.getAccountOwner()
    this.getAccountNumber()
    this.getAccountBalance()
    this.getAccountStatus()
  }

  private def setSavingsAccount(): Unit ={
    this.getAccountOwner()
    this.getAccountNumber()
    this.getAccountBalance()
    this.getAccountStatus()
  }

  private def setAccountType(accountType:String): Unit ={
    if (accountType == "ca"){
      this.setCurrentAccount()
      }
    else if (accountType =="sa"){
      this.setSavingsAccount()
      }
    else{
      println("Invalid account type, please try again")

      }
    }

  def getAccountType():String={
    return this.accountType
  }

  def openAccount(nameOwner: String, accountNumber:Int,accountType:String): Unit = {

   if (accountType == "ca"){
      this.setAccountStatus(true)
      this.setAccountType(accountType)
      this.setAccountOwner(nameOwner)
      this.setAccountNumber(accountNumber)
      this.setAccountBalance(50f)
      println("Account CA opened ")}

    else if(accountType == "sa") {
      this.setAccountStatus(true)
      this.setAccountType(accountType)
      this.setAccountOwner(nameOwner)
      this.setAccountNumber(accountNumber)
      this.setAccountBalance(120f)
      println("Account SA opened ")
    }
   else if(accountType!="ca"||accountType != "sa"){
      try{
        val correctType = readLine("Account type invalid, insert CA or SA only:\n").toLowerCase()
        if (correctType == "ca"){
          this.setAccountStatus(true)
          this.setAccountType(accountType)
          this.setAccountOwner(nameOwner)
          this.setAccountNumber(accountNumber)
          this.setAccountBalance(50f)
          println("Account CA opened")
        }else if (correctType == "sa"){
          this.setAccountStatus(true)
          this.setAccountType(accountType)
          this.setAccountOwner(nameOwner)
          this.setAccountNumber(accountNumber)
          this.setAccountBalance(120f)
          println("Account SA opened")
        }else{ throw InvalidOperation(message = "Account type invalid, please try again")}
      }catch{
        case c:InvalidOperation => c.printStackTrace()
          openAccount(nameOwner,accountNumber,accountType)
      }
    }
  }

  def closeAccount(close:Boolean): Unit ={
    if(this.getAccountBalance()> 0.0f){
      try {
        println("Invalid operation, acount have money")

      val cashOut =readLine("Do you want to cash out? Yes/No\n").toLowerCase()

        if (cashOut =="yes"){this.cashPull(this.getAccountBalance())
          this.setAccountStatus(false)
          println("Account closed successful")}

        else if (cashOut =="no") {println("Operantion canceled")}

        else {throw InvalidOperation()}
      }
      catch {
        case c: InvalidOperation => c.printStackTrace()
          closeAccount(true)
      }
    }
    else if (this.getAccountBalance()<0.0f){
      try {
        println("Invalid operation, acount have pendencies")

      val viewCash = readLine("Do you want to see account balance?\n").toLowerCase()

      if (viewCash =="yes"){ println("Account balance:" +this.getAccountBalance())}

      else if (viewCash =="no") {println("Operantion canceled")}

      else {throw InvalidOperation(message = "Insert Yes/No")}
    }
      catch {
        case c: InvalidOperation => c.printStackTrace()
          closeAccount(true)
      }
    }
    else{
      this.setAccountStatus(false)
      println("Account closed succesful")
    }
  }

  def cashDeposit(value:Float): Unit ={
    if (this.getAccountStatus() == false){
      println("Invalid operation, account disabled")
    }else{
      this.setAccountBalance(getAccountBalance()+value)
      println("Deposit whit value $"+value+" successful")
    }
  }

  def cashPull (value:Float): Unit ={
    if(value > this.getAccountBalance()){
      try {
        println("Money insufficient in account")

        val tryAgain = readLine("Do you want to see account balance and try again?\n").toLowerCase

        if (tryAgain =="yes"){
          println("Account balance "+this.getAccountBalance())
          println("Insert new value:")
          val newValue = readFloat()
          this.cashPull(newValue)
        }

        else if (tryAgain =="no") {println("Operantion canceled")}

        else {throw InvalidOperation()}
      }
      catch {
        case c: InvalidOperation => c.printStackTrace()
          cashPull(value)
      }
    }
    else if(this.getAccountStatus()== false){
      println("Invalid operation, account disabled")
    }
    else if(this.getAccountBalance() >= value){
      this.setAccountBalance(this.getAccountBalance() - value)
      println("Value of $"+value+" withdrawn")
      println("New account balance is "+getAccountBalance())
    }
  }

  def monthlyPayment(): Unit ={
    if(this.getAccountStatus() == true){
      if(this.getAccountType() =="ca"){
        this.setAccountBalance(getAccountBalance()-12f)
      }
      else{
        this.setAccountBalance(getAccountBalance()-20f)
      }
    }else{
      println("Invalid operation, account closed")
    }
  }

}