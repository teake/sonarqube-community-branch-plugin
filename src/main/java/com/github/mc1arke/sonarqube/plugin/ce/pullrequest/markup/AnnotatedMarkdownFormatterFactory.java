package com.github.mc1arke.sonarqube.plugin.ce.pullrequest.markup;

public class AnnotatedMarkdownFormatterFactory extends MarkdownFormatterFactory {

    public static final String SONARQUBE_COMMENT_MARKER = "sonarqube-auto-comment";

    @Override
    public Formatter<Document> documentFormatter() {
        return new BaseFormatter<Document>() {
            @Override
            public String format(Document node, FormatterFactory formatterFactory) {
                String unannotated = childContents(node, formatterFactory);
                // Add a comment so we'll know that this bit of markdown was added by us.
                String annotated = unannotated
                        + System.lineSeparator() + System.lineSeparator()
                        + "[//]: # \"" + SONARQUBE_COMMENT_MARKER + "\""
                        + System.lineSeparator() + System.lineSeparator();
                return annotated;
            }
        };
    }
}
