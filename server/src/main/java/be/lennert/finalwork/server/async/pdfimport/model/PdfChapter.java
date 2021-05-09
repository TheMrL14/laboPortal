package be.lennert.finalwork.server.async.pdfimport.model;

public enum PdfChapter {

    DESCRIPTION("Doelstelling en toepassingsgebied"),
    ABBREVIATIONS("Gebruikte afkortingen"),
    PROCEDURE("Procedure"),
    RESPONSIBILITIES("Verantwoordelijkheden");

    private final String header;

    PdfChapter(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }
}
