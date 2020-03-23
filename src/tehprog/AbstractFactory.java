package tehprog;

import java.awt.*;

public interface AbstractFactory {
    AbstractEmployee getEmployee(long time, Dimension s);
}
