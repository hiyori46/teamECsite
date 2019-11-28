//決済確認画面で削除ボタンが押された場合、宛先削除する為のクラス
package com.internousdev.pumpkin.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.pumpkin.dao.DestinationInfoDAO;
import com.internousdev.pumpkin.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteDestinationAction extends ActionSupport implements SessionAware {
	//JSPからの入力値をこのActionファイルで受け取るため、フィールド変数を宣言
	private String id;
	private List<DestinationInfoDTO> destinationInfoDTOList;
	private Map<String, Object>session;

	public String execute() {
		String tempLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tempLogined)?0:Integer.parseInt(tempLogined);
		if(logined != 1) {
			return "loginError";
		}
		//宛先情報を取得　宛先情報があれば消去
		String result = ERROR;
		DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
		int count = destinationInfoDAO.deleteDestination(id);
		if(count > 0) {
			destinationInfoDTOList = destinationInfoDAO.getDestinationInfo(session.get("userId").toString());
			result = SUCCESS;
		}
		return result;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<DestinationInfoDTO> getDestinationInfoDTOList(){
		return destinationInfoDTOList;
	}
	public void setDestinationInfoDTOList(List<DestinationInfoDTO> destinationInfoDTOList) {
		this.destinationInfoDTOList = destinationInfoDTOList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}