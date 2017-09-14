package com.peak.shared.table;

import java.util.Map;

import com.peak.core.db.annotation.Column;
import com.peak.core.db.annotation.TableName;
import com.peak.core.db.table.Table;

/**
 * 游戏添加渠道信息表
 * @author 0x737263
 *
 */
@TableName(name = "game_channel_info")
public class GameChannelInfo extends Table {

	@Column(alias = "channel_unique_id", pk = true)
	public long channelUniqueId;

	@Column(alias = "game_id")
	public int gameId;

	@Column(alias = "channel_id")
	public int channelId;
	
	@Column(alias = "channel_version")
	public long channleVersion;
	
	@Column(alias = "has_old_user")
	public short hashOldUser;

	@Column(alias = "config_info")
	public Map<String, String> configInfo;

	@Override
	public long getPkId() {
		return channelUniqueId;
	}

	@Override
	public void setPkId(long pk) {
		this.channelUniqueId = pk;
	}

}
