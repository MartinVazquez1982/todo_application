package org.example.view.cleanConsole;

import java.io.IOException;

/**
 * Interface to cleaning the console
 */
public interface CleanConsole {

    /**
     * Clean the console
     *
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    void clean() throws IOException, InterruptedException;

}
