package org.jayne.crawler.api;

/**
 * 用于在非接口实现方法内使用事务而设计的Api
 * <p />
 * Spring事务管理（JDK代理方式）要求添加{@link org.springframework.transaction.annotation.Transactional}注解的方法必须是接口的实现方法，
 * 而有些方法是类的内部方法，一旦放到接口中会存在误用的风险。
 * 这时，可以使用该Api封装一下需要使用事务的方法，然后再调用，以便实现事务。
 */
public interface TransactionApi {

    <T> T runInTransaction(Executable<T> executable);

}
