package be.ehb.finalwork.lennert.lapoportal.async.pdfimport;

import be.ehb.finalwork.lennert.lapoportal.async.Processor;
import be.ehb.finalwork.lennert.lapoportal.async.Reader;
import be.ehb.finalwork.lennert.lapoportal.async.Task;
import be.ehb.finalwork.lennert.lapoportal.async.Writer;
import be.ehb.finalwork.lennert.lapoportal.core.entities.SOP;
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
