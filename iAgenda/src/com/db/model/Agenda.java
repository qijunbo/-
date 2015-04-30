package com.db.model;

import java.util.Date;
import java.util.List;

import com.db.crud.anno.Collection;
import com.db.crud.anno.DateAdapter;

@Collection(name = "User.Agenda")
public class Agenda {

	String title;
	
	@DateAdapter
	Date date;

	List<Item> detail;

	public Date getDate() {
		return date;
	}

	public List<Item> getDetail() {
		return detail;
	}

	public String getTitle() {
		return title;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDetail(List<Item> detail) {
		this.detail = detail;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
