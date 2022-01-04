/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projfinal;

import java.io.IOException;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author pedrodias & paulosantos
 */
public class EDACrawler {
    Payload ini = new Payload();
    public EDACrawler(String url) throws IOException {
        //this(url, 1, true);
    }

    public Payload process(String url, int level) throws IOException {
        Payload payload = new Payload();
        
        if (!url.endsWith("/")) {
            url += "/";
        }

        Document doc = Jsoup.connect(url).get();

        Elements links = doc.select("a");
        Iterator<Element> aux = links.iterator();

        while (aux.hasNext()) {
            String href = aux.next().attr("abs:href");
            boolean inDomain = true;
            String domain = "ipt.pt";
            
            if (href.length() > 1 && !ini.links.contains(href) && ((inDomain && href.endsWith(domain)) || !inDomain && href.endsWith(domain))) {
                ini.links.add(href);
                process(href, level + 1);
            }
        }

        Elements imgs = doc.select("img");
        aux = imgs.iterator();

        while (aux.hasNext()) {
            String src = aux.next().attr("abs:src");
            if (src.length() > 1 && !ini.imgs.contains(src)) {
                ini.imgs.add(src);
            }
        }
        payload.html = doc.html();
        return payload;
    }

}
