package lk.ijse.metleCrusher.bo.custom.impl;

import lk.ijse.metleCrusher.bo.custom.ItemBO;
import lk.ijse.metleCrusher.dao.custom.ItemDAO;
import lk.ijse.metleCrusher.dao.custom.impl.ItemDAOImpl;
import lk.ijse.metleCrusher.dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBO {
    ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
    @Override
    public boolean saveItem(ItemDto itemDTO) throws SQLException, ClassNotFoundException{
        return itemDAO.save(itemDTO);
    }
    @Override
    public boolean updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
    return itemDAO.update(itemDto);
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException{
        return itemDAO.delete(code);
    }

}
