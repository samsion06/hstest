package com.hs.user.base;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserAddressServiceProto;
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
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;

public class SysAreaPerformTest {
    private CloseableHttpClient httpClient;
    private static final String SCHEME = "http";
    private static final String HOST = "127.0.0.1";
    private static final int POST = 8080;
    private static final  int corePoolSize =5;//线程数
    private static final  int requestNum=100;
    private static final  List<Map<String,String>> areaIdMap =new ArrayList<>();

    private static final String jdbcUrl="jdbc:mysql://172.18.66.88:3306/singleuser?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static final String USER="user";
    private static final String PASSWORD="user123!";

    @Test
    public void getSysAreaPerformTest() throws ExecutionException, InterruptedException {
        System.out.println("获取用户地址区域列表树-》/address/sys/area/tree-》开始压力测试");
        CyclicBarrier  cyclicBarrier=new CyclicBarrier(corePoolSize);

        ExecutorService  executorService = new ThreadPoolExecutor(corePoolSize, corePoolSize,0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(corePoolSize), // 使用有界队列，避免OOM
                new ThreadPoolExecutor.DiscardPolicy());
        List< Future<Long>> futureList =new ArrayList<>();
        final Long[] channelId=new Long[]{0l};
        final String[] channelUserId=new String[]{""};
        @SuppressWarnings("unchecked")
		Map<String,String>[] map=new HashMap[]{null};
        for (int i=0;i<corePoolSize;i++ ){
            futureList.add(executorService.submit(()->{
                cyclicBarrier.await();
                Long perLong =0l;
                for (int k=0;k<requestNum;k++) {
                      Long startTime =System.currentTimeMillis();
                      getSysAreaTreeTest();
                      perLong += System.currentTimeMillis()-startTime;
                }
                return perLong;

            }));
        }

        Long count=0l;
        for (int j = 0; j < futureList.size(); j++) {
            count+=futureList.get(j).get();
        }
        Long reqTotal =Long.valueOf(corePoolSize).longValue() * Long.valueOf(requestNum).longValue();
        double avg =((count*0.1)/(1000*0.1))/reqTotal;
        count = count/(1000);
        System.out.println("\t"+corePoolSize+"线程 "+corePoolSize*requestNum+" 请求数平均耗时："+avg+" 秒");
        System.out.println("\t"+corePoolSize+"线程 "+corePoolSize*requestNum+" 请求数总耗时："+count+" 秒");
        System.out.println("获取用户地址区域列表树-》结束压力测试");
    }



    public void getSysAreaTreeTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/address/sys/area/tree", "", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserAddressServiceProto.SysAreaNodeTreeResponse.class)) {
                } else {
                    System.out.println("result:" + resp.getData());
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }





    @Test
    public void getSubAreaPerformTest() throws ExecutionException, InterruptedException {
        System.out.println("根据地址Id获取其地址区域列表-》/address/sys/sub/area-》开始压力测试");
        CyclicBarrier  cyclicBarrier=new CyclicBarrier(corePoolSize);

        ExecutorService  executorService = new ThreadPoolExecutor(corePoolSize, corePoolSize,0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(corePoolSize), // 使用有界队列，避免OOM
                new ThreadPoolExecutor.DiscardPolicy());
        List< Future<Long>> futureList =new ArrayList<>();
        final String[] parentId=new String[]{""};
        @SuppressWarnings("unchecked")
        Map<String,String>[] map=new HashMap[]{null};
        for (int i=0;i<corePoolSize;i++ ){
            futureList.add(executorService.submit(()->{
                cyclicBarrier.await();
                Long perLong =0l;
                for (int k=0;k<requestNum;k++) {
                    map[0] = areaIdMap.get(getRandoNum());
                    Long startTime =System.currentTimeMillis();
                    getSysSubAreaTest(map[0].get("areaId"));
                    perLong += System.currentTimeMillis()-startTime;
                }
                return perLong;

            }));
        }

        Long count=0l;
        for (int j = 0; j < futureList.size(); j++) {
            count+=futureList.get(j).get();
        }
        Long reqTotal =Long.valueOf(corePoolSize).longValue() * Long.valueOf(requestNum).longValue();
        double avg =((count*0.1)/(1000*0.1))/reqTotal;
        count = count/(1000);
        System.out.println("\t"+corePoolSize+"线程 "+corePoolSize*requestNum+" 请求数平均耗时："+avg+" 秒");
        System.out.println("\t"+corePoolSize+"线程 "+corePoolSize*requestNum+" 请求数总耗时："+count+" 秒");
        System.out.println("获取用户地址区域列表树-》结束压力测试");
    }



    public void getSysSubAreaTest(String parentId) {
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/address/sys/sub/area", "", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            UserAddressServiceProto.UserSysSubAreaRequest.Builder builder = UserAddressServiceProto.UserSysSubAreaRequest.newBuilder();
            builder.setParentId(parentId);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat = new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserAddressServiceProto.SysAreaNodeTreeResponse.class)) {
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Integer getRandoNum() {
        int maxSize = areaIdMap.size();
        return new Random().nextInt(maxSize-0)+0;
    }

    @Before
    public void before(){
        httpClient = HttpClients.createDefault();
        try {
            findAreaId();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void findAreaId() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcUrl, USER,PASSWORD );
        //实例化Statement对象
        String sql ="SELECT area_id from sys_area  limit ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1,corePoolSize*requestNum);
        ResultSet set = stmt.executeQuery();
        Map<String,String > result=null;
        while (set.next()) {
            result =new HashMap<>();
            result.put("areaId",set.getString("area_id"));
            areaIdMap.add(result);
        }
        if (set!=null) {
            set.close();
        }
        stmt.close();
        con.close();
    }


    @After
    public void after() throws IOException {
        httpClient.close();
    }
}
