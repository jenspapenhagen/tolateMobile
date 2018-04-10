/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tolatemobile.enitiy;

import com.google.gson.annotations.JsonAdapter;
import lombok.AllArgsConstructor;
import lombok.*;

/**
 * the Delay
 *
 * @author jens.papenhagen
 */
@AllArgsConstructor
@Getter
public class Delay {

    private int id;

    @Setter
    private String date;

    @Setter
    private String name;

    @Setter
    private int delaytime;

    @Setter
    private String ursache;

    @Setter
    @JsonAdapter(BooleanTypeAdapter.class)
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
