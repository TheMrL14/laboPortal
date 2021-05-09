package be.lennert.finalwork.server.async;

public interface Reader<T, G> {
    G read(T toRead);
}
