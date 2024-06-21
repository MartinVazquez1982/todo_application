package org.example.view.cleanConsole;

import java.io.IOException;

/**
 * Interface to cleaning the console for Windows
 */
public class WindowsCleanner implements CleanConsole {

    /**
     * Clean the console
     *
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @Override
    public void clean() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
