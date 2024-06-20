package org.example.view.cleanConsole;

import java.io.IOException;

public class WindowsCleanner implements CleanConsole {

    @Override
    public void clean() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
