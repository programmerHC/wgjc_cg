package com.wgjc.account.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgjc.account.dao.AccountMapper;
import com.wgjc.account.entity.Account;
import com.wgjc.account.entity.AccountCondition;
import com.wgjc.account.service.AccountService;
import com.wgjc.page.entity.AjaxResult;
import com.wgjc.page.entity.PageRequest;
import com.wgjc.param.entity.Param;
import com.wgjc.param.service.ParamService;
import com.wgjc.redis.util.RedisUtil;
import com.wgjc.user.entity.User;
import com.wgjc.user.service.UserService;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/** 
 * @Description:账单操作业务实现类
 * @author hc
 * @date 2019年9月4日下午5:29:46
 */
@Service
public class AccountServiceImpl implements AccountService{
	private Log log = LogFactory.getLog(AccountServiceImpl.class);
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private UserService userService;
	@Autowired
	private ParamService paramService;
	@Autowired
	private AjaxResult ajaxResult;
	
	@Override
	public boolean save(Account record) {
		boolean flag = false;
		try {
			int result = accountMapper.addAccount(record);
			if(result > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public boolean update(Account record) {
		boolean flag = false;
		try {
			int result = accountMapper.updateAccount(record);
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
			int result = accountMapper.deleteAccountById(id);
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
	public Account getById(String id) {
		Account account = null;
		try {
			account = redisUtil.get(id, Account.class);
			if(account == null) {
				account = accountMapper.getAccountById(id);
				redisUtil.set(id, account);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return account;
	}

	@Override
	public PageInfo<Account> getPageInfo(PageRequest pageRequest, AccountCondition accountCondition) {
		PageInfo<Account> pageInfo = null;
		PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
		try {
			List<Account> list = accountMapper.getAllAcount(accountCondition);
			pageInfo = new PageInfo<Account>(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return pageInfo;
	}
	
	/**
	 * 事务保存多条账单记录,若有异常，则对数据进行回滚
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveAccounts(List<Account> accounts) {
		boolean flag = true;
		if(accounts != null && accounts.size() > 0) {
			try {
				for(Account account: accounts) {
					save(account);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
				setRollbackOnly();
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * 事务删除多条账单记录,若有异常，则对数据进行回滚
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean deleteAccounts(String[] uuids) {
		boolean flag = true;
		if(uuids != null && uuids.length > 0) {
			try {
				for(String uuid : uuids) {
					boolean flag_in  = delete(uuid);
					if(flag_in) {
						redisUtil.del(uuid);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
				setRollbackOnly();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public AjaxResult makeAccountsToExcel(List<Account> accounts) {
		boolean result = false;
		
		String path = "";
		String excelName = "";
		String userName = "文革建材客户";//默认客户名
		String buyDate  = "";
		WritableWorkbook workbook = null;
		String[] titles = {"品名","规格型号","单位","数量","单价","金额"};
		String description = "经营范围：五金、油漆、木材、水暖，琉璃瓦、石棉瓦、瓷砖、油毡、水泥、白水泥、石膏板等。"+"\n" + 
							 "手机：18055349108   电话：8371389"+"\n" + 
							 "地址：芜湖市弋江区火龙岗镇塔影行政村晋村(村大队旁)";
		
		if(accounts != null && accounts.size() > 0) {
			try {
				Account firstAccount = accounts.get(0);
				buyDate = new SimpleDateFormat("yyyy-MM-dd").format(firstAccount.getBuyDate());//获取订单日期
				
				User user = userService.getById(firstAccount.getUserId());
				if(user != null) {
					userName =  user.getRealName();
					//获取excel名称
					excelName = userName + new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date()) + "账目清单.xls";
				}else {
					//默认excel名称
					excelName = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date()) + "账目清单.xls";
				}
				
				//获取excel存储路径
				Param param = paramService.getParamByCode("ACCOUNT_EXCEL_PATH");
				if(param != null) {
					path = param.getValue() + excelName;
				}
				
				//1、创建excel文件
				File file = new File(path);
				file.createNewFile();
				
				//2、创建工作簿
				workbook = Workbook.createWorkbook(file);
				
				//3、创建sheet,设置第二三四..个sheet，依次类推即可
				WritableSheet sheet = workbook.createSheet(excelName, 0);
				
				sheet.setColumnView(0, 20); // 设置列的宽度
				sheet.setColumnView(1, 20); // 设置列的宽度
				sheet.setColumnView(2, 10); // 设置列的宽度
				sheet.setColumnView(3, 15); // 设置列的宽度
				sheet.setColumnView(4, 15); // 设置列的宽度
				sheet.setColumnView(5, 15); // 设置列的宽度
				sheet.setColumnView(6, 15); // 设置列的宽度
					
				sheet.mergeCells(0, 0, 5, 3);
				sheet.mergeCells(0, 4, 2, 5);
				sheet.mergeCells(3, 4, 5, 5);
				sheet.mergeCells(0, 38,2, 38);
				sheet.mergeCells(0, 38,2, 38);
				sheet.mergeCells(3, 38,5, 38);
				sheet.mergeCells(0, 39,5, 43);
				
				//头部标题
				WritableCellFormat wcf_head = getHeadWritableCellFormat();
				Label head_label = new Label(0,0,"文革建材经营部",wcf_head);//起始上点左边，列，行
				sheet.addCell(head_label);
				
				//用户信息
			    WritableCellFormat wcf_info = getInfoWritableCellFormat(); 
			    String info_consumer = "购货单位:" + userName; 
			    Label info_consumer_label = new Label(0,4,info_consumer,wcf_info); 
			    sheet.addCell(info_consumer_label);
			    String info_buyDate = "购货日期:" + buyDate; 
			    Label info_buyDate_label = new Label(3,4,info_buyDate,wcf_info); 
			    sheet.addCell(info_buyDate_label);
			    
			    //账单标题
			    WritableCellFormat wcf_title = getTitleWritableCellFormat();
				for(int i=0;i<titles.length;i++) {
					Label title_lable = new Label(i,6,titles[i],wcf_title);
					sheet.addCell(title_lable);
				}
				
				//账单内容添加
				WritableCellFormat wcf_content = getContentWritableCellFormat();
				BigDecimal sum_all = new BigDecimal("0");
				for(int i=0;i<accounts.size();i++) {
					Label name_lable = new Label(0,7+i,accounts.get(i).getName(),wcf_content);
					sheet.addCell(name_lable);
					Label size_lable = new Label(1,7+i,accounts.get(i).getSize(),wcf_content);
					sheet.addCell(size_lable);
					Label unit_lable = new Label(2,7+i,accounts.get(i).getUnit(),wcf_content);
					sheet.addCell(unit_lable);
					Label count_lable = new Label(3,7+i,accounts.get(i).getCount(),wcf_content);
					sheet.addCell(count_lable);
					Label price_lable = new Label(4,7+i,accounts.get(i).getPrice(),wcf_content);
					sheet.addCell(price_lable);
					
					BigDecimal count = new BigDecimal(accounts.get(i).getCount());
					BigDecimal price = new BigDecimal(accounts.get(i).getPrice());
					BigDecimal sum = count.multiply(price);
					sum_all = sum_all.add(sum);
					Label price_sum = new Label(5,7+i,sum.toString(),wcf_content);
					sheet.addCell(price_sum);
				}
				
				Label sum_desc_label = new Label(0,38,"合计人命币：",wcf_title);
				sheet.addCell(sum_desc_label);
				Label sum_all_label = new Label(3,38,"¥ "+sum_all+" 元",wcf_title);
				sheet.addCell(sum_all_label);
				
				WritableCellFormat wcf_description = getDescriptionWritableCellFormat();
				Label desc_label = new Label(0,39,description,wcf_description);
				sheet.addCell(desc_label);
				
				
				workbook.write();
				
				ajaxResult.setResult(0, "账单打印成功", path);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(),e);
				ajaxResult.setResult(1, "账单打印失败", e);
			}finally {
				try {
					workbook.close();
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage(),e);
				} 
			}
		}
		
		return ajaxResult;
	}
	
	/**
	 * @Title: setRollbackOnly  
	 * @Description:出现exception时，所有数据库操作回滚
	 */
	public void setRollbackOnly() {
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
	
	public WritableCellFormat getHeadWritableCellFormat() throws Exception {
		//5、 定义格式 字体 下划线 斜体 粗体 颜色(头部)
		WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 28,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
		
		WritableCellFormat wcf_head = new WritableCellFormat(wf_head); // 单元格定义
		wcf_head.setBackground(jxl.format.Colour.WHITE); // 设置单元格的背景颜色
		wcf_head.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式，左右居中
		wcf_head.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 设置对齐方式，上下居中
		wcf_head.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	
		return wcf_head;
	}
	
	public WritableCellFormat getInfoWritableCellFormat() throws Exception {
		//5、 定义格式 字体 下划线 斜体 粗体 颜色(头部)
		WritableFont wf_info = new WritableFont(WritableFont.ARIAL, 14,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
		
		WritableCellFormat wcf_info = new WritableCellFormat(wf_info); // 单元格定义
		wcf_info.setBackground(jxl.format.Colour.WHITE); // 设置单元格的背景颜色
		wcf_info.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式，左右居中
		wcf_info.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 设置对齐方式，上下居中
		wcf_info.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	
		return wcf_info;
	}
	
	public WritableCellFormat getTitleWritableCellFormat() throws Exception {
		//5、 定义格式 字体 下划线 斜体 粗体 颜色(头部)
		WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 12,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
		
		WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义
		wcf_title.setBackground(jxl.format.Colour.WHITE); // 设置单元格的背景颜色
		wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式，左右居中
		wcf_title.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 设置对齐方式，上下居中
		wcf_title.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	
		return wcf_title;
	}
	
	public WritableCellFormat getDescriptionWritableCellFormat() throws Exception {
		//5、 定义格式 字体 下划线 斜体 粗体 颜色(头部)
		WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 12,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
		
		WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义
		wcf_title.setBackground(jxl.format.Colour.WHITE); // 设置单元格的背景颜色
		wcf_title.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 设置对齐方式，上下居中
		wcf_title.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	
		return wcf_title;
	}
	
	public WritableCellFormat getContentWritableCellFormat() throws Exception {
		//5、 定义格式 字体 下划线 斜体 粗体 颜色(头部)
		WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 12,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
		
		WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义
		wcf_title.setBackground(jxl.format.Colour.WHITE); // 设置单元格的背景颜色
		wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式，左右居中
		wcf_title.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 设置对齐方式，上下居中
		wcf_title.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	
		return wcf_title;
	}
}
