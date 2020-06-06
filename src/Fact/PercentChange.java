package Fact;

import java.io.IOException;
import java.io.PipedInputStream;

public class PercentChange {

    public PercentChange(PipedInputStream pipeIn) throws IOException {

        int b = pipeIn.read();
        IdentifyType.setC(b);
    }
}
