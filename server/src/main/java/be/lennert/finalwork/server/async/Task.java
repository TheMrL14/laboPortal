package be.lennert.finalwork.server.async;

public interface Task<T> {
    void execute(T param);
}
