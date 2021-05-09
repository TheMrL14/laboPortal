package be.lennert.finalwork.server.async.pdfimport.steps;

import be.lennert.finalwork.server.async.Reader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.tools.PDFText2HTML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.apache.pdfbox.Loader.loadPDF;


@Component
public class PdfReader implements Reader<MultipartFile, Document> {


    private static final String TEMPDIR = System.getProperty("java.io.tmpdir") + "/temp.pdf";

    @Override
    public Document read(MultipartFile multipartFile) {
        File convFile = new File(TEMPDIR);
        try {
            multipartFile.transferTo(convFile);
            return read(convFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Document read(File file) {
        Document document = null;
        try {
            PDDocument pdfDoc = loadPDF(file);
            final PDFText2HTML pdfText2HTML = new PDFText2HTML();
            document = Jsoup.parse(pdfText2HTML.getText(pdfDoc));
            pdfDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

}
