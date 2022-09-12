open class MobileBanking(nama: String, saldo: Long,noRekening: String ) : DigitalPayment(nama, saldo) {
    var noRekening: String = noRekening
    var checkFee: Boolean = false
    var feeAntarBank: Long = 6000
    get() {
        return field
    }set(value) {
        field = value
    }
    override fun transfer(dp: DigitalPayment, nominal: Long) {

        if (nominal<0){
            println("Nominal yang anda input tidak valid!");
        } else {
            if (this.checkFee == true) {

                if ((nominal+this.feeAntarBank) > this.saldo) {
                    println("Transfer gagal! Saldo Anda tidak mencukupi.")
                } else {
                    saldo = saldo - (nominal + this.feeAntarBank)
                    dp.saldo = dp.saldo + nominal ;
                    this.printBuktiTransfer(dp, nominal);
                }
            } else {
                if (nominal > this.saldo) {
                    println("Transfer gagal! Saldo Anda tidak mencukupi.")
                } else {
                    println(checkFee)
                    saldo = saldo - (nominal);
                    dp.saldo = dp.saldo + nominal
                    this.printBuktiTransfer(dp, nominal)
                }
            }

        }
    }

}