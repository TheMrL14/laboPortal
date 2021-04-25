package be.ehb.finalwork.lennert.lapoportal.async;

public interface Reader<T, G> {
    G read(T toRead);
}
