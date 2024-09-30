package templates.io;

public class FormatToArray {
    public static String spaceAndBracket(String s) {
        String conv = s.replace(' ', ',').replace('[', '{').replace(']', '}');
        System.out.println(conv);
        return conv;
    }

    public static void main(String[] args) {

		spaceAndBracket("[[0,1],[1,2],[2,3],[3,4],[0,5],[1,6],[2,7],[3,8]]");
    }
}
