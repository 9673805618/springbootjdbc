package com.sky.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sky.domain.Category;
import com.sky.domain.Product;

@Repository("productRepositoryDBLimpl")
public class ProductRepositoryDBLimpl implements ProductRepository {

	private final static String INSERT = "INSERT INTO products(productId,productName,productPrice) values(?,?,?)";
//	private final static String ALL_PRODUCT="SELECT productId,productName,productPrice from products";
	private final static String PRODUCT_BY_ID="SELECT productId,productName,productPrice from products WHERE productId=?";
	private final static String UPDATE_PRODUCT="UPDATE products set productName=?, productPrice=? where productId=?";
	private final static String DELETE_PRODUCT="DELETE FROM products where productId=?";
	private final static String ALL_PRODUCT = 
			"Select productId, productName, productPrice , a.catID as categoryID, catName from products a , category b where a.catID = b.catID";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addProduct(Product product) {
		jdbcTemplate.update(INSERT, product.getProductId(), product.getProductName(), product.getPrice());
	}

	@Override
	public List<Product> getProduct() {
		return this.jdbcTemplate.query(ALL_PRODUCT, new ProductRowMapper());
	}

	@Override
	public void deleteproduct(Product product) {
		jdbcTemplate.update(DELETE_PRODUCT, product.getProductId());
	}

	@Override
	public Product getProductByID(String productID) {
		return this.jdbcTemplate.queryForObject(PRODUCT_BY_ID, new Object[]{productID},new ProductRowMapper());
	}

	@Override
	public Product updateByProductID(Product product) {
		jdbcTemplate.update(UPDATE_PRODUCT, product.getProductName(),product.getPrice(),product.getProductId());
		return product;
	}
	private static final class ProductRowMapper implements RowMapper<Product>{
		
		public Product mapRow(ResultSet rs, int rownum) throws SQLException{
			Product product= new Product();
        	product.setProductId(rs.getString("productId"));
        	product.setProductName(rs.getString("productName"));
        	product.setPrice(rs.getInt("productPrice"));
			Category category = new Category();
			category.setCatId(rs.getString("categoryID"));
			category.setCatName(rs.getString("catName"));
			product.setCategory(category);
			return product;
		}
	}

}
