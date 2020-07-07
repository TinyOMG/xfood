package com.example.cook.serviceimpl;

import cn.china.common.entity.Product;
import com.example.cook.service.ProductService;
import com.github.pagehelper.PageHelper;
import mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Properties;

@Service
@RestController
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper pm;





    @Bean
    public PageHelper pageHelper() {
        System.out.println("MyBatisConfiguration.pageHelper()");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }




    @Override
    public int deleteByPrimaryKey(Integer id) {
        return pm.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Product record) {
        return pm.insert(record);
    }

    @Override
    public int insertSelective(Product record) {
        return pm.insertSelective(record);
    }

    @Override
    public Product selectByPrimaryKey(Integer id) {
        return pm.selectByPrimaryKey(id);
    }


    @Override
    public List<Product> selectByProductType(@RequestParam String type) {
        return pm.selectByProductType(type);
    }

    @RequestMapping(value ="/selectByProductType",produces = "application/json;charset=utf-8")
    public List<Product> selectByProductType(@RequestParam String type, @RequestParam Integer currentPage) {
        System.out.println("========="+type+"--"+currentPage);
        PageHelper.startPage(currentPage,2);//分页语句
        return pm.selectByProductType(type);
    }







    @Override
    @RequestMapping(value ="/getPage",produces = "application/json;charset=utf-8")
    public int getPage(@RequestParam  String type) {
        System.out.println("***********"+type);
        return pm.getPage(type);
    }

    @Override
    public int updateByPrimaryKeySelective(Product record) {
        return pm.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Product record) {
        return pm.updateByPrimaryKey(record);
    }
}
