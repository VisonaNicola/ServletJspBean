package org.negoziocd.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
/**
 * This class represents the shopping cart of a user.
 * @author nvisona
 *
 */
public class ShoppingCart extends CDList implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private double total;

	/**
	 * A shopping cart is characterized by a list of cds that the user wants to buy, and the total cost for the cds 
	 */
	public ShoppingCart() {
		super();
		total=0.00;
	}
//	@Override
//	public Set<CD> getCdlist(){
//		Set<CD> validcd = new HashSet<>();
//		for(CD cd : cdlist) {
//			if(cd.getQta()>0)
//				validcd.add(cd);
//		}
//		return validcd;
//	}
	
	public double getTotal() {
		return total;
	}
	
	/**
	 * Add a cd to the set.
	 * If the cd is already contained in the set, add 1 to the quantity number.
	 * If the cd is not in the set, add it with quantity 1;
	 * In every case it updates the total variable.
	 */
	public void addCD(CD newcd) {
		if(cdlist.contains(newcd)) {
			for(CD cd : cdlist) {
				if(cd.equals(newcd))
					cd.setQta(cd.getQta()+1);
			}
		}else {
			cdlist.add(newcd);
		}
		total+=newcd.getPrice();
		BigDecimal bd = BigDecimal.valueOf(total);	//round total to the second decimal value, to avoid floating point problems.
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    total= bd.doubleValue();

			
	}
	
	/**
	 * Modify the quantity of a cd in the set.
	 * Modify the total variable according the new quantity.
	 * @param cd the cd that we want to modify the quantity in the cart
	 * @param qta the new quantity of the cd
	 */
	public void modifyQta(CD cd, int qta) {
//		CD cdtoremove=null;		//java.util.ConcurrentModificationException
//		for(CD cd : cdlist) {
//			if(cd.equals(newcd)) {
//				cd.setQta(cd.getQta()+qta);
//				total+=(newcd.getPrice() * qta);
//				if(cd.getQta()<=0)
//					cdtoremove=cd;
//			}
//		}
//		if(cdtoremove!=null)
//			cdlist.remove(cdtoremove);
		
		Iterator<CD> i = cdlist.iterator();
		while(i.hasNext()) {
			CD tempcd = i.next();
			if(tempcd.equals(cd)) {
				int oldqta = tempcd.getQta();
				tempcd.setQta(qta);
				if(tempcd.getQta()<0)
					tempcd.setQta(0);
				total+=(cd.getPrice() * (qta-oldqta));
				BigDecimal bd = BigDecimal.valueOf(total);
			    bd = bd.setScale(2, RoundingMode.HALF_UP);
			    total= bd.doubleValue();
				
			}
		}
		removeNegatives();
	}
	/**
	 * Remove from the set all the cd with an invalid quantity value (<=0)
	 */
	private void removeNegatives() {
		for (Iterator<CD> iterator = cdlist.iterator(); iterator.hasNext();) {
		    CD cd = iterator.next();
		    if (cd.getQta()<=0) {
		        // Remove the current element from the iterator and the list.
		        iterator.remove();
		    }
		}
	}
	
	
}
