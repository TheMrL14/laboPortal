package be.ehb.finalwork.lennert.lapoportal.async.pdfimport.steps;

import be.ehb.finalwork.lennert.lapoportal.async.Processor;
import be.ehb.finalwork.lennert.lapoportal.async.pdfimport.mapper.HtmlToSopMapper;
import be.ehb.finalwork.lennert.lapoportal.async.pdfimport.model.PdfChapter;
import be.ehb.finalwork.lennert.lapoportal.core.entities.SOP;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static be.ehb.finalwork.lennert.lapoportal.async.pdfimport.model.PdfChapter.PROCEDURE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


@Component
public class PdfProcessor implements Processor<Document, SOP> {

    private static final String REGEX_NUMBER_SPACE_TEXT = "[0-9]+\\.\\s";

    @Autowired
    private HtmlToSopMapper htmlToSopMapper;

    @Override
    public SOP process(Document toProcess) {
        Elements sopHtml = new Elements();
        sopHtml.add(new Element("body"));

        Boolean isProcedureChapter = false;
        for (Element line : toProcess.body().select("p")) {
            Boolean isHeader = isHeader(line);
            Boolean isValuableHeader = false;
            Boolean isUselessLine = isUselessLine(line);
            if (TRUE.equals(isUselessLine)) {
                line.remove();
                continue;
            }

            for (PdfChapter chapter : PdfChapter.values()) {
                isValuableHeader = isValuableHeader(line, chapter.getHeader());
                if (TRUE.equals(isValuableHeader)) {
                    sopHtml.add(divWithAttr(chapter.getHeader()));
                    isProcedureChapter = chapter.equals(PROCEDURE);
                    break;
                }
            }

            if (TRUE.equals(isHeader) && FALSE.equals(isValuableHeader) && FALSE.equals(isProcedureChapter)) {
                sopHtml.add(divWithAttr(line.text()));
            }

            if (FALSE.equals(isHeader) || (FALSE.equals(isValuableHeader) && TRUE.equals(isProcedureChapter))) {
                sopHtml.last().appendChild(line);
            }
        }
        return htmlToSopMapper.mapToSOP(sopHtml);

    }

    private Boolean isUselessLine(Element line) {
        return
                (!line.hasText())
                        || line.text().trim().length() <= 2
                        || line.text().startsWith("Pagina")
                        || line.text().startsWith("Figuur");
    }

    private Element divWithAttr(String attr) {
        Element div = new Element("div");
        div.attr("header", attr);
        return div;
    }


    private Boolean isValuableHeader(Element line, String value) {
        final String REGEX_IS_NUMBERED_VALUABLE_HEADER = REGEX_NUMBER_SPACE_TEXT + "(" + value + ").*";
        return line.getElementsMatchingText(REGEX_IS_NUMBERED_VALUABLE_HEADER).first() != null;
    }

    private Boolean isHeader(Element line) {
        final String REGEX_IS_NUMBERED = REGEX_NUMBER_SPACE_TEXT + ".*";
        return line.getElementsMatchingText(REGEX_IS_NUMBERED).first() != null;
    }
}
