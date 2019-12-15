package serviceproviders.navigation;

import com.google.gson.*;

import java.lang.reflect.Type;

class BingMapResourceDeserializer implements JsonDeserializer<BingMapResource> {

    @Override
    public BingMapResource deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonElement resourceSets = jsonElement.getAsJsonObject().get("resourceSets");
        JsonElement resourceSet = resourceSets.getAsJsonArray().get(0);
        JsonElement resources = resourceSet.getAsJsonObject().get("resources");
        JsonElement resource = resources.getAsJsonArray().get(0);
        return new Gson().fromJson(resource, BingMapResource.class);
    }
}