package com.wgjc.goods.controller;

import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wgjc.goods.entity.Goods;
import com.wgjc.goods.entity.GoodsCondition;
import com.wgjc.goods.service.GoodsService;
import com.wgjc.page.entity.AjaxResult;
import com.wgjc.page.entity.PageRequest;

/**
 * @Description:商品api
 * @author hc
 * @2019年9月1日
 */
@RestController
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private AjaxResult ajaxResult;
	
	@GetMapping("/goods")
	public Goods getGoods(@RequestParam(value = "uuid",required = true)String uuid) {
		Goods goods = goodsService.getById(uuid);
		return goods;
	}
	
	@PostMapping("/goods")
	public AjaxResult addGoods(Goods goods) {
		boolean flag = goodsService.save(goods);
		if(flag) {
			ajaxResult.setResult(0, "新增商品成功");
		}else {
			ajaxResult.setResult(1, "新增商品失败");
		}
		return ajaxResult;
	}
	
	@PutMapping("/goods")
	public AjaxResult updateGoods(Goods goods) {
		Goods goods_update = new Goods();
		if(goods != null && StringHelper.isNullOrEmptyString(goods.getUuid())) {
			goods_update = goodsService.getById(goods.getUuid());
			if(goods_update != null) {
				goods_update.setCount(goods.getCount());
				goods_update.setName(goods.getName());
				goods_update.setPrice(goods.getPrice());
				goods_update.setRemark(goods.getRemark());
				goods_update.setSize(goods.getSize());
				goods_update.setUnit(goods.getUnit());
			}
		}
		boolean flag = goodsService.update(goods_update);
		if(flag) {
			ajaxResult.setResult(0, "更新商品成功");
		}else {
			ajaxResult.setResult(1, "更新商品失败");
		}
		return ajaxResult;
	}
	
	@DeleteMapping("/goods")
	public AjaxResult deleteGoods(@RequestParam(value = "uuid",required = true)String uuid) {
		boolean flag =  goodsService.delete(uuid);
		if(flag) {
			ajaxResult.setResult(0, "删除商品成功");
		}else {
			ajaxResult.setResult(1, "删除商品失败");
		}
		return ajaxResult;
	}
	
	@GetMapping("/goodsAll")
	public PageInfo<Goods> getPageGoods(PageRequest pageRequest,GoodsCondition goodsCondition){
		PageInfo<Goods> pageInfo = goodsService.getPageInfo(pageRequest, goodsCondition);
		return pageInfo;
	}
}

