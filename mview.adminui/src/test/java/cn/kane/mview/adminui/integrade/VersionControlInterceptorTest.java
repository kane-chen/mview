package cn.kane.mview.adminui.integrade;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.kane.mview.adminui.integrate.entity.Requirement;
import cn.kane.mview.adminui.integrate.service.ChangesManageService;
import cn.kane.mview.adminui.integrate.service.RequirementManageService;
import cn.kane.mview.adminui.integrate.service.RequirementMemberService;
import cn.kane.mview.service.definition.entity.AbstractDefinition;
import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.definition.entity.TemplateDefinition;
import cn.kane.mview.service.definition.service.ResourceDefinitionManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/integrate-manager-test.xml"
		,"classpath:/definition-manager-test.xml"
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VersionControlInterceptorTest {
	
	@Autowired
	private RequirementManageService requirementManageService ;
	@Autowired
	private RequirementMemberService requirementMemberService ;
	@Autowired
	private ResourceDefinitionManager<AbstractDefinition> resourceDefinitionManagerFacade ;
	@Autowired
	private ChangesManageService changesManageService ;
	
	private static String requirementId  ;
	private static String operator = "kane" ;  
	private static DefinitionKey key ;
	
	@BeforeClass
	public static void setup(){
		key = new DefinitionKey() ;
		key.setType("string") ;
		key.setName("name") ;
		key.setVersion(requirementId) ;
	}
	
	@Test
	public void test001_addRequiremnt(){
		Requirement req = new Requirement() ;
		req.setName("requirementName");
		req.setDescription("description");
		req.setOperator(operator) ;
		requirementId = requirementManageService.add(req) ;
	}
	
	@Test
	public void test002_join(){
		requirementMemberService.join(operator, requirementId) ;
	}
	
	@Test
	public void test003_editDefinition(){
		TemplateDefinition def = new TemplateDefinition() ;
		def.setKey(key) ;
		def.setOperator(operator) ;
		def.setDescription("def-description") ;
		def.setContent("hello") ;
		resourceDefinitionManagerFacade.add(def) ;
	}
	
	@Test
	public void test004_query(){
		List<DefinitionKey> changes = changesManageService.list(requirementId) ;
		Assert.assertTrue(changes.contains(key));
	}
	
}
