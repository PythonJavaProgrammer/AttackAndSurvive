import java.util.*;
import javax.swing.*;

public class Menu {
    /**
     * Displays a menu with the given options and prompts the user to select an option.
     * If the user selects "Exit", the program will exit. If the user selects "Previous Area"
     * and the program is not currently in a special area, the previous area will be loaded.
     * If the user selects "Leave" and the program is currently in a special area, the user
     * will be prompted to leave the special area. If the user selects any other option, that
     * option will be returned.
     *
     * @param  arr  an ArrayList of Strings representing the options to display in the menu
     * @return      an integer representing the selected option
     * */
    public static int optionMenu(ArrayList<String> arr) {

        if (Area.inSpecialArea) {
            arr.add("Leave");
        } else if (Area.inBattle) {
            arr.add("Run");
        } else {
            arr.add("Previous Area");
        }

        arr.add("Exit");

        int selected = 0;
        String selectedSTR;
        StringBuilder options = new StringBuilder();
        String[] amountOfOPtions = new String[arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            options.append("(").append(i + 1).append(")").append(arr.get(i)).append("\n");
            amountOfOPtions[i] = Integer.toString(i + 1);

        }

        while (selected == 0) {
            Object selectedOptions = JOptionPane.showInputDialog(null, "What will you do\n" + options, "Choice", JOptionPane.QUESTION_MESSAGE, null, amountOfOPtions, ((Object[]) amountOfOPtions)[1]);

            selectedSTR = (String) selectedOptions;
            selected = Integer.parseInt(selectedSTR);
        }

        if (selected == arr.size()) {
            switch (Menu.yesNoMenu("Exit", "Are you sure you want to Exit the program")) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    arr.removeLast();
                    arr.removeLast();
                    Menu.optionMenu(arr);
                    break;
                default:
                    throw new AssertionError();
            }
        } else if (selected == arr.size() - 1 && !Area.inSpecialArea) {
            Area.prevArea();
        } else if (selected == arr.size() - 1 && Area.inSpecialArea) {
            Area.leave();
        }

        return selected;
    }

    /**
     * A description of the entire Java function.
     *
     * @param  title     description of parameter
     * @param  question  description of parameter
     * @return          description of return value
     */
    public static int yesNoMenu(String title, String question) {

        int yesNo = JOptionPane.showConfirmDialog(null, question, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (yesNo == 2) {
            System.exit(0);
        }

        return yesNo;
    }

/**
 * Selects a MonsterPocket for the player and configures its properties.
 * This method clears the Area options, adds three options representing different MonsterPockets,
 * and prompts the user to choose one. If the user chooses not to choose a MonsterPocket,
 * the method leaves the current Area. The method then displays a message asking the user
 * to pick one of the three MonsterPockets in front of them.
 * Based on the user's choice, the method sets the player's MonsterPocket name and type.
 * The method then clears the Area options, adds options for selecting a statType, and prompts
 * the user to pick a statType. The method sets the player's MonsterPocket's statType based on
 * the user's choice.
 * The method then sets the player's MonsterPocket's stats and prompts the user to give the
 * MonsterPocket a name. If the user chooses not to give a name, the method generates a default
 * name. If the user chooses to give a name, the method prompts the user to enter a name and
 * asks the user to verify their choice.
 * then makes the user battle Damien.*/
    public static void selectMP() {

        Area.options.clear();

        Area.options.add("Dungar: slight advantage in attacking");
        Area.options.add("Opel: slight advantage in defence");
        Area.options.add("Soloon: slight advantage in health");

        if (yesNoMenu("Choose", "Would you like to choose your MonsterPocket") != 0) {
            Area.leave();
        }

        JOptionPane.showMessageDialog(null, "You can pick one of the three in front of you");

        switch (optionMenu(Area.options)) {
            case 1:
                Player.playerMP.mpName = "Dungar";
                Player.playerMP.name = "Dungar";
                break;
            case 2:
                Player.playerMP.mpName = "Opel";
                Player.playerMP.name = "Opel";
                break;
            case 3:
                Player.playerMP.mpName = "Soloon";
                Player.playerMP.name = "Soloon";
                break;
        }

        Player.HasMP = true;

        Area.options.clear();

        Area.options.add("Attack type");
        Area.options.add("Defence type");
        Area.options.add("Heatlh type");

        JOptionPane.showMessageDialog(null, "Please pick a statType, statTypes determine how your monsterPocket will grow and what buffs it can use");

        switch (Menu.optionMenu(Area.options)) {
            case 1:
                Player.playerMP.statType = "Attack";
                break;
            case 2:
                Player.playerMP.statType = "Defence";
                break;
            case 3:
                Player.playerMP.statType = "Health";
                break;
        }
        
        Player.setMPStats();

        if (yesNoMenu("Choose", "Would you like to give your MonsterPocket a name") != 0) {
            JOptionPane.showMessageDialog(null, "Ok then, enjoy your Adventure and come back soon");
        } else {
            Player.playerMP.name = JOptionPane.showInputDialog("Enter MonsterPocket name");
            while (yesNoMenu("Area you sure?", "You have called your Monster Pocket " + Player.playerMP.name + ".\nIs this what you would like your MonsterPocket's name to be?") != 0) {
                Player.playerMP.name = JOptionPane.showInputDialog("Enter MonsterPocket name");
            }
        }

        JOptionPane.showMessageDialog(null, "Wait! " + Player.name + "!");
        JOptionPane.showMessageDialog(null, "Pheww");
        JOptionPane.showMessageDialog(null, "Now that you have your MonsterPocket");
        JOptionPane.showMessageDialog(null, "Let's battle");

        if (yesNoMenu(":)", "Battle?") == 0) {
            JOptionPane.showMessageDialog(null, "That's what I wanted to hear");
        } else {
            JOptionPane.showMessageDialog(null, "Nice try, but you're not getting away with this");
        }

        Main.Battle(Data.damien);

        if (Data.damien.defeated) {
            JOptionPane.showMessageDialog(null, "Good battle, but I'll win next time");
        }else {
            JOptionPane.showMessageDialog(null, "Get gud nooob!");
        }

        Area.leave();
    }
}
