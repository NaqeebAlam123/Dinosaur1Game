package game;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	/**
	 * Constructor for portable item
	 * @param name name of item
	 * @param displayChar its display character
	 */
	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
}
