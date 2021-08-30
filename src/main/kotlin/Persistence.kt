interface Persistence {

    fun saveLink(link: String): Unit
}

class StandardOutPersistence : Persistence {
    override fun saveLink(link: String) {
    }

}