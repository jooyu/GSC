package com.gsc.shared.table;

import com.gsc.core.db.annotation.Column;
import com.gsc.core.db.annotation.TableName;
import com.gsc.core.db.table.Table;

@TableName(name = "user")
public class User extends Table {
	
	@Column(alias = "game_id")
	public int gameId;
	
	@Column(alias = "open_id", pk = true)
	public String openId;
	
	@Column(alias = "channel_id")
	public int channelId;
	
	@Column(alias = "channel_uid")
	public String channelUid;
	
	@Column(alias = "ledou_player_id")
	public long ledouPlayerId;

	public static User valueOf(int gameId, String openId, int channelId, String channelUid, long ledouPlayerId) {
		User user = new User();
		user.gameId = gameId;
		user.openId = openId;
		user.channelId = channelId;
		user.channelUid = channelUid;
		user.ledouPlayerId = ledouPlayerId;
		return user;
	}
	

	@Override
	public long getPkId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPkId(long pk) {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return "User [gameId=" + gameId + ", openId=" + openId + ", channelId=" + channelId + ", channelUid=" + channelUid + ", ledouPlayerId="
				+ ledouPlayerId + "]";
	}
	
}
