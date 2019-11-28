<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="s" uri ="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" href="./css/button.css">
<link rel="stylesheet" href="./css/commonMessage.css">
<link rel="stylesheet" href="./css/header_h1.css">
<link rel="stylesheet" href="./css/tableform.css">

<meta charset="UTF-8">
<title>決済確認</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<script src="./js/pumpkin.js"></script>
	<div class="contents">
		<h1>決済確認画面</h1>
		<s:if test="destinationInfoDTOList!=null && destinationInfoDTOList.size()>0">

		<div class="info">宛先情報を選択してください。</div>
		<s:form class="settlementConfirmForm">

		<table>
				<thead>
				<tr>
						<th><s:label value ="#"/></th>
						<th><s:label value ="姓"/></th>
						<th><s:label value ="名"/></th>
						<th><s:label value ="ふりがな"/></th>
						<th><s:label value ="住所"/></th>
						<th><s:label value ="電話番号"/></th>
						<th><s:label value ="メールアドレス"/></th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value ="destinationInfoDTOList" status="st">
				<tr>
					<td>
						<s:if test="#st.index == 0">
						<input type="radio" name="id" checked="checked" value="<s:property value='id'/>"/>
						</s:if><s:else>
						<input type="radio" name="id" value="<s:property value='id'/>"/>
						</s:else>
					</td>
					<td>
						<s:property value ="familyName"/>
					</td>
					<td>
						<s:property value ="firstName"/>
					</td>
					<td>
						<s:property value ="familyNameKana"/><span>　</span><s:property value ="firstNameKana"/><br>
					</td>
					<td>
						<s:property value ="userAddress"/>
					</td>
					<td>
						<s:property value ="telNumber"/>
					</td>
					<td>
						<s:property value ="email"/>
					</td>
				</tr>
				</s:iterator>
				</tbody>
		</table>
		 <!-- ここでsタグを使用しない理由：教材の範囲内の内容で作成するため。 -->
		 		<div class="contents-btn-set">
					<input type ="button" value="決済" class="btn" onClick ="submitAction('SettlementCompleteAction')" />
				</div>
				<div class="contents-btn-set">
					<input type ="button" value ="削除" class="btn" onClick ="submitAction('DeleteDestinationAction')"/>
				</div>
		</s:form>
		</s:if>
		<s:else>
			<div class="info">
				宛先情報がありません。
			</div>
		</s:else>
			<div class="contents-btn-set">
				<s:form action="CreateDestinationAction">
					<input type="submit" value="新規宛先登録" class="btn" />
				</s:form>
			</div>
	</div>
</body>
</html>