package templates.tries;

import java.util.List;

public class Trie {
    static class Node {
        Node children[] = new Node[26];
        char c;
        int endCount = 0;
        int prefixCount = 0;

        Node(char c) {
            this.c = c;
        }

        Node() {
        }

        ;
    }

    Node head;

    public Trie() {
        head = new Node();
    }

    public Trie(List<String> wordsToInsert) {
        head = new Node();
        for (String s : wordsToInsert)
            insert(s);
    }

    public Trie(String[] wordsToInsert) {
        head = new Node();
        for (String s : wordsToInsert)
            insert(s);
    }

    /**
     * @param node - the node of the trie you want to inspect
     * @param c    - the character you want the node to have
     * @return true if the node 'node' of the trie has character 'c'
     */
    public boolean contains(Node node, char c) {
        return node.children[c - 'a'] != null;
    }

    /**
     * @param word
     * @return if the word already exists, returns false, else return true
     */

    public boolean insert(String word) {
        boolean newWord = false;
        Node traverse = head;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (traverse.children[ch - 'a'] != null)
                traverse = traverse.children[ch - 'a'];
            else {
                newWord = true;
                Node node = new Node(ch);
                traverse.children[ch - 'a'] = node;
                traverse = node;
            }
            traverse.prefixCount++;
        }
        traverse.endCount++;
        return newWord;
    }

    public int countWordsEqualTo(String word) {
        Node traverse = head;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (traverse.children[c - 'a'] == null)
                return 0;
            traverse = traverse.children[c - 'a'];
        }
        return traverse.endCount;
    }

    public int countWordsStartingWith(String word) {
        Node traverse = head;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (traverse.children[c - 'a'] == null)
                return 0;
            traverse = traverse.children[c - 'a'];
        }
        return traverse.prefixCount;
    }

    public void erase(String word) {
        Node traverse = head;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (traverse.children[c - 'a'] == null)
                return;
            traverse = traverse.children[c - 'a'];
            traverse.prefixCount--;
        }
        traverse.endCount--;
    }

}