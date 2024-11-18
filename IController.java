package org.example;

import java.io.IOException;

public interface IController {
    void processInput() throws IOException;
    void updateView();
}
