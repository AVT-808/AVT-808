package lab.habitat;

public interface IHabitatMetrics {

    int getCreatureCountByType(Class<? extends ICreature> type);
    int getTime();

}
