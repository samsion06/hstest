package com.hs.user.base;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserMobileAreaServiceProto;
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

public class UserMobileAreaCodeTest {
    private CloseableHttpClient httpClient;
    private static final String SCHEME = "http";
    private static final String HOST = "127.0.0.1";
    private static final int POST = 8080;
 /*  private static final String HOST = "172.18.66.88";
    private static final int POST = 8090;*/
    
    
    @Test
    public void userMobileAreaCodeList_Empty_Test(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/mobile/area/list/query", "", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserMobileAreaServiceProto.UserMobileAreaCodeListResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeListResponse.parseFrom(response.getEntity().getContent());
                if (resp.getUserMobileAreaCodeList().isEmpty()) {
                    System.out.println("Is Empty");
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    @Test
    public void userMobileAreaCodeListTest(){
        try {
            //URI uri = new URI(SCHEME, null, HOST, POST, "/user/mobile/area/list/query", "", null);
	        URI uri = new URI(SCHEME, null, "172.18.0.112", 8080, "/user/mobile/area/list/query", "", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserMobileAreaServiceProto.UserMobileAreaCodeListResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeListResponse.parseFrom(response.getEntity().getContent());
                    System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void userMobileAreaCodeQuery_Null_Test(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/mobile/area/query", "", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            UserMobileAreaServiceProto.UserMobileAreaCodeRequest.Builder request =UserMobileAreaServiceProto.UserMobileAreaCodeRequest.newBuilder();
            request.setMobileAreaCode("xxxxxxxxxx");
            post.setEntity(new ByteArrayEntity(request.build().toByteArray()));
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserMobileAreaServiceProto.UserMobileAreaCodeResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeResponse.parseFrom(response.getEntity().getContent());
                if (UserMobileAreaServiceProto.UserMobileAreaCode.getDefaultInstance() ==resp.getUserMobileAreaCode()||
                    UserMobileAreaServiceProto.UserMobileAreaCode.getDefaultInstance().equals(resp.getUserMobileAreaCode())
                 ) {
                    System.out.println("Is Empty");
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void userMobileAreaCodeQueryTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/mobile/area/query", "", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            UserMobileAreaServiceProto.UserMobileAreaCodeRequest.Builder request =UserMobileAreaServiceProto.UserMobileAreaCodeRequest.newBuilder();
            request.setMobileAreaCode("86");
	        request.setCcIso("CN");
            post.setEntity(new ByteArrayEntity(request.build().toByteArray()));
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserMobileAreaServiceProto.UserMobileAreaCodeResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	@Test
	public void userMobileAreaCodeQueryCcIsoTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/mobile/area/query", "", null);
			HttpPost post = new HttpPost(uri);
			post.setHeader("Content-Type", "application/x-protobuf");
			UserMobileAreaServiceProto.UserMobileAreaCodeRequest.Builder request =UserMobileAreaServiceProto.UserMobileAreaCodeRequest.newBuilder();
			request.setMobileAreaCode("001");
			request.setCcIso("US");
			//request.setCcIso("CA");
			post.setEntity(new ByteArrayEntity(request.build().toByteArray()));
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserMobileAreaServiceProto.UserMobileAreaCodeResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    
    @Test
    public void userMobileAreaCodeUpdateAreaCode_NullTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/mobile/area/update", "", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            UserMobileAreaServiceProto.UserMobileAreaCodeUpdateRequest.Builder request =UserMobileAreaServiceProto.UserMobileAreaCodeUpdateRequest.newBuilder();
	        request.setAreaId(111);
            request.setAreaCode("");
            post.setEntity(new ByteArrayEntity(request.build().toByteArray()));
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserMobileAreaServiceProto.UserMobileAreaCodeUpdateResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeUpdateResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@Test
	public void userMobileAreaCodeUpdateAreaCode_Is_Hot_Not_ExistTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/mobile/area/update", "", null);
			HttpPost post = new HttpPost(uri);
			post.setHeader("Content-Type", "application/x-protobuf");
			UserMobileAreaServiceProto.UserMobileAreaCodeUpdateRequest.Builder request =UserMobileAreaServiceProto.UserMobileAreaCodeUpdateRequest.newBuilder();
			request.setAreaCode("86");
			request.setIsHot(34);
			post.setEntity(new ByteArrayEntity(request.build().toByteArray()));
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserMobileAreaServiceProto.UserMobileAreaCodeUpdateResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeUpdateResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void userMobileAreaCodeUpdateAreaCode_Info_Not_ExistTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/mobile/area/update", "", null);
			HttpPost post = new HttpPost(uri);
			post.setHeader("Content-Type", "application/x-protobuf");
			UserMobileAreaServiceProto.UserMobileAreaCodeUpdateRequest.Builder request =UserMobileAreaServiceProto.UserMobileAreaCodeUpdateRequest.newBuilder();
			request.setAreaCode("xxxxxxxx");
			post.setEntity(new ByteArrayEntity(request.build().toByteArray()));
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserMobileAreaServiceProto.UserMobileAreaCodeUpdateResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeUpdateResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    @Test
    public void userMobileAreaCodeUpdateTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/mobile/area/update", "", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            UserMobileAreaServiceProto.UserMobileAreaCodeUpdateRequest.Builder request =UserMobileAreaServiceProto.UserMobileAreaCodeUpdateRequest.newBuilder();
            request.setAreaCode("86");
	        request.setAreaCnName("中国-1");
	        request.setAreaEnName("china-1");
	        request.setAreaPyName("zhongguo-1");
	        request.setCcIso("CN-1");
	        request.setIsHot(1);
            post.setEntity(new ByteArrayEntity(request.build().toByteArray()));
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserMobileAreaServiceProto.UserMobileAreaCodeUpdateResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeUpdateResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@Test
	public void userMobileAreaCodeUpdateAreaPyNameTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/mobile/area/update", "", null);
			HttpPost post = new HttpPost(uri);
			post.setHeader("Content-Type", "application/x-protobuf");
			UserMobileAreaServiceProto.UserMobileAreaCodeUpdateRequest.Builder request =UserMobileAreaServiceProto.UserMobileAreaCodeUpdateRequest.newBuilder();
			request.setAreaCode("86");
			request.setAreaPyName("zhongguo-2");
			request.setIsHot(1);
			post.setEntity(new ByteArrayEntity(request.build().toByteArray()));
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserMobileAreaServiceProto.UserMobileAreaCodeUpdateResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeUpdateResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void userMobileAreaCodeUpdateIsHotTest(){
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/mobile/area/update", "", null);
			HttpPost post = new HttpPost(uri);
			post.setHeader("Content-Type", "application/x-protobuf");
			UserMobileAreaServiceProto.UserMobileAreaCodeUpdateRequest.Builder request =UserMobileAreaServiceProto.UserMobileAreaCodeUpdateRequest.newBuilder();
			request.setAreaCode("86");
			request.setIsHot(0);
			post.setEntity(new ByteArrayEntity(request.build().toByteArray()));
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat =new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserMobileAreaServiceProto.UserMobileAreaCodeUpdateResponse resp = UserMobileAreaServiceProto.UserMobileAreaCodeUpdateResponse.parseFrom(response.getEntity().getContent());
				System.out.println("result:" + jsonFormat.printToString(resp));
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void userMobileAreaCodeCacheRefreshTest() {
		try {
			URI uri = new URI(SCHEME, null, HOST, POST, "/user/mobile/area/cache/refresh", "", null);
			HttpPost post = new HttpPost(uri);
			post.setHeader("Content-Type", "application/x-protobuf");
			HttpResponse response = httpClient.execute(post);
			JsonFormat jsonFormat = new JsonFormat();
			if (response.getStatusLine().getStatusCode() == 200) {
				UserMobileAreaServiceProto.ResponseCode resp = UserMobileAreaServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
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
