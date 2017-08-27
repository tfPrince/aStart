import java.util.ArrayList;
import java.util.List;

public class aStar {
	// 创建搜索区域，1代表障碍物
	public static final int[][] NODES = {
	{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
	{ 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
	{ 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

	// 设置相邻两个节点平移的距离step和对角线移动距离stepd
	public static final double STEP = 1;
	public static final double STEPD = 1.41;
	// open表，存放所有会被考虑计算最短路径的节点
	private ArrayList<Node> openList = new ArrayList<Node>();
	// close表，存放不会再被考虑的节点
	private ArrayList<Node> closeList = new ArrayList<Node>(); 

	// 遍历open表，找到F值最小的节点
	public Node findMinFNodeInOpneList() {
		Node tempNode = openList.get(0);
		for (Node node : openList) {
			if (node.F < tempNode.F) {
				tempNode = node;
			}
		}
		return tempNode;
	}

	// 找一个点的邻居节点（上下左右8个节点）
	public ArrayList<Node> findNeighborNodes(Node currentNode) {
		ArrayList<Node> arrayList = new ArrayList<Node>();
		int topX = currentNode.x - 1;
		int topY = currentNode.y;// 上邻居节点列下标不变，行下标-1
		if (canReach(topX, topY) && !exists(closeList, topX, topY)) {
			arrayList.add(new Node(topX, topY));// 若计算出的下标在搜索区域内且不在close表内，则加入列表
		}
		int bottomX = currentNode.x + 1;
		int bottomY = currentNode.y;
		if (canReach(bottomX, bottomY) && !exists(closeList, bottomX, bottomY)) {
			arrayList.add(new Node(bottomX, bottomY));
		}
		int leftX = currentNode.x;
		int leftY = currentNode.y - 1;
		if (canReach(leftX, leftY) && !exists(closeList, leftX, leftY)) {
			arrayList.add(new Node(leftX, leftY));
		}
		int rightX = currentNode.x;
		int rightY = currentNode.y + 1;
		if (canReach(rightX, rightY) && !exists(closeList, rightX, rightY)) {
			arrayList.add(new Node(rightX, rightY));
		}
		int lefttopX = currentNode.x - 1;
		int lefttopY = currentNode.y - 1;
		if (canReach(lefttopX, lefttopY)
				&& !exists(closeList, lefttopX, lefttopY)) {
			arrayList.add(new Node(lefttopX, lefttopY));
		}
		int leftbottomX = currentNode.x + 1;
		int leftbottomY = currentNode.y - 1;
		if (canReach(leftbottomX, leftbottomY)
				&& !exists(closeList, leftbottomX, leftbottomY)) {
			arrayList.add(new Node(leftbottomX, leftbottomY));
		}
		int righttopX = currentNode.x - 1;
		int righttopY = currentNode.y + 1;
		if (canReach(righttopX, righttopY)
				&& !exists(closeList, righttopX, righttopY)) {
			arrayList.add(new Node(righttopX, righttopY));
		}
		int rightbottomX = currentNode.x + 1;
		int rightbottomY = currentNode.y + 1;
		if (canReach(rightbottomX, rightbottomY)
				&& !exists(closeList, rightbottomX, rightbottomY)) {
			arrayList.add(new Node(rightbottomX, rightbottomY));
		}
		return arrayList;// 返回邻居列表
	}

	// 依靠下标判断一个点是否在搜索区域内（下标在范围内且不是障碍物位置）
	public boolean canReach(int x, int y) {
		if (x >= 0 && x < NODES.length && y >= 0 && y < NODES[0].length) {
			return NODES[x][y] == 0;
		}
		return false;
	}

	// 找最短路径
	public Node findPath(Node startNode, Node endNode) {

		// 把起点放入open表
		openList.add(startNode);

		while (openList.size() > 0) {
			// 遍历open表，查找 F值最小的节点，把它作为当前要处理的节点
			Node currentNode = findMinFNodeInOpneList();
			// 把上面找到的F值最小的节点（当前要处理的节点）从open表移除并加入close表
			openList.remove(currentNode);
			closeList.add(currentNode);
			// 找到当前节点的邻居节点
			ArrayList<Node> neighborNodes = findNeighborNodes(currentNode);
			// 遍历邻居节点
			for (Node node : neighborNodes) {
				// 如果某邻居节点已经在open表中，比较G值大小，选择G值较小的节点作为父结点
				if (exists(openList, node)) {
					foundPoint(currentNode, node);
				} else {
					// 如果某邻居节点不在open表中，计算G值、H值并添加父结点
					notFoundPoint(currentNode, endNode, node);
				}
			}
			// 如果终点在open表里，返回终点
			if (find(openList, endNode) != null) {
				return find(openList, endNode);
			}
		}
		// 如果终点在open表里，返回终点，否则返回null
		return find(openList, endNode);
	}

	// 比较G值大小，选择G值较小的节点作为父结点
	private void foundPoint(Node tempStart, Node node) {
		double G = calcG(tempStart, node);
		if (G < node.G) {
			node.parent = tempStart;
			node.G = G;
			node.calcF();
		}
	}

	// 计算G值、H值，添加父结点
	private void notFoundPoint(Node tempStart, Node end, Node node) {
		node.parent = tempStart;
		node.G = calcG(tempStart, node);
		node.H = calcH(end, node);
		node.calcF();
		openList.add(node);
	}

	// 计算G值（从起点到当前节点的路程）
	private double calcG(Node start, Node node) {
		double G;
		if (Math.abs(node.x - start.x) + Math.abs(node.y - start.y) == 2)
			G = STEPD;
		else
			G = STEP;

		double parentG = node.parent != null ? node.parent.G : 0;
		return G + parentG;
	}

	// 计算H值（从当前节点到终点的直线距离）
	private double calcH(Node end, Node node) {
		double step = Math.sqrt(Math.pow((node.x - end.x), 2)
				+ Math.pow((node.y - end.y), 2));
		return step;
	}

	public static void main(String[] args) {
		Node startNode = new Node(5, 1);
		Node endNode = new Node(6, 18);
		Node parent = new aStar().findPath(startNode, endNode);
		
		System.out.println("搜索区域： ");
		for (int i = 0; i < NODES.length; i++) {
	            for (int j = 0; j < NODES[0].length; j++) {
	                System.out.print(NODES[i][j] + ", ");
	            }
	            System.out.println();
	        }
		 
		ArrayList<Node> arrayList = new ArrayList<Node>();
		System.out.println("\n");
		System.out.println("搜索路径： ");
		System.out.print("最短路径为： " + parent.G);
		while (parent != null) {
			arrayList.add(new Node(parent.x, parent.y));
			parent = parent.parent;
		}
		System.out.println("\n");

		for (int i = 0; i < NODES.length; i++) {
			for (int j = 0; j < NODES[0].length; j++) {
				if (exists(arrayList, i, j)) {
					System.out.print("@, ");
				} else {
					System.out.print(NODES[i][j] + ", ");
				}

			}
			System.out.println();
		}

	}

	// 在列表内搜索某个节点，找到则返回节点，找不到返回null
	public static Node find(List<Node> nodes, Node point) {
		for (Node n : nodes)
			if ((n.x == point.x) && (n.y == point.y)) {
				return n;
			}
		return null;
	}

	// 判断某个节点在不在某列表内
	public static boolean exists(List<Node> nodes, Node node) {
		for (Node n : nodes) {
			if ((n.x == node.x) && (n.y == node.y)) {
				return true;
			}
		}
		return false;
	}

	// 根据下标判断某个节点在不在某列表内
	public static boolean exists(List<Node> nodes, int x, int y) {
		for (Node n : nodes) {
			if ((n.x == x) && (n.y == y)) {
				return true;
			}
		}
		return false;
	}

	// 某个节点，有下标、F值、G值、H值、父结点
	public static class Node {
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int x;
		public int y;

		public double F;
		public double G;
		public double H;

		public void calcF() {
			this.F = this.G + this.H;
		}

		public Node parent;
	}
}