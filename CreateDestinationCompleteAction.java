//宛先情報入力確認画面で登録ボタンが押された場合の処理をするクラス
package com.internousdev.pumpkin.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.pumpkin.dao.DestinationInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationCompleteAction extends ActionSupport implements SessionAware {
	//JSPからの入力値をこのActionファイルで受け取るため、フィールド変数を宣言
	private Map<String, Object> session;
	public String execute() {
		String tempLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tempLogined)?0:Integer.parseInt(tempLogined);
		if(logined != 1) {
			return "loginError";
		}
		//宛先情報を取得　DAOのインスタンスを生成
		//設計書によってNullPになっても良いところはVofを使う
		String result = ERROR;
		DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
		int count = destinationInfoDAO.insert(
				session.get("userId").toString(),
				session.get("familyName").toString(),
				session.get("firstName").toString(),
				String.valueOf(session.get("familyNameKana")),
				String.valueOf(session.get("firstNameKana")),
				String.valueOf(session.get("userAddress")),
				String.valueOf(session.get("telNumber")),
				session.get("email").toString()
				);
		if(count > 0) {
			result = SUCCESS;
		}
		// 宛先情報入力確認画面から遷移した場合に、宛先情報がsessionに入っているため削除
		session.remove("familyName");
		session.remove("firstName");
		session.remove("familyNameKana");
		session.remove("firstNameKana");
		session.remove("email");
		session.remove("telNumber");
		session.remove("userAddress");

		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
