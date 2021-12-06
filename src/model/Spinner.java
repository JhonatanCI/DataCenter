 package model;
/*Code taken by : https://www.logicbig.com/how-to/code-snippets/jcode-java-command-line-animation*/

public class Spinner {

    private String lastLine = "";

    public void print(String line) {
        //clear the last line if longer
        if (lastLine.length() > line.length()) {
            String temp = "";
            for (int i = 0; i < lastLine.length(); i++) {
                temp += " ";
            }
            if (temp.length() > 1)
                System.out.print("\r" + temp);
        }
        System.out.print("\r" + line);
        lastLine = line;
    }

    private byte anim;

    public void animate() {
        switch (anim) {
            case 1:
                print("Loading " + "yours...");
                break;
            case 2:
                print("Loading " + "yours " + "minirooms...  ");
                break;
            default:
                anim = 0;
                print("Loading... ");
        }
        anim++;
    }
}