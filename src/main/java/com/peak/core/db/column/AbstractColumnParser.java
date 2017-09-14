package com.peak.core.db.column;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.peak.core.db.table.Table;
import com.peak.core.db.table.TableInfo.TableColumnInfo;

public abstract class AbstractColumnParser {

	public abstract void readColumn(Table instance, TableColumnInfo columnInfo, ResultSet rs) throws SQLException;

	public abstract void writeColumn(ArrayList<Object> writeList, TableColumnInfo columnInfo, Object fieldValue);
}
