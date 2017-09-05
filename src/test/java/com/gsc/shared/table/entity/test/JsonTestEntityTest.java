package com.gsc.shared.table.entity.test;

import java.util.ArrayList;
import java.util.HashMap;

import com.gsc.shared.table.entity.JsonTestEntity;

public class JsonTestEntityTest {

	public static void main(String[] args) {
		JsonTestEntity entity = new JsonTestEntity();
		entity.stringValue = "string111";
		entity.intValue = 9999;
		entity.list = new ArrayList<>();
		entity.map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			entity.list.add(String.valueOf(i));
			entity.map.put(i, String.valueOf(i));
		}
		System.out.println(entity.toString());
	}

}
