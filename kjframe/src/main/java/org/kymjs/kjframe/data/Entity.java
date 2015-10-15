package org.kymjs.kjframe.data;

import org.json.JSONObject;

/**
 * 所有数据实体的基类.<br>
 * <p>
 * 如果子类要根据JsonObject实例化一个对象, 则需要实现 {@link Entity.Builder}接口,
 * 并提供getBuilder()方法返回实体构造器.
 * </p>
 * 
 * @version 1.0.0
 */
public abstract class Entity {

	/**
	 * 数据实体构造器, 提供实例化方法.
	 * 
	 * @param <T> 具体的子类数据实体对象
	 * @version 1.0.0
	 */
	public interface Builder<T> {
		/**
		 * 根据JSON数据创建实体对象.
		 * @param jsonObject 创建实体的JSON数据
		 * @return
		 */
		public T create(JSONObject jsonObject);
	}
	
}
