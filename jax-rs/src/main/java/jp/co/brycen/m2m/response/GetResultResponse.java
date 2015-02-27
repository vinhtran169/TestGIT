package jp.co.brycen.m2m.response;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.m2m.dto.Row;

/**
 * The Class GetResultResponse.
 */
@XmlRootElement(name="result")
public class GetResultResponse extends AbstractResponse{

	/** The row. */
	public Row row;
}
