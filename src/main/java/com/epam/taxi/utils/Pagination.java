package com.epam.taxi.utils;

import com.epam.taxi.db.entity.Entity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class creates pagination on the page.
 *
 * @author M.-B.Vynnytskyi
 */
public class Pagination {
    private static final int RECORDS_PER_PAGE = 3;

    /**
     * Method which create pagination and placed it in HTTP Servlet request.
     *
     * @param entityList List of entities that we want to display on our page.
     * @param request    HTTP Servlet request where the pagination attributes will be placed.
     */
    public static void createPagination(List<? extends Entity> entityList, HttpServletRequest request) {
        int page = 1;
        int numberOfRecords = entityList.size();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / RECORDS_PER_PAGE);

        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        request.setAttribute("currentPage", page);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("entities", getPageList(entityList, page));
    }

    /**
     * Method which returns list with specific number of records.
     *
     * @param entityList List of entities that we want to display on our page.
     * @param pageNumber The page number on which the user stopped.
     * @return List of records for specific page.
     */
    private static List<? extends Entity> getPageList(List<? extends Entity> entityList, int pageNumber) {
        int startPoint = (pageNumber - 1) * RECORDS_PER_PAGE;
        int endPoint = pageNumber * RECORDS_PER_PAGE;

        //Insurance from ArrayIndexOutOfBoundsException
        if (endPoint >= entityList.size()) {
            endPoint = entityList.size();
        }

        return entityList.subList(startPoint, endPoint);
    }
}
