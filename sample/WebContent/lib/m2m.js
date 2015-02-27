
/**
 * Webサービスからの戻り値にエラーが含まれているかチェック
 * エラーがある場合はPopUpでエラー内容を表示
 * @param res:::Webサービスからの戻り値
 * @returns true::エラーなし / false::エラーあり
 */
function checkError(res){
	try{
		// res.errorクラスのjsonがあればエラーが発生している
		var code = res.error.errorCode;
		var msg = res.error.errorMessage;
		dispError(code, msg);

		return false;
	} catch (e){
		// エラーなし
		return true;
	}
}


/**
 * エラーの内容をポップアップで表示する
 * @param code::エラーコード
 * @param msg::エラーメッセージ
 */
function dispError(code, msg){
	var errorStr = '';
	errorStr += "エラーが発生しました。\n\n";
	errorStr += "エラーコード:" + code + "\n";
	errorStr += "メッセージ:" + msg + "\n";

	alert(errorStr);
	return;
}