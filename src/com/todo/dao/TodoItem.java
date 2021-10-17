package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
	private String title;
	private String desc;
	private String current_date;
	private String due_date;
	private String category;
	private int importance;
	private String location;
	private int done;
	private String doneStr;


	public TodoItem(String title, String desc, String category, String due_date, int importance, String location) {
		this.title = title;
		this.desc = desc;
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
		this.current_date = f.format(new Date());
		this.category = category;
		this.due_date = due_date;
		this.importance = importance;
		this.location = location;
		this.done = 0;
	}

	public int isDone() {
		return done;
	}
	
	public void setDone(int done) {
		this.done = done;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCurrent_date() {
		return current_date;
	}

	public void setCurrent_date(String current_date) {
		this.current_date = current_date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	@Override
	public String toString() {
		if (done == 0)
			doneStr = "Not Done";
		else 
			doneStr = "Done";
		return id + " [" + category + "]( �߿䵵: " + importance + " ) " + title + " - " + desc + " - " + location + " - " + due_date + " - " + current_date + " - " + doneStr;
	}

	public String toSaveString() {
		return title + "##" + desc + "##" + current_date + "##" + category + "##" + due_date + "\n";
	}

	public void setId(int id) {
		this.id = id;

	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
}
