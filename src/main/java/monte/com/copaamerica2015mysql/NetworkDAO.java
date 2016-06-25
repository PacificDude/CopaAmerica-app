package monte.com.copaamerica2015mysql;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Miguel on 6/14/2015.
 */
//low level networking utility
public class NetworkDAO {

    public void NetworkDao(){

    }

    /**
     * Make a call to the URI and return the data that results
     * @param uri the URI that we wish to invoke using the GET method
     * @return the data from the URI
     * @throws IOException if we have any trouble when making a network call
     */
    String strResult = "";

    public String request(String uri) {
        backgroundTask bt = new backgroundTask();
        bt.doInBackground(uri);
        return strResult;
    }
        /*
        String result = "";
        HttpGet httpGet = new HttpGet(uri); //get call to Http

        // handle the response that we get in return
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                return null;
            }
        };

        //create a Http client which will coordinate the get and response
        HttpClient httpClient = new DefaultHttpClient();

        //send the URI to the get method, and have the response handler parse it and return a result
        result = httpClient.execute(httpGet, responseHandler);

        return result;
        */

        class backgroundTask extends AsyncTask<String, Void, String> {
        String test="";
            @Override
            protected void onPostExecute(String s) {
                strResult = s;
            }

            @Override
            protected String doInBackground(String... url) {

                try {
                    /****** DEPRECATED ********
                    HttpResponse httpResponse;
                    HttpClient httpClient = new DefaultHttpClient();
                    test = url[0];
                    HttpPost myConnection = new HttpPost(url[0]);
                    httpResponse = httpClient.execute(myConnection);
                    strResult = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                    */

                    URL urlConnection = new URL(url[0]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection.openConnection();
                    InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while((line = bufferedReader.readLine()) != null ){
                        sb.append(line);
                    }
                    strResult = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                    Log.d("networkDAO", "Client Protocol Exception", e);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("networkdDAO", "IOException", e);
                }

                return strResult;
            }
        }
}

/* Things might be useful in this app
BufferedRead
URL(object).openStream()
 */