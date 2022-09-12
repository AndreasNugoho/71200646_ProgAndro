class BRImo(nama:String,saldo:Long,noRekening:String): MobileBanking(nama,saldo,noRekening) {
    override fun transfer(dp: DigitalPayment, nominal: Long) {
        if(dp is BNImo){
            dp.checkFee = true
        }
        super.transfer(dp, nominal)
    }
}