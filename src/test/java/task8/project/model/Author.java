package task8.project.model;

import kong.unirest.json.JSONObject;
import task8.utils.JSONUtils;
import task8.vkApi.RequestVkApi;
import java.util.Map;

public class Author {

    private String id;
    private String name;

    public Author(String name, Map<String, String> parameters) {
        this.name = name;
        id = JSONUtils.getValue(new JSONObject(RequestVkApi.getUserId(parameters).getBody()),"id");
    }

    public Author(String name) {
        this.name = name;
        id = JSONUtils.getValue(new JSONObject(RequestVkApi.getUserId().getBody()),"id");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
