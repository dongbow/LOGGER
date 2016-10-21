package org.demo.logger.result;

public class Result {

	private int rc;
	private String error;
	private Object data;

	public Result() {
	}

	public Result(int rc, String error) {
		this.rc = rc;
		this.error = error;
	}

	public Result(int rc, Object data) {
		this.rc = rc;
		this.data = data;
	}

	public Result(int rc, String error, Object data) {
		this.rc = rc;
		this.error = error;
		this.data = data;
	}

	public int getRc() {
		return rc;
	}

	public void setRc(int rc) {
		this.rc = rc;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
