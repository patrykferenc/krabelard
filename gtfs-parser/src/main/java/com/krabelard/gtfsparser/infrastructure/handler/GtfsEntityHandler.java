package com.krabelard.gtfsparser.infrastructure.handler;

import jakarta.transaction.Transactional;
import org.onebusaway.csv_entities.EntityHandler;

public class GtfsEntityHandler implements EntityHandler {

	@Override
	@Transactional
	public void handleEntity(Object bean) {}
}
