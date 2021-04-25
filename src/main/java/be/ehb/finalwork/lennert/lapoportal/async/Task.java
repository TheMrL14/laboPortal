package be.ehb.finalwork.lennert.lapoportal.async;

public interface Task<T> {
    void execute(T param);
}
