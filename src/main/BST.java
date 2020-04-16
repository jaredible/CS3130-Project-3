package main;

/**
 * @filename BST.java
 * @author Jared Diehl
 * @date 2020-04-18
 * @course CMP SCI 3130
 * @title Project 3
 * @purpose To simulate a binary search tree (BST).
 * @notes
 */
public class BST {
  private Node root;

  public BST() {}

  public BST(int[] array) {
    for (int key : array) {
      insert(key);
    }
  }

  public boolean getExists(int key) {
    return getExists(root, key);
  }

  private boolean getExists(Node node, int key) {
    if (node == null) {
      return false;
    } else if (key == node.key) {
      return true;
    } else if (key < node.key) {
      return getExists(node.left, key);
    } else {
      return getExists(node.right, key);
    }
  }

  public void insert(int key) {
    root = insert(root, key);
  }

  private Node insert(Node node, int key) {
    if (node == null) {
      return new Node(key);
    }

    if (key == node.key) {
      node.count++;
      return node;
    }

    if (key < node.key) {
      node.left = insert(node.left, key);
    } else {
      node.right = insert(node.right, key);
    }

    return node;
  }

  public void delete(int key) {
    delete(root, key);
  }

  private Node delete(Node node, int key) {
    // Base case
    if (node == null) {
      return node;
    }

    if (key < node.key) { // If key is lesser than node's key, it lies in left subtree
      node.left = delete(node.left, key);
    } else if (key > node.key) { // If key is greater than node's key, it lies in right subtree
      node.right = delete(node.right, key);
    } else { // Key is same as node's
      // If key is present, decrement count and return
      if (node.count > 1) {
        node.count--;
        return node;
      }

      // Else, delete node

      // Node with only one or no child
      if (node.left == null) {
        Node temp = node.right;
        node = null;
        return temp;
      } else if (node.right == null) {
        Node temp = node.left;
        node = null;
        return temp;
      }

      // Get minimum value node
      Node temp = node.right;
      while (temp.left != null) {
        temp = temp.left;
      }

      // Copy inorder successor's key to this node
      node.key = temp.key;

      // Delete the inorder successor
      node.right = delete(node.right, temp.key);
    }

    return node;
  }

  public int getHeight() {
    return getHeight(root);
  }

  private int getHeight(Node node) {
    if (node == null) {
      return 0;
    } else {
      int left = getHeight(node.left);
      int right = getHeight(node.right);
      if (left > right) {
        return left + 1;
      } else {
        return right + 1;
      }
    }
  }

  public int getSmallest() {
    Node node = root;
    while (node.left != null) {
      node = node.left;
    }
    return node.key;
  }

  public int getLargest() {
    Node node = root;
    while (node.right != null) {
      node = node.right;
    }
    return node.key;
  }

  public void traversePreorder() {
    traversePreorder(root);
    System.out.println();
  }

  private void traversePreorder(Node node) {
    if (node != null) {
      System.out.print(node + " ");
      traversePreorder(node.left);
      traversePreorder(node.right);
    }
  }

  public void traverseInorder() {
    traverseInorder(root);
    System.out.println();
  }

  private void traverseInorder(Node node) {
    if (node != null) {
      traverseInorder(node.left);
      System.out.print(node + " ");
      traverseInorder(node.right);
    }
  }

  public void traversePostorder() {
    traversePostorder(root);
    System.out.println();
  }

  private void traversePostorder(Node node) {
    if (node != null) {
      traversePostorder(node.left);
      traversePostorder(node.right);
      System.out.print(node + " ");
    }
  }

  private class Node {
    int key;
    Node left, right;
    int count = 1;

    public Node(int key) {
      this.key = key;
    }

    public String toString() {
      return key + "(" + count + ")";
    }
  }
}
