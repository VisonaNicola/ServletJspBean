package org.negoziocd.beans;

import java.util.HashSet;
import java.util.Set;

/**
 * A abstract class used to create ShoppingCart and CDMarket
 * @author nvisona
 *
 */
public abstract class CDList {
	public  HashSet<CD> cdlist = new HashSet<CD>();
	public Set<CD> getCdlist(){
		return cdlist;
	}
	public void addCD(CD newcd) {
		if(cdlist.contains(newcd)) {
			for(CD cd : cdlist) {
				if(cd.equals(newcd))
					cd.setQta(cd.getQta()+1);
			}
		}else {
			cdlist.add(newcd);
		}
			
	}
}
