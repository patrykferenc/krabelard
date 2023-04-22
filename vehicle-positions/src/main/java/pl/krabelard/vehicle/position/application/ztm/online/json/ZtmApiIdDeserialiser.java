package pl.krabelard.vehicle.position.application.ztm.online.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class ZtmApiIdDeserialiser extends StdDeserializer<Integer> {

	@SuppressWarnings("unused")
	public ZtmApiIdDeserialiser() {
		super(Integer.class);
	}

	@Override
	public Integer deserialize(JsonParser parser, DeserializationContext context)
		throws IOException {
		JsonNode node = parser.getCodec().readTree(parser);
		String id = node.asText().split("\\+")[0];
		return Integer.parseInt(id);
	}
}
