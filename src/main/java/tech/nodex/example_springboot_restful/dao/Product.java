package tech.nodex.example_springboot_restful.dao;

import tech.nodex.example_springboot_restful.dao.base.BaseProduct;

import java.util.List;

/**
 * 这个类用于操作数据库中的product表。
 * 最初这个类是由ActiveRecordGenerator生成的。再次运行ActiveRecordGenerator不会覆盖这个类。
 * 因此，在这里放置你的代码是安全的。
 *
 */
@SuppressWarnings("serial")
public class Product extends BaseProduct<Product> {

    /**
     * 用于操作product表的对象。
     * 这不是自动生成的
     */
    public static final Product db = new Product();

    /**
     * 这不是自动生成的。
     * 你可以在这里创建更多的数据操作方法，这些代码不会被代码生成器覆盖。
     * @param name
     * @return
     */
    public static List<Product> findProductsByName(String name){
        return db.find("select * from product where name = ?",name);
    }

    /**
     * 这不是自动生成的。
     * 你可以在这里创建更多的数据操作方法，这些代码不会被代码生成器覆盖。
     * @param storeId
     * @return
     */
    public static List<Product> findProductsByStore(long storeId){
        return db.find("select * from product where store_id = ?",storeId);
    }
}
