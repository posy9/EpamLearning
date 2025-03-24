package com.epam.jwd.service;

import com.epam.jwd.dao.MedicamentDao;
import com.epam.jwd.model.Medicament;

import java.util.List;
public class MedicamentService {

    public static final int QUANTITY_OF_MEDICAMENT_ON_PAGE = 10;
    private final MedicamentDao medicamentDao = MedicamentDao.getInstance();

    public int countAll() throws InterruptedException {
        return medicamentDao.countAll();
    }

    public List<Medicament> findForNextPage(int pageNumber)  {
        int offset = (pageNumber-1)*QUANTITY_OF_MEDICAMENT_ON_PAGE;
        return medicamentDao.findWithLimitAndOffset(QUANTITY_OF_MEDICAMENT_ON_PAGE,offset);
    }

}
