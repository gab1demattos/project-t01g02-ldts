package com.t01g02.project.viewer;

public class CharacterSprites {
    public static String[] getSprite(char c) {
        switch (Character.toUpperCase(c)) {
            case 'S':
                return new String[]{
                        " █████ ",
                        "█     █",
                        "█      ",
                        " █████ ",
                        "      █",
                        "█     █",
                        " █████ "
                };
            case 'C':
                return new String[]{
                        " █████ ",
                        "█     █",
                        "█      ",
                        "█      ",
                        "█      ",
                        "█     █",
                        " █████ "
                };
            case 'O':
                return new String[]{
                        " █████ ",
                        "█     █",
                        "█     █",
                        "█     █",
                        "█     █",
                        "█     █",
                        " █████ "
                };
            case 'R':
                return new String[]{
                        " █████ ",
                        "█     █",
                        "█     █",
                        " █████ ",
                        "█   █  ",
                        "█    █ ",
                        "█     █"
                };
            case 'E':
                return new String[]{
                        " █████ ",
                        "█      ",
                        "█      ",
                        " █████ ",
                        "█      ",
                        "█      ",
                        " █████ "
                };
            case '0':
                return new String[]{
                        " █████ ",
                        "█     █",
                        "█     █",
                        "█  █  █",
                        "█     █",
                        "█     █",
                        " █████ "
                };
            case '1':
                return new String[]{
                        "   █   ",
                        "  ██   ",
                        "   █   ",
                        "   █   ",
                        "   █   ",
                        "   █   ",
                        "  ███  "
                };
            case '2':
                return new String[]{
                        " █████ ",
                        "█     █",
                        "      █",
                        " █████ ",
                        "█      ",
                        "█      ",
                        " █████ "
                };
            case '3':
                return new String[]{
                        " █████ ",
                        "█     █",
                        "      █",
                        " █████ ",
                        "      █",
                        "█     █",
                        " █████ "
                };
            case '4':
                return new String[]{
                        "█    █ ",
                        "█    █ ",
                        "█    █ ",
                        " █████ ",
                        "     █ ",
                        "     █ ",
                        "     █ "
                };
            case '5':
                return new String[]{
                        " █████ ",
                        "█      ",
                        "█      ",
                        " █████ ",
                        "      █",
                        "█     █",
                        " █████ "
                };
            case '6':
                return new String[]{
                        " █████ ",
                        "█      ",
                        "█      ",
                        " █████ ",
                        "█     █",
                        "█     █",
                        " █████ "
                };
            case '7':
                return new String[]{
                        " █████ ",
                        "█     █",
                        "     █ ",
                        "    █  ",
                        "   █   ",
                        "  █    ",
                        "  █    "
                };
            case '8':
                return new String[]{
                        " █████ ",
                        "█     █",
                        "█     █",
                        " █████ ",
                        "█     █",
                        "█     █",
                        " █████ "
                };
            case '9':
                return new String[]{
                        " █████ ",
                        "█     █",
                        "█     █",
                        " █████ ",
                        "      █",
                        "      █",
                        " █████ "
                };
            default:
                return new String[]{
                        "       ",
                        "       ",
                        "       ",
                        "       ",
                        "       ",
                        "       ",
                        "       "
                };
        }
    }
    public static String[] getStringSprite(String input) {
        String[] result = new String[7]; // 7 lines tall
        for (int i = 0; i < 7; i++) {
            result[i] = "";
        }

        for (char c : input.toCharArray()) {
            String[] charSprite = getSprite(c);
            for (int i = 0; i < 7; i++) {
                result[i] += charSprite[i] + " "; // Add a space between characters
            }
        }
        return result;
    }
}
