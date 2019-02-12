package com.multi.thread.guide.core.chapter12;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-12 17:55
 */
public class LockElisionExample {

    public static String toJSON(ProductInfo productInfo) {
        StringBuffer sbf = new StringBuffer();
        sbf.append("{\"productID\":\"").append(productInfo.productID);
        sbf.append("\",categoryID\":\"").append(productInfo.categoryID);
        sbf.append("\",rank\":\"").append(productInfo.rank);
        sbf.append("\",inventory\":\"").append(productInfo.inventory);
        sbf.append("}");
        return sbf.toString();
    }

    class ProductInfo {
        public int productID;

        public int categoryID;

        public int rank;

        public int inventory;
    }
}
