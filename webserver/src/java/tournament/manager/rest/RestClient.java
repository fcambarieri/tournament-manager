package tournament.manager.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tournament.manager.rest.exception.ApiCallException;

public class RestClient {

	private String baseUri;
	private HttpClient httpClient;
	private Map<String, String> defaultHeaders;
	private Log log = LogFactory.getLog(RestClient.class.getName()) ;

	public RestClient(String baseUri) {
		this(baseUri, new DefaultHttpClient());
	}

	public RestClient(String baseUri, HttpClient client) {
		this.httpClient = client;
		this.baseUri = baseUri;
	}

	protected String buildURI(String uri) {
		return baseUri + uri;
	}

	public Map<String, Object> get(String uri) {
		return get(uri,null);
	}
	
	public Map<String, Object> get(String uri, Map<String, String> headers) {
		HttpGet getRequest = new HttpGet(buildURI(uri));
		addHeaders(getRequest, headers);
		HttpResponse response = execute(getRequest);
		return parseResponse(response);
	}

	
	
	public Map<String, Object> post(String uri, String body) {
		return post(uri, body, null);
	}
	
	public Map<String, Object> post(String uri, String body, Map<String, String> headers) {
		HttpPost request = new HttpPost(buildURI(uri));
		request.setHeader("Content-Type", "application/json");
		try {
			request.setEntity(new StringEntity(body));
			HttpResponse response = execute(request);
			return parseResponse(response);
		} catch (UnsupportedEncodingException e) {
			throw new ApiCallException("Cound parse response", e);
		}
	}
	
	public Map<String, Object> put(String uri, String body) {
		return put(uri, body, null);
	}
	
	public Map<String, Object> put(String uri, String body, Map<String, String> headers) {
		HttpPut request = new HttpPut(buildURI(uri));
		request.setHeader("Content-Type", "application/json");
		try {
			request.setEntity(new StringEntity(body));
			HttpResponse response = execute(request);
			return parseResponse(response);
		} catch (UnsupportedEncodingException e) {
			throw new ApiCallException("Cound parse response", e);
		}
	}
	

	public Map<String, Object> delete(String uri) {
		HttpDelete getRequest = new HttpDelete(buildURI(uri));
		HttpResponse response = execute(getRequest);
		return parseResponse(response);
	}
	

	protected void addHeaders(HttpRequestBase base, Map<String, String> headers) {
		if (headers != null && !headers.isEmpty()) {
			Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				base.addHeader(entry.getKey(), entry.getValue());
			}
		}
	}

	protected HttpResponse execute(HttpRequestBase base) {
		base.setHeader("Accept", "application/json");
		HttpResponse response = null;
		try {
			log.debug(String.format("Executing Uri: %s Method: %s", base.getURI().toString(), base.getMethod()));
			response = httpClient.execute(base);
			//assertResponse(response);
		} catch (IOException ex) {
			log.error(String.format("Error executing Uri: %s Method: %s", base.getURI().toString(), base.getMethod()), ex);
			throw new ApiCallException("Execution api Call", ex);
		}
		return response;
	}

	protected Map<String, Object> parseResponse(HttpResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		StatusLine status = response.getStatusLine();
		map.put("status", status.getStatusCode());
		Object data = null;
		if (status.getStatusCode() != 204) {
			data = parseDataResponse(response);
		}
		map.put("data", data);
		
		if (status.getStatusCode() >= 300) {
			String msg = String.format("HTTP error code : %s - Message: %s"
					,status.getStatusCode(), data != null ? data.toString() : "");
			log.error(msg);
			throw new ApiCallException(status.getStatusCode(),String.format("HTTP error code : %s - Message: %s",status.getStatusCode(), data != null ? data.toString() : ""));
		}
		
		return map;
	}

	protected Object parseDataResponse(HttpResponse response) {
		return getJSonResponse(response);
	}

//	private void assertResponse(HttpResponse response) {
//		if (response == null) {
//			throw new RuntimeException("The response is null");
//		}
//		StatusLine status = response.getStatusLine();
//		if (status == null) {
//			throw new RuntimeException("The statusLine is null");
//			// throw new RuntimeException("Failed : HTTP error code : " +
//			// response.getStatusLine().getStatusCode());
//		}
//
//		if (status.getStatusCode() < 200 || status.getStatusCode() >= 300) {
//			log.error(String.format("HTTP error code : %s - Message: %s"
//							,response.getStatusLine().getStatusCode()));
//			throw new ApiCallException(status.getStatusCode(),
//					"Failed : HTTP error code : "
//							+ response.getStatusLine().getStatusCode());
//		}
//
//	}

	protected Object getJSonResponse(HttpResponse response) {
		try {
			
			String apiResponse = getResponse(response);
			if (apiResponse.startsWith("[")) {
				JSONArray array = new JSONArray(apiResponse);
				return array;
			}

			JSONObject json = new JSONObject(apiResponse);
			return json;
		} catch (JSONException ex) {
			throw new ApiCallException("The response is not json valid", ex);
		}
	}

	protected String getResponse(HttpResponse response) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			if (response.getEntity() == null) {
				throw new ApiCallException("No entity found");
			}
			br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));
			String output;
			while ((output = br.readLine()) != null) {
				// System.out.println(output);
				sb.append(output);
			}
		} catch (IOException ex) {
			// Logger.getLogger(APICall.class.getName()).log(Level.SEVERE, null,
			// ex);
		} catch (IllegalStateException ex) {
			// Logger.getLogger(APICall.class.getName()).log(Level.SEVERE, null,
			// ex);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				// Logger.getLogger(APICall.class.getName()).log(Level.SEVERE,
				// null, ex);
			}
		}
		return sb.toString();
	}
	
	public void setDefaultHeaders(Map<String, String> defaultHeaders) {
		this.defaultHeaders = defaultHeaders;
	}
	
	public Map<String, String> getDefaultHeaders() {
		return defaultHeaders;
	}
}
