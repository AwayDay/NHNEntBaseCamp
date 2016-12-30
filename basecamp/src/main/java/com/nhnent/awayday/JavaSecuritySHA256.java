package com.nhnent.awayday;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JavaSecuritySHA256 implements JavaSecurity {
	@Override
	public String getEncrypt(String salt, String key) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update((salt+key).getBytes());
			byte[] byteData = md.digest();
			
			StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }
            
            result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
