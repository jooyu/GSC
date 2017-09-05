package com.gsc.shared.table;

import java.util.Map;

import com.gsc.core.db.annotation.Column;
import com.gsc.core.db.annotation.TableName;
import com.gsc.core.db.table.Table;

@TableName(name = "order")
public class Order extends Table {

	@Column(alias = "order_id", pk = true)
	public String orderId;

	@Column(alias = "channel_order_id")
	public String channelOrderId;

	@Column(alias = "pay_order_id")
	public String payOrderId;

	@Column(alias = "type")
	public String type;

	@Column(alias = "paymethod")
	public int payMethod;

	@Column(alias = "channel_id")
	public int channelId;

	@Column(alias = "channel_identifier")
	public String channelIdentifier;

	@Column(alias = "game_id")
	public int gameId;

	@Column(alias = "server_id")
	public int serverId;

	@Column(alias = "server_uid")
	public String serverUid;

	@Column(alias = "open_id")
	public String openId;

	@Column(alias = "product_id")
	public long productId;

	@Column(alias = "extral_info")
	public String extralInfo;

	@Column(alias = "quantity")
	public int quantity;

	@Column(alias = "price")
	public int price;

	@Column(alias = "recharge")
	public int recharge;

	@Column(alias = "recharge_time")
	public int rechargeTime;

	@Column(alias = "pay_state")
	public int payState;

	@Column(alias = "notice_state")
	public int noticeState;

	@Column(alias = "order_create_data")
	public Map<String, String> orderCreateData;

	@Column(alias = "order_callback_data")
	public Map<String, String> orderCallbackData;

	@Column(alias = "order_cp_data")
	public Map<String, String> orderCPData;

	@Column(alias = "sync_time")
	public int syncTime;

	@Column(alias = "created_at")
	public int createdAt;

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
		return "Order [orderId=" + orderId + ", channelOrderId=" + channelOrderId + ", payOrderid=" + payOrderId + ", type=" + type + ", payMethod="
				+ payMethod + ", channelId=" + channelId + ", channelIdentifier=" + channelIdentifier + ", gameId=" + gameId + ", serverId="
				+ serverId + ", serverUid=" + serverUid + ", openId=" + openId + ", productId=" + productId + ", extralInfo=" + extralInfo
				+ ", quantity=" + quantity + ", price=" + price + ", recharge=" + recharge + ", rechargeTime=" + rechargeTime + ", payState="
				+ payState + ", noticeState=" + noticeState + ", orderCreateData=" + orderCreateData + ", orderCallbackData=" + orderCallbackData
				+ ", orderCPData=" + orderCPData + ", syncTime=" + syncTime + ", createdAt=" + createdAt + "]";
	}

}
