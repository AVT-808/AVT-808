package lab.habitat;

public interface IHabitatMetrics {
    int getCreatureCount();
    int getCreatureCountByType(Class<? extends ICreature> type);
    int getTime();
}