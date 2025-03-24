package com.epam.jwd.dao;

import com.epam.jwd.dbconnection.ConnectionPool;
import com.epam.jwd.model.Medicament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MedicamentDao extends CommonDao<Medicament> {

    private static final String STATEMENT_FOR_COUNTING_ALL = "select count(id) as quantity from medicament";
    private static final String STATEMENT_FOR_FINDING_WITH_OFFSET_AND_LIMIT = "SELECT m_name, m_price, category_name, country_name, m_information, m_amount FROM medicament m JOIN category c ON m.category_id = c.id JOIN country co ON m.country_id = co.id LIMIT ? OFFSET ?;";
    private final ConnectionPool connectionPool;

    private MedicamentDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    };

    public static MedicamentDao getInstance() {
        return MedicamentDao.Holder.INSTANCE;
    }

    private static class Holder {
        public static final MedicamentDao INSTANCE = new MedicamentDao(ConnectionPool.instance());
    }

    @Override
    public Optional<Medicament> create(Medicament medicament) {
        return Optional.of(medicament);
    }

    @Override
    public List<Medicament> Read() {
        return List.of();
    }

    public int countAll() throws InterruptedException {
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement countingPrepared = connection.prepareStatement(STATEMENT_FOR_COUNTING_ALL);
            ResultSet resultSet = countingPrepared.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("quantity");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Medicament> findWithLimitAndOffset(int limit, int offset) {
        List<Medicament> resultMedicamentList = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement findingPrepared = connection.prepareStatement(STATEMENT_FOR_FINDING_WITH_OFFSET_AND_LIMIT);
            findingPrepared.setInt(1, limit);
            findingPrepared.setInt(2, offset);
            ResultSet resultSet = findingPrepared.executeQuery();
           while (resultSet.next()) {
               resultMedicamentList.add(new Medicament(resultSet.getString("m_name"),
                       resultSet.getBigDecimal("m_price"),
                       resultSet.getString("category_name"),
                       resultSet.getString("country_name"),
                       resultSet.getString("m_information"),
                       resultSet.getInt("m_amount")
                       ));
           }
           return resultMedicamentList;
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
