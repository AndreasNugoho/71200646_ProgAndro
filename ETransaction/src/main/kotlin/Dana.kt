open class Dana(nama: String, saldo: Long,noHp:String): MobileWallet(nama,saldo,noHp) {
    var danaFeeTransfer:Long = 1000
    override fun transfer(dp: DigitalPayment, nominal: Long) {
        if(dp is Ovo){
            println("proses transfer tidak valid")
        }else if (dp is Dana){
            super.transfer(dp, nominal)
        }else{
            feeTransferBank = danaFeeTransfer
            super.transfer(dp, nominal)

        }
    }
}