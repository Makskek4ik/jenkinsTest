package task8.rest;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class UniRestManager {

    public static HttpResponse<JsonNode> GETRequest(String resources) {
        return Unirest.get(resources).asJson();
    }

    public static HttpResponse<JsonNode> POSTRequest(String resources, String name, Object obj) {
        return Unirest.post(resources).field(name, obj).asJson();
    }

    public static HttpResponse<JsonNode> POSTRequest(String resources) {
        return Unirest.post(resources).asJson();
    }

}
