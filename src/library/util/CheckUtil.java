package library.util;

/**
 * 共通チェック処理
 * @author nomura
 */
public class CheckUtil {
	private static final String STR_SET = "0123456789０１２３４５６７８９";
	private static final String STR_ALPHA_NUM = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 数字チェック
	 *
	 * @param Sting argInputString 検査する文字
	 * @return boolean  判定結果(true：数字,false：数字以外)
	 * */
	public static boolean checkNumberFormat(String argInputString) {
		String moji = "";

		//入力されたデータの文字列長分繰り返す
		for(int i = 0; i < argInputString.length(); i++) {

			//入力されたデータから1文字取得
			moji = argInputString.substring(i, i + 1);

			//入力されたデータに数字以外が含まれる場合
			if(STR_SET.indexOf(moji) == -1) {
				return false;

			}
		}
		return true;
	}

	/**
	 * 西暦フォーマットチェック（4桁の数値でなければエラー）
	 *
	 * @param Sting argInputString 検査する文字
	 * @return boolean 正誤
	 * */
	public static boolean checkYearFormat(String argInputString) {
		if(checkNumberFormat(argInputString) ) {
			if(argInputString.length() < 5){
				return true;
			}
		}
		return false;
	}

	/**
	 * NULL＆空文字チェック（NULLまたは空文字ならエラー）
	 * @param strValue 検証文字列
	 * @return 判定結果(true：NULLまたは空文字以外,false：NULLまたは空文字)
	 */
	public static boolean checkNullBlank(String strValue) {
		if(strValue == null || "".equals(strValue)) {
			return false;
		}
		return true;
	}


	/**
	 * 半角英数チェック
	 *
	 * @param Sting argInputString 検査する文字
	 * @return boolean  判定結果(true：半角英数字,false：半角英数字以外)
	 * */
	public static boolean checkAlphaNumFormat(String argInputString) {
		String moji = "";

		//入力されたデータの文字列長分繰り返す
		for(int i = 0; i < argInputString.length(); i++) {

			//入力されたデータから1文字取得
			moji = argInputString.substring(i, i + 1);

			//入力されたデータに数字以外が含まれる場合
			if(STR_ALPHA_NUM.indexOf(moji) == -1) {
				return false;

			}
		}
		return true;
	}
}
