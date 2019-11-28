//カート画面から決済ボタンを押された場合の処理をするクラス
package com.internousdev.pumpkin.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.pumpkin.dao.DestinationInfoDAO;
import com.internousdev.pumpkin.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementConfirmAction extends ActionSupport implements SessionAware {
	//JSPからの入力値をこのActionファイルで受け取るため、フィールド変数を宣言
	private List<DestinationInfoDTO> destinationInfoDTOList;
	private Map<String, Object>session;

	public String execute() {
		//ログインしているかをチェックし、していない場合セッションエラーを返す。もしもの時
		String tempLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tempLogined)?0:Integer.parseInt(tempLogined);
		if(logined != 1) {
			return "loginError";
		}
		// ログインしている場合は、ユーザーIDを取得する
		String userId = session.get("userId").toString();
		// DAOのインスタンスを生成し宛先情報をDAOに渡すーーユーザーに紐づくーー
		DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
		destinationInfoDTOList = destinationInfoDAO.getDestinationInfo(userId);

		return SUCCESS;
	}
	//jspはこのgetterを経由して値を得る.setterメソッドでJSPからの値を受け取る。
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
