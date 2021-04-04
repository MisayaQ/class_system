package com.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.base.Ret;
import com.course.common.CountResult;
import com.course.entity.CoursePurchase;
import com.course.mapper.CoursePurchaseMapper;
import com.course.service.ICoursePurchaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.course.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public Ret savePurches(CoursePurchase coursePurchase) {
        QueryWrapper queryWrapper = new QueryWrapper();
        coursePurchase.setId(UUIDUtil.getUUID());
        coursePurchase.setValidFlag(0);
        coursePurchase.setCreatedTime(LocalDateTime.now());
        coursePurchase.setUpdatedTime(LocalDateTime.now());
        coursePurchase.setState("0");
        queryWrapper.eq("user_id", coursePurchase.getUserId());
        queryWrapper.eq("details_id", coursePurchase.getDetailsId());
        List<CoursePurchase> list = coursePurchaseMapper.selectList(queryWrapper);
        System.out.println(list);
        if (list != null && list.size() != 0) {
            return Ret.error().setMsg("您已购买过此课程，请勿重复购买");
        } else {
            int result = coursePurchaseMapper.insert(coursePurchase);
            if (result > 0) {
                return Ret.ok().setData(coursePurchase);
            } else {
                return Ret.error().setMsg("新增失败");
            }
        }
    }
}
