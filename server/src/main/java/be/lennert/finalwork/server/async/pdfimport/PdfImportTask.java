package be.lennert.finalwork.server.async.pdfimport;

import be.lennert.finalwork.server.async.Processor;
import be.lennert.finalwork.server.async.Reader;
import be.lennert.finalwork.server.async.Task;
import be.lennert.finalwork.server.async.Writer;
import be.lennert.finalwork.server.core.entities.SOP;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class PdfImportTask implements Task<MultipartFile> {

    private final Reader<MultipartFile, Document> reader;
    private final Processor<Document, SOP> processor;
    private final Writer<SOP> writer;

    public PdfImportTask(@Qualifier("pdfReader") Reader<MultipartFile, Document> reader,
                         @Qualifier("pdfProcessor") Processor<Document, SOP> processor,
                         @Qualifier("pdfToDbWriter") Writer<SOP> writer) {
        this.reader = reader;
        this.processor = processor;
        this.writer = writer;
    }

    @Async
    @Override
    public void execute(MultipartFile file) {
        Document pdfToHtml = reader.read(file);
        SOP sop = processor.process(pdfToHtml);
        writer.write(sop);
    }
}
