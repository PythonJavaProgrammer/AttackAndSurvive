/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.*;
import javax.swing.*;

/**
 *
 * @author csbot
 */
public class Menu {

    public static int optionMenu(ArrayList arr) {

        if (Area.inSpecialArea == true) {
            arr.add("Leave");
        } else if (Area.inBattle == true) {
            arr.add("Run");
        } else {
            arr.add("Previous Area");
        }

        arr.add("Exit");

        int selected = 0;
        String selectedSTR;
        String options = "";
        String[] amountOfOPtions = new String[arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            options = options + "(" + (i + 1) + ")" + arr.get(i).toString() + "\n";
            amountOfOPtions[i] = Integer.toString(i + 1);

        }

        while (selected == 0) {
            Object[] possibleValues = amountOfOPtions;
            Object selectedOptions = JOptionPane.showInputDialog(null, "What will you do\n" + options, "Choice", JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues[1]);

            selectedSTR = (String) selectedOptions;
            selected = Integer.parseInt(selectedSTR);
        }

        if (selected == arr.size()) {
            switch (Menu.yesNoMenu("Exit", "Are you sure you want to Exit the program")) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    arr.remove(arr.size() - 1);
                    arr.remove(arr.size() - 1);
                    Menu.optionMenu(arr);
                    break;
                default:
                    throw new AssertionError();
            }
        } else if (selected == arr.size() - 1 && Area.inSpecialArea == false) {
            Area.prevArea();
        } else if (selected == arr.size() - 1 && Area.inSpecialArea == true) {
            Area.leave();
        }

        return selected;
    }

    public static int yesNoMenu(String title, String question) {

        int yesNo = JOptionPane.showConfirmDialog(null, question, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (yesNo == 2) {
            System.exit(0);
        }

        return yesNo;
    }

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

        if (Data.damien.defeated == true) {
            JOptionPane.showMessageDialog(null, "Good battle, but I'll win next time");
        }else {
            JOptionPane.showMessageDialog(null, "Get gud nooob!");
        }

        Area.leave();
    }
}
