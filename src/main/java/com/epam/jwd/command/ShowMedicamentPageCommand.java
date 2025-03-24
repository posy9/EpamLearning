package com.epam.jwd.command;

import com.epam.jwd.controller.ResponseFactory;
import com.epam.jwd.controller.SimpleCommandResponseFactory;
import com.epam.jwd.model.Medicament;
import com.epam.jwd.service.MedicamentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.epam.jwd.command.PagePathsRegistry.ERROR;
import static com.epam.jwd.command.ParameterNameRegistry.PAGE_PARAMETER_NAME;
import static com.epam.jwd.command.RequestAttributeRegistry.MEDICAMENT_TO_SHOW;
import static com.epam.jwd.command.SessionAttributeRegistry.CURRENT_PAGE;
import static com.epam.jwd.command.SessionAttributeRegistry.USER;
import static com.epam.jwd.command.SessionAttributeRegistry.NUMBER_OF_PAGES;
import static com.epam.jwd.service.MedicamentService.QUANTITY_OF_MEDICAMENT_ON_PAGE;

public class ShowMedicamentPageCommand implements Command {

    public static final String PATH_TO_MEDICAMENT_PAGE = "/WEB-INF/jsp/medicament.jsp";

    private static final Logger LOG = LogManager.getLogger(ShowMedicamentPageCommand.class);
    public static final String PERMISSION_FAILURE_MSG = "User does not authorized";
    public static final String ADDING_MEDICAMENT_TO_THE_REQUEST_MSG = "adding medicament to the request";
    public static final String CORRECT_PAGE_NUMBER_MSG = "Searching correct page number";
    public static final String QUANTITY_OF_PAGES_MSG = "Initializing quantity of pages";
    private final ResponseFactory responseFactory = SimpleCommandResponseFactory.getInstance();
    private final MedicamentService medicamentService = new MedicamentService();

    private ShowMedicamentPageCommand() {}

    private static class Holder {
        private static final ShowMedicamentPageCommand INSTANCE = new ShowMedicamentPageCommand();
    }

    static ShowMedicamentPageCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        if(!isAuthorized(request)) {
            LOG.error(PERMISSION_FAILURE_MSG);
            return responseFactory.createForwardResponse(ERROR.getPath());
        }

        Integer numberOfPages = numberOfPagesInitIfNotExists(request);

        preparePagination(request, numberOfPages);

        int currentPage = (Integer) request.getSessionAttribute(CURRENT_PAGE.getName());
        List<Medicament> medicamentToShow = medicamentService.findForNextPage(currentPage);
        LOG.trace(ADDING_MEDICAMENT_TO_THE_REQUEST_MSG);
        request.addRequestAttribute(MEDICAMENT_TO_SHOW.getName(), medicamentToShow);

        return responseFactory.createForwardResponse(PATH_TO_MEDICAMENT_PAGE);
    }

    private boolean isAuthorized(CommandRequest request) {
        return request.sessionExists() && request.getSessionAttribute(USER.getName()) != null;
    }

    private void preparePagination(CommandRequest request, Integer numberOfPages) {
        LOG.trace(CORRECT_PAGE_NUMBER_MSG);
        int currentPage;
        try {
            currentPage = Integer.parseInt(request.getParameter(PAGE_PARAMETER_NAME.getName()));
        } catch (NumberFormatException e) {
            currentPage = 0;
        }
        if (currentPage!=0 && currentPage <= numberOfPages) {
            request.addSessionAttribute(CURRENT_PAGE.getName(), currentPage);
        }
        else {
            if (request.getSessionAttribute(CURRENT_PAGE.getName()) == null) {
                currentPage = 1;
                request.addSessionAttribute(CURRENT_PAGE.getName(), currentPage);
            }
        }
    }

    private int numberOfPagesInitIfNotExists(CommandRequest request) {
        LOG.trace(QUANTITY_OF_PAGES_MSG);
        Integer numberOfPages = (Integer) request.getSessionAttribute(NUMBER_OF_PAGES.getName());
        if(numberOfPages == null) {
            try {
                int quantityOfMedicament = medicamentService.countAll();
                if (quantityOfMedicament%QUANTITY_OF_MEDICAMENT_ON_PAGE == 0) {
                    numberOfPages = quantityOfMedicament/QUANTITY_OF_MEDICAMENT_ON_PAGE;
                }
                else {numberOfPages = quantityOfMedicament/QUANTITY_OF_MEDICAMENT_ON_PAGE+1;};
                request.addSessionAttribute(NUMBER_OF_PAGES.getName(), numberOfPages);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return numberOfPages;
    }
}
