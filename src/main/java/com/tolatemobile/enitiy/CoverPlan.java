/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tolatemobile.enitiy;

import lombok.AllArgsConstructor;
import lombok.*;

/**
 * CoverPlan/Vertretungsplan
 *
 * @author jens.papenhagen
 */
@AllArgsConstructor
@Getter
@Setter
public class CoverPlan {

    private String title;

    private String link;

    private String description;

    private String guid;

    @Override
    public String toString() {
        return "CoverPlan{" + "title=" + title + ", link=" + link + ", description=" + description + ", guid=" + guid + '}';
    }

}
