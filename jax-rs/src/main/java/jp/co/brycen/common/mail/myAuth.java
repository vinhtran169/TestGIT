package jp.co.brycen.common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;



/**
 * SMTP認証承認クラス
 */
public class myAuth extends Authenticator {
	
	private String username;
    private String password;
    
    public myAuth(String username, String password){
        this.username = username;
        this.password = password;
    }
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(username,password);
    }	
}
