package com.course.mapper;

import com.course.common.CountResult;
import com.course.entity.CoursePurchase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程购买记录表  Mapper 接口
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Repository
public interface CoursePurchaseMapper extends BaseMapper<CoursePurchase> {

    List<CountResult> countPurchaseDataByDay(CoursePurchase coursePurchase);

    List<CountResult> countPurchaseDataByMonth(CoursePurchase coursePurchase);

    List<CoursePurchase> queryPurByPage(CoursePurchase coursePurchase);
}
