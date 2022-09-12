open class Ovo(nama: String,saldo:Long,noHp:String): MobileWallet(nama,saldo,noHp) {
    var ovoFeeTransferBank:Long = 2000
    override fun transfer(dp: DigitalPayment, nominal: Long) {
        if(dp is Dana){
            println("proses transfer tidak valid")
        }else if(dp is Ovo){
            super.transfer(dp, nominal)
        }else{
            feeTransferBank = ovoFeeTransferBank
            super.transfer(dp, nominal)
        }
    }
}