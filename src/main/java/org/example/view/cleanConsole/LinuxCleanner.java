package org.example.view.cleanConsole;

import java.io.IOException;

public class LinuxCleanner  implements CleanConsole{

    @Override
    public void clean() throws IOException, InterruptedException  {
        new ProcessBuilder("clear").inheritIO().start().waitFor();
    }

}
