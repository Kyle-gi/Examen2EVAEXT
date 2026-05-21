package org.biblioteca.herramientas;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonUtil {
    private static Gson instancia = null;

    private JsonUtil() {
    }

    public static Gson obtenerInstancia() {
        if (instancia == null) {
            instancia = crearGson();
        }
        return instancia;
    }

    private static Gson crearGson() {
        GsonBuilder constructor = new GsonBuilder();
        constructor.setPrettyPrinting();
        
        DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE;
        
        constructor.registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
            @Override
            public JsonElement serialize(LocalDate fecha, Type tipo, JsonSerializationContext contexto) {
                return new JsonPrimitive(fecha.format(formato));
            }
        });
        
        constructor.registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement elemento, Type tipo, JsonDeserializationContext contexto) throws JsonParseException {
                return LocalDate.parse(elemento.getAsString(), formato);
            }
        });
        
        return constructor.create();
    }
}

