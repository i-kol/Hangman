package main;

class Graphics {
    static void printHangman(int mistakes) {
        System.out.println(HANGMAN_STAGES[mistakes]);
    }

    private static final String[] HANGMAN_STAGES = {
            """
_________
|       |
|
|
|
|
""",
            """
_________
|       |
|       O
|
|
|
""",
            """
_________
|       |
|       O
|       |
|
|
""",
            """
_________
|       |
|       O
|      /|
|
|
""",
            """
_________
|       |
|       O
|      /|\\
|
|
""",
            """
_________
|       |
|       O
|      /|\\
|      /
|
""",
            """
_________
|       |
|       O
|      /|\\
|      / \\
|
"""
    };
}