package com.wgjc.goods.controller;

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
	GoodsService goodsService;
	@Autowired
	AjaxResult ajaxResult;
	
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
		boolean flag = goodsService.update(goods);
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

