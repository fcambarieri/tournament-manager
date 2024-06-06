package com.fcctech.tournament.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class SingleEliminationStrategyTest {

    @Test
    public void test_single_elimination() {
        LinkedList<String> competidores = new LinkedList<>();
        competidores.add("Competidor 1");
        competidores.add("Competidor 2");
        competidores.add("Competidor 3");
        competidores.add("Competidor 4");
        competidores.add("Competidor 5");
        competidores.add("Competidor 6");
        competidores.add("Competidor 7");
//        competidores.add("Competidor 8");

        //List<Match> llave = generarLlave1(competidores);
        Bracket bracket = createBracket(new Bracket(), competidores);
        Collections.sort(bracket.matches,(o1, o2) -> o1.level <= o2.level ? -1 : 1);
        for (Match enfrentamiento : bracket.matches) {
            System.out.println(enfrentamiento);
        }
    }

    @Test
    public void test_level() {
        for (int i = 1; i < 35 ; i++) {
            int nivel = (int) (Math.log(i) / Math.log(2));
            System.out.println(String.format("#cant: %d -> nivel -> %d ", i ,  nivel));
            //System.out.println(String.format("#cant: %d -> nivel -> %d", i,  matchLevel(i)));
        }
    }



    static class Match {
        public String competidorA;
        public String competidorB;

        private Integer number;

        private int level;

        private Match left, right;

        private String winner;

        private static AtomicInteger inc = new AtomicInteger(0);

        public Match ( String a, String b ){
            this.competidorA = a;
            this.competidorB = b;
            this.number = inc.incrementAndGet();
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public boolean hasWineer() {
            return winner != null;
        }

        public boolean isCompleted() {
            return competidorA != null && competidorB != null;
        }

        public void setLeft(Match left) {
            this.left = left;
        }

        public void setRight(Match right) {
            this.right = right;
        }

        public static Match build(String a, String b) {
            return new Match(a, b);
        }

        public int getLevel() {
            return level;
        }

        public String getCompetidorA() {
            return competidorA;
        }

        public String getCompetidorB() {
            return competidorB;
        }

        public Match getLeft() {
            return left;
        }

        public Match getRight() {
            return right;
        }

        @Override
        public String toString() {
            String depency = "";
            if (left != null) {
                depency = " " + left.number;
            }
            if (right != null) {
                depency = depency + ", " + right.number;
            }

            if (!depency.isEmpty()){
                depency = "[" + depency + "]";
            }

            return level + "[" + number + "] | " + competidorA + " vs " + competidorB + " " + depency;
        }

        public void setCompetidorA(String competidorA) {
            this.competidorA = competidorA;
        }

        public void setCompetidorB(String competidorB) {
            this.competidorB = competidorB;
        }
    }

    public static class Bracket {
        public LinkedList<Match> matches;

        public Bracket () {
            this.matches = new LinkedList<>();
        }

        public Bracket addMatch(Match match) {
            this.matches.addLast(match);
            return this;
        }

        private Match root;

        public void setRoot(Match root) {
            this.root = root;
        }

        public LinkedList<Match> toList() {
            return matches;
        }
    }

    private static Bracket createBracket(Bracket bracket, LinkedList<String> competitors) {

        boolean odd = competitors.size() % 2 != 0;
       // Match match = new Match(null, null);

        bracket.setRoot(insert(bracket, competitors, null, 0));
        return bracket;
    }

    public static Match insert(Bracket bracket, LinkedList<String> competitors, Match root, int level) {
        System.out.println(competitors.toString());
        if (root == null) {
            root = new Match(null, null);
            root.setLevel(level);
            bracket.addMatch(root);
        }

        if (competitors.size() == 2) {
            root.setCompetidorA(competitors.get(0));
            root.setCompetidorB(competitors.get(1));
        } else if (competitors.size() == 3) {
            root.setCompetidorA(competitors.get(0));
            competitors.remove(0);
            root.setLeft(insert(bracket, competitors, root.getLeft(), ++level));
        } else {
            boolean odd = competitors.size() % 2 != 0;
            int middle = competitors.size() / 2 ;
            if (odd) {
                middle++;
            }
            root.setLeft(insert(bracket, new LinkedList<>(competitors.subList(0, middle)), root.getLeft(), ++level));
            root.setRight(insert(bracket, new LinkedList<>(competitors.subList(middle, competitors.size())), root.getRight(), ++level));
        }

        return root;
    }




}
