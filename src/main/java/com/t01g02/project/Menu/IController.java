package com.t01g02.project.Menu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface IController {
    void processInput() throws IOException, URISyntaxException, FontFormatException;
    void updateView();
}
