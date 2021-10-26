public class BinarySearchTree {
    private BinaryNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(BinaryNode node) {
        // Check if tree is empty
        if (root == null) {
            root = node;
        }
        else {
            BinaryNode currentNode = root;
            while (currentNode != null) {
                if (node.key.getGerWord().compareTo(currentNode.key.getGerWord()) <0) {
                    // If no left child exists, add the new node
                    // here; otherwise repeat from the left child.
                    if (currentNode.left == null) {
                        currentNode.left = node;
                        currentNode = null;
                    }
                    else {
                        currentNode = currentNode.left;
                    }
                }
                else {
                    // If no right child exists, add the new node
                    // here; otherwise repeat from the right child.
                    if (currentNode.right == null) {
                        currentNode.right = node;
                        currentNode = null;
                    }
                    else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }
    }

    //searches tree for english word
    public BinaryNode engSearch(String desiredKey) {
        BinaryNode currentNode = root;
        while (currentNode != null) {
            // Return the node if the key matches
            if (currentNode.key.engWord.equals(desiredKey)) {
                return currentNode;
            }

            // Navigate to the left if the search key is
            // less than the node's key.
            else if (desiredKey.compareTo(currentNode.key.engWord)  < 0) {
                currentNode = currentNode.left;
            }

            // Navigate to the right if the search key is
            // greater than the node's key.
            else {
                currentNode = currentNode.right;
            }
        }

        // The key was not found in the tree.
        return null;
    }

    //searches tree for german word
    public BinaryNode gerSearch(String desiredKey) {
        BinaryNode currentNode = root;
        while (currentNode != null) {
            // Return the node if the key matches
            if (currentNode.key.gerWord.equals(desiredKey)) {
                return currentNode;
            }

            // Navigate to the left if the search key is
            // less than the node's key.
            else if (desiredKey.compareTo(currentNode.key.gerWord)  < 0) {
                currentNode = currentNode.left;
            }

            // Navigate to the right if the search key is
            // greater than the node's key.
            else {
                currentNode = currentNode.right;
            }
        }

        // The key was not found in the tree.
        return null;
    }



}
