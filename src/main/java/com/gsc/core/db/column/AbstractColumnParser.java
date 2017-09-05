package com.gsc.core.db.column;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gsc.core.db.table.Table;
import com.gsc.core.db.table.TableInfo.TableColumnInfo;

public abstract class AbstractColumnParser {

	//读数据抽象方法
	public abstract void readColumn(Table instance, TableColumnInfo columnInfo, ResultSet rs) throws SQLException;
	//写数据抽象方法
	public abstract void writeColumn(ArrayList<Object> writeList, TableColumnInfo columnInfo, Object fieldValue);
}
