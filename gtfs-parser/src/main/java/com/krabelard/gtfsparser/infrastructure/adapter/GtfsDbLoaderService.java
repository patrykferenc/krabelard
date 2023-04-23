package com.krabelard.gtfsparser.infrastructure.adapter;

import java.io.File;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.onebusaway.csv_entities.EntityHandler;
import org.onebusaway.gtfs.impl.GtfsDaoImpl;
import org.onebusaway.gtfs.serialization.GtfsReader;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GtfsDbLoaderService {

	private final GtfsReader reader;
	private final GtfsDaoImpl store;
	private final EntityHandler handler;

	public void populateDB() {
		var input = new File("src/test/resources/warsaw.zip");
		try {
			reader.setInputLocation(input);
			reader.setEntityStore(store);
			reader.addEntityHandler(handler);
			reader.run();
		} catch (IOException ex) {
			throw new RuntimeException();
		}
	}
}
