package com.epam.taxi.tags;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Custom tag for displaying copyright.
 *
 * @author M.-B.Vynnytskyi
 */
public class CopyrightTag extends TagSupport {
    private static final Logger LOGGER = Logger.getLogger(CopyrightTag.class);

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print("Taxi EPAM Java Training 2021");
        } catch (Exception e) {
            LOGGER.error("Cannot start tag", e);
        }
        return SKIP_BODY;
    }
}
