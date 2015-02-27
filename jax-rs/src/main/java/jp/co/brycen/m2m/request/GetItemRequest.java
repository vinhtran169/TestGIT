package jp.co.brycen.m2m.request;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.request.AbstractRequest;

/**
 * The Class GetItemRequest.
 */
@XmlRootElement(name="GetItemRequest")
public class GetItemRequest extends AbstractRequest{

	/** The id. */
	public int id;
}
