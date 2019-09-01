package com.wgjc.goods.dao;

import java.util.List;

import com.wgjc.goods.entity.Goods;
import com.wgjc.goods.entity.GoodsCondition;
/**
 * @Description:商品业务接口类
 * @author hc
 * @2019年9月1日
 */
public interface GoodsMapper {
	public Goods getGoodsById(String uuid);
	public List<Goods> getAllGoods(GoodsCondition goodsCondition);
	public int addGoods(Goods goods);
	public int updateGoods(Goods goods);
	public int deleteGoodsById(String uuid);
}
