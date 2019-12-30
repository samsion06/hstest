package com.example.utils;

import com.google.common.collect.Lists;
import com.hs.productservice.api.proto.getdetailbyid.ProductServiceApiGetDetailById;
import com.hs.productservice.api.proto.getdetailbyidlist.ProductServiceApiGetDetailByIdList;
import com.hs.productservice.api.proto.getlistbypage.ProductServiceApiGetListByPage;
import com.hs.productservice.api.proto.lockuserstock.ProductServiceApiStockService;
import com.hs.user.rel.proto.UserRelationProto;
import org.apache.http.entity.ByteArrayEntity;

import java.util.ArrayList;
import java.util.List;

public class JmeterTransferUtil {

    //商品服务
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
     * 用户关系（接口压力测试）
     */
    public static ByteArrayEntity MyFansQueryRequest(String ChannelUserId,Integer ChannelId,Integer FansType){
        UserRelationProto.MyFansQueryRequest.Builder builder = UserRelationProto.MyFansQueryRequest.newBuilder();
        builder.setChannelUserId(ChannelUserId);
        builder.setChannelId(ChannelId);
        builder.setFansType(2);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return  bytes;
    }


}
