/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projfinal;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EDACrawler {

    public EDACrawler() throws IOException {
    }

    public Payload process(String url, int nivel, boolean dominio) throws IOException {
        Payload payload = new Payload();
        if (!url.endsWith("/")) {
            url += "/";
        }
        Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
        Elements links = doc.select("a");
        Iterator<Element> aux = links.iterator();
        String[] urlCortado = url.split("/");
        

        if (dominio) {
            try {
                while (aux.hasNext()) {
                    String href = aux.next().attr("abs:href");
                    if (href.length() > 1 && !payload.links.contains(href) && !href.contains("#") && href.contains(urlCortado[2])) {
                        payload.links.add(href);
                        System.out.println(href);
                    }
                    if (nivel > 1 && href.contains(urlCortado[2])) {
                        System.out.println(urlCortado[2]);
                        payload.links.addAll(process(href, nivel - 1, dominio).links);
                        payload.imgs.addAll(process(href, nivel - 1, dominio).imgs);
                    }
                }
            } catch (org.jsoup.HttpStatusException | SocketTimeoutException | UnknownHostException | UnsupportedMimeTypeException e) {
            }

            Elements imgs = doc.select("img");
            aux = imgs.iterator();

            while (aux.hasNext()) {
                String src = aux.next().attr("abs:src");
                if (src.length() > 1 && !payload.imgs.contains(src)) {
                    System.out.println(src);
                    payload.imgs.add(src);
                }
            }
        } else {

            try {
                while (aux.hasNext()) {
                    String href = aux.next().attr("abs:href");
                    if (href.length() > 1 && !payload.links.contains(href) && !href.contains("#")) {
                        payload.links.add(href);
                        System.out.println(href);
                    }
                    if (nivel > 1) {
                        payload.links.addAll(process(href, nivel - 1, dominio).links);
                        payload.imgs.addAll(process(href, nivel - 1, dominio).imgs);
                    }
                }
            } catch (org.jsoup.HttpStatusException | SocketTimeoutException | UnknownHostException | UnsupportedMimeTypeException e) {
            }

            Elements imgs = doc.select("img");
            aux = imgs.iterator();

            while (aux.hasNext()) {
                String src = aux.next().attr("abs:src");
                if (src.length() > 1 && !payload.imgs.contains(src)) {
                    System.out.println(src);
                    payload.imgs.add(src);
                }
            }
        }

        payload.html = doc.html();
        return payload;
    }
}
