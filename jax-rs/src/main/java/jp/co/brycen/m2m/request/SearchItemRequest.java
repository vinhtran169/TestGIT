package jp.co.brycen.m2m.request;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.request.AbstractRequest;

/**
 * The Class SearchItemRequest.
 */
@XmlRootElement(name="SearchItemRequest")
public class SearchItemRequest extends AbstractRequest{
	public String item;
}
