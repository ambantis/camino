package com.okosmos.camino;

import com.okosmos.camino.domain.Board;
import com.okosmos.camino.domain.KnightBoard;
import com.okosmos.camino.domain.Solution;

import java.util.LinkedList;
import java.util.List;

/**
 * Using java is about reducing the memory footprint of the application so that Java spends less time with
 * garbage collection and more time actually running the application (Scala uses boxed types). The basic idea
 * is to list of boards to evaluate, sorted by remaining squares so that boards are eliminated as quickly as they
 * are created (roughly speaking) and thus effectively uses lazy evaluation.
 *
 * However, two problems were encountered in doing this problem:
 *   1. Linked Lists do not have a constant-time addAll (which is odd, because it would just be a matter of linking the
 *      last item of list1 to the first item of list2.
 *   2. Parallel streams are optimized for map/fold operations of a very large size (in previous tests with java 8, it
 *      seemed like a Scala parallel collection seemed to have a speedup with 50 items that could be operated on in parallel).
 *
 * The next level of speedup will be to use akka. A set of children evaluate each board, sending the solution (if done)
 * to a collector actor or else sending to itself the next boards. The routees (or children worker actors) would need to
 * be limited to some sensible default and they would need to use a priority mailbox so that the "older" boards are
 * operated upon before the "younger" boards. Beyond that, the task could utilize more cores through using akka cluster
 *
 * Tests have not been written for the Java classes yet, but if you look in the scala branches, you'll see scalatest and
 * scalacheck tests. Those would need to be modified somewhat to work with these java versions.
 *
 */
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
//        int i = 0;
        while (!boards.isEmpty()) {
//            ++i;
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
            /*
                System.out.println("completed iteration " + i + " boards.size = " + boards.size());
                System.out.println(boards.getFirst().show());
                System.out.println();
            */
            }
        }
        System.out.println("The solutions are");
        solutions.stream().forEach(System.out::println);

        System.out.println("The closed solutions are");
        solutions.stream().filter(Solution::isClosed).forEach(System.out::println);
    }

}
