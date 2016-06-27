package gson.adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by andrew on 11/17/16.
 */

public class EmptyListDeserializer implements JsonDeserializer<List<?>> {
    @Override
    public List<?> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonArray()) return Collections.emptyList(); // Avoid Exception;

        //return new Gson().fromJson(json, type);
        // failure: Missing JsonDeserializer<List<?>>
        // but we can not still emptyGson.fromJson() because it's occurs stack overflow after Gson x.x.x version
        // so we need to use original gson context for deserialize with this ListDeserailizer for next time while "" for List
        JsonArray array = json.getAsJsonArray();
        Type itemType = ((ParameterizedType) type).getActualTypeArguments()[0];
        List list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) list.add(context.deserialize(array.get(i), itemType));
        return list;
    }
}

