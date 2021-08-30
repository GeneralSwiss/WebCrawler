import org.koin.core.context.startKoin
import org.koin.dsl.module

val webCrawlerModule = module {
    single { StandardOutPersistence() as Persistence }
    single { JsoupParser() as PageParser }
    single { WebCrawler(get(), get()) }
}

fun main(vararg args: String) {
    startKoin {
        printLogger()
        modules(webCrawlerModule)
    }

    WebCrawlerService().startCrawling(args[0])
}
