package net.revature.nwarner.project0.views;

public class RoleViewFactory {

    public static RoleView getRoleView(String role) {
        switch (role) {
            case "pm":
                return new ProductManagerView();
            case "lm":
                return new LayoutManagerView();
            case "stock":
                return new StockerView();
            case "floor":
                return new FloorView();
            default:
                return null;
        }
    }
}
