package algorithm;

import gamemap.GameMap;
import java.util.LinkedList;

public abstract class BreadthFirstSearch {

    private static final int dx[] = {-1, 0, 1, 0};
    private static final int dy[] = {0, 1, 0, -1};
    private static final int INF = (int) 1e9 + 7;
    static LinkedList<Integer> queue = new LinkedList<Integer>();
    ;
    private static int dist[][] = new int[GameMap.MAX_ROW][GameMap.MAX_COLUMN];

    public BreadthFirstSearch() {
    }

    public static void CalculatorBreadthFirstSearch(int x, int y, GameMap gameMap) {
        for (int i = 0; i < gameMap.getRow(); ++i) {
            for (int j = 0; j < gameMap.getCol(); ++j) {
                dist[i][j] = +INF;
            }
        }
        dist[x][y] = 0;
        queue.add(gameMap.getIdPos(x, y));
        while (queue.size() > 0) {
            int u = queue.poll();
            int ux = gameMap.getIdX(u);
            int uy = gameMap.getIdY(u);
            for (int h = 0; h < dx.length; ++h) {
                int kx = ux + dx[h];
                int ky = uy + dy[h];
                if (kx >= 0 && kx < gameMap.getRow() && ky >= 0 && ky < gameMap.getCol()
                    && dist[kx][ky] > dist[ux][uy] + 1 && !gameMap.checkBlocked(kx, ky)) {
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
