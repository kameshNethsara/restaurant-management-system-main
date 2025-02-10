package com.ijse.rms.dao;

import com.ijse.rms.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOType{
        CUSTOMER,EMPLOYEE,USERS,ORDERS,
        PAYMENTS,QUERY,
        INVENTORY,MENU_ITEM,MENU_ITEM_INGREDIENTS,
        RESERVATIONS,
        STOCK_PURCHASE,
        SUPPLIER,TABLE,
        REMINDER,ORDER_ITEMS,
        PURCHASE_ITEMS,
        TABLE_ASSIGNMENT
    }
    public SuperDAO getDAO(DAOType type) {

        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case USERS:
                return new UserDAOImpl();
            case ORDERS:
                return new OrderDAOImpl();
            case ORDER_ITEMS:
                return new OrderItemDAOImpl();
            case PAYMENTS:
                return new PaymentDAOImpl();
            case INVENTORY:
                return new InventoryItemDAOImpl();
            case MENU_ITEM:
                return new MenuItemDAOImpl();
            case MENU_ITEM_INGREDIENTS:
                return new MenuItemIngredentDAOImpl();
            case RESERVATIONS:
                return new ReservationDAOImpl();
//            case STOCK_PURCHASE:
//                return new PurchasesDAOImpl();
//            case PURCHASE_ITEMS:
//                return new PurchaseItemsDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case TABLE:
                return new TableDAOImply();
            case TABLE_ASSIGNMENT:
                return new TableAssignmentDAOImpl();
            case QUERY:
                return new QuaryDAOImpl();
            default:
                return null;
        }
    }

}
