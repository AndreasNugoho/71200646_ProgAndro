open class MobileWallet(nama: String, saldo: Long,noHp:String): DigitalPayment(nama,saldo) {
    var feeTransferBank: Long = 0
    var noHp: String = noHp
        get() {
            return field
        }
        set(value) {
            field = value
        }

    override fun transfer(dp: DigitalPayment, nominal: Long) {
        if (nominal < 0) {
            println("Nominal yang anda input tidak valid!");
        } else if ((nominal + this.feeTransferBank) > saldo) {
            System.out.println("Transfer gagal! Saldo Anda tidak mencukupi.");
        } else {
            if (dp is BNImo || dp is BRImo) {
                if ((nominal + this.feeTransferBank) > this.saldo) {
                    System.out.println("Transfer gagal! Saldo Anda tidak mencukupi.");
                } else {
                    saldo = saldo - (nominal + this.feeTransferBank);
                    dp.saldo = dp.saldo + nominal;
                    this.printBuktiTransfer(dp, nominal);
                }
            } else {
                if (nominal > this.saldo) {
                    System.out.println("Transfer gagal! Saldo Anda tidak mencukupi.");
                } else {
                    saldo = saldo - nominal;
                    dp.saldo = dp.saldo + nominal;
                    this.printBuktiTransfer(dp, nominal);
                }
            }
        }
    }
}