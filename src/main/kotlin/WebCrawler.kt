import java.net.URI

class WebCrawler(val parser: PageParser, val persistence: Persistence) {

    private val linksToSearch: ArrayDeque<String> = ArrayDeque()
    private val visitedLinks: MutableSet<String> = mutableSetOf()
    private val visitedWebsites: MutableSet<String> = mutableSetOf()

    fun startCrawling(url: String) {
        linksToSearch.add(url)

        while (linksToSearch.isNotEmpty()) {
            val linkToSearch = linksToSearch.removeFirst()
            val links = parser.getLinks(linkToSearch)
            persistence.saveLink(linkToSearch)
            visitedLinks.add(linkToSearch)
            val host = URI(linkToSearch).host
            if (!visitedWebsites.contains(host)) {
                println("Visited a new Website: $host")
            }
            visitedWebsites.add(host)
            links.filter { !visitedLinks.contains(it) }.forEach { linksToSearch.add(it)}
        }
    }
}