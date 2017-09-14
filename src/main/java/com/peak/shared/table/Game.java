package com.peak.shared.table;

import com.peak.core.db.annotation.Column;
import com.peak.core.db.annotation.TableName;
import com.peak.core.db.table.Table;

/**
 * 游戏配置表
 * @author 0x737263
 *
 */
@TableName(name = "game")
public class Game extends Table {

	@Column(alias = "game_id", pk = true)
	public int gameId;

	@Column(alias = "name")
	public String name;

	@Column(alias = "memo")
	public String memo;

	@Override
	public long getPkId() {
		return gameId;
	}

	@Override
	public void setPkId(long pk) {
		this.gameId = (int) pk;
	}

	public static Game valueOf(String name, String memo) {
		Game table = new Game();
		table.name = name;
		table.memo = memo;
		return table;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", name=" + name + ", memo=" + memo + "]";
	}

}
