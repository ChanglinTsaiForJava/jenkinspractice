package tw.com.ispan.repository;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.com.ispan.domain.ProductBean;
import tw.com.ispan.util.DatetimeConverter;

@Repository
public class ProductInterfaceImpl implements ProductInterface {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public long count(JSONObject obj) {
		//select count(*)
		//from product
		//where ....

		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String name = obj.isNull("name") ? null : obj.getString("name");
		Double startPrice = obj.isNull("startPrice") ? null : obj.getDouble("startPrice");
		Double endPrice = obj.isNull("endPrice") ? null : obj.getDouble("endPrice");
		String startMake = obj.isNull("startMake") ? null : obj.getString("startMake");
		String endMake = obj.isNull("endMake") ? null : obj.getString("endMake");
		Integer startExpire = obj.isNull("startExpire") ? null : obj.getInt("startExpire");
		Integer endExpire = obj.isNull("endExpire") ? null : obj.getInt("endExpire");

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		
//		from product
		Root<ProductBean> table = criteriaQuery.from(ProductBean.class);
		
//		select count(*)
		criteriaQuery = criteriaQuery.select(criteriaBuilder.count(table));
		
		List<Predicate> predicates = new ArrayList<>();
		if(id!=null) {
			predicates.add(criteriaBuilder.equal(table.get("id"), id));
		}
		if(name!=null && name.length()!=0) {
			predicates.add(criteriaBuilder.like(table.get("name"), "%"+name+"%"));
		}
		if(startPrice!=null) {
			predicates.add(
					criteriaBuilder.greaterThan(table.get("price"), startPrice)
			);
		}
		if(endPrice!=null) {
			predicates.add(
					criteriaBuilder.lessThan(table.get("price"), endPrice)
			);
		}
		if(startMake!=null) {
			java.util.Date date = DatetimeConverter.parse(startMake, "yyyy-MM-dd");
			predicates.add(
					criteriaBuilder.greaterThan(table.get("make"), date)
			);
		}
		if(endMake!=null) {
			java.util.Date date = DatetimeConverter.parse(endMake, "yyyy-MM-dd");
			predicates.add(
					criteriaBuilder.lessThan(table.get("expire"), date)
			);
		}
		if(startExpire!=null) {
			predicates.add(
					criteriaBuilder.greaterThan(table.get("expire"), startExpire)
			);
		}
		if(endExpire!=null) {
			predicates.add(
					criteriaBuilder.lessThan(table.get("expire"), endExpire)
			);
		}
		
//		where ....
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[0]);
			criteriaQuery = criteriaQuery.where(array);
		}
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getSingleResult();
	}
	@Override
	public List<ProductBean> find(JSONObject obj) {
		//select *
		//from product
		//where ....
		//order by ....
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String name = obj.isNull("name") ? null : obj.getString("name");
		Double startPrice = obj.isNull("startPrice") ? null : obj.getDouble("startPrice");
		Double endPrice = obj.isNull("endPrice") ? null : obj.getDouble("endPrice");
		String startMake = obj.isNull("startMake") ? null : obj.getString("startMake");
		String endMake = obj.isNull("endMake") ? null : obj.getString("endMake");
		Integer startExpire = obj.isNull("startExpire") ? null : obj.getInt("startExpire");
		Integer endExpire = obj.isNull("endExpire") ? null : obj.getInt("endExpire");
		
		int start = obj.isNull("start") ? 0 : obj.getInt("start");
		int rows = obj.isNull("rows") ? 10 : obj.getInt("rows");
		boolean dir = obj.isNull("dir") ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") ? "id" : obj.getString("sort");

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductBean> criteriaQuery = criteriaBuilder.createQuery(ProductBean.class);
		
//		from product
		Root<ProductBean> table = criteriaQuery.from(ProductBean.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(id!=null) {
			predicates.add(criteriaBuilder.equal(table.get("id"), id));
		}
		if(name!=null && name.length()!=0) {
			predicates.add(criteriaBuilder.like(table.get("name"), "%"+name+"%"));
		}
		if(startPrice!=null) {
			predicates.add(
					criteriaBuilder.greaterThan(table.get("price"), startPrice)
			);
		}
		if(endPrice!=null) {
			predicates.add(
					criteriaBuilder.lessThan(table.get("price"), endPrice)
			);
		}
		if(startMake!=null) {
			java.util.Date date = DatetimeConverter.parse(startMake, "yyyy-MM-dd");
			predicates.add(
					criteriaBuilder.greaterThan(table.get("make"), date)
			);
		}
		if(endMake!=null) {
			java.util.Date date = DatetimeConverter.parse(endMake, "yyyy-MM-dd");
			predicates.add(
					criteriaBuilder.lessThan(table.get("expire"), date)
			);
		}
		if(startExpire!=null) {
			predicates.add(
					criteriaBuilder.greaterThan(table.get("expire"), startExpire)
			);
		}
		if(endExpire!=null) {
			predicates.add(
					criteriaBuilder.lessThan(table.get("expire"), endExpire)
			);
		}
		
//		where ....
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[0]);
			criteriaQuery = criteriaQuery.where(array);
		}
		
//		order by ....
		if(dir) {
			Order order = criteriaBuilder.desc(table.get(sort));
			criteriaQuery = criteriaQuery.orderBy(order);
		} else {
			Order order = criteriaBuilder.asc(table.get(sort));
			criteriaQuery = criteriaQuery.orderBy(order);
		}
		
		TypedQuery<ProductBean> typedQuery = entityManager.createQuery(criteriaQuery)
				.setFirstResult(start)
				.setMaxResults(rows);
		List<ProductBean> result = typedQuery.getResultList();
		if(result!=null && !result.isEmpty()) {
			return result;
		} else {
			return null;			
		}
	}
}
