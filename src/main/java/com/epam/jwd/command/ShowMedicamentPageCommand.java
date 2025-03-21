package com.epam.jwd.command;

import com.epam.jwd.controller.ResponseFactory;
import com.epam.jwd.controller.SimpleCommandResponseFactory;
import com.epam.jwd.model.Medicament;
import com.epam.jwd.service.MedicamentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.epam.jwd.service.MedicamentService.QUANTITY_OF_MEDICAMENT_ON_PAGE;

public class ShowMedicamentPageCommand implements Command {

    public static final String PATH_TO_MEDICAMENT_PAGE = "/WEB-INF/jsp/medicament.jsp";

    private static final Logger LOG = LogManager.getLogger(ShowMedicamentPageCommand.class);
    private static final String NUMBER_OF_PAGES_ATTRIBUTE_NAME = "numberOfPages";
    private static final String CURRENT_PAGE_ATTRIBUTE_NAME = "currentPage";
    private static final String CURRENT_PAGE_PARAMETER_NAME = "page";
    private static final String MEDICAMENT_TO_SHOW_ATTRIBUTE_NAME = "medicamentToShow";
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
        Integer numberOfPages = numberOfPagesInitIfNotExists(request);

        preparePagination(request, numberOfPages);
        int currentPage = (Integer) request.getSessionAttribute(CURRENT_PAGE_ATTRIBUTE_NAME);
        try {
           List<Medicament> medicamentToShow = medicamentService.findForNextPage(currentPage);
           request.addRequestAttribute(MEDICAMENT_TO_SHOW_ATTRIBUTE_NAME, medicamentToShow);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return responseFactory.createCommandResponse(PATH_TO_MEDICAMENT_PAGE);
    }

    private void preparePagination(CommandRequest request, Integer numberOfPages) {
        int currentPage;
        try {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE_PARAMETER_NAME));
        } catch (NumberFormatException e) {
            currentPage = 0;
        }
        if (currentPage!=0 && currentPage <= numberOfPages) {
            request.addSessionAttribute(CURRENT_PAGE_ATTRIBUTE_NAME, currentPage);
        }
        else {
            if (request.getSessionAttribute(CURRENT_PAGE_ATTRIBUTE_NAME) == null) {
                currentPage = 1;
                request.addSessionAttribute(CURRENT_PAGE_ATTRIBUTE_NAME, currentPage);
            }
        }
    }

    private int numberOfPagesInitIfNotExists(CommandRequest request) {
        Integer numberOfPages = (Integer) request.getSessionAttribute(NUMBER_OF_PAGES_ATTRIBUTE_NAME);
        if(numberOfPages == null) {
            try {
                int quantityOfMedicament = medicamentService.countAll();
                if (quantityOfMedicament%QUANTITY_OF_MEDICAMENT_ON_PAGE == 0) {
                    numberOfPages = quantityOfMedicament/QUANTITY_OF_MEDICAMENT_ON_PAGE;
                }
                else {numberOfPages = quantityOfMedicament/QUANTITY_OF_MEDICAMENT_ON_PAGE+1;};
                request.addSessionAttribute(NUMBER_OF_PAGES_ATTRIBUTE_NAME, numberOfPages);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return numberOfPages;
    }
}
