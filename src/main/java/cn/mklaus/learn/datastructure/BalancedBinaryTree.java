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

    public boolean contains(int key) {
        Node node = root;
        while (node != null) {
            if (key > node.key) {
                node = node.right;
            } else if (key < node.key) {
                node = node.left;
            } else {
                return true;
            }
        }
        return false;
    }

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
                    // LL, 进行右旋操作
                    parent = this.rotateWithLeftChild(parent);

                } else {
                    // LR, 先左旋，再右旋
                    parent = this.doubleRotateWithLeftChild(parent);
                }

            }


        } else if (node.key > parent.key) {


            parent.right = insert(node, parent.right);

            if (!isBalance(parent)) {

                if (isLeftChild(node, parent.right)) {
                    // RL 先右旋，再左旋
                    parent = this.doubleRotateWithRightChild(parent);


                } else {
                    // RR 进行左旋操作
                    parent = this.rotateWithRightChild(parent);

                }

            }

        }

        parent.height = Math.max(height(parent.left), height(parent.right)) + 1;

        return parent;
    }

    /**
     * 在删除某节点之前我们需要找到该节点的位置，因此寻找节点的过程可分为以下3种情况（假设我们要找的节点值为key，当前节点为node）：

     1、node的值 < key，则应该在该节点的左子树寻找。在左子树中删除该节点成功后，需要调整平衡二叉树，以保持平衡。
        如果删除节点后（在左子树中删除节点，左子树高度不变或变矮），node节点的平衡因子为-2，说明删除后node的右子树导致node不平衡，则需要调整node的右子树
        （如果node右孩子节点的右子树高度大于左子树高度，进行RR调整，如果node右孩子节点的左子树高度大于有子树高度，进行RL调整）。

     2、node的值 > key，则应该在该节点的右子树寻找。在右子树中删除该节点成功后，需要调整平衡二叉树，以保持平衡。
        如果删除节点后（在右子树中删除节点，右子树高度不变或变矮），node节点的平衡因子为2，说明删除后node的左子树导致node不平衡，则需要调整node的左子树
       （如果node左孩子节点的右子树高度大于左子树高度，进行LR调整，如果node左孩子节点的左子树高度大于有子树高度，进行LL调整）。

     3、node的值 = key，找到！然后我们就可以进行删除了，但是平衡二叉树某节点的删除又分为以下4种情况：

         3.1、该节点没有左、右孩子。直接删除该节点=NULL。

         3.2、该节点只有左孩子。将其左孩子的值复制到该节点（仅移动值即可），直接删除其左孩子。

         3.3、该节点只有右孩子。将其右孩子的值复制到该节点（仅移动值即可），直接删除其右孩子。

         3.4、该节点既有左孩子又有右孩子，则分为以下两种情况：

            该节点左子树的高度大于右子树，找到该节点左孩子的最右节点right，将right的值复制到该节点，然后删除最右节点right。
            该节点右子树的高度大于左子树，找到该节点右孩子的最左节点left，将left的值复制到该节点，然后删除最左节点left。

     * @param key   删除节点的key值
     */
    public void remove(int key) {
        remove(key, root);
    }

    public Node remove(int key, Node node) {

        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = remove(key, node.left);
            this.refreshHeight(node);
            if (!isBalance(node)) {
                node = (height(node.left.left) > height(node.left.right))
                        ? rotateWithLeftChild(node)
                        : doubleRotateWithRightChild(node);
            }
        }

        else if (key > node.key) {
            node.right = remove(key, node.right);
            this.refreshHeight(node);
            if (!isBalance(node)) {
                node = (height(node.right.left) > height(node.right.right))
                        ? doubleRotateWithRightChild(node)
                        : rotateWithRightChild(node);
            }
        }

        else {

            if (node.left != null && node.right != null) {

                if (height(node.left) > height(node.right)) {
                    Node pre = node.left;
                    while (pre.right != null) {
                        pre = pre.right;
                    }

                    node.key = pre.key;
                    node.left = remove(pre.key, node.left);
                } else {
                    Node pre = node.right;
                    while (pre.left != null) {
                        pre = pre.left;
                    }

                    node.key = pre.key;
                    node.right = remove(pre.key, node.right);
                }

            } else {
                node = (node.left != null) ? node.left : node.right;
            }

        }

        return node;
    }


    private Node rotateWithRightChild(Node node) {
        Node child = node.right;
        node.right = child.left;
        child.left = node;

        this.refreshHeight(child);
        this.refreshHeight(node);
        return child;
    }

    private Node rotateWithLeftChild(Node node) {
        Node child = node.left;
        node.left = child.right;
        child.right = node;

        this.refreshHeight(child);
        this.refreshHeight(node);
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

    private void refreshHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(this.root, sb);
        return sb.toString();
    }

    private void inOrder(Node node, StringBuilder sb) {
        if (node != null) {
            inOrder(node.left, sb);
            sb.append(node.key).append("[" + node.height + "]").append(" ");
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

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", height=" + height +
                    '}';
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
        tree.remove(5);
        System.out.println(tree);
    }

}
