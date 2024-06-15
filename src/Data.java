import javax.swing.*;

public class Data {

    //opel: Defence, dungar: damage, soloon: health, kripted: all
    static NPC damien = new NPC(1, "Damien", "Dungar", "Attack");

    static NPC tChristiaan = new NPC(5, "Christiaan", "Opel", "Defence");
    static NPC tJirah = new NPC(8, "Jirah", "Dungar", "Attack");
    static NPC tLiam = new NPC(10, "Liam", "Soloon", "Health");
    static NPC tDave = new NPC(15, "David", "Soloon", "Defence");

    static NPC gSiegfried = new NPC(18, "SeigFry", "Opel", "Attack");
    
    static NPC mKripted = new NPC(20, "Kripted", "Kripted", "All");

    /**
     * Initializes the data for the game by setting attributes for the NPCs and player's MonsterPocket.*
     * This function sets the attributes for each NPC in the game, including Damien, tChristiaan, tJirah, tLiam, tDave,
     * gSiegfried, and mKripted. It also sets the stats for the player's MonsterPocket by calling the setMPStats() method
     * in the Player class.
     */
    public static void runData() {

        NPC.setAttributes(damien);
        NPC.setAttributes(tChristiaan);
        NPC.setAttributes(tJirah);
        NPC.setAttributes(tLiam);
        NPC.setAttributes(tDave);
        NPC.setAttributes(gSiegfried);
        NPC.setAttributes(mKripted);

        Player.setMPStats();
    }

    /**
     * Displays a dialog box with the name, level, hit points (HP), attack (ATK), and defense (DEF) of the given NPC.
     *
     * @param  npc  the NPC whose data is displayed
     */
    public static void printData(NPC npc) {
        JOptionPane.showMessageDialog(null, "Name: " + npc.name + "\nLevel: " + npc.level + "/20\nHP: " + npc.hp + "/" + npc.baseStats[0] + "\nATK: " + npc.damage + "\nDEF: " + npc.defence);
    }
}
