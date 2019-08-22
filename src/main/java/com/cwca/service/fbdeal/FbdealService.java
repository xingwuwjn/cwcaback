package com.cwca.service.fbdeal;


import com.cwca.bean.fbdeal.Fbdeal;
import com.cwca.bean.fbdeal.FbdealParam;
import com.cwca.mapper.fbdeal.FbdealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FbdealService {
    @Autowired
    FbdealMapper fbdealMapper;
    //依据条件获取
    public List<Fbdeal> getAllFbdeal(FbdealParam fbdeal){
        return fbdealMapper.getAllFbdeal(fbdeal);
    }

}
