package gson.adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 11/17/16.
 */

public class NullListDeserializer implements JsonDeserializer<List<?>> {
    @Override
    public List<?> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonArray()) return null; // Avoid Exception;

        JsonArray array = json.getAsJsonArray();
        Type itemType = ((ParameterizedType) type).getActualTypeArguments()[0];
        List list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) list.add(context.deserialize(array.get(i), itemType));
        return list;
    }
}
