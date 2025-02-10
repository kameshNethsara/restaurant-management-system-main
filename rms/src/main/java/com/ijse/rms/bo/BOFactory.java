package com.ijse.rms.bo;

import com.ijse.rms.bo.custom.impl.*;
import com.ijse.rms.dao.custom.impl.MenuItemIngredentDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {
    }
    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOType{
        CUSTOMER,EMPLOYEE,USERS,PLACE_ORDER,
        INVENTORY,MENU,
        RESERVATIONS,SUPPLIER,TABLE,
        REMINDER,
    }
    public SuperBO getBO(BOFactory.BOType type) {

        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case USERS:
                return new UserBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case INVENTORY:
                return new InventoryItemBOImpl();
            case MENU:
                return new MenuBOImpl();
            case RESERVATIONS:
                return new ReservationBOImpl();
//            case PURCHASE:
//                return new PurchaseBOImpl();
//            case PURCHASE_ITEMS:
//                return new PurchaseItemsBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case TABLE:
                return new TableBOImpl();
            default:
                return null;
        }
    }
}
