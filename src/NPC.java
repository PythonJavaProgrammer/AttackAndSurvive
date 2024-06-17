import javax.swing.*;

public class NPC {

    int level;
    int money;
    int exp;
    String statType;
    String name;
    String mpName;

    boolean defeated = false;

    int hp, damage, defence;

    int[] baseStats = new int[3];

    public NPC(int level, String name, String mpName, String statType) {
        this.level = level;
        this.name = name;
        this.mpName = mpName;
        this.statType = statType;

    }

    /**
     * Executes a random action for the given NPC.
     *
     * @param  npc  the NPC for which the action is executed
     */
    public void npcAction(NPC npc) {

        int action = Main.random(0, npc.level + 2);

        if (action >= 14) {
            npc.heal(npc);
        } else if (action > 7 && action <= 9) {
            npc.buff(npc);
        } else {
            npc.attack(npc, Player.playerMP);
        }
    }

    /**
     * Generates a random encounter with an NPC, determines the stat type of the NPC,
     * creates the NPC based on the stat type, sets attributes for the NPC, and initiates a battle
     * or allows the player to continue exploring based on a random chance.
     */
    public static void randomEncounter() {

        NPC mMP = null;

        boolean startBattle = false;
        String statType = switch (Main.random(1, 3)) {
            case 1 -> "Attack";
            case 2 -> "Health";
            case 3 -> "Defence";
            default -> "";
        };

        mMP = switch (Main.random(1, 3)) {
            case 1 -> new NPC(Main.random(Area.AreaMinLevel, Area.AreaMaxLevel), "Dungar", "Dungar", statType);
            case 2 -> new NPC(Main.random(Area.AreaMinLevel, Area.AreaMaxLevel), "Soloon", "Soloon", statType);
            case 3 -> new NPC(Main.random(Area.AreaMinLevel, Area.AreaMaxLevel), "Opel", "Opel", statType);
            default -> mMP;
        };

        assert mMP != null;
        setAttributes(mMP);
        mMP.money = 0;

        if (Main.random(0, 100) <= 85) {
            startBattle = true;
        }

        if (startBattle) {
            JOptionPane.showMessageDialog(null, "You encountered a wild " + mMP.name);
            Main.Battle(mMP);
        } else {
            JOptionPane.showMessageDialog(null, "You encountered nothing");
            Area.leave();
        }
    }

    /**
     * Sets the attributes of the given NPC based on its stat type.
     *
     * @param  npc  the NPC whose attributes are set
     */
    public static void setAttributes(NPC npc) {

        int hp = 10;
        int damage = 3;
        int defence = 0;

        

        switch (npc.statType) {
            case "Health":
                hp = 15;
                damage = 2;
                break;
            case "Attack":
                damage = 5;
                hp = 8;
                break;
            case "Defence":
                defence = 1;
                hp = 9;
                break;
            case "All":
                hp = 15;
                damage = 5;
                defence = 2;
                break;
        }

        switch (npc.mpName) {
            case "Dungar":
               damage += 1;
                break;
            case "Opel":
                defence++;
                break;
            case "Soloon":
                hp += 3;
                break;
            default:
        }

        
        npc.hp = hp + (npc.level/3);
        npc.damage = damage + (npc.level/4);
        npc.defence = defence + ((npc.level/10)*3);
        
        if (npc != Player.playerMP) {
            npc.money = npc.level * 1000;
            npc.exp = npc.level * 100;
        }

        npc.baseStats[0] = npc.hp;
        npc.baseStats[1] = npc.damage;
        npc.baseStats[2] = npc.defence;

    }

    /**
     * Executes an attack action between two NPCs.
     *
     * @param  npc    the NPC initiating the attack
     * @param  enemy  the NPC being attacked
     */
    public void attack(NPC npc, NPC enemy) {

        JOptionPane.showMessageDialog(null, npc.name + " attacks");

        int damage = Main.random(npc.damage - 2, npc.damage);

        if (enemy.defence < damage) {

            damage -= enemy.defence;
            enemy.hp -= damage;

            JOptionPane.showMessageDialog(null, damage + " damage was dealt");

        } else {

            JOptionPane.showMessageDialog(null, enemy.name + "'s defence was too high and 0 damage was dealt");

        }

    }

    /**
     * Executes a buff action for the given NPC based on its stat type.
     *
     * @param  npc  the NPC to buff
     */
    public void buff(NPC npc) {
        JOptionPane.showMessageDialog(null, npc.name + " buffed their " + npc.statType);

        switch (npc.statType) {
            case "Attack":
                npc.damage = npc.baseStats[1] + npc.damage / 10;
                break;
            case "Defence":
                npc.defence = npc.baseStats[2] + npc.defence / 10;
                break;
            case "Health":
                npc.hp = npc.baseStats[0] + npc.hp / 10;
                break;
            default:
                System.out.println("You mispelled something");
        }

    }

    /**
     * Executes a heal action for the given NPC.
     *
     * @param  npc  the NPC to heal
     */
    public void heal(NPC npc) {

        JOptionPane.showMessageDialog(null, npc.name + " healed themselves");

        int healAmount = npc.hp - Main.random(npc.level, npc.hp / 2);
        npc.hp += healAmount;

        JOptionPane.showMessageDialog(null, healAmount + " hp was gained");

        while (npc.hp > npc.baseStats[0]) {
            npc.hp--;
        }
    }
}
