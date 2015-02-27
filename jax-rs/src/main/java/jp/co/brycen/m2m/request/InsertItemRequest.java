package jp.co.brycen.m2m.request;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.request.AbstractRequest;

/**
 * The Class InsertItemRequest.
 */
@XmlRootElement(name="InsertItemRequest")
public class InsertItemRequest extends AbstractRequest{
//	public int id;
	public String message;

}
