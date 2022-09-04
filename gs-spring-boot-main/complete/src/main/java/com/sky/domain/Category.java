package com.sky.domain;

import java.util.Objects;

public class Category {

	private String catId;
	private String catName;
	private int price;
	private Category category;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(String catId, String catName, int price, Category category) {
		super();
		this.catId = catId;
		this.catName = catName;
		this.price = price;
		this.category = category;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Category [catId=" + catId + ", catName=" + catName + ", price=" + price + ", category=" + category
				+ "]";
	}
	
	
	
	
	
}
