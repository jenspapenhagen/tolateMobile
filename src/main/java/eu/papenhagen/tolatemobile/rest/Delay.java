/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.papenhagen.tolatemobile.rest;

//import lombok.Getter;
//import lombok.Setter;

/**
 *
 * @author jay
 */
public class Delay {

//    @Getter
    private int id;
    
//    @Setter
//    @Getter
    private String date;
    
//    @Setter
//    @Getter
    private String name;
//    
//    @Setter
//    @Getter
    private int delaytime;
    
//    @Setter
//    @Getter
    private String ursache;
    
//    @Setter
//    @Getter
    private boolean entschuldigt;

    public Delay(int id, String date, String name, int delaytime, String ursache, boolean entschuldigt) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.delaytime = delaytime;
        this.ursache = ursache;
        this.entschuldigt = entschuldigt;
    }
    
    
}
