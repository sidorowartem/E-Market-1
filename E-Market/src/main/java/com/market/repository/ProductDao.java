package com.market.repository;

import com.market.model.Product;
import com.market.model.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDao {
    private static String SQL_GET_ALL_PRODUCTS = "select id, name, price, description, amount from product";
    private static String SQL_GET_PRODUCTS_BY_CATEGORY_ID = "select p.id, p.name, p.price, p.description, p.amount, p.category_id " +
            "from product p INNER JOIN categories c ON (p.category_id = c.id) WHERE c.id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SQL_GET_ALL_PRODUCTS, new ProductRowMapper());
    }

    public List<Product> getAllProductsInCategory(int category_id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("category_id", category_id);
        return jdbcTemplate.query(SQL_GET_PRODUCTS_BY_CATEGORY_ID,new Object[] { category_id }, new ProductRowMapper());
    }
}
