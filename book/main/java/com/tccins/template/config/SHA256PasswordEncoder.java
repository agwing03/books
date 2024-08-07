package com.tccins.template.config;

import java.security.MessageDigest;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SHA256PasswordEncoder implements PasswordEncoder{
	 	@Override
	    public String encode(CharSequence rawPassword) {
	        if (rawPassword == null) {
	            throw new IllegalArgumentException("rawPassword cannot be null");
	        }
	        return this.getSHA256(rawPassword);
	    }

	    @Override
	    public boolean matches(CharSequence rawPassword, String encodedPassword) {
	        if (rawPassword == null) {
	            throw new IllegalArgumentException("rawPassword cannot be null");
	        }
	        if (encodedPassword == null || encodedPassword.length() == 0) {
	            return false;
	        }

	        String encodedRawPw = this.getSHA256(rawPassword);
	        if (encodedPassword.length() != encodedRawPw.length()) {
	            return false;
	        }
	        for (int i = 0; i < encodedPassword.length(); i++) {
	            if (encodedPassword.charAt(i) != encodedRawPw.charAt(i))
	                return false;
	        }
	        return true;
	    }

		public String getSHA256(CharSequence rawPassword) {
			String SHA = "";
			try {
				MessageDigest sh = MessageDigest.getInstance("SHA-256");
				sh.update(rawPassword.toString().getBytes());
				byte byteData[] = sh.digest();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
				SHA = sb.toString();
				log.debug(SHA);
			} catch (Exception e) {
				log.debug(e.getMessage());
				e.printStackTrace();
				SHA = null;
			}
			return SHA;
		}
}
