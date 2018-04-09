/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tolatemobile.enitiy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * the intern object
 * @author jens.papenhagen
 */
@AllArgsConstructor
public class Delay {

    @Getter
    private int id;

    @Setter
    @Getter
    private String date;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private int delaytime;

    @Setter
    @Getter
    private String ursache;

    @Setter
    @Getter
    private boolean entschuldigt;
   

    public String toJSON() {
        String output = "{\"id\": " + id
                + ",\"date\":\" " + date + "\""
                + ",\"name\":\" " + name + "\""
                + ",\"delaytime\": " + delaytime
                + ",\"ursache\":\" " + ursache + "\""
                + ",\"entschuldigt\":" + entschuldigt
                + "}";
        return output;
    }
}
