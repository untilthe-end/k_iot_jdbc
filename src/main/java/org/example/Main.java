package org.example;

import org.example.manager.MemberManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MemberManager mm = new MemberManager();

        try {
            mm.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}