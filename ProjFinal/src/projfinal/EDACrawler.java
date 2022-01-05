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
    Payload payload = new Payload();

    public EDACrawler() throws IOException {
    }
    
    public void linkRepetido(Payload payload, String href, int n){
        
    }
    

    public Payload process(String url, int level) throws IOException {
        
        String[] urlSepar = url.split("/");
        String urlInit= urlSepar[2]; //URL HOST, ou seja www.google.com sem mais nada por exemplo
        

        //if (level<=this.level) System.out.println("Tetse");;
        
        if (!url.endsWith("/")) {
            url += "/";
        }

        Document doc = Jsoup.connect(url).get();

        Elements links = doc.select("a");
        Iterator<Element> aux = links.iterator();

        while (aux.hasNext()) {
            repetido = false;
            String href = aux.next().attr("abs:href");
            if (href.length() > 1) {
                String[] corte = href.split("/"); //Corta o link em cada barra existente
                String profundidade = corte[0] + "//" + corte[2] + "/"; //link padrão
                try{
                    for(int i=2; i<=level;i++){
                        profundidade +=  corte[i+1] + "/";
                    }
                        //Anti repetição
                    if(profundidade.length() > 1 && !payload.links.contains(profundidade)){
                        payload.links.add(profundidade);
                    }
                }catch(IndexOutOfBoundsException e){}
            }else{
                /*for(int i = 0; i<payload.links.size(); i++){
                    if(href.equals(payload.links.get(i)) && href.contains(urlInit)){
                        repetido = true;
                        System.out.println(payload.links.get(i));
                    }
                }
                linkRepetido(payload, href, 1);*/
            }
        }
        
            
        Elements imgs = doc.select("img");
        aux = imgs.iterator();

        while (aux.hasNext()) {
            //temp = aux.next();
            String src = aux.next().attr("abs:src");
            if (src.length() > 1) {
                String[] corte = src.split("/"); //Corta o link em cada barra existente
                String profundidade = corte[0] + "//" + corte[2] + "/"; //link padrão
                try{
                    for(int i=2; i<=level;i++){
                        profundidade += corte[i+1] + "/"; //adiciona niveis ao link
                    }
                    
                    if(corte[level+2].contains(".png") || corte[level+2].contains(".jpg") || corte[level+2].contains(".gif")){
                        profundidade += corte[level+2];
                        if(profundidade.length() > 1 && !payload.imgs.contains(profundidade)){
                            payload.imgs.add(profundidade);
                        }
                    }

                }catch(IndexOutOfBoundsException e){}
                
            } else {
                /*for(int i = 0; i<payload.imgs.size();i++){
                    if(src.equals(payload.imgs.get(i))){
                        repetido=true;
                    }
                }
                linkRepetido(payload, src, 2);*/
            }
        }

        payload.html = doc.html();

        return payload;
    }

}
