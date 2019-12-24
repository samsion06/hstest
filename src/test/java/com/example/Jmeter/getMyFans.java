//package com.example.Jmeter;
//
//import com.example.utils.ConvertData;
//import com.example.utils.HttpConfig;
//import com.googlecode.protobuf.format.JsonFormat;
//import com.hs.productservice.api.proto.getdetailbyid.ProductServiceApiGetDetailById;
//import com.hs.user.rel.proto.UserRelationProto;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ByteArrayEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.jmeter.config.Arguments;
//import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
//import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
//import org.apache.jmeter.samplers.SampleResult;
//
//import java.io.IOException;
//import java.net.URI;
//
//public class getMyFans extends AbstractJavaSamplerClient {
//
//    private String result;
//    private String url;
//    private Integer port;
//    private String ChannelUserId;
//    private Integer FansType;
//
//    @Override
//    public Arguments getDefaultParameters() {
//        Arguments params = new Arguments();
//        params.addArgument("url", "");
//        params.addArgument("port", "");
//        params.addArgument("ChannelUserId", "");
//        params.addArgument("FansType", "");
//
//        return params;
//    }
//    @Override
//    public void setupTest(JavaSamplerContext arg0) {
//        url = arg0.getParameter("url");
//        port = arg0.getIntParameter("port");
//        ChannelUserId=arg0.getParameter("ChannelUserId");
//        FansType = arg0.getIntParameter("FansType");
//    }
//
//    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost post=null;
//        URI uri;
//        ByteArrayEntity byteArrayEntity;
//        JsonFormat jsonFormat = new JsonFormat();
//        SampleResult sr = new SampleResult();
//        try {
//            byteArrayEntity = ConvertData.MyFansQueryRequest(ChannelUserId, 1,FansType,sr);
//            uri = new URI(HttpConfig.scheme, null, url, port, "/user/relation/myFans", "", null);
//            post = new HttpPost(uri);
//            post.setEntity(byteArrayEntity);
//            post.setHeader("Content-Type", "application/x-protobuf");
//            sr.sampleStart();
//            HttpResponse response = httpClient.execute(post);
//            if (response.getStatusLine().getStatusCode() == 200) {
//                UserRelationProto.MyFansQueryResponse resp = UserRelationProto.MyFansQueryResponse.parseFrom(response.getEntity().getContent());
//                sr.sampleEnd();
//                sr.setSuccessful(true);
//                result=jsonFormat.printToString(resp);
//                } else {
//                sr.setSuccessful(false);
//                result=response.getStatusLine().getStatusCode()+"";
//                }
//            sr.setResponseData(result , "utf-8");
//            sr.setDataType(SampleResult.TEXT);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if(httpClient != null) {
//                    httpClient.close();
//                }
//                if(post != null){
//                    post.releaseConnection();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return sr;
//    }
//
//    @Override
//    public void teardownTest(JavaSamplerContext arg0) {
//
//    }
//
//}
