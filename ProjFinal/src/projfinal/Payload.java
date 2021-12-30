/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projfinal;

import java.util.ArrayList;

/**
 *
 * @author pedrodias & paulosantos
 */
public class Payload {
    public ArrayList<String> links;
    public ArrayList<String> imgs;
    public String html = "";

    public Payload() {
        links = new ArrayList<>();
        imgs = new ArrayList<>();
    }

}
