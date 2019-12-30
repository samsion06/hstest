package com.hs.user.base;

import com.hs.user.base.proto.UserBaseServiceProto;
import com.mysql.cj.core.util.StringUtils;
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

public class UserInfoPerformTest {
    private CloseableHttpClient httpClient;
    private static final String SCHEME = "http";
    private static final String HOST = "user.panghuasheng.com";
    private static final int POST = 8080;
    private static final boolean isDetail =false;//每个线程的耗时开关
    private static final  int corePoolSize =1;//线程数
    private static final  int requestNum=100;
    private static final  List<Map<String,String>> userMap =new ArrayList<>();
    private static final  List<Map<String,String>> openIdUnionIdMap =new ArrayList<>();

    private static final String jdbcUrl="jdbc:mysql://172.18.66.88:3306/singleuser?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static final String USER="user";
    private static final String PASSWORD="user123!";

    @Test
    public void getUserInfoPdPerformTest() throws ExecutionException, InterruptedException {
        System.out.println("根据channelUserId获取用户信息-》/base/user/info/pd/get/by/channelUserId-》开始压力测试");
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
                    map[0] = userMap.get(getRandoUser());
                    if ( !StringUtils.isNullOrEmpty( map[0].get("channelId"))) {
                        channelId[0] = Long.valueOf( map[0].get("channelId"));
                    }
                    channelUserId[0] = map[0].get("channelUserId");
                    Long startTime =System.currentTimeMillis();
                    getUserInfoPdTest(channelId[0],channelUserId[0]);
                    perLong += System.currentTimeMillis()-startTime;
                }
                return perLong;

            }));
        }

        Long count=0l;
        Long temp=0l;
        for (int j = 0; j < futureList.size(); j++) {
            temp =futureList.get(j).get();
            count+=futureList.get(j).get();
            if (isDetail) {
                System.out.println("线程-"+j+"耗时："+((temp*0.1)/(1000*0.1)+" 秒"));
            }
        }
        Long reqTotal =Long.valueOf(corePoolSize).longValue() * Long.valueOf(requestNum).longValue();
        double avg =((count*0.1)/(1000*0.1))/reqTotal;
        count = count/(1000);
        System.out.println("\t"+corePoolSize+" 线程平均耗时："+avg+" 秒");
        System.out.println("\t"+corePoolSize+" 线程总耗时："+count+" 秒");
        System.out.println("根据channelUserId获取用户信息-》结束压力测试");
    }



    @Test
    public void getUserInfoPdByMobilePerformTest() throws ExecutionException, InterruptedException {
        System.out.println();
        System.out.println();
        System.out.println(" 根据手机号获取用户信息-》/base/user/info/pd/get/by/mobile-》开始压力测试");
        CyclicBarrier  cyclicBarrier=new CyclicBarrier(corePoolSize);
        ExecutorService  executorService = new ThreadPoolExecutor(corePoolSize, corePoolSize,0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(corePoolSize), // 使用有界队列，避免OOM
                new ThreadPoolExecutor.DiscardPolicy());
        List< Future<Long>> futureList =new ArrayList<>();
        final Long[] channelId=new Long[]{0l};
        final String[] mobileAreaCode=new String[]{""};
        final String[] mobile=new String[]{""};
        @SuppressWarnings("unchecked")
		Map<String,String>[] map=new HashMap[]{null};
        for (int i=0;i<corePoolSize;i++ ){
            futureList.add(executorService.submit(()->{
                cyclicBarrier.await();
                Long perLong =0l;
                for (int k=0;k<requestNum;k++) {
                    map[0] = userMap.get(getRandoUser());
                    if ( !StringUtils.isNullOrEmpty(map[0].get("channelId"))) {
                        channelId[0] = Long.valueOf(map[0].get("channelId"));
                    }
                    mobileAreaCode[0] =map[0].get("mobileAreaCode");
                    mobile[0] =map[0].get("mobile");
                    Long startTime =System.currentTimeMillis();
                    getUserInfoPdByMobileTest(channelId[0],mobileAreaCode[0],mobile[0]);
                    perLong += System.currentTimeMillis()-startTime;
                }
                return perLong;
            }));
        }

        Long count=0l;
        Long temp=0l;
        for (int j = 0; j < futureList.size(); j++) {
            temp =futureList.get(j).get();
            count+=futureList.get(j).get();
            if (isDetail) {
                System.out.println("线程-"+j+"耗时："+((temp*0.1)/(1000*0.1)+" 秒"));
            }
        }
        count = count/1000;
        Long reqTotal =Long.valueOf(corePoolSize).longValue() * Long.valueOf(requestNum).longValue();
        double avg =((count*0.1)/(1000*0.1))/reqTotal;
        System.out.println("\t"+corePoolSize+" 线程平均耗时："+avg+" 秒");
        System.out.println("\t"+corePoolSize+" 线程总耗时："+count+" 秒");
        System.out.println(" 根据手机号获取用户信息-》结束压力测试");
    }

    private Integer getRandoUser() {
        int maxSize = userMap.size();
        return new Random().nextInt(maxSize-0)+0;
    }

    private Integer getRandoUnionOpenId() {
        int maxSize = openIdUnionIdMap.size();
        return new Random().nextInt(maxSize-0)+0;
    }

    @Test
    public void getUserInfoPdByInviteCodePerformTest() throws ExecutionException, InterruptedException {
        System.out.println();
        System.out.println();
        System.out.println("根据邀请码获取用户信息-》/base/user/info/pd/get/by/invite/code-》开始压力测试");
        CyclicBarrier  cyclicBarrier=new CyclicBarrier(corePoolSize);
        ExecutorService  executorService = new ThreadPoolExecutor(corePoolSize, corePoolSize,0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(corePoolSize), // 使用有界队列，避免OOM
                new ThreadPoolExecutor.DiscardPolicy());
        List< Future<Long>> futureList =new ArrayList<>();

        final Long[] channelId=new Long[]{0l};
        final String[] inviteCode=new String[]{""};

        @SuppressWarnings("unchecked")
		Map<String,String>[] map=new HashMap[]{null};
        for (int i=0;i<corePoolSize;i++ ){
            futureList.add(executorService.submit(()->{
                cyclicBarrier.await();
                Long perLong =0l;
                for (int k=0;k<requestNum;k++) {
                    map[0] = userMap.get(getRandoUser());
                    if ( !StringUtils.isNullOrEmpty(map[0].get("channelId"))) {
                        channelId[0] = Long.valueOf(map[0].get("channelId"));
                    }
                    inviteCode[0] =map[0].get("inviteCode");
                    Long startTime =System.currentTimeMillis();
                    getUserInfoPdByInviteCodeTest(channelId[0],inviteCode[0]);
                    perLong += System.currentTimeMillis()-startTime;
                }
                return perLong;
            }));
        }

        Long count=0l;
        Long temp=0l;
        for (int j = 0; j < futureList.size(); j++) {
            temp =futureList.get(j).get();
            count+=futureList.get(j).get();
            if (isDetail) {
                System.out.println("线程-"+j+"耗时："+((temp*0.1)/(1000*0.1)+" 秒"));
            }
        }
        count = count/1000;
        Long reqTotal =Long.valueOf(corePoolSize).longValue() * Long.valueOf(requestNum).longValue();
        double avg =((count*0.1)/(1000*0.1))/reqTotal;
        System.out.println("\t"+corePoolSize+" 线程平均耗时："+avg+" 秒");
        System.out.println("\t"+corePoolSize+" 线程总耗时："+count+" 秒");
        System.out.println(" 根据邀请码获取用户信息-》结束压力测试");
    }


    @Test
    public void getUserInfoPdByOpenIdPerformTest() throws ExecutionException, InterruptedException {
        System.out.println();
        System.out.println();
        System.out.println("根据微信OpenId取用户信息-》/base/user/info/pd/get/by/channelUserId-》开始压力测试");
        CyclicBarrier  cyclicBarrier=new CyclicBarrier(corePoolSize);
        ExecutorService  executorService = new ThreadPoolExecutor(corePoolSize, corePoolSize,0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(corePoolSize), // 使用有界队列，避免OOM
                new ThreadPoolExecutor.DiscardPolicy());
        List< Future<Long>> futureList =new ArrayList<>();
        final Long[] channelId=new Long[]{0l};
        final String[] openId=new String[]{""};

        @SuppressWarnings("unchecked")
		Map<String,String>[] map=new HashMap[]{null};
        for (int i=0;i<corePoolSize;i++ ){
            futureList.add(executorService.submit(()->{
                cyclicBarrier.await();
                Long perLong =0l;
                for (int k=0;k<requestNum;k++) {
                    map[0] = openIdUnionIdMap.get(getRandoUnionOpenId());
                    if ( !StringUtils.isNullOrEmpty(map[0].get("channelId"))) {
                        channelId[0] = Long.valueOf(map[0].get("channelId"));
                    }
                    openId[0] =map[0].get("openId");
                    Long startTime =System.currentTimeMillis();
                    getUserInfoPdByUnionIdOrOpenIdTest(channelId[0],openId[0],"");
                    perLong += System.currentTimeMillis()-startTime;
                }
                return perLong;
            }));
        }

        Long count=0l;
        Long temp=0l;
        for (int j = 0; j < futureList.size(); j++) {
            temp =futureList.get(j).get();
            count+=futureList.get(j).get();
            if (isDetail) {
                System.out.println("线程-"+j+"耗时："+((temp*0.1)/(1000*0.1)+" 秒"));
            }
        }
        count = count/1000;
        Long reqTotal =Long.valueOf(corePoolSize).longValue() * Long.valueOf(requestNum).longValue();
        double avg =((count*0.1)/(1000*0.1))/reqTotal;
        System.out.println("\t"+corePoolSize+" 线程平均耗时："+avg+" 秒");
        System.out.println("\t"+corePoolSize+" 线程总耗时："+count+" 秒");
        System.out.println("根据微信OpenId或UnionId获取用户信息-》结束压力测试");
    }


    @Test
    public void getUserInfoPdByUnionIdPerformTest() throws ExecutionException, InterruptedException {
        System.out.println();
        System.out.println();
        System.out.println("根据微信OpenId或UnionId取用户信息-》/base/user/info/pd/get/by/channelUserId-》开始压力测试");
        CyclicBarrier  cyclicBarrier=new CyclicBarrier(corePoolSize);
        ExecutorService  executorService = new ThreadPoolExecutor(corePoolSize, corePoolSize,0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(corePoolSize), // 使用有界队列，避免OOM
                new ThreadPoolExecutor.DiscardPolicy());
        List< Future<Long>> futureList =new ArrayList<>();
        final Long[] channelId=new Long[]{0l};
        final String[] unionId=new String[]{""};
        @SuppressWarnings("unchecked")
		Map<String,String>[] map=new HashMap[]{null};
        for (int i=0;i<corePoolSize;i++ ){
            futureList.add(executorService.submit(()->{
                cyclicBarrier.await();
                Long perLong =0l;
                for (int k=0;k<requestNum;k++) {
                    map[0] = openIdUnionIdMap.get(getRandoUnionOpenId());
                    if ( !StringUtils.isNullOrEmpty(map[0].get("channelId"))) {
                        channelId[0] = Long.valueOf(map[0].get("channelId"));
                    }
                    unionId[0] =map[0].get("unionId");

                    Long startTime =System.currentTimeMillis();
                    getUserInfoPdByUnionIdOrOpenIdTest(channelId[0],"",unionId[0]);
                    perLong += System.currentTimeMillis()-startTime;
                }
                return perLong;
            }));
        }

        Long count=0l;
        Long temp=0l;
        for (int j = 0; j < futureList.size(); j++) {
            temp =futureList.get(j).get();
            count+=futureList.get(j).get();
            if (isDetail) {
                System.out.println("线程-"+j+"耗时："+((temp*0.1)/(1000*0.1)+" 秒"));
            }
        }
        count = count/1000;
        Long reqTotal =Long.valueOf(corePoolSize).longValue() * Long.valueOf(requestNum).longValue();
        double avg =((count*0.1)/(1000*0.1))/reqTotal;
        System.out.println("\t"+corePoolSize+" 线程平均耗时："+avg+" 秒");
        System.out.println("\t"+corePoolSize+" 线程总耗时："+count+" 秒");
        System.out.println("根据微信OpenId或UnionId获取用户信息-》结束压力测试");
    }



    public void getUserInfoPdTest(  Long channelId,String channelUserId){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/info/pd/get/by/channelUserId", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInfoRequest.Builder builder = UserBaseServiceProto.UserInfoRequest.newBuilder();
            builder.setChannelId(channelId);
            builder.setChannelUserId(channelUserId);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getUserInfoPdByMobileTest(Long channelId,String mobileAreaCode,String mobile){
        try {
            URI uri = new URI(SCHEME, null, HOST, 80, "/base/user/info/pd/get/by/mobile", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInfoByMobileRequest.Builder builder = UserBaseServiceProto.UserInfoByMobileRequest.newBuilder();
            builder.setChannelId(channelId);
            builder.setMobileAreaCode(mobileAreaCode);
            builder.setMobile(mobile);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void getUserInfoPdByInviteCodeTest(Long channelId,String inviteCode){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/info/pd/get/by/invite/code", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInviteCodeQueryRequest.Builder builder = UserBaseServiceProto.UserInviteCodeQueryRequest.newBuilder();
            builder.setChannelId(54305345);
            builder.setInviteCode("w8ltlhpkw");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getUserInfoPdByUnionIdOrOpenIdTest(Long channelId,String openId,String unionId){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/info/pd/get/by/unionId/openId", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInfoUnionIdOpenIdRequest.Builder builder = UserBaseServiceProto.UserInfoUnionIdOpenIdRequest.newBuilder();
            builder.setChannelId(channelId);
            if (StringUtils.isNullOrEmpty(openId)) {
                builder.setOpenId(openId);
            } else if (StringUtils.isNullOrEmpty(unionId)) {
                builder.setUnionId(unionId);
            }

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
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
        try {
            findUser();
            findUserUnionIdOpenId();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void findUser() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
       Connection con = DriverManager.getConnection(jdbcUrl, USER,PASSWORD );
              //实例化Statement对象
       String sql ="select  ubi.mobile_area_code, ubi.mobile ,ubi.channel_id , ubi.channel_user_id ,hui.register_push_no\n" +
               "\t\tfrom user_base_info ubi  inner join hsrj_user_info hui\n " +
               "\t\ton  ubi.channel_user_id =hui.channel_user_id and ubi.mobile_area_code is not null and  ubi.mobile is not null" +
               " and hui.register_push_no is  not null  and ubi.is_delete = 0 limit ? ";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1,requestNum);
        ResultSet set = stmt.executeQuery();
        Map<String,String > result=null;
        while (set.next()) {
            result =new HashMap<>();
            result.put("mobileAreaCode",set.getString("mobile_area_code"));
            result.put("mobile",set.getString("mobile"));
            result.put("channelId",set.getString("channel_id"));
            result.put("channelUserId",set.getString("channel_user_id"));
            result.put("inviteCode",set.getString("register_push_no"));
            userMap.add(result);
        }
        if (set!=null) {
            set.close();
        }
        stmt.close();
        con.close();

    }


    private void findUserUnionIdOpenId() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcUrl, USER,PASSWORD );
        //实例化Statement对象
        String sql ="select channel_id , channel_user_id ,open_id,union_id\n" +
                "from user_weixin_auth_info where is_delete = 0 and open_id is not null and union_id is not null limit ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1,corePoolSize);
        ResultSet set = stmt.executeQuery();
        Map<String,String > result=null;
        while (set.next()) {
            result =new HashMap<>();
            result.put("openId",set.getString("open_id"));
            result.put("unionId",set.getString("union_id"));
            result.put("channelId",set.getString("channel_id"));
            result.put("channelUserId",set.getString("channel_user_id"));
            openIdUnionIdMap.add(result);
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
