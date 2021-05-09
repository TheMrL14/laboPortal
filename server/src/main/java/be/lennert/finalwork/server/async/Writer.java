package be.lennert.finalwork.server.async;

public interface Writer<T> {
    void write(T output);
}
