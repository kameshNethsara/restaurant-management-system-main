package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.CrudDAO;
import com.ijse.rms.dto.OrderItemDto;
import com.ijse.rms.dto.OrderViewDto;
import com.ijse.rms.entity.OrderItem;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<OrderItem> {
    //public boolean saveOrderItem(OrderItemDto orderItemDto) throws SQLException;
}
