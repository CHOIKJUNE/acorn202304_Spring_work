package com.gura.spring05.exception;

public class NotDeleteException extends RuntimeException{

	/*
	 * 예외 클래스를 만드는 방법
	 * 
	 * 1.RuntimeException 클래스를 상속받아서 만든다.
	 * 2.String type을 전달받는 생성자를 정의한 다음 생성자에 전달되는 문자열을 부모 생성자에
	 * 전달하는 코드를 작성한다.
	 * - 이 클래스로 생성된 객체는 getMessage()라는 메서드를 가지고 있는데 (Throwable클래스를 상속받은 Exception클래스를 상속받은
	 * RuntimeException클래스를 상속받은 NotDeleteException클래스이기 때문에 메서드는 오버라이딩 되어 getMessage를 사용할 수 있다.
	 * 해당 메서드는 생성자에 전달받았던 예외 메세지를 리턴해준다.
	 * 
	 * ex)
	 * NotDeleteException nde = new NotDeleteException("oh! no");
	 * String msg = nde.getMessage(); // "oh! no"
	 * 
	 * - 어디에선가 이 예외를 발생시키고 싶으면 throw예약어를 활용하면 된다.
	 * 
	 * ex)
	 * throw new NotDeleteException("남의 파일 지우기 없기");
	 */
	public NotDeleteException(String message) {
		super(message); //최종적으로 Throwable클래스에 detailMessage로 저장된다.
	}
}
