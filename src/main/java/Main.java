import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.google.gson.*;
import org.apache.http.HttpHeaders;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Main {
    public  static final  String JSON_KEY="JSON_VALUE";
    public static  String getRequest;
    public static  String getRequest_1;
    public static String filepath="/home/bhargava/Documents/read.txt";
    public  static String api="https://some_dummy_url/path1/path2/path3";
    public static String api_1="https://some_dummy_url/path1/path2/path3";
    public static String api_get_request=null;
    static {
        try {
            getRequest = getAllCompanyUsers(api);
            getRequest_1 = getAllCompanyUsers(api_1);
             api_get_request = getRequest+getRequest_1;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
       List<String> singleEmail = processEmailList(filepath);
       ArrayList<String> listOfAuthUserIds = new ArrayList<String>();
        Map map;
        System.out.println(getRequest);
        for (String arr: singleEmail)
        {
           map =  parseJSONObject(api_get_request);
          //  if((getAuthUserIdFromEmail(map,arr))!=null)
            {
                listOfAuthUserIds.add(getAuthUserIdFromEmail(map,arr));
            }
        }
       
        System.out.println("listofAuthUserIds "+listOfAuthUserIds);
        for(String  auth_user_id : listOfAuthUserIds)
        {
            System.out.println(auth_user_id);
            try {
                deleteRolev2(param1,param2);
            } catch (IOException e) {

            }
        }

    }
    public static List processEmailList (String filepath)
    {
        System.out.println("processEmailList");
        String content=null; File f;Scanner sc=null;
        List<String> lowercaseEmails = new ArrayList<>();
        try {
             f = new File(filepath);
             sc = new Scanner(f);
             content = sc.useDelimiter("\\Z").next();
            System.out.println("content "+content);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(sc!=null)
            {
                sc.close();
            }
        }
        String emails=content.replaceAll("\n", ",");
        List<String> items = Arrays.asList(emails.split("\\s*,\\s*"));
        for(String arr : items)
        {
            lowercaseEmails.add(arr.toLowerCase());
        }
        return lowercaseEmails;

    }

        public static String getAllCompanyUsers(String api) throws IOException {
            URL urlForGetRequest = new URL(api);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.addRequestProperty("Content-Type", "application/json");
            conection.setRequestMethod("GET");
            conection.setRequestProperty("Token", "Value");
            conection.setRequestProperty("Token", "Value");
            conection.setDoOutput(false);
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                return response.toString();
            } else {
                System.out.println("GET NOT WORKED");
            }
            return null;
        }



    public static void deleteRolev2(String cf_user_id, String role) throws IOException {
        PostObject requestBody = new PostObject(cf_user_id,role);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost("https://some_production_url/path1/path2/path3");
        System.out.println(requestBody);
        postRequest.setEntity(new StringEntity(requestBody.toString()));
        postRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        postRequest.addHeader("Token", "Value");
        postRequest.addHeader("Token", "Value");
        try (CloseableHttpResponse httpResponse = httpClient.execute(postRequest)) {
            String content = EntityUtils.toString(httpResponse.getEntity());
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if(statusCode==200)
            {
                System.out.println("statusCode = " + statusCode);
                System.out.println("content = " + content);
		  getRequest = getAllCompanyUsers(api);
            	getRequest_1 = getAllCompanyUsers(api_1);
             	api_get_request = getRequest+getRequest_1;	
            }
            else
            {
                System.out.println("statusCode = " + statusCode);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

        public static Map parseJSONObject (String jsonObject)
        {
            Map<String, String> hashMap = new HashMap<String, String>();
            Gson gson = new Gson();
            User user;

            String recordJson = jsonObject;
            JsonParser jsonParser = new JsonParser();
            JsonObject jo = (JsonObject) jsonParser.parse(recordJson);
            JsonArray arr = jo.getAsJsonArray("sresp");
            for (JsonElement userObject : arr) {
                JsonObject userObj = userObject.getAsJsonObject();
                user = gson.fromJson(userObj, User.class);
                hashMap.put(user.getEmail(), user.getCf_user_id());

            }
            return hashMap;

        }

        public static String getAuthUserIdFromEmail (Map mp, String email)
        {
            String value=null;
          if(mp.containsKey(email))
           {
             value = mp.get(email).toString();
           }
            return value;
        }
    }

