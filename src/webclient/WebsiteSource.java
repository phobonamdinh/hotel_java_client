package webclient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class WebsiteSource {
    public static String getSource(String url) throws Exception {
        String retValue = "";
        HttpClient httpclient = new DefaultHttpClient();
        WebClientDevWrapper.wrapClient(httpclient);
        try {
            HttpGet httpget = new HttpGet(url);

            // Execute HTTP request
            HttpResponse response = httpclient.execute(httpget);

            //Get status : Response Ok will be HTTP/1.1 200 OK
            System.out.println(response.getStatusLine());

            // Get hold of the response entity
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    retValue =
                            WebsiteSource.getStringFromInputStream(instream);

                } catch (RuntimeException ex) {
                    httpget.abort();
                    throw ex;
                } finally {
                    // Closing the input stream will trigger connection release
                    try {
                        instream.close();
                    } catch (Exception e) {
                    }
                }
            }

        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return retValue;
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
    
}