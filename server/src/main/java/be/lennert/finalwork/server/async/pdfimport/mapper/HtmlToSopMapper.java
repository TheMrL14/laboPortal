package be.lennert.finalwork.server.async.pdfimport.mapper;

import be.lennert.finalwork.server.core.entities.Abbreviation;
import be.lennert.finalwork.server.core.entities.SOP;
import be.lennert.finalwork.server.core.entities.Step;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static be.lennert.finalwork.server.async.pdfimport.model.PdfChapter.*;


@Component
public class HtmlToSopMapper {

    private static final String REGEX_NUMBER_SPACE_TEXT = "^([0-9]+\\.\\s).*$";
    private static final String REGEX_NUMBER_POINT_SPACE = "^([0-9]+\\.\\s)";

    private Elements chapters;

    private String title;
    private String description;
    private List<Step> procedure;
    private List<Abbreviation> abbreviations;

    private HtmlToSopMapper() {
    }

    public SOP mapToSOP(Elements chapters) {
        this.chapters = chapters;

        Elements firstChapter = this.chapters.first().getElementsByTag("p");

        this.chapters.remove(0);

        for (Element e : this.chapters) {
            firstChapter.forEach(line -> e.html(e.html().replace(line.outerHtml(), "")));
        }

        title = firstChapter.text().substring(0, firstChapter.text().indexOf("STANDARD OPERATING PROCEDURE"));

        description = getElemntsByAbbr(DESCRIPTION.getHeader()).text();

        Elements steps = getElemntsByAbbr(PROCEDURE.getHeader());
        procedure = getProcedureFromElements(steps);
        Elements abbreviationElements = getElemntsByAbbr(ABBREVIATIONS.getHeader());
        abbreviations = getAbbreviationsFromElements(abbreviationElements);

        return buildSop();
    }

    private Elements getElemntsByAbbr(String selector) {
        return chapters.select(formatSelector(selector)).select("p");
    }

    private String formatSelector(String value) {
        return "div[header~=(" + value + ").*" + "]";
    }

    private List<Step> getProcedureFromElements(Elements steps) {

        List<Step> stepList = new ArrayList<>();
        boolean isFirstStep = false;
        for (Element step : steps) {

            Boolean isStep = isStep(step);
            Boolean isSubHeader = isSubHeader(step);

            if (Boolean.TRUE.equals(isStep)) {
                isFirstStep = true;
                stepList.add(new Step(step.text().replace("-", ""), false));
            } else if (Boolean.TRUE.equals(isSubHeader)) {
                isFirstStep = true;
                stepList.add(new Step(step.text().replaceAll(REGEX_NUMBER_POINT_SPACE, ""), true));
            } else if (isFirstStep) {
                Step lastStep = stepList.get(stepList.size() - 1);
                lastStep.setMessage(lastStep.getMessage() + " " + step.text());
            }

        }
        return stepList;
    }

    private Boolean isStep(Element step) {
        return step.text().startsWith("-");
    }

    private Boolean isSubHeader(Element step) {
        return !step.select("b").isEmpty() && step.text().matches(REGEX_NUMBER_SPACE_TEXT);
    }

    private List<Abbreviation> getAbbreviationsFromElements(Elements abbrs) {
        return abbrs.stream()
                .map(abbr -> new Abbreviation(abbr.text().trim()))
                .collect(Collectors.toList());
    }

    private SOP buildSop() {
        SOP newSOP = SOP.builder()
                .abbreviations(abbreviations)
                .description(description)
                .title(title)
                .type(SOP.SopType.DEVICE)
                .build();
        newSOP.setProcedureWithStepNr(procedure);
        return newSOP;
    }
}
