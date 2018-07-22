package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.bean.Province;

public interface DictMapper {
	/**
	 * 查询省份信息
	 * @return
	 */
	List<Province> selectProvince();

}







