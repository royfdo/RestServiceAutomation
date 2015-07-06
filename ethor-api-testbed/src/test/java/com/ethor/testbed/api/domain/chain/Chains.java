package com.ethor.testbed.api.domain.chain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This class holds list of chains.
 * 
 * @author Roy Fernando.
 */

@XmlRootElement(name = "chains")
public class Chains {

	@JsonProperty(value = "chains")
	private List<Chain> chains;

	public List<Chain> getChains() {
		return this.chains;
	}

	@XmlElement(name = "chain")
	public void setChains(List<Chain> objects) {
		this.chains = objects;
	}

	public Chain getChainById(final String chainId) {
		if (chains != null) {
			for (Chain chainObj : chains) {
				if (chainObj.getId().equals(chainId)) {
					return chainObj;
				}
			}
		}
		throw new IllegalArgumentException("Chain not found for id <" + chainId + ">");
	}
	
	public Chain getChainByName(final String chainName) {
		if (chains != null) {
			for (Chain chainObj : chains) {
				if (chainObj.getName().trim().equals(chainName.trim())) {
					return chainObj;
				}
			}
		}
		throw new IllegalArgumentException("Chain not found for name <" + chainName + ">");
	}

}
