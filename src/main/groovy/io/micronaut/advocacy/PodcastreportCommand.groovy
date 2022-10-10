package io.micronaut.advocacy

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import io.micronaut.configuration.picocli.PicocliRunner
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import groovy.xml.XmlSlurper

import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

/**
 * @see <a href="https://groovy-lang.org/processing-xml.html">Processing XML</a>
 */
@Command(name = 'podcastreport', description = '...',
        mixinStandardHelpOptions = true)
@CompileStatic
class PodcastreportCommand implements Runnable {

    @Option(names = ['-v', '--verbose'], description = '...')
    boolean verbose

    @Option(names = ['-u', '--url'], description = 'podcast url', required = true)
    String url

    @Option(names = ['-o', '--output'], required = true)
    File output

    static void main(String[] args) throws Exception {
        PicocliRunner.run(PodcastreportCommand.class, args)
    }

    @CompileDynamic
    void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        DateTimeFormatter rfc822DateFormat = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z");
        String text = new URL(url).text
        def rss = new XmlSlurper().parseText(text)
        List<Episode> episodeList = []
        rss.channel.item.each {
            TemporalAccessor date = rfc822DateFormat.parse(it.pubDate as String)
            episodeList << new Episode(title: it.title, author: it.author, publication: formatter.format(date))
        }
        String result = episodeList.sort({a, b -> b.publication <=> a.publication})
            .collect {it -> "${it.title},${it.author},${it.publication}"}.join("\n")

        output.text = result
    }
}
