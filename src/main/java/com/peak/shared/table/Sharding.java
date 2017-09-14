package com.peak.shared.table;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.peak.core.db.annotation.Column;
import com.peak.core.db.annotation.TableName;
import com.peak.core.db.table.Table;

/**
 * 
 * @author 0x737263
 *
 */
@TableName(name = "sharding_configs")
public class Sharding extends Table {

	@Column(alias = "auto_id", pk = true)
	public long autoId;
	
	@Column(alias = "game_id")
	public int gameId;
	
	@Column(alias = "db_host")
	public String dbHost;
	
	@Column(alias = "db_user")
	public String dbUser;
	
	@Column(alias = "db_password")
	public String dbPassword;
	
	@Column(alias = "db_name")
	public String dbName;

	@Override
	public long getPkId() {
		return autoId;
	}

	@Override
	public void setPkId(long pk) {
		this.autoId = pk;
	}
	
	@Override
	public Table mapRow(ResultSet rs, int rowNum) throws SQLException {
		return super.mapRow(rs, rowNum);
	}

	@Override
	public String toString() {
		return "Sharding [autoId=" + autoId + ", gameId=" + gameId + ", dbHost=" + dbHost + ", dbUser=" + dbUser + ", dbPassword=" + dbPassword
				+ ", dbName=" + dbName + "]";
	}


}
