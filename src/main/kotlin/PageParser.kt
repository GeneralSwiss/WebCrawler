import org.jsoup.Jsoup
import org.jsoup.UnsupportedMimeTypeException
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.net.URI

interface PageParser {
    fun getLinks(url: String): List<String>
}

class JsoupParser : PageParser {

    private val USER_AGENT =
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1"
    override fun getLinks(url: String): List<String> {
        val uri = URI(url)
        val connection = Jsoup.connect(url).userAgent(USER_AGENT)
        val document: Document
        try {
            document = connection.get()
        } catch (e: Exception) {
            println("*******************")
            println("Problem with ${uri}")
            println("*******************")
            return emptyList();
        }
        if (connection.response().statusCode() == 200) {
//            println("We got some data at ${uri}.")
        }
        if (!connection.response().contentType()?.contains("text/html")!!) {
//            print("This is not HTML Data at ${uri}.")
            return emptyList()
        }
        val links: Elements = document.select("a[href]")
//        println("Found ${links.size} links on ${uri}")
        return links.map { it.absUrl("href") }.toList()
    }
}