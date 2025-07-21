package org.kosa.myproject.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.myproject.model.vo.CustomerVO;
@Mapper // MyBatis Mapper Proxy 생성을 위한 어노테이션 
public interface CustomerMapper {

	CustomerVO findCustomerById(String id);

	int registerCustomer(CustomerVO customerVO);

	int checkDuplicateId(String id);

	List<CustomerVO> findCustomerListByAddress(String address);

}
