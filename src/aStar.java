import java.util.ArrayList;
import java.util.List;

public class aStar {
	// ������������1�����ϰ���
	public static final int[][] NODES = {nihubu
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

	// �������������ڵ�ƽ�Ƶľ���step�ͶԽ����ƶ�����stepd
	public static final double STEP = 1;
	public static final double STEPD = 1.41;
	// open��������лᱻ���Ǽ������·���Ľڵ�
	private ArrayList<Node> openList = new ArrayList<Node>();
	// close����Ų����ٱ����ǵĽڵ�
	private ArrayList<Node> closeList = new ArrayList<Node>(); 

	// ����open���ҵ�Fֵ��С�Ľڵ�
	public Node findMinFNodeInOpneList() {
		Node tempNode = openList.get(0);
		for (Node node : openList) {
			if (node.F < tempNode.F) {
				tempNode = node;
			}
		}
		return tempNode;
	}

	// ��һ������ھӽڵ㣨��������8���ڵ㣩
	public ArrayList<Node> findNeighborNodes(Node currentNode) {
		ArrayList<Node> arrayList = new ArrayList<Node>();
		int topX = currentNode.x - 1;
		int topY = currentNode.y;// ���ھӽڵ����±겻�䣬���±�-1
		if (canReach(topX, topY) && !exists(closeList, topX, topY)) {
			arrayList.add(new Node(topX, topY));// ����������±��������������Ҳ���close���ڣ�������б�
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
		return arrayList;// �����ھ��б�
	}

	// �����±��ж�һ�����Ƿ������������ڣ��±��ڷ�Χ���Ҳ����ϰ���λ�ã�
	public boolean canReach(int x, int y) {
		if (x >= 0 && x < NODES.length && y >= 0 && y < NODES[0].length) {
			return NODES[x][y] == 0;
		}
		return false;
	}

	// �����·��
	public Node findPath(Node startNode, Node endNode) {

		// ��������open��
		openList.add(startNode);

		while (openList.size() > 0) {
			// ����open������ Fֵ��С�Ľڵ㣬������Ϊ��ǰҪ����Ľڵ�
			Node currentNode = findMinFNodeInOpneList();
			// �������ҵ���Fֵ��С�Ľڵ㣨��ǰҪ����Ľڵ㣩��open���Ƴ�������close��
			openList.remove(currentNode);
			closeList.add(currentNode);
			// �ҵ���ǰ�ڵ���ھӽڵ�
			ArrayList<Node> neighborNodes = findNeighborNodes(currentNode);
			// �����ھӽڵ�
			for (Node node : neighborNodes) {
				// ���ĳ�ھӽڵ��Ѿ���open���У��Ƚ�Gֵ��С��ѡ��Gֵ��С�Ľڵ���Ϊ�����
				if (exists(openList, node)) {
					foundPoint(currentNode, node);
				} else {
					// ���ĳ�ھӽڵ㲻��open���У�����Gֵ��Hֵ����Ӹ����
					notFoundPoint(currentNode, endNode, node);
				}
			}
			// ����յ���open��������յ�
			if (find(openList, endNode) != null) {
				return find(openList, endNode);
			}
		}
		// ����յ���open��������յ㣬���򷵻�null
		return find(openList, endNode);
	}

	// �Ƚ�Gֵ��С��ѡ��Gֵ��С�Ľڵ���Ϊ�����
	private void foundPoint(Node tempStart, Node node) {
		double G = calcG(tempStart, node);
		if (G < node.G) {
			node.parent = tempStart;
			node.G = G;
			node.calcF();
		}
	}

	// ����Gֵ��Hֵ����Ӹ����
	private void notFoundPoint(Node tempStart, Node end, Node node) {
		node.parent = tempStart;
		node.G = calcG(tempStart, node);
		node.H = calcH(end, node);
		node.calcF();
		openList.add(node);
	}

	// ����Gֵ������㵽��ǰ�ڵ��·�̣�
	private double calcG(Node start, Node node) {
		double G;
		if (Math.abs(node.x - start.x) + Math.abs(node.y - start.y) == 2)
			G = STEPD;
		else
			G = STEP;

		double parentG = node.parent != null ? node.parent.G : 0;
		return G + parentG;
	}

	// ����Hֵ���ӵ�ǰ�ڵ㵽�յ��ֱ�߾��룩
	private double calcH(Node end, Node node) {
		double step = Math.sqrt(Math.pow((node.x - end.x), 2)
				+ Math.pow((node.y - end.y), 2));
		return step;
	}

	public static void main(String[] args) {
		Node startNode = new Node(5, 1);
		Node endNode = new Node(6, 18);
		Node parent = new aStar().findPath(startNode, endNode);
		
		System.out.println("�������� ");
		for (int i = 0; i < NODES.length; i++) {
	            for (int j = 0; j < NODES[0].length; j++) {
	                System.out.print(NODES[i][j] + ", ");
	            }
	            System.out.println();
	        }
		 
		ArrayList<Node> arrayList = new ArrayList<Node>();
		System.out.println("\n");
		System.out.println("����·���� ");
		System.out.print("���·��Ϊ�� " + parent.G);
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

	// ���б�������ĳ���ڵ㣬�ҵ��򷵻ؽڵ㣬�Ҳ�������null
	public static Node find(List<Node> nodes, Node point) {
		for (Node n : nodes)
			if ((n.x == point.x) && (n.y == point.y)) {
				return n;
			}
		return null;
	}

	// �ж�ĳ���ڵ��ڲ���ĳ�б���
	public static boolean exists(List<Node> nodes, Node node) {
		for (Node n : nodes) {
			if ((n.x == node.x) && (n.y == node.y)) {
				return true;
			}
		}
		return false;
	}

	// �����±��ж�ĳ���ڵ��ڲ���ĳ�б���
	public static boolean exists(List<Node> nodes, int x, int y) {
		for (Node n : nodes) {
			if ((n.x == x) && (n.y == y)) {
				return true;
			}
		}
		return false;
	}

	// ĳ���ڵ㣬���±ꡢFֵ��Gֵ��Hֵ�������
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