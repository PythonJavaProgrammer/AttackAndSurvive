public class Player {

    static String name;
    static String statType;

    static NPC playerMP = new NPC(1, "", "", "");

    static int hp;
    static int damage;
    static int defence;
    static int exp = 0;

    static String ending;
    
    static boolean HasMP = false;
    static boolean HasGoneHome = false;
    static boolean DiscoveredSecret = false;

    static int courage = 0;
    static int money = 0;

    /**
     * Executes the player's action based on the selected option from the menu.
     *
     * @param enemy the NPC enemy that the player is interacting with
     */
    public static void action(NPC enemy) {
        Area.options.clear();

        Area.options.add("Attack");
        Area.options.add("Buff");
        Area.options.add("Heal");

        switch (Menu.optionMenu(Area.options)) {
            case 1:
                playerMP.attack(playerMP, enemy);
                break;
            case 2:
                playerMP.buff(playerMP);
                break;
            case 3:
                playerMP.heal(playerMP);
                break;
            default:
                throw new AssertionError();
        }

    }

    /**
     * Sets the stats of the player's MonsterPocket (playerMP) based on the player's experience points (exp).
     * The damage and defence of the playerMP are set to the corresponding values in the baseStats array.
     * The playerMP's hit points (hp) are decremented until they are below the base hit points.
     * The level of the playerMP is calculated based on the player's experience points.
     * The playerMP's level is set to the calculated level.
     * The playerMP's attributes are set based on its statType using the NPC.setAttributes method.
     */
    public static void setMPStats() { 

        playerMP.damage = playerMP.baseStats[1];
        playerMP.defence = playerMP.baseStats[2];
        
        while (playerMP.hp >= playerMP.baseStats[0]) {
            playerMP.hp--;
        }
        
        int level = 1;
        
        while (getLevel(level) <= playerMP.exp) {
            level++;
        }
        while (level > 20) {
            level--;
        }
        
        
        playerMP.level = level;

        NPC.setAttributes(playerMP);
    }

    /**
     * Calculates the level of the given player based on their experience points.
     * @param x the player's experience points
     * @return the player's level
     */
    public static int getLevel(int x) {
        double a, b;
        int y;
        
        a = 1.7;
        b = 2;
        
        y = (int) (a * Math.pow(b, x));
        
        return y;
    }

}
