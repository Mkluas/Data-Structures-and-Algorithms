package cn.mklaus.learn.datastructure;

/**
 *
 * 二叉查找树
 *
 * 树集合了数组（查找速度快）和链表（插入、删除速度快）的优点
 * 二叉树是一种特殊的树，即：树中的每个节点最多只能有两个子节点
 * 二叉搜索树是一种特殊的二叉树，即：节点的左子节点的值都小于这个节点的值，节点的右子节点的值都大于等于这个节点的值
 *
 * 使用中序遍历遍历搜索二叉树，将得到包含搜索二叉树中所有节点值的升序排序结果
 *
 * @author Mklaus
 * @date 2018-02-09 下午3:03
 */
public class BinarySearchTree {

    private Node root;

    public Node get(int key) {
        Node node = root;
        while (node != null && node.key != key) {
            if (node.key > key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    public void insert(int key, Object value) {
        if (get(key) != null) {
            throw new IllegalStateException("Node with key '" + key +  "' has already existed!");
        }

        Node node = new Node(key, value);
        if (root == null) {
            root = node;
            return;
        }

        Node parent = root;
        Node currentNode = root;

        while (currentNode != null) {
            if (currentNode.key > key) {
                parent = currentNode;
                currentNode = currentNode.left;
            } else {
                parent = currentNode;
                currentNode = currentNode.right;
            }
        }

        if (parent.key > node.key) {
            parent.left = node;
        } else {
            parent.right = node;
        }
    }

    public boolean delete(int key) {
        if (root == null) {
            return false;
        }

        Node parent = root;
        Node node = root;
        boolean isLeftNode = false;
        while (node != null && node.key != key) {
            parent = node;
            if (node.key > key) {
                isLeftNode = true;
                node = node.left;
            } else {
                isLeftNode = false;
                node = node.right;
            }
        }

        if (node == null) {
            return false;
        }

        // 删除的是叶子节点
        if (node.right == null && node.left == null) {
            if (node.key == root.key) {
                root = null;
            } else {
                if (isLeftNode) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        }

        // 删除节点的左孩子节点不为空
        else if (node.right == null) {
            if (node.key == root.key) {
                root = node.left;
            } else {
                if (isLeftNode) {
                    parent.left = node.left;
                } else {
                    parent.right = node.left;
                }
            }
        }

        // 删除节点的右孩子节点不为空
        else if (node.left == null) {
            if (node.key == root.key) {
                root = node.right;
            } else {
                if (isLeftNode) {
                    parent.left = node.right;
                } else {
                    parent.right = node.right;
                }
            }
        }

        // 删除节点的左右孩子节点都不为空
        else {
            Node followNode = this.extraFollowingNode(node);

            if (node.key == root.key) {
                root = followNode;
            } else {
                if (isLeftNode) {
                    parent.left = followNode;
                } else {
                    parent.right = followNode;
                }
            }

            followNode.right = node.right;
            followNode.left = node.left;
        }

        return true;
    }

    /**
     * 提取出删除节点的中序后继节点
     * @param deleteNode    要删除的节点
     * @return
     */
    private Node extraFollowingNode(Node deleteNode) {
        Node parent = deleteNode;
        Node node = deleteNode.right;
        while (node.left != null) {
            parent = node;
            node = node.left;
        }

        if (node.key != deleteNode.right.key) {
            parent.left = node.right;
        } else {
            parent.right = node.right;
        }
        return node;
    }

    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    static class Node {
        private int key;
        private Object value;
        private Node left;
        private Node right;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "key : " + key + ", value : " + value;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5, "");
        tree.insert(2, "");
        tree.insert(9, "");
        tree.insert(3, "");
        tree.inOrder(tree.root);
        System.out.println();

        tree.insert(8, "");
        tree.insert(1, "");
        tree.inOrder(tree.root);
        System.out.println();
        tree.delete(2);

        tree.inOrder(tree.root);
    }

}
