package templates.tries;

class TrieNew {

    static class Node {
        char c;
        Node[] sons = new Node[26];
        int nEnds = 0;
        int nTouches = 0;

        public Node() {
        }

        public Node(char c) {
            this.c = c;
            nTouches = 1;
        }

        public Node get(char c) {
            return this.sons[c - 'a'];
        }

        public void set(char c, Node node) {
            this.sons[c - 'a'] = node;
        }

        public Node add(char c) {
            Node node = new Node(c);
            sons[c - 'a'] = node;
            return node;
        }

    }

    Node head;

    public TrieNew() {
        this.head = new Node();
    }

    public void insert(String word) {
        Node curr = head;
        for (int i = 0, n = word.length(); i < n; i++) {
            char c = word.charAt(i);
            if (curr.get(c) != null) {
                curr = curr.get(c);
                curr.nTouches++;
            } else {
                curr = curr.add(c);
            }
        }
        curr.nEnds++;
    }

    public boolean search(String word) {
        Node curr = head;
        for (int i = 0, n = word.length(); i < n; i++) {
            char c = word.charAt(i);
            if (curr.get(c) == null)
                return false;
            curr = curr.get(c);
        }
        return curr.nEnds > 0;
    }

    public boolean startsWith(String prefix) {
        Node curr = head;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (curr.get(c) == null)
                return false;
            curr = curr.get(c);
        }
        return true;
    }

    public int countWordsEqualTo(String word) {
        Node curr = head;
        for (int i = 0, n = word.length(); i < n; i++) {
            char c = word.charAt(i);
            if (curr.get(c) == null)
                return 0;
            curr = curr.get(c);
        }
        return curr.nEnds;
    }

    public int countWordsStartingWith(String word) {
        Node curr = head;
        for (int i = 0, n = word.length(); i < n; i++) {
            char c = word.charAt(i);
            if (curr.get(c) == null)
                return 0;
            curr = curr.get(c);
        }
        return curr.nTouches;
    }

    public void erase(String word) {
        Node curr = head;
        for (int i = 0, n = word.length(); i < n; i++) {
            char c = word.charAt(i);
            Node son = curr.get(c);
            if (son.nTouches == 1) {
                curr.set(c, null);
                break;
            } else {
                son.nTouches--;
                curr = son;
            }
        }
        curr.nEnds--;
    }
}