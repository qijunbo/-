package com.db.model;

import java.util.ArrayList;
import java.util.List;

import com.db.crud.anno.Collection;
import com.db.crud.anno.DateAdapter;

@Collection(name = "User.Agenda")
public class Agenda {

	String title;

	@DateAdapter
	String date;

	List<Item> items;

	String detail;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<Item> getItems() {
		return items;
	}

	public String getTitle() {
		return title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void addItem(Item item) {
		if (items == null) {
			items = new ArrayList<Item>();
		}
		items.add(item);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
