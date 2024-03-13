/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.util;

/**
 *
 * @author thinkpad
 */
public class ApplicationConstants {

    public class DispatchFeature {

        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";
        public static final String STARTUP_CONTROLLER = "startupController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
        public static final String ADD_TO_CART_CONTROLLER = "addToCartController";
        public static final String REMOVE_ITEM_FROM_CART_CONTROLLER = "removeItemFromCartController";
        public static final String CREATE_ACCOUNT_CONTROLLER = "createAccountController";
        public static final String LOGOUT_CONTROLLER = "logoutController";
        public static final String GO_TO_SHOPPING_CONTROLLER = "goToShoppingController";
        public static final String CHECKOUT_CONTROLLER = "checkoutController";
        public static final String VIEW_CART_PAGE = "viewCartPage";
    }
    
    public class CreateAccountFeature {

        public static final String LOGIN_PAGE = "";
        public static final String ERROR_PAGE = "createAccountErrorPage";
    }
    
    public class LoginFeature {

        public static final String INVALID_PAGE = "invalidPage";
        public static final String SEARCH_PAGE = "homePage";
    }
    
    public class LogoutFeature {

        public static final String LOGIN_PAGE = "";
    }
    
    public class SearchLatstnameFeature {

        public static final String SEARCH_PAGE = "homePage";
    }
    
    public class DeleteAccountFeature {

        public static final String ERROR_PAGE = "errorsPage";
    }
    
    public class UpdateAccountFeature {

        public static final String ERROR_PAGE = "errorsPage";
    }
    
    public class StartUpFeature {

        public static final String LOGIN_PAGE = "";
        public static final String SEARCH_PAGE = "homePage";
    }
    
    public class AddToCartFeature {

        public static final String PRODUCT_PAGE = "productPage";
    }
    
    public class GoToShoppingFeature {

        public static final String PRODUCT_PAGE = "productPage";
    }
    
    public class CheckoutFeature {

        public static final String ERROR_PAGE = "errorsPage";
        public static final String GO_TO_SHOPPING_CONTROLLER = "goToShoppingController";
        public static final String VIEW_CART_PAGE = "viewCartPage";
    }
}
