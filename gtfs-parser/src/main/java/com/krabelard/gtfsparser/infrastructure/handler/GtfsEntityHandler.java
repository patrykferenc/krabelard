package com.krabelard.gtfsparser.infrastructure.handler;

import jakarta.transaction.Transactional;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.onebusaway.csv_entities.EntityHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GtfsEntityHandler implements EntityHandler {

	@Override
	@Transactional
	public void handleEntity(Object bean) {
		log.debug("Loaded file from .zip file : %s".formatted(bean.toString()));
	}
}
