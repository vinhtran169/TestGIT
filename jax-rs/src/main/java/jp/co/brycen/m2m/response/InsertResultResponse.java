package jp.co.brycen.m2m.response;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.response.AbstractResponse;

/**
 * The Class InsertResultResponse.
 */
@XmlRootElement(name = "result")
public class InsertResultResponse extends AbstractResponse {

	public Boolean result = false;
}
