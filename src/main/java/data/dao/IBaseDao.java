package data.dao;

import java.sql.SQLException;
import java.util.List;

public interface IBaseDao<T> {
    void insertEntity(T entity) throws SQLException;

    T selectEntityById(int id) throws SQLException;

    List<T> selectAllEntity() throws SQLException;

    void updateEntityById(T entity, int id) throws SQLException;

    void deleteEntityById(int id) throws SQLException;

}
