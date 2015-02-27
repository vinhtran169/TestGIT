package jp.co.brycen.m2m.request;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.request.AbstractRequest;

/**
 * The Class DeleteItemRequest.
 */
@XmlRootElement(name="DeleteItemRequest")
public class DeleteItemRequest extends AbstractRequest{
	public int id;

}
