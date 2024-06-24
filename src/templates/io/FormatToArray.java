package templates.io;

public class FormatToArray {
    public static String spaceAndBracket(String s) {
        String conv = s.replace(' ', ',').replace('[', '{').replace(']', '}');
        System.out.println(conv);
        return conv;
    }

    public static void main(String[] args) {

		spaceAndBracket("[[1,2,3,4],[1,2],[3,4],[0,4],[]]");
    }
}
