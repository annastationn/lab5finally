package main.java;

import java.util.Scanner;
public class PromptScanner {
        private static Scanner userScanner;

        public static Scanner getUserScanner() {
            return userScanner;
        }

        public static void setUserScanner(Scanner userScanner) {
            PromptScanner.userScanner = userScanner;
        }
    }

