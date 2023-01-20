package org.negoziocd.beans;

import java.util.Objects;
/**
 * This class represents a cd that is sold in the cd market
 * @author nvisona
 *
 */
public class CD implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String title, author;
	private double price;
	private int id, qta;
	public CD() {
		
	}
	/**
	 * Create a new cd
	 * @param id the id of the cd
	 * @param title title of the cd
	 * @param author author of the cd
	 * @param price price of the cd
	 */
	public CD(int id,String title, String author, double price) {
		this.id=id;
		this.title = title;
		this.author = author;
		this.price = price;
		qta=1;
	}
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}	
	public int getQta() {
		return qta;
	}
	public void setQta(int qta) {
		this.qta = qta;
	}
	
	
	@Override
	public String toString() {
		return title + " "+author+" "+price;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(author, price, title);
	}
	
	/**
	 * Two cds are considered the same cd if they have the same id, name, author and price.
	 */	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CD other = (CD) obj;
		return id == other.getId()
				&& Objects.equals(author, other.author)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(title, other.title);
	}
	
}
