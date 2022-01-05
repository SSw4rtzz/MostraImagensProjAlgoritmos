package projfinal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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

    private int level = 1;
    private boolean repetido;
    private Element temp;

    public EDACrawler() throws IOException {
    }
    
    public void linkRepetido(Payload payload, String href, int n){
        
    }

    public Payload process(String url, int level) throws IOException {
        Payload payload = new Payload();
        String[] urlSepar = url.split("/");
        String urlInit= urlSepar[2]; //URL HOST, ou seja www.google.com sem mais nada por exemplo
        
        System.out.println("Link: " + urlInit + "\nProfundidade: " + level); 

        //if (level<=this.level) System.out.println("Tetse");;
        
        if (!url.endsWith("/")) {
            url += "/";
        }

        Document doc = Jsoup.connect(url).get();

        Elements links = doc.select("a");
        Iterator<Element> aux = links.iterator();

        while (aux.hasNext()) {
            repetido = false;
            String href = aux.next().attr("href");
            if (href.length() > 1 && href.contains(urlInit)) {
                String[] corte = href.split("/"); //Corta o link em cada barra existente
                String profundidade = url;
                try{
                    for(int i=0; i<level;i++){
                        profundidade = profundidade + corte[i+2] + "/"; //adiciona niveis ao link
                    }
                    if(profundidade.length() > 1 && !payload.links.contains(profundidade)){
                        payload.links.add(profundidade);
                    }
                }catch(IndexOutOfBoundsException e){
                    
                }
            }else{
                for(int i = 0; i<payload.links.size(); i++){
                    if(href.equals(payload.links.get(i)) && href.contains(urlInit)){
                        repetido = true;
                        System.out.println(payload.links.get(i));
                    }
                }
                linkRepetido(payload, href, 1);
            }
                //process(href, level+1);
                //System.out.println("Teste - " + href + "|");
        }
        
            
        Elements imgs = doc.select("img");
        aux = imgs.iterator();

        while (aux.hasNext()) {
            //temp = aux.next();
            
            String src = aux.next().attr("abs:src");
            if (src.length() > 1 /*&& payload.imgs.isEmpty()*/) {
                payload.imgs.add(src);
            } else {
                for(int i = 0; i<payload.imgs.size();i++){
                    if(src.equals(payload.imgs.get(i))){
                        repetido=true;
                    }
                }
                linkRepetido(payload, src, 2);
            }
        }

        payload.html = doc.html();

        return payload;
    }

}
