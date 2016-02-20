package cn.kane.mview.adminui.integrade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.kane.mview.adminui.integrate.service.RequirementMemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:/integrate-manager-test.xml"
		,"classpath:/definition-manager-test.xml"
})
public class RequirementMemberServiceTest {

	@Autowired
	private RequirementMemberService requirementMemberService ;
	private String operator = "kane" ;
	private String requirementId = "111" ;
	
	@Before
	public void join(){
		requirementMemberService.join(operator, requirementId) ;
	}
	
	@Test
	public void query(){
		String queryed = requirementMemberService.query(operator) ;
		Assert.assertEquals(requirementId, queryed) ;
	}
}
