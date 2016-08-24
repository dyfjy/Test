package org.andy.exception;


/**
 * 异常处理类
 * 
 * @author wy
 * 
 */
@SuppressWarnings("serial")
public class OperationException extends Exception {
	private int messageID = 0;
	private String message = "";

	public OperationException(String message) {
		super(message);
		this.message = message;
	}

	public OperationException(String message, int messageID) {
		super(message);
		this.message = message;
		this.messageID = messageID;
	}

	public OperationException(int messageID) {
		this.messageID = messageID;
	}

	public String toString() {
		return message;
	}

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
