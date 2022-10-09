package uet.oop.bomberman.algorithm;

import java.util.LinkedList;
import uet.oop.bomberman.gamemap.GameMap;

public abstract class BreadthFirstSearch {
    private static int dist[][];
    private static int dx[] = {-1, 0, 1, 0};
    private static int dy[] = { 0, 1, 0,-1};

    private static final int INF = (int) 1e9 + 7;
    static LinkedList<Integer> queue;

    public BreadthFirstSearch() {

    }

    public static void initBreadthFirstSearch(GameMap gameMap) {
        dist = new int[1000][1000];
        queue = new LinkedList<Integer>();
    }

    public static void CalculatorBreadthFirstSearch(int x, int y, GameMap gameMap) {
        for (int i = 0; i < gameMap.getRow(); ++i) {
            for (int j = 0; j < gameMap.getCol(); ++j) {
                dist[i][j] = +INF;
            }
        }
        dist[x][y] = 0;
        while (queue.size() > 0) {
            queue.removeFirst();
        }
        queue.add(gameMap.getIdPos(x, y));
        while (queue.size() > 0) {
            int u = queue.poll();
            int ux = gameMap.getIdX(u);
            int uy = gameMap.getIdY(u);
//            System.out.println(ux + " " + uy);
            for (int h = 0; h <= 3; ++h) {
                int kx = ux + dx[h];
                int ky = uy + dy[h];
                if (kx >= 0 && kx < gameMap.getRow() && ky >= 0 && ky < gameMap.getCol()
                    && dist[kx][ky] > dist[ux][uy] + 1 && !gameMap.getPosIsBlocked(kx, ky)) {
                    dist[kx][ky] = dist[ux][uy] + 1;
                    queue.add(gameMap.getIdPos(kx, ky));
                }
            }
        }
    }

    public static int minDistance(int x, int y) {
        return dist[x][y];
    }
}
