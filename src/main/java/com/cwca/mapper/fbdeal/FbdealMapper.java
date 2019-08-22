package com.cwca.mapper.fbdeal;

import com.cwca.bean.fbdeal.Fbdeal;
import com.cwca.bean.fbdeal.FbdealParam;

import java.util.List;

public interface FbdealMapper {

    //依据条件获取文章
    List<Fbdeal> getAllFbdeal(FbdealParam fbdeal);
}
