package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Util {
	
	public static String sendJsonPost(String Json, String urlPath) {
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            if (Json != null) {     
            	OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8"); // utf-8����
                out.append(Json);
                out.flush();
                out.close();
            }
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
	
	 public static Map json_decode(String[] data, String jsonData)
	    {
	        String status = "";
	        String code = "";
	        String reason = "";
	        String uid = "";
	        Map<String, String> map = new HashMap<String, String>();
	        try
	        {
	                JSONObject jsonObject = JSONObject.fromObject(jsonData);
	                if (jsonObject != null) {
	                    status = jsonObject.getString("status");
	                    map.put("status", status);
	                    uid = jsonObject.getString("uid");
	                    map.put("uid", uid);
	                    JSONObject bizData = jsonObject.optJSONObject("bizData");
		                if (bizData != null)  {
		                for (int i = 0; i < data.length; i++) {
		                    map.put(data[i], bizData.getString(data[i]));
		                }
		            }  
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return map;
	    }
	 
	 public static String json_encode(Map<String, String> map)
	 {
		 JSONObject object = JSONObject.fromObject(map);
		 return object.toString();
	 }
	 
	 //待测试
	 public static String[] getResult(String jsonData) {
	        String[] result = {};
	        try{
	            JSONObject jsonObject = JSONObject.fromObject(jsonData);
	            if(jsonObject != null) {
	                JSONObject bizData = jsonObject.getJSONObject("bizData");
	                if(bizData != null) {
	                    JSONArray resultTmp = bizData.getJSONArray("result");
	                    result = new String[resultTmp.size()];
	                    System.out.println("元素个数: "+resultTmp.size());
	                    for(int j = 0;j < resultTmp.size();j++){
	                        JSONObject resultTmpJSONObject = resultTmp.getJSONObject(j);
	                        result[j] = resultTmpJSONObject.getString("did");
	                    }
	                }
	            }
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println("响应数据处理结果:");
	        printArray(result);
	        return result;
	    }
	 
	 public static void printArray(String[] arr)
	 {
			for (int i = 0; i < arr.length; i++) {
				System.out.print( arr[i] + " ");
			}
			System.out.println();
	 }


}
