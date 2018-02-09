package cn.mklaus.learn.datastructure;


import lombok.EqualsAndHashCode;

import java.util.Random;

/**
 * 跳跃表
 * 介绍：https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653190879&idx=1&sn=1916d0f6e72f34408261d70d13eecf5b
 *
 * 学习：java.util.concurrent.ConcurrentSkipListMap
 *
 * @author Mklaus
 * @date 2018-02-09 上午11:12
 */
public class SkipList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int level;
    private Random random;

    public SkipList() {
        random = new Random();
        this.reset();
    }


    private void reset() {
        this.head = this.newHead();
        this.tail = this.newTail();
        level = 0;
        size = 0;
        this.horizontalLink(head, tail);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在最下面一层，找到要插入的位置前面的那个key
     *
     * @param key   插入元素的key值
     * @return
     */
    public Node<T> findPreEntry(int key) {
        Node<T> n = head;
        while (true) {

            /**
             * 一直向右找，例: k=34。 10 ---> 20 ---> 30 ---> 40 ^ | p 会在30处停止
             */
            while (n.right.key != Node.MAX_KEY && n.right.key <= key) {
                n = n.right;
            }


            if (n.down != null) {
                // 如果还有下一层，就到下一层继续查找
                n = n.down;
            } else {
                break;
            }
        }

        return n;
    }

    public T get(int key) {
        Node<T> node = this.findPreEntry(key);
        return node.key == key ? node.value : null;
    }

    private static final double THRESHOLD = 0.5;
    public T put(int key, T value) {
        Node<T> preNode = this.findPreEntry(key);

        if (preNode.key == key) {
            T old = preNode.value;
            preNode.value = value;
            return old;
        }

        Node node = new Node(key, value);
        this.insertAfterEntry(node, preNode);

        int currentLevel = 0;

        while (random.nextDouble() < THRESHOLD) {
            currentLevel++;

            if (currentLevel > level) {
                Node head = this.newHead();
                Node tail = this.newTail();
                this.horizontalLink(head, tail);

                this.verticalLink(head, this.head);
                this.verticalLink(tail, this.tail);

                this.head = head;
                this.tail = tail;
                level++;
            }

            while (preNode.up == null) {
                preNode = preNode.left;
            }

            preNode = preNode.up;

            /**
             * 注意，本实现中只有第0层的链表持有键对应的值，1 ~ level 层中的SkipListEntry对象
             * 仅仅持有键的引用，值为空，以便节省空间。
             */
            Node newUpNode = new Node(key, null);
            this.insertAfterEntry(newUpNode, preNode);
            this.verticalLink(newUpNode, node);
            node = newUpNode;
        }

        size++;
        return null;
    }

    public void remove(int key) {
        Node node = this.findPreEntry(key);
        if (node.key != key) {
            return;
        }

        //删除元素后重新关联，同时使被删除的对象游离，便于垃圾回收
        this.clearHorizontalLink(node);

        //自底向上，使所有键等于key的SkipListEntry对象左右两个方向的引用置空
        while (node.up != null) {
            node = node.up;
            this.clearHorizontalLink(node);
        }

        //自顶向下，使所有键等于key的SkipListEntry对象上下两个方向的引用置空
        while (node.down != null) {
            Node down = node.down;
            node.down = null;
            down.up = null;
            node = down;
        }

        /*
         * 删除元素后，如果顶层的链表只有head和tail两个元素，则删除顶层。
         * 删除顶层以后最新的顶层如果依然只有head和tail两个元素，则也要被删除，以此类推。
         */
        while (head.right.key == tail.key && level > 0) {
            Node downHead, downTail;
            downHead = head.down;
            downTail = tail.down;

            head.right = null;
            head.down = null;
            tail.left = null;
            tail.down = null;

            downHead.up = null;
            downTail.up = null;

            head = downHead;
            tail = downTail;

            level--;
        }

        size--;
    }

    private void clearHorizontalLink(Node<T> node) {
        node.left.right = node.right;
        node.right.left = node.left;
        node.right = null;
        node.left = null;
    }

    private void horizontalLink(Node n1, Node n2) {
        n1.right = n2;
        n2.left = n1;
    }

    private void verticalLink(Node n1,Node n2) {
        n1.down = n2;
        n2.up = n1;
    }

    /**
     * 在指定的Entry前面插入新Entry
     * @param n1    要插入的新Entry
     * @param n2    指定的Entry
     */
    private void insertAfterEntry(Node n1, Node n2) {
        n1.left = n2;
        n1.right = n2.right;
        n2.right.left = n1;
        n2.right = n1;
    }

    public Node<T> newHead() {
        return new Node<T>(Node.MIN_KEY, null);
    }

    public Node<T> newTail() {
        return new Node<T>(Node.MAX_KEY, null);
    }

    @EqualsAndHashCode
    static class Node<T> {
        public static final int MAX_KEY = Integer.MAX_VALUE;
        public static final int MIN_KEY = Integer.MIN_VALUE;
        public int key;
        public T value;
        public Node<T> left, right, up, down;

        public Node(int key, T value) {
            this.key = key;
            this.value = value;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= level; i++) {
            Node h = head;
            int down = i;
            while (down-- > 0) {
                h = h.down;
            }

            sb.append("level ").append(level - i).append(": ").append(h.key);

            Node start = h;
            while (start.right != null) {
                start = start.right;
                sb.append(" <-> ").append(start.key);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
