package jp.sample.jaxrs.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Row {
	public int id;
	public String message;
}
