package jp.sample.jaxrs.service;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class Result {
	public List<Row> rows;
}
