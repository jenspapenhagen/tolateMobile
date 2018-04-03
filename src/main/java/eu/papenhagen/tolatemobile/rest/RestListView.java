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
package eu.papenhagen.tolatemobile.rest;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.converter.InputStreamIterableInputConverter;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.InputStreamListDataReader;
import com.gluonhq.connect.provider.ListDataReader;
import com.gluonhq.connect.provider.RestClient;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class RestListView extends View {

    public RestListView(String name) {
        super(name);

        // create a RestClient to the specific URL
        RestClient restClient = RestClient.create()
                .method("GET")
                .host("http://phptestfield.byethost10.com")
                .path("/api.php/tolate")
                .queryParam("transform", "1");

        // create a custom Converter that is able to parse the response into a list of objects
        InputStreamIterableInputConverter<Latenes> converter = new ItemsIterableInputConverter<>(Latenes.class);

        ListDataReader<Latenes> listDataReader = new InputStreamListDataReader<>(restClient.createRestDataSource(), converter);

        // retrieve a list from the DataProvider
        GluonObservableList<Latenes> lateness = DataProvider.retrieveList(listDataReader);

        // create a JavaFX ListView and populate it with the retrieved list
        ListView<Latenes> lvLatenes = new ListView<>(lateness);
        lvLatenes.setCellFactory(lv -> {
            return new ListCell<Latenes>() {
                @Override
                protected void updateItem(Latenes tolate, boolean empty) {
                    super.updateItem(tolate, empty);

                    if (!empty) {
                        setText(tolate.getName() + " - " + tolate.getUrsache());
                    } else {
                        setText(null);
                    }
                }
            };
        });

        setCenter(lvLatenes);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(Main.MENU_LAYER)));
        appBar.setTitleText("List Viewer");
    }

}
