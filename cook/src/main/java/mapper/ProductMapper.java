package mapper;


import cn.china.common.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);
    List<Product> selectByProductType(String type);
    int getPage(String type);
    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}