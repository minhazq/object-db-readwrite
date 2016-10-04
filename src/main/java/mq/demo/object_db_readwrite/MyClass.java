package mq.demo.object_db_readwrite;

import java.io.Serializable;

public class MyClass implements Serializable{

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
