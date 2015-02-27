package jp.co.brycen.m2m.response;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.response.AbstractResponse;

/**
 * The Class UpdateResultResponse.
 */
@XmlRootElement(name = "result")
public class UpdateResultResponse extends AbstractResponse {

	/** The result. */
	public Boolean result = false;
}
