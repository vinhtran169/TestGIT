package jp.co.brycen.m2m.request;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.request.AbstractRequest;

/**
 * The Class UpdateItemRequest.
 */
@XmlRootElement(name="UpdateItemRequest")
public class UpdateItemRequest extends AbstractRequest{

	/** The id. */
	public int id;

	/** The message. */
	public String message;
}
