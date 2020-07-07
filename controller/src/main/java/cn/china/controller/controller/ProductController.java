package cn.china.controller.controller;

import cn.china.common.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value ="/selectByProductType",produces = "application/json;charset=utf-8")
    public List<Product> selectByProductType(@RequestParam String type,@RequestParam Integer currentPage,HttpServletResponse res){
        res.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> map=new HashMap<>();
        map.put("type",type);
        map.put("currentPage",currentPage);
        List<Product> list=restTemplate.getForObject("http://cook/selectByProductType?type={type}&currentPage={currentPage}",List.class,map);
       return list;
    }


    @RequestMapping(value ="/getPage",produces = "application/json;charset=utf-8")
    public Integer getPage(@RequestParam String type,HttpServletResponse res){
        res.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> map=new HashMap<>();
        map.put("type",type);
        Integer index=restTemplate.getForObject("http://cook/getPage?type={type}",Integer.class,map);
        return index;
    }

    @RequestMapping(value ="/addCar",produces = "application/json;charset=utf-8")
    public String addCar(@RequestParam Integer id,@RequestParam String url,@RequestParam String name,@RequestParam String price,@RequestParam String username, HttpServletResponse res){

        res.setHeader("Access-Control-Allow-Origin", "*");

        Product product=new Product();
        product.setId(id);
        product.setProductUrl(url);
        product.setProductName(name);
        product.setProductPrice(Double.parseDouble(price));
        product.setProductFlavor(username);//临时占用,无意冒犯
        product.setProductAmount(1);

                    System.out.println("====进入controller");
                    return restTemplate.postForObject("http://user/addCar",product,String.class);

    }





}
