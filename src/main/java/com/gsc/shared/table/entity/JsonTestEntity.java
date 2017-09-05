package com.gsc.shared.table.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.gsc.core.db.JsonEntity;

public class JsonTestEntity extends JsonEntity {

	@JSONField(serialize = false)
	public String stringValue;
	public int intValue;
	public List<String> list;
	public Map<Integer, String> map;

	public static JsonTestEntity valueOf() {
		JsonTestEntity entity = new JsonTestEntity();
		entity.stringValue = "string111";
		entity.intValue = 9999;
		entity.list = new ArrayList<>();
		entity.map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			entity.list.add(String.valueOf(i));
			entity.map.put(i, String.valueOf(i));
		}
		return entity;
	}

	@Override
	public String toString() {
		return "JsonTestEntity [stringValue=" + stringValue + ", intValue=" + intValue + ", list=" + list + ", map=" + map + "]";
	}

}
