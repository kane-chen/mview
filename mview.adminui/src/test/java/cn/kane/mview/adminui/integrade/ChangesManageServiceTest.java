package cn.kane.mview.adminui.integrade;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.kane.mview.adminui.integrate.service.ChangesManageService;
import cn.kane.mview.service.definition.entity.DefinitionKey;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/integrate-manager-test.xml"
		,"classpath:/definition-manager-test.xml"
})
public class ChangesManageServiceTest {

	@Autowired
	private ChangesManageService changesManageService ;
	private static String requirementId = "1111" ;
	private static DefinitionKey key ;
	
	@BeforeClass
	public static void setup(){
		key = new DefinitionKey() ;
		key.setType("type") ;
		key.setName("name") ;
		key.setVersion(requirementId) ;
	}
	
	@Before
	public void add(){
		changesManageService.add(requirementId, key) ;
	}
	
	@Test
	public void query(){
		List<DefinitionKey> changes = changesManageService.list(requirementId) ;
		Assert.assertTrue(changes.contains(key));
	}
	
}
