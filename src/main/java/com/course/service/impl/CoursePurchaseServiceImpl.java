package com.course.service.impl;

import com.course.common.CountResult;
import com.course.entity.CoursePurchase;
import com.course.mapper.CoursePurchaseMapper;
import com.course.service.ICoursePurchaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程购买记录表  服务实现类
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Service
public class CoursePurchaseServiceImpl extends ServiceImpl<CoursePurchaseMapper, CoursePurchase> implements ICoursePurchaseService {

    @Autowired
    private CoursePurchaseMapper coursePurchaseMapper;

    @Override
    public List<CountResult>
    countPurchaseDataByDay(CoursePurchase coursePurchase) {
        return coursePurchaseMapper.countPurchaseDataByDay(coursePurchase);
    }

    @Override
    public List<CountResult> countPurchaseDataByMonth(CoursePurchase coursePurchase) {
        return coursePurchaseMapper.countPurchaseDataByMonth(coursePurchase);
    }

    @Override
    public List<CoursePurchase> queryPurByPage(CoursePurchase coursePurchase) {
        return coursePurchaseMapper.queryPurByPage(coursePurchase);
    }
}
