package com.fcctech.tournament;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fcctech.tournament.domain.ModalityType;
import com.fcctech.tournament.payload.response.IntPair;
import com.fcctech.tournament.payload.response.ModalityFightResponse;
import com.fcctech.tournament.payload.response.ModalityResponse;
import com.fcctech.tournament.payload.response.ModalityStyleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class TestHelper {

    public static class ModalityResponseDeserializer extends StdDeserializer<ModalityResponse> {

        public ModalityResponseDeserializer() {
            this(null);
        }

        public ModalityResponseDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public ModalityResponse deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {
            ModalityResponse response;
            ObjectCodec codec = parser.getCodec();
            JsonNode node = codec.readTree(parser);
            ModalityType type = ModalityType.valueOf(node.get("type").asText());
            if (ModalityType.COMBAT.equals(type)) {
                response = ModalityFightResponse
                        .builder()
                        .id(node.get("id").asLong())
                        .name(node.get("name").asText())
                        .categoryId(node.get("category_id").asLong())
                        .weightRange(IntPair.builder()
                                .from(node.get("weight_range").get("from").asInt())
                                .to(node.get("weight_range").get("to").asInt())
                                .build())
                        .ageRange(IntPair.builder()
                                .from(node.get("age_range").get("from").asInt())
                                .to(node.get("age_range").get("to").asInt())
                                .build())
                        .type(type)
                        .build();
            } else if (ModalityType.FORM.equals(type)) {
                response = ModalityStyleResponse.builder()
                        .id(node.get("id").asLong())
                        .name(node.get("name").asText())
                        .categoryId(node.get("category_id").asLong())
                        .agesRange(IntPair.builder()
                                .from(node.get("ages_range").get("from").asInt())
                                .to(node.get("ages_range").get("to").asInt())
                                .build())
                        .type(type)
                        .build();
            } else {
                throw new RuntimeException("change me");
            }
            return response;
        }

    }

    private static SimpleModule module =
            new SimpleModule("ModalityDeserializer", new Version(1, 0, 0, null, null, null))
                    .addDeserializer(ModalityResponse.class, new ModalityResponseDeserializer());

    public static ObjectMapper mapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
            .registerModule(module)
            .findAndRegisterModules();
    ;

    public static String asJsonString(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T read(String content, T clazz) {
        try {
            return mapper.readValue(content, new TypeReference<T>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date toDate(int year, int month, int day) {

        return new Date(LocalDate.of(year, month, day).toEpochDay());
    }

}
