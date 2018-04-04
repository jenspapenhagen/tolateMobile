package eu.papenhagen.tolatemobile.rest;

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
