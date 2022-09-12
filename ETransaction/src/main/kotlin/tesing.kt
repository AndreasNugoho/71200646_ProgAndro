class tesing(nama:String,nickname:String) {
    var nama : String = nama
    var nickname : String = nickname
        get() {
            return field
        }
        set(value) {
            field = value
        }
}
fun main(){
    var nama = tesing("david","kingdavid")
    //test get
    println(nama.nama)
    println(nama.nickname)
    //test set
    nama.nama = "Kunalso"
    println(nama.nama)
}
