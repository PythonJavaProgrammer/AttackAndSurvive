import javax.swing.*;

public class Main {

    static boolean hasSave;

    /**
     * Conducts a battle between the player's monster pocket and an NPC.
     * Manages turns, actions, and outcomes of the battle.
     *
     * @param  npc    the non-player character to battle against
     */

    public static void Battle(NPC npc) {

        Area.inBattle = true;
        boolean npcTurn = false;

        Data.printData(Player.playerMP);
        Data.printData(npc);

        while (Player.playerMP.hp > 0 && npc.hp > 0) {

            if (npcTurn) {
                npc.npcAction(npc);
                npcTurn = false;
            } else {
                Player.action(npc);
                npcTurn = true;
            }

            Data.printData(Player.playerMP);
            Data.printData(npc);

        }

        if (Player.playerMP.hp <= 0) {
            JOptionPane.showMessageDialog(null, Player.playerMP.name + " fainted");
            gameOver();
        } else {
            npc.defeated = true;
            Player.money += npc.money;
            Player.playerMP.exp += npc.exp;

            JOptionPane.showMessageDialog(null, "You succesfully defeated " + npc.name);
            JOptionPane.showMessageDialog(null, "You recieve R" + npc.money);
            JOptionPane.showMessageDialog(null, "You receive " + npc.exp + "EXP");
        }

        NPC.setAttributes(npc);
        Player.setMPStats();
    }

    /**
     * Starts the game.
     * This method clears the options in the Area class.
     * It checks if the user wants to play the game by displaying a yes/no menu.
     * If the user chooses not to play, the program exits.
     * If a save has been detected, the method prompts the user to load the save.
     * If the user chooses to load the save, it displays a welcome message and navigates to the Area.leave() method.
     * If the user chooses not to load the save, it prompts the user to enter a player name.
     * If the user enters a name, it displays a welcome message and navigates to the Area.village() method.
     * If no save has been detected, the method prompts the user to enter a player name.
     * If the user enters a name, it displays a welcome message, saves the data, and navigates to the Area.village() method.
     *
     */

    public static void startGame() {


        Area.options.clear();

        if (Menu.yesNoMenu("PlayGame?", "Would you like to play MonsterPocket") != 0) {
            System.exit(0);
        }

        if (hasSave) {

            switch (Menu.yesNoMenu("UseSave?", "A save as been detected, Load Save?")) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Welcome " + Player.name);
                    
                    Area.leave();
                    break;
                case 1:
                    hasSave = false;

                    do {
                        Player.name = JOptionPane.showInputDialog("Please enter player name");
                    } while (Menu.yesNoMenu("Sure?", "You have chosen " + Player.name + " " + "will this be your name") != 0);

                    JOptionPane.showMessageDialog(null, "Welcome " + Player.name);

                    Area.village();

                    break;
            }

        } else {

            do {
                Player.name = JOptionPane.showInputDialog("Please enter player name");
            } while (Menu.yesNoMenu("Sure?", "You have chosen " + Player.name + "Will this be your name") != 0);

            JOptionPane.showMessageDialog(null, "Welcome " + Player.name);

            saveData();
            Area.village();

        }

    }
    /**
     * Handles the game over scenario by prompting the user to start or exit the game.
     */
    public static void gameOver() {
        
        if (Menu.yesNoMenu("Start?", "Will you continue") != 0) {
            saveData();
            System.exit(0);
        }
        else {
            JOptionPane.showMessageDialog(null, "May you win this time");
            Area.mpCenter();
        }
        
    }

    /**
     * Handles the ending scenario by displaying the received ending to the player.
     */
    public static void ending() {
        JOptionPane.showMessageDialog(null, "You have received the [" + Player.ending.toUpperCase() + "] Ending");
        
    }
    
    public static void retrieveData() {
        //this will retrieve any save data
    }

    public static void saveData() {
        //this will save data
    }

    /**
     * Generates a random integer between the specified minimum and maximum values (inclusive).
     *
     * @param  min  the minimum value of the range
     * @param  max  the maximum value of the range
     * @return      a random integer between min and max (inclusive)
     */
    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static void main(String[] args) {
        Data.runData();
        retrieveData();

        startGame();
    }
}
