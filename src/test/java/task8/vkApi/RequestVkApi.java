package task8.vkApi;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import task8.project.model.Image;
import task8.project.model.WallPost;
import task8.rest.UniRestManager;
import task8.utils.ConfigFileReader;
import task8.utils.JSONUtils;
import task8.utils.Logger;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestVkApi {

    private final static String URL = ConfigFileReader.getVkURL();
    private final static String V = ConfigFileReader.getVersionVkApi();
    private final static String V_KEY = "v=";
    private final static String TOKEN_KEY = "access_token";
    private final static String TOKEN = ConfigFileReader.getToken();
    private final static String WALL_POST = "wall.post?";
    private final static String WALL_UP_LOAD_SERVER = "photos.getWallUploadServer?";
    private final static String SAVE_WALL_PHOTO = "photos.saveWallPhoto?";
    private final static String WALL_EDIT = "wall.edit?";
    private final static String WALL_CREATE_COMMENT = "wall.createComment?";
    private final static String IS_LIKED = "likes.isLiked?";
    private final static String WALL_DELETE = "wall.delete?";
    private final static String USERS_GET = "users.get?";
    private final static String FILE_NAME = "file1";


    public static WallPost sendPost(WallPost wallPost, String fileName) {
        Map<String, String> parameters = preparationForSendPost(wallPost);
        parameters.put("attachments", getAttachment(fileName));
        HttpResponse<JsonNode> response = UniRestManager.POSTRequest(getURL(parameters, WALL_POST));
        Logger.debug(response.getBody());
        wallPost.setPostId(JSONUtils.getValue(new JSONObject(response.getBody()), "post_id"));
        return wallPost;
    }

    public static WallPost sendPost(WallPost wallPost) {
        HttpResponse<JsonNode> response = UniRestManager.POSTRequest(getURL(preparationForSendPost(wallPost), WALL_POST));
        Logger.debug(response.getBody());
        wallPost.setPostId(JSONUtils.getValue(new JSONObject(response.getBody()), "post_id"));
        return wallPost;
    }

    private static Map<String, String> preparationForSendPost(WallPost wallPost) {
        Map<String, String> parameters = new LinkedHashMap<>();
        if (wallPost.getMessage() != null) parameters.put("message", wallPost.getMessage());
        return parameters;
    }

    public static void editWallPost(WallPost wallPost, String fileName) {
        Map<String, String> parameters = preparationForEditPost(wallPost);
        parameters.put("attachments", getAttachment(fileName));
        UniRestManager.POSTRequest(getURL(parameters, WALL_EDIT));
    }

    public static void editWallPost(WallPost wallPost) {
        UniRestManager.POSTRequest(getURL(preparationForEditPost(wallPost), WALL_EDIT));
    }

    private static Map<String, String> preparationForEditPost(WallPost wallPost) {
        Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put("post_id", wallPost.getPostId());
        if (wallPost.getMessage() != null) parameters.put("message", wallPost.getMessage());
        return parameters;
    }

    public static String getUploadServerPhoto() {
        HttpResponse<JsonNode> response = UniRestManager.GETRequest(getURL(WALL_UP_LOAD_SERVER));
        Logger.debug("Method name: getUploadServerPhoto response: " + response.getBody());
        return JSONUtils.getValue(new JSONObject(response.getBody()), "upload_url");
    }

    public static String getAttachment(String fileName) {
        Image image = new Image(fileName);
        HttpResponse<JsonNode> response = UniRestManager.POSTRequest(getUploadServerPhoto(), FILE_NAME, image.getFileImage());
        Logger.debug("Method name: getAttachment response: " + response.getBody());
        String server = JSONUtils.getValue(new JSONObject(response.getBody()), "server");
        String photo = URLEncoder.encode(JSONUtils.getValue(new JSONObject(response.getBody()), "photo"), StandardCharsets.UTF_8);
        String hash = JSONUtils.getValue(new JSONObject(response.getBody()), "hash");
        return saveWallPhoto(server, photo, hash);
    }

    public static String saveWallPhoto(String server, String photo, String hash) {
        Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put("server", server);
        parameters.put("photo", photo);
        parameters.put("hash", hash);
        HttpResponse<JsonNode> response = UniRestManager.GETRequest(getURL(parameters, SAVE_WALL_PHOTO));
        Logger.debug("Method name: saveWallPhoto response: " + response.getBody());
        String idPhoto = JSONUtils.getValue(new JSONObject(response.getBody()), "id");
        String owner_id = JSONUtils.getValue(new JSONObject(response.getBody()), "owner_id");
        Logger.debug("photo" + owner_id + "_" + idPhoto);
        return "photo" + owner_id + "_" + idPhoto;
    }

    public static void createComment(WallPost wallPost, String text) {
        Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put("post_id", wallPost.getPostId());
        parameters.put("message", text);
        UniRestManager.POSTRequest(getURL(parameters, WALL_CREATE_COMMENT));
    }

    public static boolean isLiked(WallPost wallPost) {
        Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put("type", "post");
        parameters.put("item_id", wallPost.getPostId());
        HttpResponse<JsonNode> response = UniRestManager.GETRequest(getURL(parameters, IS_LIKED));
        return JSONUtils.getValue(new JSONObject(response.getBody()), "liked").equals("1");
    }

    public static void deletePost(WallPost wallPost) {
        Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put("post_id", wallPost.getPostId());
        UniRestManager.POSTRequest(getURL(parameters, WALL_DELETE));
    }

    private static String getURL(Map<String, String> parametersMap, String nameMethod) {
        StringBuilder parameters = new StringBuilder();
        for (Map.Entry<String, String> pair : parametersMap.entrySet()) {
            parameters.append(pair.getKey()).append("=").append(pair.getValue()).append("&");
        }
        return URL + nameMethod + parameters.toString() + TOKEN_KEY + "=" + TOKEN + "&" + V_KEY + V;
    }

    private static String getURL(String nameMethod) {
        return URL + nameMethod + TOKEN_KEY + "=" + TOKEN + "&" + V_KEY + V;
    }

    public static HttpResponse<JsonNode> getUserId(Map<String, String> parameters) {
        return UniRestManager.POSTRequest(getURL(parameters, USERS_GET));
    }

    public static HttpResponse<JsonNode> getUserId() {
        return UniRestManager.POSTRequest(getURL(USERS_GET));
    }
}
