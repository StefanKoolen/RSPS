package server.model.players;

public class Hit {
	public CombatType combatType;
	public int damage;
	
	public Hit(int damage, CombatType combatType) {
		this.damage = damage;
		this.combatType = combatType;
	}
	
	/**
	 * Handles the combat Style icon of an individual hit. 
	 * @author Advocatus <davidcntt@hotmail.com>
	 *
	 */
	public static enum CombatType {
		
		/**
		 * The hit was performed by utilizing the attack
		 * strength or defence skills.
		 */
	    MELEE	(0),
	    
	    /**
	     * The hit was performed by utilizing the magic skill.
	     */
	    MAGE	(2),
	    
	    /**
	     * The hit was performed by utilizing the ranged skill.
	     */
	    RANGE	(1),
	    
	    /**
	     * Designates that the hit was either 0 or null.
	     */
	    BLOCK	(3), NONE (3);
	    
		/**
		 * The combat affinity of this hit.
		 */
		private final int affinity;
		
		/**
		 * Designates the hits combat affinity.
		 * @param affinity The combat affinity of the hit.
		 */
		private CombatType(int affinity) {
			this.affinity = affinity;
		}

		/**
		 * Gets the combat affinity of the hit.
		 * @return The hits combat affinity
		 */
		public int getAffinity() {
			return this.affinity;
		}
	}

}
