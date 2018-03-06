package ru.javawebinar.topjava.util.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatterTag extends SimpleTagSupport {
    private String format;
    private String date;

    public void setFormat(String format) {
        this.format = format;
    }

    public void setDate(String date) {
        this.date = date;
    }

    StringWriter sw = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String result = "";

        if (date != null && format != null) {
            LocalDateTime localDate = LocalDateTime.parse(date);
            result = localDate.format(DateTimeFormatter.ofPattern(format));
        }

        out.print(result);
    }
}
