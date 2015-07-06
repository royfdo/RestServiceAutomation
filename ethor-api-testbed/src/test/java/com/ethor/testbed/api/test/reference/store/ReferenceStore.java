package com.ethor.testbed.api.test.reference.store;

import java.util.ArrayList;
import java.util.List;

import com.ethor.testbed.api.domain.category.Category;

/**
 * Main class which store received details from the API.
 * 
 * @author Roy Fernando.
 */
public class ReferenceStore {

	private List<ChainStore> chainStores = new ArrayList<ChainStore>();

	public List<ChainStore> getChainStores() {
		return chainStores;
	}

	public void setChainStores(final List<ChainStore> chainStores) {
		this.chainStores = chainStores;
	}

	public boolean containsChain(final String chainId) {

		if (chainStores != null) {
			for (ChainStore chainStore : chainStores) {
				if (chainStore.getChain().getId().equals(chainId)) {
					return true;
				}
			}
		}
		return false;
	}

	public ChainStore getChainStoreByChainId(final String chainId) {
		if (chainStores != null) {
			for (ChainStore chainStore : chainStores) {
				if (chainStore.getChain().getId().equals(chainId)) {
					return chainStore;
				}
			}
		}
		throw new IllegalArgumentException("Chain not found for chain id <" + chainId + ">");
	}

	public Category getCategory(final String chainId, final String restaurantId, final String categoryName) {
		return getChainStoreByChainId(chainId).getRestaurantStoreByRestaurantId(restaurantId)
				.getCategoryStoreByCategoryName(categoryName).getCategory();
	}
}
