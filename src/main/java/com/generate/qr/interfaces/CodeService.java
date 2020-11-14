package com.generate.qr.interfaces;

public interface CodeService {

	public String generateCode(String data) throws Exception;

	public String getDataCode(String data) throws Exception;

}
