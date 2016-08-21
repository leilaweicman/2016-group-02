package grupo2.tpAnual.helpers;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class MapExtensions {

	public static <T> T toObject(Map<String, Object> map, Class<T> clazz) {
		try {
			T instance = clazz.newInstance();
			BeanUtils.populate(instance, map);
			return instance;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
