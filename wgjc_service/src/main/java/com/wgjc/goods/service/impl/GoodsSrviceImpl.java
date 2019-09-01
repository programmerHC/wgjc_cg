package com.wgjc.goods.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgjc.goods.dao.GoodsMapper;
import com.wgjc.goods.entity.Goods;
import com.wgjc.goods.entity.GoodsCondition;
import com.wgjc.goods.service.GoodsService;
import com.wgjc.page.entity.PageRequest;
import com.wgjc.redis.util.RedisUtil;

/**
 * @Description:商品业务逻辑处理实现类
 * @author hc
 * @2019年9月1日
 */
public class GoodsSrviceImpl implements GoodsService {
	private static Log log = LogFactory.getLog(GoodsSrviceImpl.class);
	
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	public boolean save(Goods record) {
		boolean flag = false;
		try {
			goodsMapper.addGoods(record);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public boolean update(Goods record) {
		boolean flag = false;
		try {
			int result = goodsMapper.updateGoods(record);
			if(result > 0) {
				redisUtil.del(record.getUuid());
				redisUtil.set(record.getUuid(), record);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public boolean delete(String id) {
		boolean flag = false;
		try {
			int result = goodsMapper.deleteGoodsById(id);
			if(result > 0) {
				redisUtil.del(id);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public Goods getById(String id) {
		Goods goods = null;
		try {
			goods = goodsMapper.getGoodsById(id);
			if(goods != null) {
				redisUtil.set(id, goods); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return goods;
	}

	@Override
	public PageInfo<Goods> getPageInfo(PageRequest pageRequest, GoodsCondition goodsCondition) {
		PageInfo<Goods> goodsPageInfo = null;
	    try {
			PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
			List<Goods> list = goodsMapper.getAllGoods(goodsCondition);
			goodsPageInfo = new PageInfo<Goods>(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return goodsPageInfo;
	}

}
