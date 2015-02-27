package jp.co.brycen.m2m.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.m2m.dto.Row;

@XmlRootElement(name="result")
public class ResultResponse extends AbstractResponse{
	public List<Row> rows;
}
