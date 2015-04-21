package com.okosmos.camino.domain;

import java.util.LinkedList;
import java.util.List;

public class ElCamino {
    public static void main(String[] args) {
        int n = 8;
        int x = 0;
        int y = 0;
        switch (args.length) {
            default:
                // use the default values, an 8x8 board with knight starting point on A1 on the chess board
                break;

            case 1:
                n = Integer.valueOf(args[0]);
                break;

            case 3:
                n = Integer.valueOf(args[0]);
                x = Integer.valueOf(args[1]);
                y = Integer.valueOf(args[2]);
                break;
        }

        LinkedList<Board> boards = new LinkedList<>();
        List<Solution> solutions = new LinkedList<>();
        boards.add(new KnightBoard(n, x, y));
        int i = 1;
        while (!boards.isEmpty()) {
            ++i;
            Board head = boards.remove();
            if (head.isDone()) {
                solutions.add(head.solution().get());
            } else {
                for (Board b : head.next()) {
                   if (b.isDone()) {
                       solutions.add(b.solution().get());
                   } else {
                       boards.addFirst(b);
                   }
                }
                System.out.println("completed iteration " + i + " boards.size = " + boards.size());
                System.out.println(boards.getFirst().show());
                System.out.println();
            }
        }
        solutions.stream().forEach(System.out::println);
    }

}
