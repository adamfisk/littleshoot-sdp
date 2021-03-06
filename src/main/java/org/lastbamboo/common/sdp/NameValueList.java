/*******************************************************************************
* Product of NIST/ITL Advanced Networking Technologies Division (ANTD).        *
*******************************************************************************/
package org.lastbamboo.common.sdp;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
* Implements a simple NameValue association with a quick lookup 
* function (via a hash table) this class is not thread safe 
* because it uses HashTables.
*
*@version  JAIN-SIP-1.1
*
*@author M. Ranganathan <mranga@nist.gov>  <br/>
*
*<a href="{@docRoot}/uncopyright.html">This code is in the public domain.</a>
*
*/

public class NameValueList extends GenericObjectList {

	public NameValueList(String listName) {
		super(listName, NameValue.class);
	}

	public void add(NameValue nv) {
		if (nv == null)
			throw new NullPointerException("null nv");
		super.add(nv);
	}

	/**
	* Set a namevalue object in this list.
	*/

	public void set(NameValue nv) {
		this.delete(nv.name);
		this.add(nv);
	}

	/**
	* Set a namevalue object in this list.
	*/
	public void set(String name, Object value) {
		NameValue nv = new NameValue(name, value);
		this.set(nv);
	}

	/**
	* Add a name value record to this list.
	*/

	public void add(String name, Object obj) {
		NameValue nv = new NameValue(name, obj);
		add(nv);
	}

	/**
	     *  Compare if two NameValue lists are equal.
	 *@param otherObject  is the object to compare to.
	 *@return true if the two objects compare for equality.
	     */
	public boolean equals(Object otherObject) {
		if (!otherObject.getClass().equals(this.getClass())) {
			return false;
		}
		NameValueList other = (NameValueList) otherObject;

		if (this.size() != other.size()) {
			return false;
		}
		ListIterator li = this.listIterator();

		while (li.hasNext()) {
			NameValue nv = (NameValue) li.next();
			boolean found = false;
			ListIterator li1 = other.listIterator();
			while (li1.hasNext()) {
				NameValue nv1 = (NameValue) li1.next();
				// found a match so break;
				if (nv.equals(nv1)) {
					found = true;
					break;
				}
			}
			if (!found)
				return false;
		}
		return true;
	}

	/**
	*  Do a lookup on a given name and return value associated with it.
	*/
	public Object getValue(String name) {
		NameValue nv = this.getNameValue(name);
		if (nv != null)
			return nv.value;
		else
			return null;
	}

	/**
	* Get the NameValue record given a name.
	* @since 1.0
	*/
	public NameValue getNameValue(String name) {
		ListIterator li = this.listIterator();

		NameValue retval = null;
		while (li.hasNext()) {
			NameValue nv = (NameValue) li.next();
			if (nv.getName().equalsIgnoreCase(name)) {
				retval = nv;
				break;
			}
		}
		return retval;
	}

	/**
	* Returns a boolean telling if this NameValueList
	* has a record with this name  
	* @since 1.0
	*/
	public boolean hasNameValue(String name) {
		return getNameValue(name) != null;
	}

	/**
	* Remove the element corresponding to this name.
	* @since 1.0
	*/
	public boolean delete(String name) {
		ListIterator li = this.listIterator();
		NameValue nv;
		boolean removed = false;
		while (li.hasNext()) {
			nv = (NameValue) li.next();
			if (nv.getName().equalsIgnoreCase(name)) {
				li.remove();
				removed = true;
			}
		}
		return removed;

	}

	/**
	 *Get the list iterator for this list.
	 */
	public Iterator getIterator() {
		return super.getIterator();
	}

	/**
	 *Get a list of parameter names.
	 *@return a list iterator that has the names of the parameters.
	 */
	public Iterator getNames() {
		LinkedList ll = new LinkedList();
		Iterator iterator = this.getIterator();
		while (iterator.hasNext()) {
			String name = ((NameValue) iterator.next()).name;
			ll.add(name);
		}
		return ll.listIterator();
	}

	/**
	 *default constructor.
	 */
	public NameValueList() {
	}

	/** Get the parameter as a String.
	 *@return the parameter as a string.
	 */
	public String getParameter(String name) {
		Object val = this.getValue(name);
		if (val == null)
			return null;
		if (val instanceof GenericObject)
			return ((GenericObject) val).encode();
		else
			return val.toString();
	}
}
