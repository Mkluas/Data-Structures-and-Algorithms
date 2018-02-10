package cn.mklaus.learn.datastructure;


/**
 * 平衡二叉树
 *
 * 任何一个节点的左右子树深度差不超过1
 *
 * @author Mklaus
 * @date 2018-02-10 上午10:54
 */
public class BalancedBinaryTree  {

    private Node root;


    public void insert(int key, Object value) {
        Node node = new Node(key, value);
        root = insert(node, root);
    }

    public Node insert(Node node, Node parent) {
        if (parent == null) {
            return node;
        }

        if (node.key < parent.key) {

            parent.left = insert(node, parent.left);

            if (!isBalance(parent)) {

                if (isLeftChild(node, parent.left)) {
                    // 左左
                    parent = this.rotateWithLeftChild(parent);

                } else {
                    // 左右

                    parent = this.doubleRotateWithLeftChild(parent);
                }

            }


        } else if (node.key > parent.key) {


            parent.right = insert(node, parent.right);

            if (!isBalance(parent)) {

                if (isLeftChild(node, parent.right)) {
                    System.out.println("parent = " + parent.key);
                    // 右左
                    parent = this.doubleRotateWithRightChild(parent);


                } else {
                    // 右右
                    parent = this.rotateWithRightChild(parent);

                }

            }

        }

        parent.height = Math.max(height(parent.left), height(parent.right)) + 1;

        return parent;
    }

    private Node rotateWithRightChild(Node node) {
        Node child = node.right;
        node.right = child.left;
        child.left = node;

        child.height = Math.max(height(child.left), height(child.right)) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return child;
    }

    private Node rotateWithLeftChild(Node node) {
        Node child = node.left;
        node.left = child.right;
        child.right = node;

        child.height = Math.max(height(child.left), height(child.right)) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return child;
    }

    private Node doubleRotateWithRightChild(Node node) {
        node.right = rotateWithLeftChild(node.right);
        return rotateWithRightChild(node);
    }

    private Node doubleRotateWithLeftChild(Node node) {
        node.left = rotateWithRightChild(node.left);
        return rotateWithLeftChild(node);
    }


    private boolean isLeftChild(Node node, Node parent) {
        return node.key < parent.key;
    }

    private boolean isBalance(Node node) {
        return Math.abs(height(node.left) - height(node.right)) < 2;
    }

    private int height(Node node) {
        return node == null ? -1 : node.height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(this.root, sb);
        return sb.toString();
    }

    private void inOrder(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.key).append("[" + node.height + "]").append(" ");
            inOrder(node.left, sb);
            inOrder(node.right, sb);
        }
    }

    static class Node {
        private int key;
        private Object value;
        private int height;
        private Node left;
        private Node right;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        BalancedBinaryTree tree = new BalancedBinaryTree();
        tree.insert(3, "");
        tree.insert(9, "");
        tree.insert(2, "");
        tree.insert(8, "");
        tree.insert(5, "");
        tree.insert(7, "");
        tree.insert(1, "");
        System.out.println(tree);
    }

}
