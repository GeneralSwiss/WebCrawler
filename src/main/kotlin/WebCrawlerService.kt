import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WebCrawlerService : KoinComponent {

    val webCrawler by inject<WebCrawler>()

    fun startCrawling(url: String) {
        webCrawler.startCrawling(url)
    }
}