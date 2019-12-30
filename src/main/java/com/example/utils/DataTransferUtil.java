package com.example.utils;

import com.google.common.collect.Lists;
import com.hs.productservice.api.proto.getdetailbyid.ProductServiceApiGetDetailById;
import com.hs.productservice.api.proto.getdetailbyidlist.ProductServiceApiGetDetailByIdList;
import com.hs.productservice.api.proto.getlistbypage.ProductServiceApiGetListByPage;
import com.hs.productservice.api.proto.lockuserstock.ProductServiceApiStockService;
import com.hs.user.base.proto.*;
import com.hs.user.rel.proto.UserRelationProto;
import org.apache.http.entity.ByteArrayEntity;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;


public class DataTransferUtil {

    private static String incomeMessage="传入参数:"+" {";

    //花生日记登录
    public static ByteArrayEntity userInfoPdLoginRequestConvertBuilder(Integer channelId, String mobile, String pwd, String mobileAreaCode ){
        UserBaseServiceProto.UserInfoPdLoginRequest.Builder builder= UserBaseServiceProto.UserInfoPdLoginRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setMobile(mobile);
        builder.setPwd(pwd);
        builder.setMobileAreaCode(mobileAreaCode);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        System.out.println(builder);
        Reporter.log("花生日记用户登录_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //修改昵称
    public static ByteArrayEntity userNickNameUpdateRequestConvertBuilder(Integer channelId, String channelUserId, String nickName){
        UserBaseServiceProto.UserNickNameUpdateRequest.Builder builder = UserBaseServiceProto.UserNickNameUpdateRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setChannelUserId(channelUserId);
        builder.setNickName(nickName);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("修改昵称_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //用户注册
    public static ByteArrayEntity userBaseRegisterRequestConvertBuilder(String mobile) {

        UserBaseServiceProto.UserBaseRegisterRequest.Builder builder = UserBaseServiceProto.UserBaseRegisterRequest.newBuilder();
        UserBaseServiceProto.UserBaseRegister.Builder  userBaseRegisterBuild = UserBaseServiceProto.UserBaseRegister.newBuilder();
        UserBaseServiceProto.UserLoginRegister.Builder  UserLoginRegister = UserBaseServiceProto.UserLoginRegister.newBuilder();
        UserBaseServiceProto.UserZyyxRegister.Builder  userZyyxRegister = UserBaseServiceProto.UserZyyxRegister.newBuilder();
        userBaseRegisterBuild.setMobileAreaCode("68");
        userBaseRegisterBuild.setMobile(mobile);
        userBaseRegisterBuild.setChannelUserId(Math.round((int)(Math.random()*100))+""+System.currentTimeMillis());
        userBaseRegisterBuild.setHeadImgUrl("/sdf/sdf/sdfsdf");
        userBaseRegisterBuild.setBirthday("2019-06-04");
        userBaseRegisterBuild.setSex(0);
        userBaseRegisterBuild.setNickName("小周直邮");
        userBaseRegisterBuild.setSource(1);
        userBaseRegisterBuild.setRealName("小周直邮");
        userBaseRegisterBuild.setChannelId(2);
        userBaseRegisterBuild.setIdentityCard("634233425567868");
        userBaseRegisterBuild.setUserRole(1);

        userZyyxRegister.setRegisterRecommendUserId("661571043571580");

        UserLoginRegister.setLoginPwd(userBaseRegisterBuild.getMobile());
        UserLoginRegister.setLoginName(userBaseRegisterBuild.getMobile());

        builder.setUserBaseRegister(userBaseRegisterBuild);
        builder.setUserLoginRegister(UserLoginRegister);
        builder.setUserZyyxRegister(userZyyxRegister);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());

        System.out.println(builder);
//        userBasePdRegisterBuild.setRegisterInviteCode("Mw2mABG");
//        userBasePdRegisterBuild.setInviteCode("jkdsfwe");
//        userBasePdRegisterBuild.setCompanyId(1);
//        userBasePdRegisterBuild.setOperator(1);
//        userBasePdRegisterBuild.setIsOfflineOperator(1);
//        userBasePdRegisterBuild.setIsOfflineUser(1);
//        userBasePdRegisterBuild.setUserTagStatus(1);
        //UserBaseServiceProto.UserWeChatRegister.Builder  UserWeChatRegister = UserBaseServiceProto.UserWeChatRegister.newBuilder();
        //UserBaseServiceProto.UserBasePdRegister.Builder  userBasePdRegisterBuild = UserBaseServiceProto.UserBasePdRegister.newBuilder();

        //UserWeChatRegister.setOpenId("");
        //UserWeChatRegister.setUnionId("");

        //builder.setUserWeChatRegister(UserWeChatRegister);
        //builder.setUserBasePdRegister(userBasePdRegisterBuild);
        return bytes;
    }

    //修改头像
    public static ByteArrayEntity userHeadImgUpdateRequestConvertBuilder(Integer channelId, String channelUserId, String headImageUrl){
        UserBaseServiceProto.UserHeadImgUpdateRequest.Builder builder= UserBaseServiceProto.UserHeadImgUpdateRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setChannelUserId(channelUserId);
        builder.setHeadImageUrl(headImageUrl);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("修改头像_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //获取用户信息
    public static ByteArrayEntity userInfoUnionIdOpenIdRequestConvertBuilder(Integer channelId, String unionId, String openId){
        UserBaseServiceProto.UserInfoUnionIdOpenIdRequest.Builder builder= UserBaseServiceProto.UserInfoUnionIdOpenIdRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setOpenId(openId);
        builder.setUnionId(unionId);
        //System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("获取用户信息_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //修改手机号码
    public static ByteArrayEntity userMobileUpdateRequestConvertBuilder(Integer channelId, String mobileNew, String channelUserId, String mobileAreaCodeNew){
        UserBaseServiceProto.UserMobileUpdateRequest.Builder builder=  UserBaseServiceProto.UserMobileUpdateRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setMobileNew(mobileNew);
        builder.setChannelUserId(channelUserId);
        builder.setMobileAreaCodeNew(mobileAreaCodeNew);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("修改手机号码_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //修改密码
    public static ByteArrayEntity userPwdUpdateRequestConvertBuilder(String channelUserId, Integer channelId, String loginPwdNew){
        UserLoginInfoServiceProto.UserPwdUpdateRequest.Builder builder=UserLoginInfoServiceProto.UserPwdUpdateRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setLoginPwdNew(loginPwdNew);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("修改密码_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //忘记密码 index 表要看有没有区号映射
    public static ByteArrayEntity userForgetPwdRequest(Integer channelId, String loginPwd, String mobile, String mobileareacode){
        UserLoginInfoServiceProto.UserForgetPwdRequest.Builder builder= UserLoginInfoServiceProto.UserForgetPwdRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setLoginPwd(loginPwd);
        builder.setMobile(mobile);
        builder.setMobileAreaCode(mobileareacode);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        System.out.println(builder);
        Reporter.log("忘记密码_"+incomeMessage+builder+ "}");
        return bytes;
    }

    //用户实名认证
    public static ByteArrayEntity UserIdCardIdentifyRequestConvertBuilder(String channelUserId, Integer channelId, String realName, String idCardNum, String attachmentUrl){
        UserIdCardIdentifyServiceProto.UserIdCardIdentifyRequest.Builder builder = UserIdCardIdentifyServiceProto.UserIdCardIdentifyRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setRealName(realName);
        builder.setIdCardNum(idCardNum);
        builder.setAttachmentUrl(attachmentUrl);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //用户实名认证查询
    public static ByteArrayEntity userIdCardStatusQueryRequestConvertBuilder(String channelUserId, Integer channelId){
        UserIdCardIdentifyServiceProto.UserIdCardStatusQueryRequest.Builder builder=UserIdCardIdentifyServiceProto.UserIdCardStatusQueryRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //邀请码
    public static ByteArrayEntity userInviteCodeQueryRequest(String inviteCode, Integer channelId){
        UserBaseServiceProto.UserInviteCodeQueryRequest.Builder builder = UserBaseServiceProto.UserInviteCodeQueryRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setInviteCode(inviteCode);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("邀请码获取用户信息_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //用户忘记登录密码
    public static ByteArrayEntity userForgetPwdRequest(Integer channelId, String loginPwd, String mobile){
        UserLoginInfoServiceProto.UserForgetPwdRequest.Builder builder=UserLoginInfoServiceProto.UserForgetPwdRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setLoginPwd(loginPwd);
        builder.setMobile(mobile);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //微信绑定
    public static ByteArrayEntity userWeChatAuthRequest(String appId, Integer channelId, String channelUserId, String openId){
        UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder=UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
        builder.setAppId(appId);
        builder.setChannelId(channelId);
        builder.setChannelUserId(channelUserId);
        builder.setOpenId(openId);
        //System.out.println("入参 \n："+builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("微信绑定_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //微信解绑
    public static ByteArrayEntity userWeChatAuthUnBindRequest(String openId, Integer channelId, String channelUserId, String appId ){
        UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.Builder builder=UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.newBuilder();
        builder.setOpenId(openId);
        builder.setChannelId(channelId);
        builder.setChannelUserId(channelUserId);
        builder.setAppId(appId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        System.out.println(builder);
        Reporter.log("微信绑定解绑_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //微信一键登录
    public static ByteArrayEntity userWeChatOneKeyLoginRequest(Integer channelId, String mobile, String inviteChannelUserId, String mobileAreaCode){
        UserWeChatAuthServiceProto.UserWeChatOneKeyLoginRequest.Builder builder=UserWeChatAuthServiceProto.UserWeChatOneKeyLoginRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setMobile(mobile);
        builder.setInviteChannelUserId(inviteChannelUserId);
        builder.setMobileAreaCode(mobileAreaCode);
        //System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("微信一键登录_"+incomeMessage+builder+ "}");
        return bytes;
    }

    //根据渠道用户Id查询用户微信列表信息(
    public static ByteArrayEntity  getUserWeChatAuthByChannelUserIdRequest(Integer channelId,String channelUserId,String appId){
        UserWeChatAuthServiceProto.getUserWeChatAuthByChannelUserIdRequest.Builder builder= UserWeChatAuthServiceProto.getUserWeChatAuthByChannelUserIdRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setChannelUserId(channelUserId);
       // builder.setAppId(appId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        System.out.println(builder);
        Reporter.log("根据渠道用户Id查询用户微信列表信息_"+incomeMessage+builder+ "}");
        return bytes;
    }

    //绑定支付宝
    public static ByteArrayEntity userAliPayBidingRequest(String channelUserId, Integer channelId, String alipayRealname, String alipayAccount, String  alipayUserId){
        UserAliPayAuthServiceProto.UserAliPayBidingRequest.Builder builder=UserAliPayAuthServiceProto.UserAliPayBidingRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAlipayAccount(alipayAccount);
        builder.setAlipayRealname(alipayRealname);
        builder.setAlipayUserId(alipayUserId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        System.out.println(builder);
        Reporter.log("绑定支付宝_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //支付宝授权
    public static ByteArrayEntity userAliPayAuthRequest(String channelUserId, Integer channelId, String alipayUserId){
        UserAliPayAuthServiceProto.UserAliPayAuthRequest.Builder builder=UserAliPayAuthServiceProto.UserAliPayAuthRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAlipayUserId(alipayUserId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        System.out.println(builder);
        Reporter.log("支付宝授权_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //用户支付宝取消授权
    public static ByteArrayEntity userAliPayAuthCancelRequest(String channelUserId, Integer channelId){
        UserAliPayAuthServiceProto.UserAliPayAuthCancelRequest.Builder builder=UserAliPayAuthServiceProto.UserAliPayAuthCancelRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setChannelUserId(channelUserId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        System.out.println(builder);
        Reporter.log("取消支付宝授权_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //用户支付宝授权查询
    public static ByteArrayEntity userAliPayAuthInfoRequest(String channelUserId, Integer channelId){
        UserAliPayAuthServiceProto.UserAliPayAuthInfoRequest.Builder builder =  UserAliPayAuthServiceProto.UserAliPayAuthInfoRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("支付宝授权查询_"+incomeMessage+builder+ "}");
        return bytes;
    }

    //添加用户收货地址
    public static ByteArrayEntity userAddressInfoAddRequest(String channelUserId, Integer channelId, String address){
        UserAddressServiceProto.UserAddressInfoAddRequest.Builder builder=UserAddressServiceProto.UserAddressInfoAddRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAddress(address);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("添加用户收货地址_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //获取用户收货地址
    public static ByteArrayEntity userAddressRequest(String channelUserId, Integer channelId, String addressId){
        UserAddressServiceProto.UserAddressRequest.Builder builder=UserAddressServiceProto.UserAddressRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAddressId(addressId);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("获取用户收货地址_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //删除收货地址
    public static ByteArrayEntity userAddressDelete(String channelUserId, Integer channelId, String addressId){
        UserAddressServiceProto.UserAddressRequest.Builder builder=UserAddressServiceProto.UserAddressRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAddressId(addressId);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("删除用户收货地址_"+incomeMessage+builder+ "}");
        return bytes;
    }

    //更新用户收货地址
    public static ByteArrayEntity userAddressInfoUpdateRequest(String channelUserId, Integer channelId, String addressId, String username){
        UserAddressServiceProto.UserAddressInfoUpdateRequest.Builder builder=UserAddressServiceProto.UserAddressInfoUpdateRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAddressId(addressId);
        builder.setUserName(username);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("更新用户收货地址_"+incomeMessage+builder+ "}");
        return bytes;
    }
    //更新用户收货地址标签
    public static ByteArrayEntity UserAddressTagRequest(String channelUserId,Integer channelId,String addressId,Integer addressTag){
        UserAddressServiceProto.UserAddressTagRequest.Builder builder= UserAddressServiceProto.UserAddressTagRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAddressId(addressId);
        builder.setAddressTag(addressTag);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //分页查询户收货地址列表
    public static ByteArrayEntity userAddressPageRequest(String channelUserId, Integer channelId, Integer pageSize, Integer pageNum){
        UserAddressServiceProto.UserAddressPageRequest.Builder builder=UserAddressServiceProto.UserAddressPageRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setPageNum(pageNum);
        builder.setPageSize(pageSize);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        Reporter.log("分页查询户收货地址列表_"+incomeMessage+builder+ "}");
        return bytes;
    }

    //获取省市区域树
    public static ByteArrayEntity UserSysSubAreaRequest(String parentId){
        UserAddressServiceProto.UserSysSubAreaRequest.Builder builder=UserAddressServiceProto.UserSysSubAreaRequest.newBuilder();
        builder.setParentId(parentId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }

    /**
     * 商品服务
     * @param currentPage
     * @param pageSize
     * @param group  1-社区团购 2.直邮 3.课代表
     * @param group  1-普通商品 2-秒杀商品 3-新人专属 4-团长专属
     * @return
     */

    //分页查询商品列表
    public static ByteArrayEntity getListByPageRequestDTO(Integer currentPage, Integer pageSize, Integer group){
        ProductServiceApiGetListByPage.GetListByPageRequestDTO.Builder builder= ProductServiceApiGetListByPage.GetListByPageRequestDTO.newBuilder();
        builder.setCurrentPage(currentPage);
        builder.setPageSize(pageSize);
        builder.setGroup(group);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }

    //根据ID获取商品详情
    public static ByteArrayEntity getDetailByIdRequestDTO(Long id){
        ProductServiceApiGetDetailById.GetDetailByIdRequestDTO.Builder builder= ProductServiceApiGetDetailById.GetDetailByIdRequestDTO.newBuilder();
        builder.setId(id);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }

    //根据ID集合获取商品详情
    public static ByteArrayEntity getDetailByIdListRequestDTO(Integer mode, Long id, String StoreId){
        ProductServiceApiGetDetailByIdList.GetDetailByIdListRequestDTO.Builder builder=ProductServiceApiGetDetailByIdList.GetDetailByIdListRequestDTO.newBuilder();
        List<ProductServiceApiGetDetailByIdList.GoodIdDTO> goodIdDTOList = Lists.newArrayList();
        ProductServiceApiGetDetailByIdList.GoodIdDTO.Builder goodIdDTOBuilder = ProductServiceApiGetDetailByIdList.GoodIdDTO.newBuilder();
        for (int i=0; i<2; i++) {
            goodIdDTOBuilder.clear();
            goodIdDTOBuilder.setGoodId(id);
            goodIdDTOBuilder.setSkuId(id);
            goodIdDTOBuilder.setStoreId(StoreId);
            goodIdDTOBuilder.setGroup(2);
            goodIdDTOList.add(goodIdDTOBuilder.build());
        }
        builder.addAllGoods(goodIdDTOList);
        //传1
        builder.setMode(mode);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }

    //库存增-减-锁
    public static ByteArrayEntity lockStockByListRequestDto(String porderId, Long GoodId, Long skuId, ProductServiceApiStockService.GoodStockOperateEnum Enum){
        System.out.println(Enum);
        ProductServiceApiStockService.LockStockByListRequestDto.Builder builder = ProductServiceApiStockService.LockStockByListRequestDto.newBuilder();
        builder.setOrderId(porderId);
        ProductServiceApiStockService.LockUserStockDTO.Builder userstockdto = ProductServiceApiStockService.LockUserStockDTO.newBuilder();
        userstockdto.setGoodId(GoodId);
        userstockdto.setSkuId(skuId);
        userstockdto.setNum(1);
        userstockdto.setIsRemoveAdd(false);
        userstockdto.setOrderId(porderId);
        userstockdto.setStockOperate(Enum);
        ProductServiceApiStockService.LockUserStockDTO dto = userstockdto.build();
        //将对象放进集合
        List<ProductServiceApiStockService.LockUserStockDTO> lockUserStockDtoList = new ArrayList<>();
        lockUserStockDtoList.add(dto);
        //将集合放进请求Builder上
        builder.addAllLockUserStockDtos(lockUserStockDtoList);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return  bytes;
    }

    /**
     * @param
     * //用户关系
     */
//    public static ByteArrayEntity MyFansQueryRequest(String ChannelUserId,Integer ChannelId,Integer FansType,SampleResult sr){
//        UserRelationProto.MyFansQueryRequest.Builder builder = UserRelationProto.MyFansQueryRequest.newBuilder();
//        builder.setChannelUserId(ChannelUserId);
//        builder.setChannelId(ChannelId);
//        builder.setFansType(2);
//        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
//        sr.setSamplerData("data:\n"+builder.toString());
//        sr.setDataType(SampleResult.TEXT);
//        return  bytes;
//    }

    //淘宝授权
    public static ByteArrayEntity HsrjUserTaobaoAuthRequest(String channelUserId,Long relationId,Long specialId,Long companyId,Long tbAccountId,String tbAccount){
        HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.Builder builder = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setRelationId(relationId);
        builder.setSpecialId(specialId);
        builder.setCompanyId(companyId);
        builder.setTbAccountId(tbAccountId);
        builder.setTbAccount(tbAccount);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        System.out.println(builder);
        Reporter.log("淘宝授权_"+incomeMessage+builder+ "}");
        return  bytes;
    }

    //用户淘宝取消授权
    public static ByteArrayEntity HsrjUserTaobaoAuthCancelRequest(String channelUserId){
        HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthCancelRequest.Builder builder= HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthCancelRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        System.out.println(builder);
        Reporter.log("用户淘宝取消授权_"+incomeMessage+builder+ "}");
        return  bytes;
    }

    //用户淘宝授权信息查询(
    public static ByteArrayEntity HsrjUserTaobaoAuthQueryRequest(String channelUserId,String tbAccount,Long companyId,Long tbAccountId){
        HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthQueryRequest.Builder builder=HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthQueryRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setTbAccount(tbAccount);
        builder.setCompanyId(companyId);
        builder.setTbAccountId(tbAccountId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        System.out.println(builder);
        Reporter.log("用户淘宝授权信息查询_"+incomeMessage+builder+ "}");
        return  bytes;
    }
}
