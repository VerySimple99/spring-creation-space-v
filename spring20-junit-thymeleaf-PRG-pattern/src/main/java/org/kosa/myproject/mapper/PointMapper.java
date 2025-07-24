package org.kosa.myproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.myproject.domain.Point;

@Mapper
public interface PointMapper {
    // findPointById는 이제 point_tx_id를 기준으로 찾도록 변경 (Integer 타입)
    Point findPointById(Integer pointTxId);

    // register 시 Point 객체를 받아서 삽입
    int register(Point point);
}
