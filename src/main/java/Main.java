import org.json.*;

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws IOException, JSONException {
        var groupName = "iritrtf_urfu";
        var token = "95b10a5e16213f4b8041462c19312c117c94bce53a10c2eddeead4f86c1f134f9312dd33a000d472ffd17";
        var femaleCount = 0;
        var maleCount = 0;
        var jsonArray = getUsersFriendsVKAPI(groupName, token);

        for (var i = 0; i < jsonArray.length(); i++) {
            var user = jsonArray.getJSONObject(i);
            if (user.getInt("sex") == 1)
                femaleCount++;
            else if (user.getInt("sex") == 2)
                maleCount++;
        }

        System.out.println("Среди участников группы https://vk.com/" + groupName);
        System.out.println("Количество женщин: " + femaleCount);
        System.out.println("Количество мужщин: " + maleCount);
    }

    private static JSONArray getUsersFriendsVKAPI(String groupName, String token) throws IOException, JSONException {
        var stringUrl = String.format("https://api.vk.com/method/groups.getMembers" +
                        "?group_id=%s" +
                        "&fields=sex" +
                        "&access_token=%s" +
                        "&v=5.126",
                groupName,
                token);
        var urlConnection = new URL(stringUrl).openConnection();
        var buffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        var result = buffer.readLine();
        buffer.close();
        return new JSONObject(result).getJSONObject("response").getJSONArray("items");
    }
}