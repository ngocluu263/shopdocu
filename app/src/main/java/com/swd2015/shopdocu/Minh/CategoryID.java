package com.swd2015.shopdocu.Minh;

/**
 * Created by Minh on 12/3/2015.
 */
public class CategoryID {

    public static int GetCategoryID(CategoryEnum category)
    {
        switch (category){
            case NOI_NGOAI_THAT_GIA_DINH:
                return 0;
            case QUAN_CA_PHE:
                return 6;
            case DO_VAN_PHONG:
                return 10;
            case DO_QUAN_AN:
                return 14;
            case DO_KHACH_SAN:
                return 18;
            case DIENTU_DIENMAY:
                return 21;
            case KHAC:
                return 25;
        }
        return 0;
    }

}
