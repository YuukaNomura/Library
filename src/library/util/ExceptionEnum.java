package library.util;


/**
 * Exceptionのエラーメッセージ
 * @author nomura
 */
public enum ExceptionEnum {

	NullBlank("NullBlank"),
	LongLength("LongLength"),
	FormatErr("FormatErr");


	private String ErrMsg;

	private ExceptionEnum(String ErrMsg) {
		this.ErrMsg = ErrMsg;
	}

	public String getErrMsg() {
		return this.ErrMsg;
	}


}
