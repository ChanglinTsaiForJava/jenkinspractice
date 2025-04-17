package tw.com.ispan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.domain.ProductBean;
import tw.com.ispan.repository.ProductRepository;
import tw.com.ispan.util.DatetimeConverter;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public long count(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return productRepository.count(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<ProductBean> find(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return productRepository.find(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ProductBean findById(Integer id) {
		if(id!=null) {
			Optional<ProductBean> optional = this.productRepository.findById(id);
			if(optional!=null && optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}

	public boolean exists(Integer id) {
		if(id!=null) {
			return this.productRepository.existsById(id);
		}
		return false;
	}
	
	public ProductBean create(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String name = obj.isNull("name") ? null : obj.getString("name");
			Double price = obj.isNull("price") ? null : obj.getDouble("price");
			String make = obj.isNull("make") ? null : obj.getString("make");
			Integer expire = obj.isNull("expire") ? null : obj.getInt("expire");		
			if(id!=null) {
				Optional<ProductBean> optional = this.productRepository.findById(id);
				if(optional!=null && optional.isEmpty()) {
					ProductBean insert = new ProductBean();
					insert.setId(id);
					insert.setName(name);
					insert.setPrice(price);
					insert.setMake(DatetimeConverter.parse(make, "yyyy-MM-dd"));
					insert.setExpire(expire);
					return this.productRepository.save(insert);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ProductBean modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String name = obj.isNull("name") ? null : obj.getString("name");
			Double price = obj.isNull("price") ? null : obj.getDouble("price");
			String make = obj.isNull("make") ? null : obj.getString("make");
			Integer expire = obj.isNull("expire") ? null : obj.getInt("expire");

			if(id!=null) {
				Optional<ProductBean> optional = this.productRepository.findById(id);
				if(optional!=null && optional.isPresent()) {
					ProductBean update = optional.get();
					update.setName(name);
					update.setPrice(price);
					update.setMake(DatetimeConverter.parse(make, "yyyy-MM-dd"));
					update.setExpire(expire);
					
					return this.productRepository.save(update);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean remove(Integer id) {
		if(id!=null) {
			Optional<ProductBean> optional = this.productRepository.findById(id);
			if(optional!=null && optional.isPresent()) {
				try {
					this.productRepository.deleteById(id);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		}
		return false;
	}
	
	public List<ProductBean> select(ProductBean bean) {
		List<ProductBean> result = null;
		if(bean!=null && bean.getId()!=null && !bean.getId().equals(0)) {
			Optional<ProductBean> optional = this.productRepository.findById(bean.getId());
			if(optional!=null && optional.isPresent()) {
				ProductBean temp = optional.get();
				result = new ArrayList<ProductBean>();
				result.add(temp);
			}
		} else {
			result = this.productRepository.findAll();
		}
		return result;
	}
	
	public ProductBean insert(ProductBean bean) {
		if(bean!=null && bean.getId()!=null) {
			Optional<ProductBean> optional = this.productRepository.findById(bean.getId());
			if(optional!=null && optional.isEmpty()) {
				return this.productRepository.save(bean);
			}
		}
		return null;
	}
	
	public ProductBean update(ProductBean bean) {
		if(bean!=null && bean.getId()!=null) {
			Optional<ProductBean> optional = this.productRepository.findById(bean.getId());
			if(optional!=null && optional.isPresent()) {
				return this.productRepository.save(bean);
			}
		}
		return null;
	}
	
	public boolean delete(ProductBean bean) {
		if(bean!=null && bean.getId()!=null) {
			Optional<ProductBean> optional = this.productRepository.findById(bean.getId());
			if(optional!=null && optional.isPresent()) {
				ProductBean delete = optional.get();
				this.productRepository.delete(delete);
				return true;
			}
		}
		return false;
	}
}
