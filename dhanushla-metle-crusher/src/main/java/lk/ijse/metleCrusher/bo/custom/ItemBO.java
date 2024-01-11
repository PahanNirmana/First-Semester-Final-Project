package lk.ijse.metleCrusher.bo.custom;

import lk.ijse.metleCrusher.bo.SuperBO;
import lk.ijse.metleCrusher.dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO {

    ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException;

    boolean saveItem(ItemDto itemDTO) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
}
