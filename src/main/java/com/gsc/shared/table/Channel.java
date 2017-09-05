package com.gsc.shared.table;

import com.gsc.core.db.annotation.Column;
import com.gsc.core.db.annotation.TableName;
import com.gsc.core.db.table.Table;

/**
 * 渠道配置表
 * @author 0x737263
 *
 */
@TableName(name = "channel")
public class Channel extends Table {

	@Column(alias = "channel_id", pk = true)
	public int channelId;

	@Column(alias = "name")
	public String name;

	@Column(alias = "en_name")
	public String enName;
	
	@Column(alias = "platform")
	public int platform;

	@Column(alias = "memo")
	public String memo;

	@Override
	public long getPkId() {
		return channelId;
	}

	@Override
	public void setPkId(long pk) {
		this.channelId = (int) pk;
	}

	public static Channel valueOf(String name, String enName, int platform, String memo) {
		Channel table = new Channel();
		table.name = name;
		table.enName = enName;
		table.platform = platform;
		table.memo = memo;
		return table;
	}

}
