package my.example.message;

import my.example.exception.BaseException;

public class Information extends BaseException {
	
	private static final long serialVersionUID = 1L;

	public Information(String code) {
        super(code);
    }

    public static Information valueOf(String str) {
        return new Information(str);
    }

}
