package com.epam.taxi.command;

import com.epam.taxi.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {
    private static final long serialVersionUID = 5419403039606311780L;

    public abstract Path execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }

}
