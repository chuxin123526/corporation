package com.corporation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.corporation.mapper.CorporationMapper;
import com.corporation.po.Corporation;

/**
 * 
 * @ClassName: TestCorporation
 * @Description: testCorporationMapper
 * @author: JJChen
 * @date: 2016年11月28日 上午9:07:40
 */
public class TestCorporation
{
	@Test
	public void testInsert() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		CorporationMapper corporationMapper =(CorporationMapper) applicationContext.getBean("corporationMapper") ; 
		Corporation corporation = new Corporation() ; 
		corporation.setIntroduction("<p>台球是一项在国际上广泛流行的高雅室内体育运动，是一种用球杆在台上击球、依靠计算得分确定比赛胜负的室内娱乐体育项目。</p><p>台球已发展成为多种多样：有中式八球、俄式落袋台球、英式落袋台球、开伦台球、美式落袋台球和斯诺克台球，其中斯诺克最为普遍，已成为一项比赛项目。</p><p>据说，一颗象牙只能制5个球，英国仅制作台球每年就需要上万头大象，制造好的象牙台球还要经过严格挑选，重量必须相同，因此价格非常昂贵！所以象牙台球仅适合宫廷贵族们玩乐！后来，被称为美国塑料工业之父的海亚特，研制出一种用硝化纤维素、樟脑、酒精等化工原料混合制成的台球，它大大降低了台球的成本；随后在1920年又出现了一种石碳酸脂铸成的台球，台球的制造费用就更低了。</p><p>现在的台球都是用合成树脂做的，主要有聚酯、不饱和聚酯、酚醛树脂、脲醛树脂等品种。不同材料的价格和档次相差很远。国际比赛的标准用球是用酚醛树脂做的。</p>");
		corporationMapper.insert(corporation) ; 
	}
	
	@Test
	public void testClick() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		CorporationMapper corporationMapper =(CorporationMapper) applicationContext.getBean("corporationMapper") ; 
		corporationMapper.click((long)1) ;
	}
	
	@Test
	public void wfwf() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		
	}
	
}
