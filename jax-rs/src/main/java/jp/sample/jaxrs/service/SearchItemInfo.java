package jp.sample.jaxrs.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SearchItemInfo")
public class SearchItemInfo {
	public String item;
}
