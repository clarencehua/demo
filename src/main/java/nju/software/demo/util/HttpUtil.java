package nju.software.demo.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * http ������
 */
public class HttpUtil {

    public static String post(String requestUrl, String accessToken, String params) throws Exception {
    //	OutputStreamWriter out = null;
        String generalUrl = requestUrl + "?access_token=" + accessToken;
        URL url = new URL(generalUrl);
        // �򿪺�URL֮�������
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        // ����ͨ�õ���������
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        
        // �õ���������������
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(params);
        out.flush();
        out.close();
      
        // ����ʵ�ʵ�����
        connection.connect();
        // ��ȡ������Ӧͷ�ֶ�
        Map<String, List<String>> headers = connection.getHeaderFields();
        // �������е���Ӧͷ�ֶ�
        for (String key : headers.keySet()) {
            System.out.println(key + "--->" + headers.get(key));
        }
        // ���� BufferedReader����������ȡURL����Ӧ
        BufferedReader in = null;
        if (requestUrl.contains("nlp"))
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
        else
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        System.out.println("result:" + result);
        return result;
    }
}
