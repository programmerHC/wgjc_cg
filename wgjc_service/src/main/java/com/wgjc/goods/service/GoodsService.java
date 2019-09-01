package com.wgjc.goods.service;

import com.github.pagehelper.PageInfo;
import com.wgjc.base.service.BaseService;
import com.wgjc.goods.entity.Goods;
import com.wgjc.goods.entity.GoodsCondition;
import com.wgjc.page.entity.PageRequest;

/**
 * @Description:Goods业务逻辑Service接口
 * @author hc
 * @2019年9月1日
 */
public interface GoodsService extends BaseService<Goods> {
	public PageInfo<Goods> getPageInfo(PageRequest pageRequest,GoodsCondition goodsCondition);
}
