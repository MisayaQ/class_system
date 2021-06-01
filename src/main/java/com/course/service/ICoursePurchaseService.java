package com.course.service;

import com.course.base.Ret;
import com.course.common.CountResult;
import com.course.entity.CoursePurchase;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 课程购买记录表  服务类
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
public interface ICoursePurchaseService extends IService<CoursePurchase> {

    List<CountResult> countPurchaseDataByDay(CoursePurchase coursePurchase);

    List<CountResult> countPurchaseDataByMonth(CoursePurchase coursePurchase);

    List<CoursePurchase> queryPurByPage(CoursePurchase coursePurchase);

    Ret savePurches(CoursePurchase coursePurchase);

    //统计总数
    Ret getPurchaseCountAll(String countType) throws ParseException;

    //统计图
    Ret getPurchaseCount(String countType);

    Ret selectPurchaseByPage(Integer page, Integer pageSize, CoursePurchase coursePurchase);
}
