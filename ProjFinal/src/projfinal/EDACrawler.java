/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projfinal;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author pedrodias & paulosantos
 */
public class EDACrawler {

    public EDACrawler() throws IOException {
    }

    public Payload process(String url,int nivel) throws IOException {
        Payload payload = new Payload();
        //int auxNivel = nivel;
        //ArrayList<String> auxPayload = new ArrayList<String>();

        
        if (!url.endsWith("/")) {
            url += "/";
        }

        Document doc = Jsoup.connect(url).get();

        Elements links = doc.select("a");
        Iterator<Element> aux = links.iterator();
    try{
        while (aux.hasNext()) {
            
            String href = aux.next().attr("abs:href");
            
            if (href.length() > 1 && !payload.links.contains(href)) {
                payload.links.add(href);
                System.out.println(href);
            }
            if (nivel>1 && !href.contains("#")){
                    payload.links.addAll(process(href, nivel-1).links);
                    payload.imgs.addAll(process(href, nivel-1).imgs);
                    //for(int i=0;i<=auxpayload.links.size();i++){
                    //payload.links.add(auxpayload.links.get(i));
                }
                }
             }catch(org.jsoup.HttpStatusException | SocketTimeoutException | UnsupportedMimeTypeException e){}
       
        
        Elements imgs = doc.select("img");
        aux = imgs.iterator();

        while (aux.hasNext()) {
            String src = aux.next().attr("abs:src");
            if (src.length() > 1) {
                payload.imgs.add(src);
            }
        }

        payload.html = doc.html();

        //return payload;
        System.out.println("--------------------------------");
        System.out.println(payload.links.size());
        return payload;
    }

}
