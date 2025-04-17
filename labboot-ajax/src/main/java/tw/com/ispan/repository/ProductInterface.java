package tw.com.ispan.repository;

import java.util.List;

import org.json.JSONObject;

import tw.com.ispan.domain.ProductBean;

public interface ProductInterface {

	long count(JSONObject obj);

	List<ProductBean> find(JSONObject obj);

}