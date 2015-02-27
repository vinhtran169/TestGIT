package jp.co.brycen.common.mail;

import java.io.ByteArrayOutputStream;
import java.security.spec.KeySpec;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;

import org.apache.commons.codec.binary.Base64;

public class mail {
	private String strSendId;
	private String strAddress;
	private String strCC;
	private String strBCC;
	private String strTitle;
	private String strMailBody;
	private String strMsgID;

	public static final String CRYPTER_KEY = "fdsmt";
	public static final String CRYPTER_TYPE_DEC = "dec";
	public static final String CRYPTER_TYPE_ENC = "enc";

	/**
	 * strSendIdを取得します。
	 * @return strSendId
	 */
	public String getStrSendId() {
	    return strSendId;
	}
	/**
	 * strSendIdを設定します。
	 * @param strSendId strSendId
	 */
	public void setStrSendId(String strSendId) {
	    this.strSendId = strSendId;
	}
	/**
	 * strAddressを取得します。
	 * @return strAddress
	 */
	public String getStrAddress() {
	    return strAddress;
	}
	/**
	 * strAddressを設定します。
	 * @param strAddress strAddress
	 */
	public void setStrAddress(String strAddress) {
	    this.strAddress = strAddress;
	}
	/**
	 * strCCを取得します。
	 * @return strCC
	 */
	public String getStrCC() {
	    return strCC;
	}
	/**
	 * strCCを設定します。
	 * @param strCC strCC
	 */
	public void setStrCC(String strCC) {
	    this.strCC = strCC;
	}
	/**
	 * strBCCを取得します。
	 * @return strBCC
	 */
	public String getStrBCC() {
	    return strBCC;
	}
	/**
	 * strBCCを設定します。
	 * @param strBCC strBCC
	 */
	public void setStrBCC(String strBCC) {
	    this.strBCC = strBCC;
	}
	/**
	 * strTitleを取得します。
	 * @return strTitle
	 */
	public String getStrTitle() {
	    return strTitle;
	}
	/**
	 * strTitleを設定します。
	 * @param strTitle strTitle
	 */
	public void setStrTitle(String strTitle) {
	    this.strTitle = strTitle;
	}
	/**
	 * strMailBodyを取得します。
	 * @return strMailBody
	 */
	public String getStrMailBody() {
	    return strMailBody;
	}
	/**
	 * strMailBodyを設定します。
	 * @param strMailBody strMailBody
	 */
	public void setStrMailBody(String strMailBody) {
	    this.strMailBody = strMailBody;
	}
	public String getStrMsgID() {
		return strMsgID;
	}
	public void setStrMsgID(String strMsgID) {
		this.strMsgID = strMsgID;
	}

	public enum JobKind{InReplyTo,References,MSGID};

	/***********************************************************************
	 * メールヘッダからキー値を取得する
	 * @throws MessagingException
	 **********************************************************************/

	public static String [] GetHeaderVal(Message message,JobKind kind) throws MessagingException{

		String strReferencesVal[] = null;
		String StrVal;

		if(kind.equals(JobKind.InReplyTo)){
			StrVal="In-Reply-To";
		}
		else if(kind.equals(JobKind.MSGID)){
			StrVal="Message-Id";
		}
		else{
			StrVal="References";
		}
		//In-Reply-To:
		Enumeration<Header> allHeaders2 = null;
		try {
			//メールヘッダを取得する
			allHeaders2 = message.getAllHeaders();
		}
		catch (MessagingException e) {
			throw e;
		}
		while (allHeaders2.hasMoreElements()) {
			Header header = allHeaders2.nextElement();
			if (header.getName().equals(StrVal)){
				strReferencesVal=header.getValue().split(" ");
			}
	    }
		return strReferencesVal;
	}

	/**
	 * @param str 暗号復号する文字列
	 * @param type enc:暗号、dec:復号
	 * @return String 暗号復号された文字列
	 * @throws Exception
	 */
	public String decryption(String str, String type) throws Exception {

		byte[] tmpKeyValue = CRYPTER_KEY.getBytes("UTF-8");
		byte[] keyValue = new byte[8];
		for(int i=0;i<tmpKeyValue.length;i++) {
			keyValue[i] = tmpKeyValue[i];
		}
		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		try {
			KeySpec keySpec = new DESKeySpec(keyValue);
			SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
			IvParameterSpec iv = new IvParameterSpec(keyValue);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

			if (CRYPTER_TYPE_ENC.equals(type)) {
				cipher.init(Cipher.ENCRYPT_MODE,key,iv);
		    	bout.write(cipher.doFinal(str.getBytes("UTF-8")));
			    return new String(Base64.encodeBase64(bout.toByteArray()),"UTF-8");
			}

			if (CRYPTER_TYPE_DEC.equals(type)) {
				cipher.init(Cipher.DECRYPT_MODE,key,iv);
				byte[] decoded = Base64.decodeBase64(str.getBytes("UTF-8"));// works with apache.commons.codec-1.3
				bout.write(cipher.doFinal(decoded));
				return new String(bout.toByteArray(),"UTF-8");
			}
		} catch(Exception e) {
			throw e;
		}
		return "";
	}
}
