package tw.com.ispan.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.ProductBean;
import tw.com.ispan.dto.ProductResponse;
import tw.com.ispan.service.ProductService;
import tw.com.ispan.util.DatetimeConverter;

@RestController
@RequestMapping("/ajax/pages/products")
public class ProductAjaxController {
    
    @Autowired
    private ProductService productService;

    @DeleteMapping("/{id}")
    public ProductResponse remove(@PathVariable Integer id) {
        ProductResponse response = new ProductResponse();
        if(id==null) {
            response.setSuccess(false);
            response.setMessage("Id是必要欄位");
        } else if(!productService.exists(id)) {
            response.setSuccess(false);
            response.setMessage("Id不存在");
        } else {
            if(!productService.remove(id)) {
                response.setSuccess(false);
                response.setMessage("刪除失敗");
            } else {
                response.setSuccess(true);
                response.setMessage("刪除成功");
            }
        }
        return response;
    }

    @PutMapping("/{id}")
    public ProductResponse modify(@PathVariable Integer id, @RequestBody ProductBean body) {
        ProductResponse response = new ProductResponse();
        if(id==null) {
            response.setSuccess(false);
            response.setMessage("Id是必要欄位");
        } else if(!productService.exists(id)) {
            response.setSuccess(false);
            response.setMessage("Id不存在");
        } else {
            ProductBean product = productService.update(body);
            if(product==null) {
                response.setSuccess(false);
                response.setMessage("修改失敗");
            } else {
                response.setSuccess(true);
                response.setMessage("修改成功");
            }
        }
        return response;
    }

    @PostMapping
    public ProductResponse create(@RequestBody String entity) {
        ProductResponse response = new ProductResponse();
        JSONObject obj = new JSONObject(entity);
        Integer id = obj.isNull("id") ? null : obj.getInt("id");
        if(id==null) {
            response.setSuccess(false);
            response.setMessage("Id是必要欄位");
        } else if(productService.exists(id)) {
            response.setSuccess(false);
            response.setMessage("Id存在");
        } else {
            ProductBean product = productService.create(entity);
            if(product==null) {
                response.setSuccess(false);
                response.setMessage("新增失敗");
            } else {
                response.setSuccess(true);
                response.setMessage("新增成功");
            }
        }
        return response;
    }

    @PostMapping("/find")
    public ProductResponse find(@RequestBody String entity) {
        ProductResponse response = new ProductResponse();

        long count = productService.count(entity);
        response.setCount(count);

        List<ProductBean> products = productService.find(entity);
        if(products!=null && !products.isEmpty()) {
            response.setList(products);
        } else {
            response.setList(new ArrayList<>());
        }
        return response;
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable(name = "id") Integer id) {
        JSONObject responseBody = new JSONObject();
        JSONArray array = new JSONArray();
        if(id!=null) {
            ProductBean product = productService.findById(id);
            if(product!=null) {
                String make = DatetimeConverter.toString(product.getMake(), "yyyy-MM-dd");
                JSONObject item = new JSONObject()
                        .put("id", product.getId())
                        .put("name", product.getName())
                        .put("price", product.getPrice())
                        .put("make", make)
                        .put("expire", product.getExpire());
                array = array.put(item);
            }
        }
        responseBody.put("list", array);
        return responseBody.toString();
    }



}
