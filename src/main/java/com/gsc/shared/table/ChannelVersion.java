package com.gsc.shared.table;

import com.gsc.core.db.annotation.Column;
import com.gsc.core.db.annotation.TableName;
import com.gsc.core.db.table.Table;

@TableName(name = "channel_version")
public class ChannelVersion extends Table {
	
	@Column(alias = "channel_id", pk = true)
	public int channelId;
	
	@Column(alias = "channel_version")
	public int channelVersion;
	
	@Column(alias = "config_params")
	public String configParams;

	@Override
	public long getPkId() {
		return channelId;
	}

	@Override
	public void setPkId(long pk) {
		this.channelId = (int) pk;
	}

}
