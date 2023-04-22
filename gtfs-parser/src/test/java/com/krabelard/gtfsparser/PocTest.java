package com.krabelard.gtfsparser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.onebusaway.csv_entities.EntityHandler;
import org.onebusaway.gtfs.impl.GtfsDaoImpl;
import org.onebusaway.gtfs.model.Route;
import org.onebusaway.gtfs.model.Stop;
import org.onebusaway.gtfs.serialization.GtfsReader;

import java.io.File;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PocTest {

    GtfsReader reader;

    @BeforeAll
    void setup() {
        reader = new GtfsReader();
    }

    @Test
    void whenReadingFromFile_shouldParseDataIntoCorrectDto() throws IOException {
        // given

        var input = new File("src/test/resources/warsaw.zip");
        reader.setInputLocation(input);
        reader.addEntityHandler(new GtfsEntityHandler());

        GtfsDaoImpl store = new GtfsDaoImpl();
        reader.setEntityStore(store);

        reader.run();

        // Access entities through the store
        for (Route route : store.getAllRoutes()) {
            System.out.println("route: " + route.getShortName());
        }

    }

    private static class GtfsEntityHandler implements EntityHandler {

        @Override
        public void handleEntity(Object bean) {
            if (bean instanceof Stop stop) {
                System.out.println("stop: " + stop.getName());
            }
        }

    }

}
