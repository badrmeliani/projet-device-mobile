package com.valueit.device.service.Utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CustomAuthorityDeserializer extends StdDeserializer<Object> {
    public CustomAuthorityDeserializer() {
        super(JsonObject.class);
        }

    @Override
    public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();

        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()) {
            JsonNode next = elements.next();
            JsonNode authority = next.get("authority");
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.asText()));
        }
        return grantedAuthorities;
    }

//    @Override
////    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
////        return null;
////    }
//    public Object deserialize(JsonParser jp, DeserializationContext ctxt)
//            throws IOException, JsonProcessingException {
//        if (jp.getCurrentToken() != JsonToken.START_OBJECT) {
//            throw new IOException("invalid start marker");
//        }
//        Presence p = new Presence();
//        while (jp.nextToken() != JsonToken.END_OBJECT) {
//            String fieldname = jp.getCurrentName();
//            jp.nextToken();  //move to next token in string
//            if ("userEmail".equals(fieldname)) {
//                p.setUserEmail(jp.getText());
//            } else if ("domain".equals(fieldname)) {
//                p.setDomain(jp.getText());
//            } else if ("type".equals(fieldname)) {
//                p.setType(jp.getText());
//            }
//        }
//        jp.close();
//        return p;
//
//        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
//        JsonNode jsonNode = mapper.readTree(jp);
//        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
//
//        Iterator<JsonNode> elements = jsonNode.elements();
//        while (elements.hasNext()) {
//            JsonNode next = elements.next();
//            JsonNode authority = next.get("authority");
//            grantedAuthorities.add(new SimpleGrantedAuthority(authority.asText()));
//        }
//        return grantedAuthorities;
//    }
}
