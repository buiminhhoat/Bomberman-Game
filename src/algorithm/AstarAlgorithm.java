package algorithm;

import gamemap.GameMap;
import java.util.PriorityQueue;

public class AstarAlgorithm {

    private static final int dx[] = {-1, 0, 1, 0};
    private static final int dy[] = {0, 1, 0, -1};
    private static final int INF = (int) 1e9 + 7;
    private boolean solvable;
    private Node nodeNext = new Node();
    private Node node[][] = new Node[GameMap.MAX_ROW][GameMap.MAX_COLUMN];
    private PriorityQueue<Node> pq = new PriorityQueue<>();

    public void CalculatorAstarAlgorithm(int startX, int startY,
        int targetX, int targetY, GameMap gameMap) {
        while (!pq.isEmpty()) {
            Node nodeMin = pq.poll();
        }

        if (gameMap.checkBlocked(startX, startY)) {
            nodeNext = null;
            return;
        }

        for (int i = 0; i < gameMap.getRow(); ++i) {
            for (int j = 0; j < gameMap.getCol(); ++j) {
                if (node[i][j] == null) {
                    node[i][j] = new Node(i, j, null, 0);
                }
                node[i][j].setNumMoves(INF);
            }
        }
        node[startX][startY].parent = null;
        node[startX][startY].numMoves = 0;

        pq.add(node[startX][startY]);
        while (!pq.isEmpty()) {
            Node nodeMin = pq.poll();
            if (nodeMin.x == targetX && nodeMin.y == targetY) {
                if (nodeMin == null || nodeMin.parent == null) {
                    solvable = true;
                    break;
                }
                if (nodeNext == null) {
                    nodeNext = new Node();
                }
                nodeNext.setX(nodeMin.parent.getX());
                nodeNext.setY(nodeMin.parent.getY());
                nodeNext.setParent(nodeMin.parent);
                nodeNext.setH(nodeMin.h);
                nodeNext.setNumMoves(nodeMin.numMoves);
                break;
            }
            int ux = nodeMin.x;
            int uy = nodeMin.y;
            for (int h = 0; h < dx.length; ++h) {
                int kx = ux + dx[h];
                int ky = uy + dy[h];
                if (node[kx][ky] == null) {
                    node[kx][ky] = new Node(kx, ky, nodeMin, h);
                }
                Node neighbor = node[kx][ky];
                if (kx >= 0 && kx < gameMap.getRow() && ky >= 0 && ky < gameMap.getCol()
                    && !gameMap.checkBlocked(kx, ky)
                    && neighbor.numMoves > nodeMin.numMoves + 1) {
                    neighbor.numMoves = nodeMin.numMoves + 1;
                    neighbor.parent = nodeMin;
                    neighbor.h = h;
                    pq.add(neighbor);
                }
            }
        }
    }

    public Node getNodeNext(int x, int y) {
        return nodeNext;
    }

    public class Node implements Comparable<Node> {

        private int x;
        private int y;
        private int h;
        private Node parent;
        private int numMoves;

        public Node() {

        }

        public Node(int x, int y, Node parent, int h) {
            this.x = x;
            this.y = y;
            this.parent = parent;
            this.h = h;
            if (parent == null) {
                numMoves = 0;
            } else {
                numMoves = parent.numMoves + 1;
            }
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public int getNumMoves() {
            return numMoves;
        }

        public void setNumMoves(int numMoves) {
            this.numMoves = numMoves;
        }

        @Override
        public int compareTo(Node o) {
            return this.numMoves - o.numMoves;
        }
    }
}
