package com.peak.shared.table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.peak.core.db.annotation.Column;
import com.peak.core.db.annotation.TableName;
import com.peak.core.db.table.Table;
import com.peak.shared.table.entity.JsonTestEntity;

@TableName(name = "test_type", isIdBuilder = true, startId = 10000)
public class TestType extends Table {

	@Column(alias = "long_type", pk = true)
	public long longType;

	@Column(alias = "bool_type")
	public boolean boolType;

	@Column(alias = "int_type")
	public int intType;

	@Column(alias = "decimal_type")
	public BigDecimal decimalType;

	@Column(alias = "string_type")
	public String stringType;

	@Column(alias = "byte_array_type")
	public byte[] byteArrayType;

	@Column(alias = "list_type")
	public List<String> listType;

	@Column(alias = "map_type")
	public Map<Integer, Integer> mapType = new ConcurrentHashMap<>();

	@Column(alias = "json_obj_type")
	public JsonTestEntity jsonEntity;
	
	@Column(alias = "entity_maps_type")
	public Map<Integer, JsonTestEntity> entityMaps = new ConcurrentHashMap<>();

	@Override
	public long getPkId() {
		return longType;
	}

	@Override
	public void setPkId(long pk) {
		this.longType = pk;
	}

	public static TestType valueOf() {
		TestType table = new TestType();
		table.boolType = true;
		table.intType = 1;
		table.decimalType = new BigDecimal(99);
		table.stringType = "fadsfasfasdf";
		table.byteArrayType = new byte[5];
		table.listType = new ArrayList<>();
		table.mapType = new HashMap<>();
		table.entityMaps = new HashMap<>();

		for (int i = 0; i < 20; i++) {
			table.listType.add(String.valueOf(i));
			table.mapType.put(i, i);
			table.entityMaps.put(i, JsonTestEntity.valueOf());
		}
		table.jsonEntity = JsonTestEntity.valueOf();
		
		return table;
	}

	@Override
	public String toString() {
		return "TestType [longType=" + longType + ", boolType=" + boolType + ", intType=" + intType + ", decimalType=" + decimalType + ", stringType="
				+ stringType + ", byteArrayType=" + Arrays.toString(byteArrayType) + ", listType=" + listType + ", mapType=" + mapType
				+ ", jsonEntity=" + jsonEntity + "]";
	}
}
