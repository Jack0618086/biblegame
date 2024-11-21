package com.company.Sprite;

import com.company.GameView.DisasterView;
import com.company.GameView.RedSeaGameView;
import com.company.GameView.TenCommandmentsView;
import com.company.Main;
import com.company.Sprite.DisasterViewSprite.Bug;
import com.company.Sprite.DisasterViewSprite.Frog;
import com.company.Sprite.DisasterViewSprite.Ice;
import com.company.Sprite.DisasterViewSprite.Tombstone;
import com.company.Sprite.RedSeaViewSprite.Anubis;
import com.company.Sprite.RedSeaViewSprite.Cat;
import com.company.Sprite.RedSeaViewSprite.Pharaoh;
import com.company.Sprite.TenCommandmentSprite.TenCommandment;

import javax.swing.*;
import java.util.ArrayList;

public class Moses extends Sprite{

    public Moses(int x, int y){
        setPosition(x, y);
        img = new ImageIcon("Moses.png");
    }

    @Override
    public String overlap(int x, int y) {
        if (Main.gameView instanceof DisasterView) {
            // check for bugs and frogs
            ArrayList<Frog> frogs = ((DisasterView) Main.gameView).getFrogs();
            ArrayList<Bug> bugs = ((DisasterView) Main.gameView).getBugs();
            for (Frog f : frogs) {
                if (f.getRelativePosition() != null && x == f.getRelativePosition().x && y == f.getRelativePosition().y) {
                    return  "Die";
                }
            }
            for (Bug b : bugs) {
                if (b.getRelativePosition() != null && x == b.getRelativePosition().x && y == b.getRelativePosition().y) {
                    return  "Die";
                }
            }
            // check for ice and tombstones
            ArrayList<Ice> ices = ((DisasterView) Main.gameView).getIceCubes();
            ArrayList<Tombstone> tombstones = ((DisasterView) Main.gameView).getStones();
            for (Tombstone s : tombstones) {
                if (s.getRelativePosition() != null && x == s.getRelativePosition().x && y == s.getRelativePosition().y) {
                    return  "Cannot move";
                }
            }
            for (Ice i : ices) {
                if (i.getRelativePosition() != null && x == i.getRelativePosition().x && y == i.getRelativePosition().y) {
                    return  "Cannot move";
                }
            }
            // check for door
            Door door = Main.gameView.getDoor();
            if (x == door.getRelativePosition().x && y == door.getRelativePosition().y){
                // 在 Java 中，字串比較是區分大小寫的，所以 "Next level" 和 "Next Level" 會被視為不同的字串。
                // return "Next level"; l 要大寫
                return "Next Level";
            }
        } else if (Main.gameView instanceof RedSeaGameView) {
            // check for cats
            ArrayList<Cat> cats = ((RedSeaGameView) Main.gameView).getCats();
            for (Cat s : cats) {
                if (s.getRelativePosition() != null && x == s.getRelativePosition().x && y == s.getRelativePosition().y) {
                    return "Cannot move";
                }
            }
            // check pharaoh and anubis
            ArrayList<Anubis> anubis = ((RedSeaGameView) Main.gameView).getAnubis();
            ArrayList<Pharaoh> pharaohs = ((RedSeaGameView) Main.gameView).getPharaohs();
            for (Anubis a : anubis) {
                if (a.getRelativePosition() != null && x == a.getRelativePosition().x && y == a.getRelativePosition().y) {
                    return  "Die";
                }
            }
            for (Pharaoh a : pharaohs) {
                if (a.getRelativePosition() != null && x == a.getRelativePosition().x && y == a.getRelativePosition().y) {
                    return  "Die";
                }
            }
            // check for door
            Door door = Main.gameView.getDoor();
            if (x == door.getRelativePosition().x && y == door.getRelativePosition().y){
                // 在 Java 中，字串比較是區分大小寫的，所以 "Next level" 和 "Next Level" 會被視為不同的字串。
                // return "Next level"; l 要大寫
                return "Next Level";
            }
        } else if (Main.gameView instanceof TenCommandmentsView){
            ArrayList<TenCommandment> stones = ((TenCommandmentsView) Main.gameView).getStones();
            for (TenCommandment stone : stones) {
                if (stone.getRelativePosition() != null && stone.getRelativePosition().x == x && stone.getRelativePosition().y == y) {
                    stone.setNullPosition();
                    ((TenCommandmentsView) Main.gameView).setCount(1);
                    if (((TenCommandmentsView) Main.gameView).getCount() == 10) {
                        return "Game over";
                    } else {
                        return "none";
                    }
                }
            }
        }
        return "none";
    }
}