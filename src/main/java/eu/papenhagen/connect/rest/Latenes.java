/**
 * Copyright (c) 2016, Gluon
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of Gluon, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL GLUON BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package eu.papenhagen.connect.rest;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Latenes {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty delaytime = new SimpleIntegerProperty();
    private final StringProperty ursache = new SimpleStringProperty();
    private final BooleanProperty  entschuldigt = new SimpleBooleanProperty();
    
//    private int id;
//    private String date;
//    private String name;
//    private int delaytime;
//    private String ursache;
//    private boolean entschuldigt;

    public final int getId() {
        return id.get();
    }

    public final void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public final String getDate() {
        return date.get();
    }

    public final void setDate(String value) {
        date.set(value);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public final String getName() {
        return name.get();
    }

    public final void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public final int getDelaytime() {
        return delaytime.get();
    }

    public final void setDelaytime(int value) {
        delaytime.set(value);
    }

    public IntegerProperty delaytimeProperty() {
        return delaytime;
    }

    public final String getUrsache() {
        return ursache.get();
    }

    public final void setUrsache(String value) {
        ursache.set(value);
    }

    public StringProperty ursacheProperty() {
        return ursache;
    }

    public final boolean isEntschuldigt() {
        return entschuldigt.get();
    }

    public final void setEntschuldigt(boolean value) {
        entschuldigt.set(value);
    }

    public BooleanProperty entschuldigtProperty() {
        return entschuldigt;
    }

   
    

}
