package be.ehb.finalwork.lennert.lapoportal.async.pdfimport.mapper;

import be.ehb.finalwork.lennert.lapoportal.core.entities.Abbreviation;
import be.ehb.finalwork.lennert.lapoportal.core.entities.SOP;
import be.ehb.finalwork.lennert.lapoportal.core.entities.Step;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static be.ehb.finalwork.lennert.lapoportal.async.pdfimport.model.PdfChapter.*;


@Component
public class HtmlToSopMapper {

    private Elements chapters;

    private String title;
    private String description;
    private List<Step> procedure;
    private List<Abbreviation> abbreviations;

    private HtmlToSopMapper() {
    }

    public SOP mapToSOP(Elements chapters) {
        this.chapters = chapters;

        Elements firstChapter =this.chapters.first().getElementsByTag("p");

        this.chapters.remove(0);

        for (Element e : this.chapters) {
            firstChapter.forEach(line->e.html( e.html().replace(line.outerHtml(), "")));
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
        boolean isStep = false;
        for (Element step : steps) {
            if (step.text().startsWith("-") || !step.select("b").isEmpty()) {
                isStep = true;
                stepList.add(new Step(step.text().replace("-", "")));

            } else if (isStep) {
                Step lastStep = stepList.get(stepList.size() - 1);
                lastStep.setMessage(lastStep.getMessage() + " " + step.text());
            }

        }
        IntStream.range(0, stepList.size())
                .forEach(i -> stepList.get(i).setStepNr(i + 1));
        return stepList;
    }

    private List<Abbreviation> getAbbreviationsFromElements(Elements abbrs) {
        return abbrs.stream()
                .map(abbr -> new Abbreviation(abbr.text().trim()))
                .collect(Collectors.toList());
    }

    private SOP buildSop() {
        return SOP.builder()
                .abbreviations(abbreviations)
                .description(description)
                .procedure(procedure)
                .title(title)
                .build();
    }
}
