package cn.china.user.serviceimpl;

import cn.china.common.entity.Product;
import cn.china.common.entity.User;
import cn.china.user.service.UserService;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Service
@RestController
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper um;

    @Autowired
    ListOperations<String,Object> listOperations;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return um.deleteByPrimaryKey(id);
    }

    @Override
    @RequestMapping(value ="/insert",produces = "application/json;charset=utf-8")
    public int insert(User record) {
        return um.insert(record);
    }

    @Override
    @RequestMapping(value ="/insertSelective",produces = "application/json;charset=utf-8")
    public int insertSelective(@RequestBody User record) {

        return um.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return um.selectByPrimaryKey(id);
    }

    @Override
    public User selectByUsername(String username) {
        return um.selectByUsername(username);
    }

    @RequestMapping(value ="/selectByUsername",produces = "application/json;charset=utf-8")
    public User selectByUsername(@RequestBody User user) {
        System.out.println("---------service"+user.toString());
        System.out.println(user.getUsername());
        return um.selectByUsername(user.getUsername());
    }

    @RequestMapping(value ="/addCar",produces = "application/json;charset=utf-8")
    public String addCar(@RequestBody Product product){

        System.out.println("====进入userService");

       List<Object> list=listOperations.range(product.getProductFlavor(),0,listOperations.size(product.getProductFlavor())-1);
      if(list!=null){
          for(Object obj:list){
              Product pro= (Product) obj;
              if(pro.getId()==product.getId()){
                  pro.setProductAmount(pro.getProductAmount()+1);



                  if(listOperations.leftPushAll(product.getProductFlavor(),list)>0){
                      System.out.println(listOperations.range(product.getProductFlavor(),0,10));
                      return "ok";
                  }else{
                      return "no";
                  }
              }else{
                  if(listOperations.leftPushAll(product.getProductFlavor(),product)>0){
                      System.out.println(listOperations.range("car",0,10));
                      return "ok";
                  }else{
                      return "no";
                  }
              }

          }
      }else{
          if(listOperations.leftPushAll(product.getProductFlavor(),product)>0){
              System.out.println(listOperations.range("car",0,10));
              return "ok";
          }else{
              return "no";
          }
      }

       return "error";



    }



    @Override
    public int updateByPrimaryKeySelective(User record) {
        return um.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return um.updateByPrimaryKey(record);
    }

}
