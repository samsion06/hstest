package com.hs.user.base;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.search.proto.GeneralSearchProto;
import com.hs.search.proto.ResultResponseProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

public class UserBaseEsSyncTest {
	private CloseableHttpClient httpClient;
	private static  String SCHEME = "http";
	private static  String HOST = "127.0.0.1";
	private static  int POST = 8080;
	public final static String USER_BASE_ES_INDEX="hs-user-center";
	@Test
	public void getEsGetTest(){
		try {
			URI uri = new URI(SCHEME, null, "172.18.66.85", 10020, "/es/get", "", null);
			HttpPost post = new HttpPost(uri);
			GeneralSearchProto.IdRequest.Builder builder = GeneralSearchProto.IdRequest.newBuilder();
			builder.setIndex(USER_BASE_ES_INDEX);
			builder.setId("721577265782492");
			post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
			post.setHeader("Content-Type", "application/x-protobuf");
			
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				ResultResponseProto.ResultResponse  resp =  ResultResponseProto.ResultResponse .parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Before
	public void before(){
		httpClient = HttpClients.createDefault();
	}
	
	@After
	public void after() throws IOException {
		httpClient.close();
	}
}
